<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 2af07f9905004dbe7d00512811ff97e7e65652cd
package testUtillities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class printStringToFile {

	public static void main(String[] args) {
		try {
			File file = new File("test1.txt");
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write("This is ");
			fileWriter.write("a test");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
<<<<<<< HEAD
=======
=======
package testUtillities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class printStringToFile {

	public static void main(String[] args) {
		try {
			File file = new File("test1.txt");
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write("This is ");
			fileWriter.write("a test");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
>>>>>>> e8eb1f44fda3cde8b26ea1f5809df084d0c46fe1
>>>>>>> 2af07f9905004dbe7d00512811ff97e7e65652cd
