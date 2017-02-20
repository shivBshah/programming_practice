/**
  @author: Shiv Shah
  Assignment 1: Java Unsynchronized Threads File Manipulation
  Description: This class spawns threads for copying data from input files to output files, and then combines 
               all those output files into one. It does so by reading an initial input file, which contains the information
               about the number of threads and other input files, and then creates thread for reading those input files
               and write to corresponding out files, which are then combined at the end.
*/

import java.io.*;
import java.util.*;

public class Shah_Shiv_Driver {
	
   public static void main (String[] args)   
   {
      System.out.println("Driver: BEGIN!");
      Scanner scan = null;
      File file = null;
      try {
         file = new File("in.txt"); //initial file to be read
         scan = new Scanner(file);
      
         int numThreads = 0; 
         if (scan.next().toUpperCase().equals("T"))
            numThreads = scan.nextInt(); //number of threads
         
         String[] inFiles = new String[numThreads]; //list of input files
         if (scan.next().toUpperCase().equals("F"))
            for (int i = 0; i < numThreads; ++i)
               inFiles[i] = scan.next();  
         scan.close();
         
         String[] outFiles = new String[numThreads];
         Thread[] threads = new Thread[numThreads];
         for (int i = 0; i < numThreads; ++i) //create thread for each file                                        
         {                                   // and pass input and output file
            String outFile = "t"+i+"_out.txt";//name of the output file
            Thread t = new Shah_Shiv_MyThread(""+i, inFiles[i], outFile); //create thread
            threads[i] = t;                                               
            outFiles[i] = outFile;
            t.start();  //start the thread
         }
         
         await_execution(threads); //after all threads have finished their execution
         merge(outFiles,"out.txt"); //take the output files and combine their contents into target file 
               
      }//handling file exceptions
      catch (FileNotFoundException ex){ 
         System.out.println("ERROR: File " + file.getName() + " was not found.");
      }
      catch (Exception e){
         System.out.println("ERROR: Could not open "+ file.getName());
      }      
      	
      System.out.println("Driver: END!"); //end of the driver program
   }
	
   /**
      This method takes threads and wait for them to finish execution
      @param threads list of threads to wait for
   */
   private static void await_execution(Thread[] threads) {
      for (Thread t: threads)
      {
         try {
            t.join();
         }
         catch (InterruptedException ie) { 
            ie.printStackTrace();
         }
      }
   }
   
   /**
      This method takes input files and copy their contents into a single specified file
      @param inFiles list of files containing the contents to be copied
      @param mergedFile file to copy contents to
   */
   private static void merge(String[] inFiles, String mergedFile){
      
      List<BufferedReader> readers = new ArrayList<BufferedReader>();
      //create list of Readers for all files 
      for (int i = 0; i < inFiles.length; ++i)  
      {
         try {
            BufferedReader bfr = new BufferedReader(new FileReader(inFiles[i]));
            readers.add(bfr);  
         }
         catch (FileNotFoundException ex){  
            System.out.println("ERROR: File " + inFiles[i] + " was not found.");
         }
         catch (Exception ex){  
            System.out.println("ERROR: Could not open " + inFiles[i] + ".");
         }
      }
      
      StringBuilder sb = new StringBuilder();  
      boolean stop = false;
       
      //read lines from each file simultaneously 
      //and store them in string
      //until there are no more lines in any file    
      while (!stop) {    
         int emptyFile = 0; //number of files whose all lines have been read
         for (BufferedReader reader: readers)
         {
            try { //read one line from all files
               String line = reader.readLine(); 
               if (line == null) 
                  emptyFile++; 
               else {
                  sb.append(line);
                  sb.append(System.getProperty("line.separator"));
               }
            }
            catch (IOException ex) {
               System.out.println("Error reading line.");
            }
         }
         //stop when all lines from all files have been read
         if (emptyFile == readers.size()) 
            stop = true;
      }
      
      //write the lines read from input files into destination file
      try {
         BufferedWriter writer = new BufferedWriter(new FileWriter(mergedFile));
         writer.write(sb.toString());         
         try {
            writer.close();
         } catch(IOException ie) { }
      }
      catch (IOException ex) {
         System.out.println("ERROR: Could not open " + mergedFile + ".");
      }

   }
    
}