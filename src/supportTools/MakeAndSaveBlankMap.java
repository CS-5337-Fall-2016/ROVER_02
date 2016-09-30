<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 2af07f9905004dbe7d00512811ff97e7e65652cd
package supportTools;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.Coord;
import common.MapTile;
import common.PlanetMap;
import common.RoverLocations;
import common.ScanMap;
import common.ScienceLocations;
import enums.Science;
import enums.Terrain;
import json.MyWriter;

public class MakeAndSaveBlankMap {

	public static void main(String[] args) throws IOException {
		int mapWidth = 100;
		int mapHeight = 60;
		
		String fileName = "Map" + mapWidth + "x" + mapHeight + "blank.txt";
			
		SwarmMapInit mapInit = new SwarmMapInit(fileName, mapWidth, mapHeight, new PlanetMap(mapWidth, mapHeight), new RoverLocations(), new ScienceLocations());

		mapInit.saveToDisplayTextFile(fileName);
		mapInit.printToDisplayTextFile();
				
	}
}
<<<<<<< HEAD
=======
=======
package supportTools;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.Coord;
import common.MapTile;
import common.PlanetMap;
import common.RoverLocations;
import common.ScanMap;
import common.ScienceLocations;
import enums.Science;
import enums.Terrain;
import json.MyWriter;

public class MakeAndSaveBlankMap {

	public static void main(String[] args) throws IOException {
		int mapWidth = 100;
		int mapHeight = 60;
		
		String fileName = "Map" + mapWidth + "x" + mapHeight + "blank.txt";
			
		SwarmMapInit mapInit = new SwarmMapInit(fileName, mapWidth, mapHeight, new PlanetMap(mapWidth, mapHeight), new RoverLocations(), new ScienceLocations());

		mapInit.saveToDisplayTextFile(fileName);
		mapInit.printToDisplayTextFile();
				
	}
}
>>>>>>> e8eb1f44fda3cde8b26ea1f5809df084d0c46fe1
>>>>>>> 2af07f9905004dbe7d00512811ff97e7e65652cd
