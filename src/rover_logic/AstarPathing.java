package rover_logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import common.Coord;
import common.MapTile;
import enums.Direction;
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
		this.globalMap = globalMapIn;
		this.target = new NodeA(targetIn);
		this.currLOC = new NodeA(currLOCIn, createScore(currLOCIn));
		this.startLOC = currLOC;
		openList = new ArrayList<NodeA>();
		closedList = new ArrayList<NodeA>();

	}

	public Stack<Direction> findPath() {
		System.out.println("A* Pathfinding In Progess...");
		double moveCost = 0;
		this.openList.add(this.currLOC);
		while (!(this.openList.isEmpty())) {
			moveCost += 1;
			this.currLOC = findLowestCost(this.openList);
			//System.out.println("Add" + currLOC.getCoord().toString() + currLOC.getScore());
			this.openList.remove(this.currLOC);
			this.closedList.add(this.currLOC);
			if (this.currLOC == null) {
				System.out.println("No Path to Target Location!");
				return null;
			}
			if (this.currLOC.equals(this.target)) {
				System.out.println("A* Pathfinding: Path created!");
				return createPath(this.currLOC);
			}
			List<NodeA> adjList = this.getAdjacentCoordinates(this.currLOC, moveCost);
			for (int na = 0; na < adjList.size(); na++) {
				if (!(this.closedList.contains(na))) {
					if (!(this.openList.contains(adjList.get(na)))) {
						adjList.get(na).setParent(this.currLOC);
						this.openList.add(adjList.get(na));
					}
				}
			}
		}
		return null; // this code reachable when there are no paths to the
						// target
	}

	// Method to get all adjacent coordinates
	public List<NodeA> getAdjacentCoordinates(NodeA node, double add) {
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
		
		if ((!this.isBlocked(s)) && !(globalMap.get(s) == null)) {
			adjList.add(new NodeA(s, createScore(s) + add));
		}
		if ((!this.isBlocked(e)) && !(globalMap.get(e) == null)) {
			adjList.add(new NodeA(e, createScore(e) + add));
		}
		if ((!this.isBlocked(w)) && !(globalMap.get(w) == null)) {
			adjList.add(new NodeA(w, createScore(w) + add));
		}
		if ((!this.isBlocked(n)) && !(globalMap.get(n) == null)) {
			adjList.add(new NodeA(n, createScore(n) + add));
		}
		return adjList;
	}

	public NodeA findLowestCost(List<NodeA> nodeList) {
		NodeA lowNode = nodeList.get(0);
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.get(i).getScore() < lowNode.getScore()) {
				lowNode = nodeList.get(i);
			}
		}
		if (globalMap.get(lowNode.getCoord()) == null) {
			return lowNode;
		}
		else {
			return null;
		}
	}

	public double createScore(Coord c) {
		double dx = Math.abs(this.target.getCoord().xpos - c.xpos);
		double dy = Math.abs(this.target.getCoord().ypos - c.ypos);
		return dx + dy;
	}

	public boolean isBlocked(Coord c) {
		try {
			List<Terrain> walls = Arrays.asList(Terrain.NONE, Terrain.SAND);
			MapTile m = this.globalMap.get(c);
			return walls.contains(m.getTerrain()) || m.getHasRover();
		}
		catch (Exception e){
			return true;
		}
		

	}

	public Stack<Direction> createPath(NodeA dest) {
		Stack<Direction> path = new Stack<Direction>();
		NodeA currNode = dest;
		while (!currNode.equals(startLOC)) {
			path.push(currNode.getDirection());
			currNode = currNode.getParent();
			//System.out.println("Moving Backwards" + currNode.getCoord().toString());
		}
		//System.out.println("Size of List: " + path.size());

		return path;
	}

}