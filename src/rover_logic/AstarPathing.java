package rover_logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import common.Coord;
import common.MapTile;
import enums.Terrain;
import rover_logic.Node;

public class AstarPathing {
	Map<Coord, MapTile> globalMap;
	Coord currLOC;
	Coord target;
	List<Coord> openList;
	List<Coord> closedList;
	List<Coord> path;

	public AstarPathing(Map<Coord, MapTile> globalMapIn, Coord currLOCIn, Coord targetIn) {
		globalMap = globalMapIn;
		currLOC = currLOCIn;
		target = targetIn;
		this.openList.add(this.currLOC);
		List<Node> nodeList = new ArrayList<Node>(); //List that will have coordinates and data
		while (true) { //infinite loop, fix later
			this.currLOC = this.findLowestCost(nodeList);
			openList.remove(currLOC);
			closedList.add(currLOC);
			if (currLOC.equals(target)) {
				//return path
			}
			openList.addAll(this.getAdjacentCoordinates(currLOC, globalMap));
			
		}
		
	}
	
	public List<Coord> getPath() {
		return this.path;
	}
	
	public double getDistance(Coord current, Coord dest) {
        double dx = current.xpos - dest.xpos;
        double dy = current.ypos - dest.ypos;
        return Math.sqrt((dx * dx) + (dy * dy)) * 100;
    }
	
	//Method to get all adjacent coordinates
	public List<Coord> getAdjacentCoordinates(Coord curr, Map<Coord, MapTile> global) {
        List<Coord> adjList = new ArrayList<>();

        // coordinates
        int west = curr.xpos - 1;
        int east = curr.xpos + 1;
        int north = curr.ypos - 1;
        int south = curr.ypos + 1;

        Coord s = new Coord(curr.xpos, south); // S
        Coord e = new Coord(east, curr.ypos); // E
        Coord w = new Coord(west, curr.ypos); // W
        Coord n = new Coord(curr.xpos, north); // N

        adjList.add(e);
        adjList.add(w);
        adjList.add(s);
        adjList.add(n);
        for(int i = 0; i < adjList.size(); i++) {
        	if (isBlocked(adjList.get(i))) {
        		adjList.remove(adjList.get(i)); //issue with adjList.size() when removing
        	}
        }

        return adjList;
    }
	
	public Coord findLowestCost(List<Node> nodes) {
		Node low = nodes.get(0);
		for (int i = 0; i < nodes.size(); i++) {
			if (low.getData() > nodes.get(i).getData()) {
				low = nodes.get(i);
			}
		}
		return low.getCoord();
	}
	
	public boolean isBlocked(Coord c) {
		List<Terrain> walls = Arrays.asList(Terrain.NONE, Terrain.SAND);
		MapTile m = globalMap.get(c);
		return walls.contains(m.getTerrain()) || m.getHasRover();
			
	}
	
	
}
