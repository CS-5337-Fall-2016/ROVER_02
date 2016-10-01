package swarmBots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import common.Coord;
import common.MapTile;
import common.ScanMap;
import enums.Terrain;
import java.util.Random;

/**
 * The seed that this program is built on is a chat program example found here:
 * http://cs.lmu.edu/~ray/notes/javanetexamples/ Many thanks to the authors for
 * publishing their code examples
 */

public class ROVER_02 {

	BufferedReader in;
	PrintWriter out;
	String rovername;
	ScanMap scanMap;
	int sleepTime;
	String SERVER_ADDRESS = "localhost";
	static final int PORT_ADDRESS = 9537;

	public ROVER_02() {
		// constructor
		System.out.println("ROVER_02 rover object constructed");
		rovername = "ROVER_02";
		SERVER_ADDRESS = "localhost";
		// this should be a safe but slow timer value
		sleepTime = 300; // in milliseconds - smaller is faster, but the server will cut connection if it is too small
	}
	
	public ROVER_02(String serverAddress) {
		// constructor
		System.out.println("ROVER_02 rover object constructed");
		rovername = "ROVER_02";
		SERVER_ADDRESS = serverAddress;
		sleepTime = 200; // in milliseconds - smaller is faster, but the server will cut connection if it is too small
	}

	/**
	 * Connects to the server then enters the processing loop.
	 */
	private void run() throws IOException, InterruptedException {

		// Make connection to SwarmServer and initialize streams
		Socket socket = null;
		try {
			socket = new Socket(SERVER_ADDRESS, PORT_ADDRESS);

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			
	
			// Process all messages from server, wait until server requests Rover ID
			// name - Return Rover Name to complete connection
			while (true) {
				String line = in.readLine();
				if (line.startsWith("SUBMITNAME")) {
					out.println(rovername); // This sets the name of this instance
											// of a swarmBot for identifying the
											// thread to the server
					break;
				}
			}
	
			
			// ********* Rover logic setup *********
			
			String line = "";
			Coord rovergroupStartPosition = null;
			Coord targetLocation = null;
			
			/**
			 *  Get initial values that won't change
			 */
			// **** get equipment listing ****			
			ArrayList<String> equipment = new ArrayList<String>();
			equipment = getEquipment();
			System.out.println(rovername + " equipment list results " + equipment + "\n");
			
			
			// **** Request START_LOC Location from SwarmServer ****
			out.println("START_LOC");
			line = in.readLine();
            if (line == null) {
            	System.out.println(rovername + " check connection to server");
            	line = "";
            }
			if (line.startsWith("START_LOC")) {
				rovergroupStartPosition = extractLocationFromString(line);
			}
			System.out.println(rovername + " START_LOC " + rovergroupStartPosition);
			
			
			// **** Request TARGET_LOC Location from SwarmServer ****
			out.println("TARGET_LOC");
			line = in.readLine();
            if (line == null) {
            	System.out.println(rovername + " check connection to server");
            	line = "";
            }
			if (line.startsWith("TARGET_LOC")) {
				targetLocation = extractLocationFromString(line);
			}
			System.out.println(rovername + " TARGET_LOC " + targetLocation);
			
			

			
			boolean[] direction = new boolean[4]; // An array of booleans (North, South, East, West)
			direction[0] = false;
			direction[1] = false;
			direction[2] = false;
			direction[3] = false;
			
			boolean stuck = false; // just means it did not change locations between requests,
				// could be velocity limit or obstruction etc.
			boolean blocked = false;
			
			boolean[] blockedArray = new boolean[4];
			blockedArray[0] = false;
			blockedArray[1] = false;
			blockedArray[2] = false;
			blockedArray[3] = false;
	
			String[] cardinals = new String[4];
			cardinals[0] = "N";
			cardinals[1] = "S";
			cardinals[2] = "E";
			cardinals[3] = "W";
	
			String currentDir = cardinals[0];
			Coord currentLoc = null;
			Coord previousLoc = null;
			Random rn = new Random();
	

			/**
			 *  ####  Rover controller process loop  ####
			 */
			while (true) {
	
				
				// **** Request Rover Location from SwarmServer ****
				out.println("LOC");
				line = in.readLine();
	            if (line == null) {
	            	System.out.println(rovername + " check connection to server");
	            	line = "";
	            }
				if (line.startsWith("LOC")) {
					// loc = line.substring(4);
					currentLoc = extractLocationFromString(line);
					
				}
				System.out.println(rovername + " currentLoc at start: " + currentLoc);
				
				// after getting location set previous equal current to be able to check for stuckness and blocked later
				previousLoc = currentLoc;		
	
				// ***** do a SCAN *****

				// gets the scanMap from the server based on the Rover current location
				doScan(); 
				// prints the scanMap to the Console output for debug purposes
				scanMap.debugPrintMap();
				
				// ***** get TIMER remaining *****
				out.println("TIMER");
				line = in.readLine();
	            if (line == null) {
	            	System.out.println(rovername + " check connection to server");
	            	line = "";
	            }
				if (line.startsWith("TIMER")) {
					String timeRemaining = line.substring(6);
					System.out.println(rovername + " timeRemaining: " + timeRemaining);
				}
				
				// ***** MOVING *****
				// try moving north
				// tile S = y + 1; N = y - 1; E = x + 1; W = x - 1
				if (checkBlocked(currentDir)) {
					int n = rn.nextInt(3);
					currentDir = cardinals[n];
				}
				else {
					out.print("MOVE " + currentDir);
				}
				// check scanMap to see if path is blocked to the north
				// (scanMap may be old data by now)
				//System.out.println("ROVER_02 scanMapTiles[2][1].getHasRover() " + scanMapTiles[2][1].getHasRover());
				//System.out.println("ROVER_02 scanMapTiles[2][1].getTerrain() " + scanMapTiles[2][1].getTerrain().toString());
											
	
				// another call for current location
				out.println("LOC");
				line = in.readLine();
				if(line == null){
					System.out.println("ROVER_02 check connection to server");
					line = "";
				}
				if (line.startsWith("LOC")) {
					currentLoc = extractLocationFromString(line);
					
				}
	
				// test for stuckness
				stuck = currentLoc.equals(previousLoc);
	
				//System.out.println("ROVER_02 stuck test " + stuck);
	
				// TODO - logic to calculate where to move next
	
				
				// this is the Rovers HeartBeat, it regulates how fast the Rover cycles through the control loop
				Thread.sleep(sleepTime);
				
				System.out.println("ROVER_02 ------------ bottom process control --------------"); 
			}
		
		// This catch block closes the open socket connection to the server
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        if (socket != null) {
	            try {
	            	socket.close();
	            } catch (IOException e) {
	            	System.out.println("ROVER_02 problem closing socket");
	            }
	        }
	    }
	} // END of Rover main control loop
	
