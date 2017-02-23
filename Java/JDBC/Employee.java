/**
 * @author Shiv Shah
 * Description: This class acts as an object for Employees and used by ReportGenerator.java to save the employee
 *              information. It save the ss, name, the projects they are working on along with the number of hours.
 *              It also returns the formatted string that represents the Employee.
 *             
 * */

import java.util.*;
public class Employee {
   private String ssn;
   private String name;
   private ArrayList<String> projects;
   private  ArrayList<Double> hours;
   private double totalHours;
   
   /**
    * Constructor to initialize the Employee object with initial value
    * @param ssn  the ssn of the employee
    * @param name name of the employee
    * @param projects list of projects he/she are working 
    * @param hours list of hours for the projects he/she are working
    * */
   public Employee(String ssn, String name, ArrayList<String> projects, ArrayList<Double> hours)
   {
      this.ssn = ssn;
      this.name= name;
      this.projects = projects;
      this.hours = hours;
   }
   
   /**
    * The method returns the total number of hours an employee is working
    * @return sum - the sum of the hours from all projects
    * */
   public double totalHours(){
      double sum = 0;
      for(int i =0; i < hours.size(); i++)
      {
         sum += hours.get(i);
      }
      return sum;
   }
   
   /**
    * This method returns the string representation of the objects, formatted as per the 
    * requirement.
    * @return result - the formatted string consisting of the employee name
    *                  the list of the projects they work on, hours for each project,
    *                  and total hours they are working.
    * */
   public String toString() {
      String result = "";
      result += name + "\n";
      
      String projectStr = "";
      //build formatted string for each projects.
      for(int i = 0; i < projects.size(); ++i)
      {
         projectStr += String.format("     %-20s\t%4.1f\n",projects.get(i),hours.get(i));
      }
      
      double totalHours = totalHours();
      String dashes = "";
      //build string for dashes so that it same size as the total hours
      for (int i = 0; i < Double.toString(totalHours).length();++i)
      {
         dashes += "-";
      }
      result += projectStr + String.format("\t\t\t\t%4s\n\t\t\t\t%4.1f",dashes,totalHours);
      return result;
      
   }
}