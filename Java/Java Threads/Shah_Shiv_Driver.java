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
         if (scan.next().toUpperCase().equals("F")) //inFiles = scan.nextLine().split(" ");
            for (int i = 0; i < numThreads; ++i)
               inFiles[i] = scan.next();  
         scan.close();
         
         String[] outFiles = new String[numThreads];
         Thread[] threads = new Thread[numThreads];
         for (int i = 0; i < numThreads; ++i)
         {
            String outFile = "t"+i+"_out.txt";
            Thread t = new Shah_Shiv_MyThread(""+i, inFiles[i]);
            threads[i] = t;
            outFiles[i] = outFile;
            t.start();
         }
         
         await_execution(threads);
         read_write(outFiles,"out.txt");           
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
   
   private static void read_write(String[] readFiles, String writeFile){
      
      BufferedReader reader = null;
      BufferedWriter writer = null;
      
      for (int i = 0; i < readFiles.length; ++i) 
      {
         try {
            reader = new BufferedReader(new FileReader(readFiles[i]));
            writer = new BufferedWriter(new FileWriter(writeFile, true));
            
            String line = null;
            while ((line = reader.readLine()) != null) {
               writer.write(line);
               writer.newLine();
            }
         }
         catch (FileNotFoundException ex) {
            System.out.println("ERROR: File " + readFiles[i] + " was not found.");
         }
         catch (IOException e) {
            System.out.println("ERROR: Could not open " + writeFile);
         }
         finally {
            try {
               if (reader!=null) reader.close(); 
               if (writer!=null) writer.close();
            }
            catch (IOException e) {
               e.printStackTrace();
            } 
         }  
      }
   }
    
}