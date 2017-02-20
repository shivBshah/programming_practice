import java.util.*;
import java.io.*;

public class FileMerger {

   public static void main(String[] args) {
   
      String[] inFiles = {"t0_out.txt","t1_out.txt","t2_out.txt", "t3_out.txt"};
      String mergedFile = "out.txt";
      
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
      
      boolean stop = false;
      StringBuilder sb = new StringBuilder();      
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

      System.out.println(sb.toString());
      
   }  
}