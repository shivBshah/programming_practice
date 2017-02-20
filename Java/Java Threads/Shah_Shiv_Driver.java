import java.io.*;
import java.util.*;

public class Shah_Shiv_Driver {
	
   public static void main (String[] args)   
   {
      System.out.println("Driver: BEGIN!");
      Scanner scan = null;
      File file = null;
      try {
         file = new File("in.txt");
         scan = new Scanner(file);
      
         int numThreads = 0;
         if (scan.next().toUpperCase().equals("T"))
            numThreads = scan.nextInt();
         
         String[] inFiles = new String[numThreads];
         if (scan.next().toUpperCase().equals("F"))
            for (int i = 0; i < numThreads; ++i)
               inFiles[i] = scan.next();  
         scan.close();
         
         String[] outFiles = new String[numThreads];
         Thread[] threads = new Thread[numThreads];
         for (int i = 0; i < numThreads; ++i)
         {
            String outFile = "t"+i+"_out.txt";
            Thread t = new Shah_Shiv_MyThread(""+i, inFiles[i], outFile);
            threads[i] = t;
            outFiles[i] = outFile;
            t.start();
         }
         
         await_execution(threads);
         merge(outFiles,"out.txt");           
      }
      catch (FileNotFoundException ex){
         System.out.println("ERROR: File " + file.getName() + " was not found.");
      }
      catch (Exception e){
         System.out.println("ERROR: Could not open "+ file.getName());
      }      
      	
      System.out.println("Driver: END!");
   }
	
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
   
   private static void merge(String[] inFiles, String mergedFile){
      
      BufferedReader[] readers = new BufferedReader[inFiles.length];
      for (int i = 0; i < inFiles.length; ++i) 
      {
         try {
            BufferedReader bfr = new BufferedReader(new FileReader(inFiles[i]));
            readers[i] = bfr;  
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
      while (!stop) {    
         int emptyFile = 0;
         for (int i = 0; i < readers.length; ++i)
         {
            try {
               String line = readers[i].readLine();
               if (line == null) 
               {
                  emptyFile++;
                  line = "";
               }
               else {
                  sb.append(line);
                  sb.append(System.getProperty("line.separator"));
               }
            }
            catch (IOException ex) {
               System.out.println("Error reading line.");
            }
         }
         if (emptyFile == inFiles.length)
            stop = true;
      }
      
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