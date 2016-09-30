package swarmBots;

import java.io.BufferedReader;
<<<<<<< HEAD
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
=======
<<<<<<< HEAD
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
>>>>>>> b349cc8295343bc1226cc3eb8bc1c3c7e0b8fd9f
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
<<<<<<< HEAD
=======
=======
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
>>>>>>> e8eb1f44fda3cde8b26ea1f5809df084d0c46fe1
>>>>>>> b349cc8295343bc1226cc3eb8bc1c3c7e0b8fd9f
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import common.Coord;
import common.MapTile;
import common.ScanMap;
<<<<<<< HEAD
import enums.Direction;
import enums.Science;
=======
<<<<<<< HEAD
import enums.Direction;
import enums.Science;
=======
>>>>>>> e8eb1f44fda3cde8b26ea1f5809df084d0c46fe1
>>>>>>> b349cc8295343bc1226cc3eb8bc1c3c7e0b8fd9f
import enums.Terrain;

/**
 * The seed that this program is built on is a chat program example found here:
 * http://cs.lmu.edu/~ray/notes/javanetexamples/ Many thanks to the authors for
 * publishing their code examples
 */

public class ROVER_02 {

<<<<<<< HEAD
    BufferedReader in;
    PrintWriter out;
=======
<<<<<<< HEAD
    BufferedReader in;
    PrintStream out;
>>>>>>> b349cc8295343bc1226cc3eb8bc1c3c7e0b8fd9f
    String rovername;
    ScanMap scanMap;
    int sleepTime;
    String SERVER_ADDRESS = "localhost";
    static final int PORT_ADDRESS = 9537;
    private int totalTiles = 2500;
    private int xTile = 50;
    private int yTile = 50;

<<<<<<< HEAD
    Direction currentDirection = Direction.WEST;
=======
    Direction currentDirection = Direction.EAST;
>>>>>>> b349cc8295343bc1226cc3eb8bc1c3c7e0b8fd9f
    Coord cc = null;
    HashSet<Coord> science_collection = new HashSet<Coord>();
    HashSet<Coord> displayed_science = new HashSet<Coord>();
    List<Link> blue = new ArrayList<Link>();
    List<Socket> sockets = new ArrayList<Socket>();

    // just means it did not change locations between requests, could be
    // velocity limit or obstruction etc.
    boolean stuck = false;
    boolean blocked = false;

    public ROVER_02() {
        System.out.println("ROVER_02 rover object constructed");
        rovername = "ROVER_02";
        SERVER_ADDRESS = "localhost";

        // this should be a safe but slow timer value
        // in milliseconds - smaller is faster, but the server
        // will cut connection if it is too small
        sleepTime = 300;

    }

    public ROVER_02(String serverAddress) {
        System.out.println("ROVER_02 rover object constructed");
        rovername = "ROVER_02";
<<<<<<< HEAD
        SERVER_ADDRESS = serverAddress;
=======
        SERVER_ADDRESS = "localhost";
>>>>>>> b349cc8295343bc1226cc3eb8bc1c3c7e0b8fd9f

        // in milliseconds - smaller is faster, but the server
        // will cut connection if it is too small
        sleepTime = 200;

    }

    class RoverComm implements Runnable {

        String ip;
        int port;
        Socket socket;

        public RoverComm(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }

        @Override
        public void run() {
            do {
                try {
                    socket = new Socket(ip, port);
                } catch (UnknownHostException e) {
                    System.out.println(e);
                } catch (IOException e) {
                    System.out.println(e);
                }
            } while (socket == null);
            sockets.add(socket);
            System.out
                    .println(socket.getPort() + " " + socket.getInetAddress());
        }

    }

    /**
     * add all rover's ip and port number into a list so they can be connected
     */
    public void initConnection() {
        // dummy value # 1
        blue.add(new Link("Dummy Group #1", "localhost", 8000));
        // dummy value # 2
        blue.add(new Link("Dummy Group #2", "localhost", 9000));
    }

