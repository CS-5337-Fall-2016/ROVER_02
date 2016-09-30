<<<<<<< HEAD
package enums;

public enum Science {
	NONE("N"), 
	RADIOACTIVE("Y"), 
	ORGANIC("O"), 
	MINERAL("M"), 
	ARTIFACT("A"), 
	CRYSTAL("C");

	private final String value;
	
	private Science(String value) {
		this.value = value;
	}

	public String getSciString() {
		return value;
	}
	
    public static Science getEnum(String input){
    	Science output;
    	
    	switch(input){
    	case "N":
    		output = Science.NONE;
    		break;
    	case "Y":
    		output = Science.RADIOACTIVE;
    		break;
    	case "O":
    		output = Science.ORGANIC;
    		break;
    	case "M":
    		output = Science.MINERAL;
    		break;
    	case "A":
    		output = Science.ARTIFACT;
    		break;
    	case "C":
    		output = Science.CRYSTAL;
    		break;

    	default:
    		output = Science.NONE;
    	}	
    	return output;
    }
}
=======
package enums;

public enum Science {
	NONE("N"), 
	RADIOACTIVE("Y"), 
	ORGANIC("O"), 
	MINERAL("M"), 
	ARTIFACT("A"), 
	CRYSTAL("C");

	private final String value;
	
	private Science(String value) {
		this.value = value;
	}

	public String getSciString() {
		return value;
	}
	
    public static Science getEnum(String input){
    	Science output;
    	
    	switch(input){
    	case "N":
    		output = Science.NONE;
    		break;
    	case "Y":
    		output = Science.RADIOACTIVE;
    		break;
    	case "O":
    		output = Science.ORGANIC;
    		break;
    	case "M":
    		output = Science.MINERAL;
    		break;
    	case "A":
    		output = Science.ARTIFACT;
    		break;
    	case "C":
    		output = Science.CRYSTAL;
    		break;

    	default:
    		output = Science.NONE;
    	}	
    	return output;
    }
}
>>>>>>> e8eb1f44fda3cde8b26ea1f5809df084d0c46fe1
