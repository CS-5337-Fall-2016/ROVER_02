<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 2af07f9905004dbe7d00512811ff97e7e65652cd
package testUtillities;


import java.util.List;
import javax.swing.*;

// Thanks to this posting for the seed this was constructed from:
// http://stackoverflow.com/questions/30204521/thread-output-to-gui-text-field

public class GUIdisplay1 extends JPanel implements MyGUIAppendable {
   private JTextArea area = new JTextArea(30, 50);

   public GUIdisplay1() {
      JScrollPane scrollPane = new JScrollPane(area);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      add(scrollPane);
   }

   @Override
   public void append(String text) {
      area.append(text);
   }

   static void createAndShowGui(MyGUIWorker myGuiWorker, GUIdisplay1 mainGuiPanel) {

      // add a Prop Change listener here to listen for 
      // DONE state then call get() on myWorker
	   myGuiWorker.execute();

      JFrame frame = new JFrame("GUIdisplay");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.getContentPane().add(mainGuiPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }
}

class MyGUIWorker extends SwingWorker<Void, String> {
   private MyGUIAppendable myAppendable;
   private String msg;

   public MyGUIWorker(MyGUIAppendable myAppendable) {
      this.myAppendable = myAppendable;
   }

   
   @Override
   protected Void doInBackground() throws Exception {
	   //put the conversion code here and then publish it
	   publish(msg);
      return null;
   }

	public void printOut(String msg){
		this.msg = msg;
		try {
			doInBackground();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

@Override
   protected void process(List<String> chunks) {
      for (String text : chunks) {
         myAppendable.append(text + "\n");
      }
   }
}

interface MyGUIAppendable {
   public void append(String text);
<<<<<<< HEAD
=======
=======
package testUtillities;


import java.util.List;
import javax.swing.*;

// Thanks to this posting for the seed this was constructed from:
// http://stackoverflow.com/questions/30204521/thread-output-to-gui-text-field

public class GUIdisplay1 extends JPanel implements MyGUIAppendable {
   private JTextArea area = new JTextArea(30, 50);

   public GUIdisplay1() {
      JScrollPane scrollPane = new JScrollPane(area);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      add(scrollPane);
   }

   @Override
   public void append(String text) {
      area.append(text);
   }

   static void createAndShowGui(MyGUIWorker myGuiWorker, GUIdisplay1 mainGuiPanel) {

      // add a Prop Change listener here to listen for 
      // DONE state then call get() on myWorker
	   myGuiWorker.execute();

      JFrame frame = new JFrame("GUIdisplay");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.getContentPane().add(mainGuiPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }
}

class MyGUIWorker extends SwingWorker<Void, String> {
   private MyGUIAppendable myAppendable;
   private String msg;

   public MyGUIWorker(MyGUIAppendable myAppendable) {
      this.myAppendable = myAppendable;
   }

   
   @Override
   protected Void doInBackground() throws Exception {
	   //put the conversion code here and then publish it
	   publish(msg);
      return null;
   }

	public void printOut(String msg){
		this.msg = msg;
		try {
			doInBackground();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

@Override
   protected void process(List<String> chunks) {
      for (String text : chunks) {
         myAppendable.append(text + "\n");
      }
   }
}

interface MyGUIAppendable {
   public void append(String text);
>>>>>>> e8eb1f44fda3cde8b26ea1f5809df084d0c46fe1
>>>>>>> 2af07f9905004dbe7d00512811ff97e7e65652cd
}