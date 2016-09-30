<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 2af07f9905004dbe7d00512811ff97e7e65652cd
package enums;

public enum Terrain {
	NONE ("X"),	// bottomless pit?
	ROCK ("R"),
	SOIL ("N"),
	GRAVEL ("G"),
	SAND ("S"),
	FLUID ("F");
	
	private final String value;
	
	private Terrain(String value) {
		this.value = value;
	}

	public String getTerString() {
		return value;
	}
	
    public static Terrain getEnum(String input){
    	Terrain output;
    	
    	switch(input){
    	case "X":
    		output = Terrain.NONE;
    		break;
    	case "R":
    		output = Terrain.ROCK;
    		break;
    	case "N":
    		output = Terrain.SOIL;
    		break;
    	case "G":
    		output = Terrain.GRAVEL;
    		break;
    	case "S":
    		output = Terrain.SAND;
    		break;
    	case "F":
    		output = Terrain.FLUID;
    		break;

    	default:
    		output = Terrain.NONE;
    	}	
    	return output;
    }
}
<<<<<<< HEAD
=======
=======
package enums;

public enum Terrain {
	NONE ("X"),	// bottomless pit?
	ROCK ("R"),
	SOIL ("N"),
	GRAVEL ("G"),
	SAND ("S"),
	FLUID ("F");
	
	private final String value;
	
	private Terrain(String value) {
		this.value = value;
	}

	public String getTerString() {
		return value;
	}
	
    public static Terrain getEnum(String input){
    	Terrain output;
    	
    	switch(input){
    	case "X":
    		output = Terrain.NONE;
    		break;
    	case "R":
    		output = Terrain.ROCK;
    		break;
    	case "N":
    		output = Terrain.SOIL;
    		break;
    	case "G":
    		output = Terrain.GRAVEL;
    		break;
    	case "S":
    		output = Terrain.SAND;
    		break;
    	case "F":
    		output = Terrain.FLUID;
    		break;

    	default:
    		output = Terrain.NONE;
    	}	
    	return output;
    }
}
>>>>>>> e8eb1f44fda3cde8b26ea1f5809df084d0c46fe1
>>>>>>> 2af07f9905004dbe7d00512811ff97e7e65652cd
