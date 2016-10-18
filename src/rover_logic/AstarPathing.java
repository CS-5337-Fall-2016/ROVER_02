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
	}
	
	public void findPath() {
		this.openList.add(this.currLOC);
		while(!(openList.isEmpty())) {
			this.currLOC = findLowestCost(openList);
			openList.remove(currLOC);
			closedList.add(currLOC);
			if (currLOC.equals(target)) {
				//return path
			}
			List<Coord> adjList = this.getAdjacentCoordinates(currLOC, globalMap);
			for (Coord c : adjList) {
				if (openList.contains(c) && )
				if (closedList.contains(c) || this.isBlocked(c)) {
					continue;
				}
				if (!(openList.contains(c)) && !(closedList.contains(c))) {
					//compute score, set parent
					openList.add(c);
				}
				else {
					
				}
				
			}
		}
	}
	
	public List<Coord> getPath() {
		return this.path;
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
        for (Coord c : adjList) {
        	if (isBlocked(c)) {
        		adjList.remove(c);
        	}
        }
        return adjList;
    }
	
	public Coord findLowestCost(List<Coord> cl) {
		Coord lowCoord = cl.get(0);
		for (Coord c: cl) {
			if (getScore(lowCoord) > getScore(c)) {
				lowCoord = c;
			}
		}
		return lowCoord;
	}
	
	public double getScore(Coord c) {
		double dx = Math.abs(target.xpos - c.xpos);
		double dy = Math.abs(target.ypos - c.ypos);
		return dx + dy;
	}
	
	public boolean isBlocked(Coord c) {
		List<Terrain> walls = Arrays.asList(Terrain.NONE, Terrain.SAND);
		MapTile m = globalMap.get(c);
		return walls.contains(m.getTerrain()) || m.getHasRover();
			
	}
	
	
}
