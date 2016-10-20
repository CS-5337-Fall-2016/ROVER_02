package rover_logic;

import java.util.Arrays;

import common.Coord;
import enums.Direction;

// Node class for Astar search
public class NodeA implements Comparable<NodeA> {
    private Coord coord;
    private double score;
    private NodeA parent;

    public NodeA(Coord coord) {
        this.coord = coord;
    }
    
    public NodeA(Coord coord, double score) {
        this.coord = coord;
        this.score = score;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public NodeA getParent() {
		return parent;
	}

	public void setParent(NodeA parent) {
		this.parent = parent;
	}

	// only check by its coordinate, not data
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof NodeA))
            return false;
        if (this == o)
            return true;
        NodeA other = (NodeA) o;
        return this.getCoord().equals(other.getCoord());
    }

    public String toString() {
        String str = "";
        str += "coord: " + coord + ", score: " + score;
        return str;
    }

	@Override
	public int compareTo(NodeA o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Direction getDirection() {
		int xpos = this.coord.xpos - this.parent.getCoord().xpos;
		int ypos = this.coord.ypos - this.parent.getCoord().ypos;
		int[] diff = {xpos, ypos};
		int[] N = {0, 1};
		int[] S = {0, -1};
		int[] E = {-1, 0};
		int[] W = {1, 0};
		if (Arrays.equals(diff, N)) {
			return Direction.NORTH;
		}
		else if(Arrays.equals(diff, S)) {
			return Direction.SOUTH;
		}
		else if(Arrays.equals(diff, E)) {
			return Direction.EAST;
		}
		else if(Arrays.equals(diff, W)) {
			return Direction.WEST;
		}
		else {
			return null;
		}
	}


}
