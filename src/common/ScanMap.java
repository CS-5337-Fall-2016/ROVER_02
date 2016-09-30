<<<<<<< HEAD
=======
<<<<<<< HEAD
package common;

public class ScanMap {
	private MapTile[][] scanArray;
	private int edgeSize;
	private Coord centerPoint;
	
	public ScanMap(){
		
		this.scanArray = null;
		this.edgeSize = 0;
		this.centerPoint = null;		
	}
	
	public ScanMap(MapTile[][] scanArray, int size, Coord centerPoint){
		this.scanArray = scanArray;
		this.edgeSize = size;
		this.centerPoint = centerPoint;		
	}
	
	public MapTile[][] getScanMap(){
		return scanArray;
	}
	
	public void debugPrintMap(){
		for(int k=0;k<edgeSize + 2;k++){System.out.print("--");}
		System.out.print("\n");
		for(int j= 0; j< edgeSize; j++){
			System.out.print("| ");
			for(int i= 0; i<edgeSize; i++){
				//check and print edge of map has first priority
				if(scanArray[i][j].getTerrain().toString().equals("NONE")){
					System.out.print("XX");
					
				// next most important - print terrain and/or science locations
					//terrain and science
				} else if(!(scanArray[i][j].getTerrain().toString().equals("SOIL"))
						&& !(scanArray[i][j].getScience().toString().equals("NONE"))){
					// both terrain and science
					
					System.out.print(scanArray[i][j].getTerrain().toString().substring(0,1) + scanArray[i][j].getScience().getSciString());
					
					//just terrain
				} else if(!(scanArray[i][j].getTerrain().toString().equals("SOIL"))){
					System.out.print(scanArray[i][j].getTerrain().toString().substring(0,1) + " ");
					//just science
				} else if(!(scanArray[i][j].getScience().toString().equals("NONE"))){
					System.out.print(" " + scanArray[i][j].getScience().getSciString());
					
				// if still empty check for rovers and print them
				} else if(scanArray[i][j].getHasRover()){
					System.out.print("[]");
					
				// nothing here so print nothing
				} else {
					System.out.print("  ");
				}
			}	
			System.out.print(" |\n");		
		}
		for(int k=0;k<edgeSize +2;k++){System.out.print("--");}
		System.out.print("\n");
	}
	
	public int getEdgeSize(){
		return edgeSize;
	}
}
=======
>>>>>>> 2af07f9905004dbe7d00512811ff97e7e65652cd
package common;

public class ScanMap {
	private MapTile[][] scanArray;
	private int edgeSize;
	private Coord centerPoint;
	
	public ScanMap(){
		this.scanArray = null;
		this.edgeSize = 0;
		this.centerPoint = null;		
	}
	
	public ScanMap(MapTile[][] scanArray, int size, Coord centerPoint){
		this.scanArray = scanArray;
		this.edgeSize = size;
		this.centerPoint = centerPoint;		
	}
	
	public MapTile[][] getScanMap(){
		return scanArray;
	}
	
	public void debugPrintMap(){
		for(int k=0;k<edgeSize + 2;k++){System.out.print("--");}
		System.out.print("\n");
		for(int j= 0; j< edgeSize; j++){
			System.out.print("| ");
			for(int i= 0; i< edgeSize; i++){
				//check and print edge of map has first priority
				if(scanArray[i][j].getTerrain().toString().equals("NONE")){
					System.out.print("XX");
					
				// next most important - print terrain and/or science locations
					//terrain and science
				} else if(!(scanArray[i][j].getTerrain().toString().equals("SOIL"))
						&& !(scanArray[i][j].getScience().toString().equals("NONE"))){
					// both terrain and science
					
					System.out.print(scanArray[i][j].getTerrain().toString().substring(0,1) + scanArray[i][j].getScience().getSciString());
					//just terrain
				} else if(!(scanArray[i][j].getTerrain().toString().equals("SOIL"))){
					System.out.print(scanArray[i][j].getTerrain().toString().substring(0,1) + " ");
					//just science
				} else if(!(scanArray[i][j].getScience().toString().equals("NONE"))){
					System.out.print(" " + scanArray[i][j].getScience().getSciString());
					
				// if still empty check for rovers and print them
				} else if(scanArray[i][j].getHasRover()){
					System.out.print("[]");
					
				// nothing here so print nothing
				} else {
					System.out.print("  ");
				}
			}	
			System.out.print(" |\n");		
		}
		for(int k=0;k<edgeSize +2;k++){System.out.print("--");}
		System.out.print("\n");
	}
	
	public int getEdgeSize(){
		return edgeSize;
	}
}
<<<<<<< HEAD
=======
>>>>>>> e8eb1f44fda3cde8b26ea1f5809df084d0c46fe1
>>>>>>> 2af07f9905004dbe7d00512811ff97e7e65652cd
