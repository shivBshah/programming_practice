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
      try {
         in = new BufferedReader(new FileReader(inFile));
         out = new BufferedWriter(new FileWriter("t"+getName()+"_out.txt"));
         
         String line;
         int lineNum = 0;
         while ((line = in.readLine()) != null)
         {
            out.write("Thread[" + getName()+ "]: Line["+ lineNum + "]: " + line);
            out.newLine();
            lineNum++;
         }
      }
      catch (FileNotFoundException ex) {
         System.out.println("File " + inFile + " was not found.");
      }
      catch (IOException e) {
         System.out.println("Error Opening File");
      }
      finally {
        try {
           if (in!=null)in.close();
           if (out!=null)out.close();
        }
        catch (IOException e) {
           e.printStackTrace();
        }
      }
            
		System.out.println("MyThread[" + getName() + "]: END!");
	}
	 
}
