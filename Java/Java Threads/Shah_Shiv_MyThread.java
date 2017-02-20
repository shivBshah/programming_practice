/**
   @author: Shiv Shah
   Assignment 1: Java Unsynchronized Threads File Manipulation
   Description: This class extends Java Thread class to execute defined logic for each thread.
                It takes a input file and a output file, and reads and copies contents from 
                input file into output file, while handling File exceptions.
*/

import java.io.*;
import java.util.*;

public class Shah_Shiv_MyThread extends Thread {
  
   private String inFile; //input file
   private String outFile; //output file
   
   /**
      This constructor method intializes input and output files.
      @param str Name of the thread
      @param inFile Name of the input file
      @param outFile Name of the output file
   */
	public Shah_Shiv_MyThread(String str, String inFile, String outFile) {
		super(str);
      this.inFile = inFile;
      this.outFile = outFile;
	}
	
   /**
      This method reads the input file and write its contents to another file.
   */
	public void run () 
	{
		System.out.println("MyThread[" + getName() + "]: BEGIN!");
      //Defining file reader and writer
      BufferedReader in = null;
      BufferedWriter out = null; 
      File f = null;
      try {
         //initializing file reader and writer
         f = new File(inFile);
         in = new BufferedReader(new FileReader(f));
         f = new File(outFile);
         out = new BufferedWriter(new FileWriter(f));
         
         String line;
         int lineNum = 0;
         while ((line = in.readLine()) != null) //while all the lines are not read
         {                                     //read each line and write them to output file
            out.write("Thread[" + getName()+ "]: Line["+ (lineNum+1) + "]: " + line);
            out.newLine();
            lineNum++;
         }
         //close the file reader and writer
         try {
            in.close();
            out.close();
         }
         catch (IOException e) { e.printStackTrace(); }
      }
      catch (FileNotFoundException ex) { 
         System.out.println("ERROR: File " + inFile + " was not found.");
      }
      catch (IOException e) {
         System.out.println("ERROR: Could not open " + f.getName() + ".");
      }
                
		System.out.println("MyThread[" + getName() + "]: END!");
	}
	 
}