    /**
     * Connects to the server then enters the processing loop.
     */
    private void run() throws IOException, InterruptedException {

        // Make connection and initialize streams
        Socket socket = new Socket(SERVER_ADDRESS, PORT_ADDRESS);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
<<<<<<< HEAD
        out = new PrintWriter(socket.getOutputStream(), true);
=======
        out = new PrintStream(socket.getOutputStream());
>>>>>>> b349cc8295343bc1226cc3eb8bc1c3c7e0b8fd9f

        // Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Process all messages from server, wait until server requests Rover ID
        // name

        // connect to all all the other rovers
        initConnection();
        for (Link link : blue) {
            new Thread(new RoverComm(link.ip, link.port)).start();
        }

        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {

                // This sets the name of this instance of a swarmBot for
                // identifying thread to the server
                out.println(rovername);
                break;
            }
        }

        // ******** Rover logic *********
        // int cnt=0;
        String line = "";

        String[] cardinals = new String[4];
        cardinals[0] = "N";
        cardinals[1] = "E";
        cardinals[2] = "S";
        cardinals[3] = "W";

        String currentDir = cardinals[0];
        Coord currentLoc = null;
        Coord previousLoc = null;

        // start Rover controller process
        while (true) {

            // currently the requirements allow sensor calls to be made with no
            // simulated resource cost

            // **** location call ****
            out.println("LOC");
            line = in.readLine();
            if (line == null) {
                System.out.println("ROVER_02 check connection to server");
                line = "";
            }
            if (line.startsWith("LOC")) {
                // loc = line.substring(4);
                currentLoc = extractLOC(line);
                cc = new Coord(Integer.valueOf(line.split(" ")[1]),
                        Integer.valueOf(line.split(" ")[2]));
            }
            System.out.println("ROVER_02 currentLoc at start: " + currentLoc);

            // after getting location set previous equal current to be able to
            // check for stuckness and blocked later
            previousLoc = currentLoc;

            // **** get equipment listing ****
            ArrayList<String> equipment = new ArrayList<String>();
            equipment = getEquipment();
            // System.out.println("ROVER_02 equipment list results drive " +
            // equipment.get(0));
            System.out.println(
                    "ROVER_02 equipment list results " + equipment + "\n");

            // ***** do a SCAN *****
            // System.out.println("ROVER_02 sending SCAN request");
            this.doScan();
            scanMap.debugPrintMap();

            // ***** MOVING *****

            // pull the MapTile array out of the ScanMap object
            MapTile[][] scanMapTiles = scanMap.getScanMap();
            int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
            // tile S = y + 1; N = y - 1; E = x + 1; W = x - 1

            // ***************************************************
<<<<<<< HEAD

            masterMove(currentDirection, scanMapTiles, centerIndex);
            shareScience();
            
            
            

            // ***********************************************************
            
          move(Direction.SOUTH);
          if(Terrain.ROCK != null && Terrain.SAND !=null)
        	  move(Direction.EAST);
//          else
//        	  move(Direction.WEST);
//              move(Direction.SOUTH);
//              for(int xTile = 0; xTile<=7 ; xTile++){
//            	  move(Direction.NORTH);
//              }
//        
              move(Direction.NORTH);
              

            // ***************************************************
=======
           /* move(Direction.EAST);
            move(Direction.SOUTH);*/
            masterMove(currentDirection, scanMapTiles, centerIndex);
            System.out.println("master move");
            shareScience();
            
            System.out.println("sharing science");
            
>>>>>>> b349cc8295343bc1226cc3eb8bc1c3c7e0b8fd9f

            // another call for current location
            out.println("LOC");
            line = in.readLine();
            if (line == null) {
                System.out.println("ROVER_02 check connection to server");
                line = "";
            }
            if (line.startsWith("LOC")) {
<<<<<<< HEAD
=======
            	System.out.println(line);
>>>>>>> b349cc8295343bc1226cc3eb8bc1c3c7e0b8fd9f
                currentLoc = extractLOC(line);
            }

            // System.out.println("ROVER_02 currentLoc after recheck: " +8
            // currentLoc);
            // System.out.println("ROVER_02 previousLoc: " + previousLoc);

            // test for stuckness
            stuck = currentLoc.equals(previousLoc);

            // System.out.println("ROVER_02 stuck test " + stuck);
<<<<<<< HEAD
            System.out.println("ROVER_02 blocked test " + blocked);
=======
            if(stuck){
            System.out.println("ROVER_02 blocked test " + blocked);
            
            }
>>>>>>> b349cc8295343bc1226cc3eb8bc1c3c7e0b8fd9f

            // TODO - logic to calculate where to move next

            Thread.sleep(sleepTime);

            System.out.println(
                    "ROVER_02 ------------ bottom process control --------------");
        }

    }

    // ################ Support Methods ###########################

    private void clearReadLineBuffer() throws IOException {
        while (in.ready()) {
            // System.out.println("ROVER_02 clearing readLine()");
            String garbage = in.readLine();
        }
    }

    // method to retrieve a list of the rover's equipment from the server
    private ArrayList<String> getEquipment() throws IOException {
        // System.out.println("ROVER_02 method getEquipment()");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        out.println("EQUIPMENT");

        String jsonEqListIn = in.readLine(); // grabs the string that was
                                             // returned first
        if (jsonEqListIn == null) {
            jsonEqListIn = "";
        }
        StringBuilder jsonEqList = new StringBuilder();
        // System.out.println("ROVER_02 incomming EQUIPMENT result - first
        // readline: " + jsonEqListIn);

        if (jsonEqListIn.startsWith("EQUIPMENT")) {
            while (!(jsonEqListIn = in.readLine()).equals("EQUIPMENT_END")) {
                if (jsonEqListIn == null) {
                    break;
                }
                // System.out.println("ROVER_02 incomming EQUIPMENT result: " +
                // jsonEqListIn);
                jsonEqList.append(jsonEqListIn);
                jsonEqList.append("\n");
                // System.out.println("ROVER_02 doScan() bottom of while");
            }
        } else {
            // in case the server call gives unexpected results
            clearReadLineBuffer();
            return null; // server response did not start with "EQUIPMENT"
        }

        String jsonEqListString = jsonEqList.toString();
        ArrayList<String> returnList;
        returnList = gson.fromJson(jsonEqListString,
                new TypeToken<ArrayList<String>>() {
                }.getType());
        // System.out.println("ROVER_02 returnList " + returnList);

        return returnList;
    }

    // sends a SCAN request to the server and puts the result in the scanMap
    // array
    public void doScan() throws IOException {
        // System.out.println("ROVER_02 method doScan()");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        out.println("SCAN");

        String jsonScanMapIn = in.readLine(); // grabs the string that was
                                              // returned first
        if (jsonScanMapIn == null) {
            System.out.println("ROVER_02 check connection to server");
            jsonScanMapIn = "";
        }
        StringBuilder jsonScanMap = new StringBuilder();
        System.out.println("ROVER_02 incomming SCAN result - first readline: "
                + jsonScanMapIn);

        if (jsonScanMapIn.startsWith("SCAN")) {
            while (!(jsonScanMapIn = in.readLine()).equals("SCAN_END")) {
                // System.out.println("ROVER_02 incomming SCAN result: " +
                // jsonScanMapIn);
                jsonScanMap.append(jsonScanMapIn);
                jsonScanMap.append("\n");
                // System.out.println("ROVER_02 doScan() bottom of while");
            }
        } else {
            // in case the server call gives unexpected results
            clearReadLineBuffer();
            return; // server response did not start with "SCAN"
        }
        // System.out.println("ROVER_02 finished scan while");

        String jsonScanMapString = jsonScanMap.toString();
<<<<<<< HEAD
        // debug print json object to a file
        // new MyWriter( jsonScanMapString, 0); //gives a strange result -
        // prints the \n instead of newline character in the file

        // System.out.println("ROVER_02 convert from json back to ScanMap
        // class");
        // convert from the json string back to a ScanMap object
=======
       
>>>>>>> b349cc8295343bc1226cc3eb8bc1c3c7e0b8fd9f
        scanMap = gson.fromJson(jsonScanMapString, ScanMap.class);
    }

    // this takes the LOC response string, parses out the x and x values and
    // returns a Coord object
    public static Coord extractLOC(String sStr) {
        sStr = sStr.substring(4);
        if (sStr.lastIndexOf(" ") != -1) {
            String xStr = sStr.substring(0, sStr.lastIndexOf(" "));
            // System.out.println("extracted xStr " + xStr);

            String yStr = sStr.substring(sStr.lastIndexOf(" ") + 1);
            // System.out.println("extracted yStr " + yStr);
            return new Coord(Integer.parseInt(xStr), Integer.parseInt(yStr));
        }
        return null;
    }

    /** determine if the rover is about to reach a "blocked" tile */
    public boolean isNextBlock(Direction direction, MapTile[][] scanMapTiles,
            int centerIndex) {
<<<<<<< HEAD

        switch (direction) {
        case NORTH:
=======
System.out.println("blocked");
        switch (direction) {
        case NORTH:
        	
>>>>>>> b349cc8295343bc1226cc3eb8bc1c3c7e0b8fd9f
            return isBlocked(scanMapTiles[centerIndex][centerIndex - 1]);
        case SOUTH:
            return isBlocked(scanMapTiles[centerIndex][centerIndex + 1]);
        case WEST:
            return isBlocked(scanMapTiles[centerIndex - 1][centerIndex]);
        case EAST:
            return isBlocked(scanMapTiles[centerIndex + 1][centerIndex]);
        default:
            // this code should be unreachable
            return false;
        }
    }

    /** determine if the rover is on ROCK NONE OR SAND */
    private boolean isBlocked(MapTile tile) {
<<<<<<< HEAD
        List<Terrain> blockers = Arrays.asList(Terrain.ROCK, Terrain.NONE,
=======
        List<Terrain> blockers = Arrays.asList(Terrain.NONE,
>>>>>>> b349cc8295343bc1226cc3eb8bc1c3c7e0b8fd9f
                Terrain.SAND);
        Terrain terrain = tile.getTerrain();
        return tile.getHasRover() || blockers.contains(terrain);
    }

    /** return a DIFFERENT direction */
    private Direction changeDirection(Direction direction) {
        switch (direction) {
        case NORTH:
            return Direction.WEST;
        case SOUTH:
            return Direction.EAST;
        case WEST:
            return Direction.SOUTH;
        case EAST:
            return Direction.NORTH;
        default:
            return null;
        }
    }

    /** move the rover one tile */
    private void move(Direction direction) {
        switch (direction) {
        case NORTH:
            out.println("MOVE N");
            break;
        case SOUTH:
            out.println("MOVE S");
            break;
        case WEST:
            out.println("MOVE W");
            break;
        case EAST:
            out.println("MOVE E");
            break;
        }
    }

    /**
     * recursively call itself until it find a direction that won't lead to a
     * blocked path
     */
    private Direction findGoodDirection(Direction direction,
            MapTile[][] scanMapTiles, int centerIndex) {

        if (isNextBlock(direction, scanMapTiles, centerIndex)) {
            return findGoodDirection(changeDirection(direction), scanMapTiles,
                    centerIndex);
        } else {
            return direction;
        }
    }

    /** the rover move logic */
    private void masterMove(Direction direction, MapTile[][] scanMapTiles,
            int centerIndex) {
        detectRadioactive(scanMapTiles);
        if (isNextBlock(direction, scanMapTiles, centerIndex)) {
            Direction goodDirection = findGoodDirection(direction, scanMapTiles,
                    centerIndex);
            if (isNextEdge(direction, scanMapTiles, centerIndex)) {
                currentDirection = findGoodDirection(direction, scanMapTiles,
                        centerIndex);
<<<<<<< HEAD
                move(currentDirection);
            } else {
=======
                System.out.println("CurrentDirection : "+currentDirection);
                move(currentDirection);
            } else {
            	System.out.println("GoodDirection : "+goodDirection);
>>>>>>> b349cc8295343bc1226cc3eb8bc1c3c7e0b8fd9f
                move(goodDirection);
            }

        } else {
<<<<<<< HEAD
=======
        	System.out.println("Direction : "+direction);
>>>>>>> b349cc8295343bc1226cc3eb8bc1c3c7e0b8fd9f
            move(direction);
        }
    }

    /**
     * determine if the rover is about to reach a "NONE" tile. use to indicate
     * that you've reach the edge of the map and may need to permantly change
     * direction
     */
    private boolean isNextEdge(Direction direction, MapTile[][] scanMapTiles,
            int centerIndex) {

        switch (direction) {
        case NORTH:
            return isNone(scanMapTiles[centerIndex][centerIndex - 1]);
        case SOUTH:
            return isNone(scanMapTiles[centerIndex][centerIndex + 1]);
        case WEST:
            return isNone(scanMapTiles[centerIndex - 1][centerIndex]);
        case EAST:
            return isNone(scanMapTiles[centerIndex + 1][centerIndex]);
        default:
            // this code should be unreachable
            return false;
        }
    }

    /**
     * iterate through a scan map to find a tile with radiation. get the
     * adjusted (absolute) coordinate of the tile and added into a hash set
     */
    private void detectRadioactive(MapTile[][] scanMapTiles) {
        for (int x = 0; x < scanMapTiles.length; x++) {
            for (int y = 0; y < scanMapTiles[x].length; y++) {
                MapTile mapTile = scanMapTiles[x][y];
                if (mapTile.getScience() == Science.RADIOACTIVE) {
                    int tileX = cc.xpos + (x - 5);
                    int tileY = cc.ypos + (y - 5);
                    System.out.println("Radioactive Location: [x:" + tileX
                            + " y: " + tileY);
                    science_collection.add(new Coord(tileX, tileY));
                }
            }
        }
    }

    /** determine if the tile is NONE */
    private boolean isNone(MapTile tile) {
        return tile.getTerrain() == Terrain.NONE;
    }

    /**
     * write to each rover the coords of a tile that contains radiation. will
     * only write to them if the coords haven't is new.
     */
    public void shareScience() {
        for (Coord c : science_collection) {
            if (!displayed_science.contains(c)) {
                for (Socket s : sockets)
                    try {
                        new DataOutputStream(s.getOutputStream())
                                .writeBytes(c.toString() + "\r\n");
                    } catch (Exception e) {

                    }
                displayed_science.add(c);
            }
<<<<<<< HEAD
        }
=======
            System.out.println("x : "+c.xpos+" y : "+c.ypos);
        }
       
>>>>>>> b349cc8295343bc1226cc3eb8bc1c3c7e0b8fd9f
    }

    /**
     * Runs the client
     */

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println(1);
            new ROVER_02().run();
        } else {
            System.out.println(2);
            new ROVER_02(args[0]).run();
        }
    }

