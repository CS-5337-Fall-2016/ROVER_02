<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 2af07f9905004dbe7d00512811ff97e7e65652cd
package common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Coord {
	// thanks to this posting http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java
	
	public final int xpos;
	public final int ypos;
	
	@Override
	public String toString() {
		return "Coord [xpos=" + xpos + ", ypos=" + ypos + "]";
	}

	public Coord(int x, int y){
		this.xpos = x;
		this.ypos = y;
	}
	
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(xpos).
            append(ypos).
            toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
       if (!(obj instanceof Coord))
            return false;
        if (obj == this)
            return true;

        Coord theOther = (Coord) obj;
//        return new EqualsBuilder().
//            // if deriving: appendSuper(super.equals(obj)).
//            append(xpos, theOther.xpos).
//            append(ypos, theOther.ypos).
//            isEquals();
        return ((this.xpos == theOther.xpos) && (this.ypos == theOther.ypos));
    }
	
}
<<<<<<< HEAD
=======
=======
package common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Coord {
	// thanks to this posting http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java
	
	public final int xpos;
	public final int ypos;
	
	@Override
	public String toString() {
		return "Coord [xpos=" + xpos + ", ypos=" + ypos + "]";
	}

	public Coord(int x, int y){
		this.xpos = x;
		this.ypos = y;
	}
	
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(xpos).
            append(ypos).
            toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
       if (!(obj instanceof Coord))
            return false;
        if (obj == this)
            return true;

        Coord theOther = (Coord) obj;
//        return new EqualsBuilder().
//            // if deriving: appendSuper(super.equals(obj)).
//            append(xpos, theOther.xpos).
//            append(ypos, theOther.ypos).
//            isEquals();
        return ((this.xpos == theOther.xpos) && (this.ypos == theOther.ypos));
    }
	
}
>>>>>>> e8eb1f44fda3cde8b26ea1f5809df084d0c46fe1
>>>>>>> 2af07f9905004dbe7d00512811ff97e7e65652cd
