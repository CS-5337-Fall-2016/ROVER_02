<<<<<<< HEAD
package testUtillities;

import javax.swing.SwingUtilities;

public class GUIdisplay1Test {
	
	static GUIdisplay1 mainPanel;
	static MyGUIWorker myWorker;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		mainPanel = new GUIdisplay1();
		myWorker = new MyGUIWorker(mainPanel);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUIdisplay1.createAndShowGui(myWorker, mainPanel);
			}
		});

		
		for (int i = 2; i < 29; i++) {
			System.out.println(i);
			myWorker.printOut(Integer.toString(i));

			Thread.sleep(500); // Time delay to sync output

		}

	}

=======
package testUtillities;

import javax.swing.SwingUtilities;

public class GUIdisplay1Test {
	
	static GUIdisplay1 mainPanel;
	static MyGUIWorker myWorker;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		mainPanel = new GUIdisplay1();
		myWorker = new MyGUIWorker(mainPanel);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUIdisplay1.createAndShowGui(myWorker, mainPanel);
			}
		});

		
		for (int i = 2; i < 29; i++) {
			System.out.println(i);
			myWorker.printOut(Integer.toString(i));

			Thread.sleep(500); // Time delay to sync output

		}

	}

>>>>>>> e8eb1f44fda3cde8b26ea1f5809df084d0c46fe1
}