<<<<<<< HEAD
=======
=======
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
			
			

			
	
			boolean	goingNorth = false;
			boolean goingSouth = false;
			boolean goingEast = true;
			boolean goingWest = false;
			boolean stuck = false; // just means it did not change locations between requests,
									// could be velocity limit or obstruction etc.
			boolean blocked = false;
			boolean blockedNorth = false;
			boolean blockedSouth = false;
			boolean blockedEast = false;
			boolean blockedWest = false;
	
			String[] cardinals = new String[4];
			cardinals[0] = "N";
			cardinals[1] = "E";
			cardinals[2] = "S";
			cardinals[3] = "W";
	
			String currentDir = cardinals[0];
			Coord currentLoc = null;
			Coord previousLoc = null;
			int stepCount = 0;
	

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
				// try moving east 1 block if blocked
				if (blocked) {
					//if(stepCount > 0){
						//out.println("MOVE E");
						//System.out.println("ROVER_02 request move E");
						//stepCount -= 1;
					//}
					//else {
						//blocked = false;
						//reverses direction after being blocked and side stepping
						//goingSouth = !goingSouth;
					//}
					
//					for (int i = 0; i < 5; i++) {
//						out.println("MOVE E");
//						//System.out.println("ROVER_02 request move E");
//						Thread.sleep(300);
//					}
//					blocked = false;
//					//reverses direction after being blocked
//					goingSouth = !goingSouth;
				} 
				else {
	
					// pull the MapTile array out of the ScanMap object
					MapTile[][] scanMapTiles = scanMap.getScanMap();
					int centerIndex = (scanMap.getEdgeSize() - 1)/2;
					// tile S = y + 1; N = y - 1; E = x + 1; W = x - 1
	
					if (goingSouth) {
						// check scanMap to see if path is blocked to the south
						// (scanMap may be old data by now)
						if (scanMapTiles[centerIndex][centerIndex +1].getHasRover() 
								|| scanMapTiles[centerIndex][centerIndex +1].getTerrain() == Terrain.ROCK
								|| scanMapTiles[centerIndex][centerIndex +1].getTerrain() == Terrain.SAND
								|| scanMapTiles[centerIndex][centerIndex +1].getTerrain() == Terrain.NONE) {
							//blocked = true;
							stepCount = 3;  //side stepping
							goingSouth = false;
							goingEast = true;
						} 
						else if (stepCount > 0){
							// request to server to move
							out.println("MOVE S");
							stepCount -= 1;
							//System.out.println("ROVER_02 request move S");
						}
						else {
							goingSouth = false;
							goingEast = true;
							stepCount = 3;
						}
						
					}
						// check scanMap to see if path is blocked to the north
						// (scanMap may be old data by now)
						//System.out.println("ROVER_02 scanMapTiles[2][1].getHasRover() " + scanMapTiles[2][1].getHasRover());
						//System.out.println("ROVER_02 scanMapTiles[2][1].getTerrain() " + scanMapTiles[2][1].getTerrain().toString());
					if (goingNorth) {
						if (scanMapTiles[centerIndex][centerIndex -1].getHasRover() 
								|| scanMapTiles[centerIndex][centerIndex -1].getTerrain() == Terrain.ROCK
								|| scanMapTiles[centerIndex][centerIndex -1].getTerrain() == Terrain.SAND
								|| scanMapTiles[centerIndex][centerIndex -1].getTerrain() == Terrain.NONE) {
							//blocked = true;
							stepCount = 2;  //side stepping
						} 
						else {
							// request to server to move
							out.println("MOVE N");
							//System.out.println("ROVER_02 request move N");
						}
					}
						
					if (goingEast) {
						if (scanMapTiles[centerIndex][centerIndex -1].getHasRover() 
								|| scanMapTiles[centerIndex + 1][centerIndex].getTerrain() == Terrain.ROCK
								|| scanMapTiles[centerIndex + 1][centerIndex].getTerrain() == Terrain.SAND
								|| scanMapTiles[centerIndex + 1][centerIndex].getTerrain() == Terrain.NONE) {
							//blocked = true;
							stepCount = 2;  //side stepping
							goingEast = false;
							goingSouth = true;
						} 
						else if (stepCount > 0) {
							// request to server to move
							out.println("MOVE E");
							stepCount -= 1;
							//System.out.println("ROVER_02 request move E");
						}
						else {
							goingEast = false;
							goingSouth = true;
							stepCount = 2;
						}
					}
						
					if (goingWest) {
						if (scanMapTiles[centerIndex][centerIndex -1].getHasRover() 
								|| scanMapTiles[centerIndex][centerIndex -1].getTerrain() == Terrain.ROCK
								|| scanMapTiles[centerIndex][centerIndex -1].getTerrain() == Terrain.SAND
								|| scanMapTiles[centerIndex][centerIndex -1].getTerrain() == Terrain.NONE) {
							//blocked = true;
							stepCount = 2;  //side stepping
						} 
						else {
							// request to server to move
							out.println("MOVE W");
							//System.out.println("ROVER_02 request move W");
						}
					}
											
				}
	
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
				System.out.println("ROVER_02 blocked test " + blocked);
	
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
>>>>>>> e8eb1f44fda3cde8b26ea1f5809df084d0c46fe1
>>>>>>> b349cc8295343bc1226cc3eb8bc1c3c7e0b8fd9f
}
