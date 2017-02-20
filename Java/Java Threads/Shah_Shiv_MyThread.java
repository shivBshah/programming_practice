import java.io.*;
import java.util.*;

public class Shah_Shiv_MyThread extends Thread {
  
   private String inFile;
   
	public Shah_Shiv_MyThread(String str, String inFile) {
		super(str);
      this.inFile = inFile;
	}
	 
	public void run () 
	{
		System.out.println("MyThread[" + getName() + "]: BEGIN!");
      BufferedReader in = null;
      BufferedWriter out = null; 
      File f = null;
      try {
         f = new File(inFile);
         in = new BufferedReader(new FileReader(f));
         f = new File("t"+getName()+"_out.txt");
         out = new BufferedWriter(new FileWriter(f));
         
         String line;
         int lineNum = 0;
         while ((line = in.readLine()) != null)
         {
            out.write("Thread[" + getName()+ "]: Line["+ (lineNum+1) + "]: " + line);
            out.newLine();
            lineNum++;
         }
         
         try {
            in.close();
            out.close();
         }
         catch (IOException e) { e.printStackTrace(); }
      }
      catch (FileNotFoundException ex) {
         System.out.println("File " + inFile + " was not found.");
      }
      catch (IOException e) {
         System.out.println("Error: Could not open " + f.getName() + ".");
      }
                
		System.out.println("MyThread[" + getName() + "]: END!");
	}
	 
}
