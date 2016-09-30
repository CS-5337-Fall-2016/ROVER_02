<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 2af07f9905004dbe7d00512811ff97e7e65652cd
package testUtillities;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;

import common.Coord;
import common.ScienceLocations;
import enums.Science;


public class SubsettingTest {
	
	
	
	public static void main(String[] args) {

		ScienceLocations scienceLocations = new ScienceLocations();
		
		System.out.println(scienceLocations.getHashMapClone());
		
		
		
		HashMap<Coord, Science> sciHash = new HashMap<Coord, Science>();
		HashMap<Coord, Science> filteredSciHash = new HashMap<Coord, Science>();
		
		sciHash = scienceLocations.getHashMapClone();
					
	    for (Entry<Coord, Science> entry : sciHash.entrySet()) {
	        if (Objects.equals(Science.RADIOACTIVE, entry.getValue())) {
	        	filteredSciHash.put(entry.getKey(), Science.RADIOACTIVE);
	            System.out.println( entry.getKey());
	        }
	    }
	    
	    System.out.println("");
	    System.out.println("filteredSciHash " + filteredSciHash);
		
	}
	

}
<<<<<<< HEAD
=======
=======
package testUtillities;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;

import common.Coord;
import common.ScienceLocations;
import enums.Science;


public class SubsettingTest {
	
	
	
	public static void main(String[] args) {

		ScienceLocations scienceLocations = new ScienceLocations();
		
		System.out.println(scienceLocations.getHashMapClone());
		
		
		
		HashMap<Coord, Science> sciHash = new HashMap<Coord, Science>();
		HashMap<Coord, Science> filteredSciHash = new HashMap<Coord, Science>();
		
		sciHash = scienceLocations.getHashMapClone();
					
	    for (Entry<Coord, Science> entry : sciHash.entrySet()) {
	        if (Objects.equals(Science.RADIOACTIVE, entry.getValue())) {
	        	filteredSciHash.put(entry.getKey(), Science.RADIOACTIVE);
	            System.out.println( entry.getKey());
	        }
	    }
	    
	    System.out.println("");
	    System.out.println("filteredSciHash " + filteredSciHash);
		
	}
	

}
>>>>>>> e8eb1f44fda3cde8b26ea1f5809df084d0c46fe1
>>>>>>> 2af07f9905004dbe7d00512811ff97e7e65652cd
