/**
   @author Shiv Shah
   Date: 3/23/2017
*/
import java.util.*;
import java.io.*;

public class CPU_Sched{
   
   public static void main(String[] args){
    
      final String[] inputFileNames = {"fcfs_in4.txt","sjnnp_in4.txt", "pnp_in4.txt"};  
      final String[] outputFileNames = {"fcfs_out4.txt","sjnnp_out4.txt", "pnp_out4.txt"};   
      
      for (int i = 0; i < inputFileNames.length; ++i){
         File file = null;
         try {
            file = new File(inputFileNames[i]);
            BufferedReader bfr = new BufferedReader(new FileReader(file));
            
            String line = null;
            line = bfr.readLine();
            String algorithm = line.split(" ")[1];
            
            List<Process> procQueue = new ArrayList<Process>();
            while ((line = bfr.readLine()) != null) {
                  String[] arr = line.split(" ");
                  Process p;
                  if (arr.length == 5)
                     p = new Process(Integer.parseInt(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4]));
                  else
                     p = new Process(Integer.parseInt(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]));
                  procQueue.add(p);          
               }
               String output = schedule(procQueue, algorithm); 
               writeToFile(outputFileNames[i],output);                            
         }
         catch (FileNotFoundException ex){
            System.out.println("ERROR: File " + file.getName() + " was not found.");
         }
         catch (Exception e){
            System.out.println("ERROR: Could not open "+ e);
         }
                  
      } 
   }
   
   private static String schedule(List<Process> procQueue, String algorithm){
      Queue<Process> exitProcs = new LinkedList<Process>();
      String newline = System.getProperty("line.separator");
      
      String output = "CPU sheduling algorithm: fcfs"+newline;
      output += "Total number of CPU requests: " + procQueue.size() + newline;
      output += "---------------------------------------------------------"+newline;
      int clock = 0;
      while(!procQueue.isEmpty()){
         output += "Clock: "+clock +newline;
         output += "Pending CPU request(s):"+newline;
           
         List<Process> pendingJobs = new ArrayList<Process>();
                  
         for(Process p: procQueue){
            if (p.enterTime() <= clock){
               pendingJobs.add(p);
               String request = "";
               request = p.id() + " " + p.enterTime() + " " + p.bursts();
               if (algorithm.equals("pnp"))
                  request += " " + p.priority();
               output += request + newline;
                  
            }
         }
         
         Process nextP;
         if (algorithm.equals("fcfs"))
            nextP = next_fcfs_job(pendingJobs);
         else if (algorithm.equals("sjnnp"))
             nextP = next_sjnnp_job(pendingJobs);
         else
            nextP = next_pnp_job(pendingJobs);
            
         for( ; clock<nextP.enterTime();++clock)
            output += "Clock: "+clock + newline;
            
         output += newline+ "CPU Request serviced during this clock interval: " +nextP.id() + " " + nextP.enterTime() + " " + nextP.bursts();
         if (algorithm.equals("pnp"))
            output += " " + nextP.priority();
         output += newline + "---------------------------------------------------------" + newline;
         clock += nextP.bursts(); 
         nextP.setExitTime(clock);
         exitProcs.add(nextP);
         procQueue.remove(nextP);
      }
      output += "Turn-Around Time Computations" + newline + newline;
      String temp = "";
        
      int[] tats = new int[exitProcs.size()];
      int[] wts = new int[exitProcs.size()];
      int i = 0;
      for(Process p: exitProcs){
         int tat = (p.exitTime() - p.enterTime());
         tats[i] =  tat;
         output += "TAT("+p.id()+") = " + tat +newline;
            
         int wt = tat - p.bursts();
         temp += "WT("+p.id()+") = " + wt + newline;
         wts[i] = wt;
         i++;
      }
        
      output += newline+"Average TAT = "+calcAvg(tats);
      temp += newline+"Average WT = "+calcAvg(wts);
      output += newline+"---------------------------------------------------------"+newline;
      output += "Wait Time Computations"+newline+newline+temp+newline;
      return output;
   
   }
   
   private static Process next_fcfs_job(List<Process> pendingJobs){
         Process nextP = pendingJobs.get(0);
         for (Process p: pendingJobs)
            if (p.enterTime() < nextP.enterTime() || (p.enterTime() == nextP.enterTime() && p.id() < nextP.id()))
               nextP = p;
         return nextP;
   }

   private static Process next_sjnnp_job(List<Process> pendingJobs){  
         Process nextP = pendingJobs.get(0);
         for (Process p: pendingJobs){
            if (p.bursts() < nextP.bursts())
                  nextP = p;
            else if (p.bursts() == nextP.bursts())
                  if (p.enterTime() < nextP.enterTime() || (p.enterTime() == nextP.enterTime() && p.id() < nextP.id()))
                       nextP = p;
                               
          }   
          return nextP;                                            
   }
   
   private static Process next_pnp_job(List<Process> pendingJobs){
         Process nextP = pendingJobs.get(0);
         for (Process p: pendingJobs){
            if (p.priority() < nextP.priority())
                  nextP = p;
            else if (p.priority() == nextP.priority())
                  if (p.enterTime() < nextP.enterTime() || (p.enterTime() == nextP.enterTime() && p.id() < nextP.id()))
                       nextP = p;
                               
         } 
         return nextP;
   }
      
   private static void writeToFile(String outputFileName, String output){
      File file = null;
      try {
         file = new File(outputFileName);
         BufferedWriter bfw = new BufferedWriter(new FileWriter(file));
         bfw.write(output);
         bfw.close();
      }
      catch (FileNotFoundException ex){
         System.out.println("ERROR: File " + file.getName() + " was not found.");
      }
      catch (Exception e){
         System.out.println("ERROR: Could not open "+ file.getName());
      }      
         
   }
   
   private static double calcAvg(int[] arr){
      int sum = 0;
      for(int i = 0; i < arr.length; i++){
         sum += arr[i];
      }
      return (double)sum/arr.length;
   }
}

class Process{
   private int id;
   private int enterTime;
   private int exitTime;
   private int bursts;
   private int priority;
   
   public Process(int id, int timestamp, int bursts){
      this.id = id;
      this.enterTime = timestamp;
      this.bursts = bursts;
   }
   
   public Process(int id, int timestamp, int bursts, int priority){
      this.id = id;
      this.enterTime = timestamp;
      this.bursts = bursts;
      this.priority = priority;
   }
   
   public void setExitTime(int exitTime){
      this.exitTime = exitTime;
   }
   
   public int id(){
      return id;
   }
   
   public int enterTime(){
      return enterTime;
   }

   public int exitTime(){
      return exitTime;
   }

   public int bursts(){
      return bursts;
   }
      
   public int priority(){
      return priority;
   }
   
   public boolean equals(Process p) {
      return p.id == this.id;
   }
}