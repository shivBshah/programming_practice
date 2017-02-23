/**
  @author Shiv Bhushan Shah
  4/20/2016
  Class Description: This class assists in the task of scheduling courses in a way that does not cause conflicts. 
                     Given the list of courses, it compares them against given conditions to determine which courses should 
                     not be put together in the same time slot, creating an adjacency matrix. Next, it uses that matrix to
                     deduce the minimun number of times required to schedule courses, and in effect stores the time slot #
                     for each courses in an array. This array is then used to create and return the final schedule of given courses. 
*/

import java.util.*;
import java.io.*;

public class CourseScheduler {

   private int minSlots;          //minimum no of time slots required to schedule courses without conflict
   private Course[] courses;      //array to store the course object
   private int[] times;           //array to store time slot for each course
   private boolean[][] adjMatrix; //2D array to store the adjacent relation between any two courses
	
   /**
      The constructor method to read and store information from given file, and
      appropriately initialize instance variables 
      @param filename the name of the file containing list of courses
   */
   
   public CourseScheduler(String filename) 
   {  
    // int numCourse = 0;      
     
     try {
         Scanner in = new Scanner(new File(filename));
         int numCourse = in.nextInt();
         in.nextLine();
         courses = new Course[numCourse];
         times = new int[numCourse];
         adjMatrix = new boolean[numCourse][numCourse];
         
         while(numCourse >= 1)
         { 
            String line = in.nextLine();
            Scanner scan = new Scanner(line);
            scan.useDelimiter(",");
          
            String dept =  scan.next();
            String level = scan.next();
            String building = scan.next();
            String room = scan.next();
            String instructor = scan.next();          
          
            Course nextCourse = new Course(dept, level, building, room, instructor); 
            courses[courses.length - numCourse] = nextCourse;
            numCourse--;
         }
  
          fillAdjMatrix(); //call to fill adjacency matrix 
      }
      catch (FileNotFoundException ex)
      {
         System.out.println("ERROR: FILE NOT FOUND!!!");
      }
      
   }
   
   /**
      This method uses given conditions to build boolean matrix that stores
      whether any two courses are linked or in conflict.
   */
   
   private void fillAdjMatrix()
   {
      for (int i = 0; i < adjMatrix.length; ++i)
      {
         Course c1 = courses[i];
         for (int j = 0; j < adjMatrix.length; ++j)
         {
            Course c2 = courses[j];
            adjMatrix[i][j] = true;
            
            boolean sameInstructor = c1.getInstructor().equals(c2.getInstructor());
            boolean sameClassroom = (c1.getBuilding() + c1.getRoom()).equals(c2.getBuilding() + c2.getRoom());
            boolean sameDiscipline = false;
            
            if(c1.getDept().equals(c2.getDept()))
            {
               if (c1.getLevel().charAt(0) == '3' || c1.getLevel().charAt(0) == '4' && 
                    c2.getLevel().charAt(0) == '3' || c2.getLevel().charAt(0) == '4' )
               {
                  sameDiscipline = true;  
               }               
            }
            
            if(!sameInstructor && !sameClassroom && !sameDiscipline)
               adjMatrix[i][j] = false;
         }
      }
   }
	
   /**
      This method helps to determine the minimum no. of time slots required to 
      schedule courses without causing conflicts
      @return minSlots - the value containing the minimum no. of slots
   */
   
   public int minSlots()
   {
      minSlots = 0;
      boolean done = false;
      times[0] = 1;
      
      while(!done)             
      {
         minSlots++;            //starting from 1, try the value for minimum number of slots,
         done = timeSlots(0);   //in increasing order,
      }                         //and see which value gives successfully does the job
      return minSlots;          //return the value that does the job
   }
	
   /**
      This recursive method, using minimum no. of slots, helps in finding 
      one possible way of assigning timeslots to courses.
      @param i the value indicating the first course to start with
      @return stop - boolean value that indicates whether all courses have been
                     successfully assigned time slots
   */
   
   private boolean timeSlots(int i)
   {
      boolean stop = false;
      int time;
      int n = courses.length-1;
      if(promising(i))
      {
         if(i == n)      //stop when the solution is found   
         {               //do not look for other possible solutions
            stop = true;
         }
         else		
         {
            for(time = 1; time <= minSlots; time++)  //Try every timeslot
            {                                        //for next course
               if (!stop)                            //as long as the solution is not found 
               {                              
                  times[i+1] = time;
                  stop = timeSlots(i+1);
               }
            }
         }
      }
      return stop;
   }  
	
   /**
      This method ensures that time slot assigned to any course is promising and not in conflict
      @param i the course number to be checked
      @return s - true if the timeslot assignment for given course is promising, false otherwise
   */
   
   private boolean promising(int i)
   {
      int j = 0;
      boolean s = true; 
      while(j < i && s) {                              //check if an
         if(adjMatrix[i][j] && times[i] == times[j])   //adjacent course in the graph
            s = false;                                 //already has this timeslot
         j++;
      }
      return s;
   }
   
   /**
      This method builds the course schedule utilizing the information what course has what time slot#
      @return schedule - the string containing the sorted list of courses for each time slot 
   */
   
   public String getSchedule()
   {
      minSlots();
      String schedule = "";
      
      for(int i = 1; i <= minSlots; ++i)
      {
         List<Course> cList = new ArrayList<Course>(); //create course list for every timeslot
         schedule += "\nTime " + i + ":\n";
         for(int j = 0; j < times.length; ++j)
         {
            if(times[j] == i)   
            {
               cList.add(courses[j]); 
            } 
         }
        
         Collections.sort(cList); //sort course list for every timeslot
         for(Course c: cList)   
         {
            schedule += "\t" + c.getDept() + " " + c.getLevel() + " " +
                       c.getBuilding() + " " + c.getRoom() + " " + c.getInstructor() + "\n";
         } 
       
      }
      return schedule;
   }
  
}