	// ####################### Support Methods #############################
	
	private void clearReadLineBuffer() throws IOException{
		while(in.ready()){
			//System.out.println("ROVER_02 clearing readLine()");
			in.readLine();	
		}
	}
	

	// method to retrieve a list of the rover's EQUIPMENT from the server
	private ArrayList<String> getEquipment() throws IOException {
		//System.out.println("ROVER_02 method getEquipment()");
		Gson gson = new GsonBuilder()
    			.setPrettyPrinting()
    			.enableComplexMapKeySerialization()
    			.create();
		out.println("EQUIPMENT");
		
		String jsonEqListIn = in.readLine(); //grabs the string that was returned first
		if(jsonEqListIn == null){
			jsonEqListIn = "";
		}
		StringBuilder jsonEqList = new StringBuilder();
		//System.out.println("ROVER_02 incomming EQUIPMENT result - first readline: " + jsonEqListIn);
		
		if(jsonEqListIn.startsWith("EQUIPMENT")){
			while (!(jsonEqListIn = in.readLine()).equals("EQUIPMENT_END")) {
				if(jsonEqListIn == null){
					break;
				}
				//System.out.println("ROVER_02 incomming EQUIPMENT result: " + jsonEqListIn);
				jsonEqList.append(jsonEqListIn);
				jsonEqList.append("\n");
				//System.out.println("ROVER_02 doScan() bottom of while");
			}
		} else {
			// in case the server call gives unexpected results
			clearReadLineBuffer();
			return null; // server response did not start with "EQUIPMENT"
		}
		
		String jsonEqListString = jsonEqList.toString();		
		ArrayList<String> returnList;		
		returnList = gson.fromJson(jsonEqListString, new TypeToken<ArrayList<String>>(){}.getType());		
		//System.out.println("ROVER_02 returnList " + returnList);
		
		return returnList;
	}
	

