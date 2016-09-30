<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 2af07f9905004dbe7d00512811ff97e7e65652cd
package testUtillities;

public class TimeValueExperiment {
	public static void main(String[] args) {

		long time1 = System.currentTimeMillis();
		
		System.out.println("time1 is " + time1);
		
		while(true){
			
			System.out.println("current time is " + System.currentTimeMillis()); 
			
			System.out.println("time difference is " + (System.currentTimeMillis() - time1));
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
<<<<<<< HEAD
=======
=======
package testUtillities;

public class TimeValueExperiment {
	public static void main(String[] args) {

		long time1 = System.currentTimeMillis();
		
		System.out.println("time1 is " + time1);
		
		while(true){
			
			System.out.println("current time is " + System.currentTimeMillis()); 
			
			System.out.println("time difference is " + (System.currentTimeMillis() - time1));
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
>>>>>>> e8eb1f44fda3cde8b26ea1f5809df084d0c46fe1
>>>>>>> 2af07f9905004dbe7d00512811ff97e7e65652cd
