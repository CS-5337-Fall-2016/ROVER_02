package rover_logic;

import common.Coord;

// Node class for Astar search
public class NodeA implements Comparable<Node> {
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
	public int compareTo(Node o) {
		return 0;
	}


}
