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
	private Map<Coord, MapTile> globalMap;
	private NodeA currLOC;
	private NodeA target;
	private List<NodeA> openList;
	private List<NodeA> closedList;

	public AstarPathing(Map<Coord, MapTile> globalMapIn, Coord currLOCIn, Coord targetIn) {
		this.globalMap = globalMapIn;
		this.currLOC = new NodeA(currLOCIn, createScore(currLOCIn));
		this.target = new NodeA(targetIn);
	}
	
	public void findPath() {
		this.openList.add(this.currLOC);
		while(!(this.openList.isEmpty())) {
			this.currLOC = findLowestCost(this.openList);
			this.openList.remove(this.currLOC);
			this.closedList.add(this.currLOC);
			if (this.currLOC.equals(this.target)) {
				//return path
			}
			List<NodeA> adjList = this.getAdjacentCoordinates(this.currLOC, this.globalMap);
			for (NodeA na : adjList) {
				if (this.closedList.contains(na)) {
					continue;
				}
				if (!(this.openList.contains(na)) || na.getScore() < this.currLOC.getScore()) {	
					na.setScore(createScore(na.getCoord()));
					na.setParent(this.currLOC);
					this.openList.add(na);
				}
				
			}
		}
	}
	
	//Method to get all adjacent coordinates
	public List<NodeA> getAdjacentCoordinates(NodeA node, Map<Coord, MapTile> global) {
        List<NodeA> adjList = new ArrayList<>();
        Coord curr = node.getCoord();

        // coordinates
        int west = curr.xpos - 1;
        int east = curr.xpos + 1;
        int north = curr.ypos - 1;
        int south = curr.ypos + 1;

        Coord s = new Coord(curr.xpos, south); // S
        Coord e = new Coord(east, curr.ypos); // E
        Coord w = new Coord(west, curr.ypos); // W
        Coord n = new Coord(curr.xpos, north); // N

        adjList.add(new NodeA(e));
        adjList.add(new NodeA(w));
        adjList.add(new NodeA(s));
        adjList.add(new NodeA(n));
        for (NodeA na : adjList) {
        	if (isBlocked(na.getCoord())) {
        		adjList.remove(na);
        	}
        }
        return adjList;
    }
	
	public NodeA findLowestCost(List<NodeA> nodeList) {
		NodeA lowNode = nodeList.get(0);
		for (NodeA n: nodeList) {
			if (n.getScore() < lowNode.getScore() ) {
				lowNode = n;
			}
		}
		return lowNode;
	}
	
	private double createScore(Coord c) {
		double dx = Math.abs(target.getCoord().xpos - c.xpos);
		double dy = Math.abs(target.getCoord().ypos - c.ypos);
		return dx + dy;
	}
	
	private boolean isBlocked(Coord c) {
		List<Terrain> walls = Arrays.asList(Terrain.NONE, Terrain.SAND);
		MapTile m = globalMap.get(c);
		return walls.contains(m.getTerrain()) || m.getHasRover();
			
	}
	
	
}
