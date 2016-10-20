package rover_logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import common.Coord;
import common.MapTile;
import enums.Terrain;
import rover_logic.NodeA;

public class AstarPathing {
	private Map<Coord, MapTile> globalMap;
	private NodeA currLOC;
	private NodeA startLOC;
	private NodeA target;
	private List<NodeA> openList;
	private List<NodeA> closedList;

	public AstarPathing(Map<Coord, MapTile> globalMapIn, Coord currLOCIn, Coord targetIn) {
		globalMap = globalMapIn;
		currLOC = new NodeA(currLOCIn, createScore(currLOCIn));
		startLOC = currLOC;
		target = new NodeA(targetIn);
	}
	
	public Stack<String> findPath() {
		this.openList.add(currLOC);
		while(!(openList.isEmpty())) {
			this.currLOC = findLowestCost(openList);
			openList.remove(currLOC);
			closedList.add(currLOC);
			if (currLOC.equals(target)) {
				return createPath(target);
			}
			List<NodeA> adjList = this.getAdjacentCoordinates(currLOC, globalMap);
			for (NodeA na : adjList) {
				if (openList.contains(na) && na.getScore() < currLOC.getScore()) {
					
				}
				if (closedList.contains(na) && na.getScore() < currLOC.getScore()) {
					
				}
				if (!(openList.contains(na)) && !(closedList.contains(na))) {
					
					openList.add(na);
				}
				
			}
		}
		return null; //this code reachable when there are no paths to the target
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
	
	public double createScore(Coord c) {
		double dx = Math.abs(target.getCoord().xpos - c.xpos);
		double dy = Math.abs(target.getCoord().ypos - c.ypos);
		return dx + dy;
	}
	
	public boolean isBlocked(Coord c) {
		List<Terrain> walls = Arrays.asList(Terrain.NONE, Terrain.SAND);
		MapTile m = globalMap.get(c);
		return walls.contains(m.getTerrain()) || m.getHasRover();
			
	}
	
	public Stack<String> createPath(NodeA dest) {
		Stack<String> path = new Stack<String>();
		NodeA currNode = dest;
		while (!(currNode.equals(startLOC))) {
			path.push(currNode.getDirection());
			currNode = currNode.getParent();
		}
		return path;
	}
	
	
}