	// sends a SCAN request to the server and puts the result in the scanMap array
	public void doScan() throws IOException {
		//System.out.println("ROVER_02 method doScan()");
		Gson gson = new GsonBuilder()
    			.setPrettyPrinting()
    			.enableComplexMapKeySerialization()
    			.create();
		out.println("SCAN");

		String jsonScanMapIn = in.readLine(); //grabs the string that was returned first
		if(jsonScanMapIn == null){
			System.out.println("ROVER_02 check connection to server");
			jsonScanMapIn = "";
		}
		StringBuilder jsonScanMap = new StringBuilder();
		System.out.println("ROVER_02 incomming SCAN result - first readline: " + jsonScanMapIn);
		
		if(jsonScanMapIn.startsWith("SCAN")){	
			while (!(jsonScanMapIn = in.readLine()).equals("SCAN_END")) {
				//System.out.println("ROVER_02 incomming SCAN result: " + jsonScanMapIn);
				jsonScanMap.append(jsonScanMapIn);
				jsonScanMap.append("\n");
				//System.out.println("ROVER_02 doScan() bottom of while");
			}
		} else {
			// in case the server call gives unexpected results
			clearReadLineBuffer();
			return; // server response did not start with "SCAN"
		}
		//System.out.println("ROVER_02 finished scan while");

		String jsonScanMapString = jsonScanMap.toString();
		// debug print json object to a file
		//new MyWriter( jsonScanMapString, 0);  //gives a strange result - prints the \n instead of newline character in the file

		//System.out.println("ROVER_02 convert from json back to ScanMap class");
		// convert from the json string back to a ScanMap object
		scanMap = gson.fromJson(jsonScanMapString, ScanMap.class);		
	}
	

	// this takes the server response string, parses out the x and x values and
	// returns a Coord object	
	public static Coord extractLocationFromString(String sStr) {
		int indexOf;
		indexOf = sStr.indexOf(" ");
		sStr = sStr.substring(indexOf +1);
		if (sStr.lastIndexOf(" ") != -1) {
			String xStr = sStr.substring(0, sStr.lastIndexOf(" "));
			//System.out.println("extracted xStr " + xStr);

			String yStr = sStr.substring(sStr.lastIndexOf(" ") + 1);
			//System.out.println("extracted yStr " + yStr);
			return new Coord(Integer.parseInt(xStr), Integer.parseInt(yStr));
		}
		return null;
	}
	
	private boolean checkBlocked(String currDir) {
		// pull the MapTile array out of the ScanMap object
		MapTile[][] scanMapTiles = scanMap.getScanMap();
		int centerIndex1 = ((scanMap.getEdgeSize() - 1)/2);
		int centerIndex2 = ((scanMap.getEdgeSize() - 1)/2);
		if (currDir.equals("N")) {
			centerIndex2 -= 1;
		}
		else if (currDir.equals("S")) {
			centerIndex2 += 1;
		}
		else if (currDir.equals("E")) {
			centerIndex1 += 1;
		}
		else if (currDir.equals("W")) {
			centerIndex1 -= 1;
		}
		// tile S = y + 1; N = y - 1; E = x + 1; W = x - 1
		if (scanMapTiles[centerIndex1][centerIndex2].getHasRover() 
				|| scanMapTiles[centerIndex1][centerIndex2].getTerrain() == Terrain.ROCK
				|| scanMapTiles[centerIndex1][centerIndex2].getTerrain() == Terrain.SAND
				|| scanMapTiles[centerIndex1][centerIndex2].getTerrain() == Terrain.NONE) {
			return true;
		}
		else {
			return false;
		}
	}
	

	/**
	 * Runs the client
	 */
	public static void main(String[] args) throws Exception {
		ROVER_02 client;
    	// if a command line argument is included it is used as the map filename
		// if present uses an IP address instead of localhost 
		
		if(!(args.length == 0)){
			client = new ROVER_02(args[0]);
		} else {
			client = new ROVER_02();
		}
		
		client.run();
	}
}
