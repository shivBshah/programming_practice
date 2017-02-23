/**
 * @author Shiv Shah
 * Description: The purpose of this class is to generate a report for each employee that lists 
 *              the project name and number of hours for the projects they are working on, along 
 *              with the total works they are working. To generate the report, it establishes connection
 *              to the mysql database and runs query, retrieve results and manipulate them using 
 *              Employee class to create the report in desired format.
 * */

import java.sql.*;
import java.util.*;

public class ReportGenerator
{
   public static void main(String args[]) 
   {
      Connection con = null;
      Statement stmt1;
      Statement stmt2;
      Employee emp;
   
      try
      {
        //establish driver and connection to the mysql database
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         con = DriverManager.getConnection("jdbc:mysql:///COMPANY", "student", "simple16"); 
                                                                         
         if(!con.isClosed())
         {  
         //statements to execute the queries 
            stmt1 = con.createStatement();
            stmt2 = con.createStatement();
         //query string to get the employees ssn and their name in asecending order by name
            String empQuery = "SELECT Ssn, CONCAT(Fname, \" \", Lname) AS Name FROM EMPLOYEE ORDER BY Name";
            ResultSet employees = stmt1.executeQuery(empQuery);
         
         //iterate through each tuple or each ssn in the result set 
         //and get projects and hours worked for each employee
         //and print them using Employee object
            while(employees.next())
            {  
               String ssn = employees.getString("Ssn");
               String name = employees.getString("Name");
            //query string to get the project details of the current employee
               String projectQuery = "SELECT Pname, Hours FROM PROJECT JOIN WORKS_ON ON Pno=Pnumber WHERE"+
                               " Essn = " + ssn;
               ResultSet projectSet = stmt2.executeQuery(projectQuery);
            
            //arraylist to store all projects and num of hours worked on them
               ArrayList<String> projects = new ArrayList<String>();
               ArrayList<Double> hours = new ArrayList<Double>();
            
            //store project names and corresponding hours in arraylist,
            //record 0 is employees have not worked on any project
               while (projectSet.next())
               {
                  projects.add(projectSet.getString("Pname"));
                  String hour = projectSet.getString("Hours");
                  if (hour == null)
                     hours.add(0.0);
                  else
                     hours.add(Double.parseDouble(hour));
               }
               emp = new Employee(ssn,name,projects,hours); 
               System.out.println(emp.toString()+"\n");
            } 
            
         } 
      } 
      catch(Exception e) 
      {
         System.err.println("Exception: " + e.getMessage());
      } 
      finally
      {  //close the connection after the report is generated
         try
         {
            if(con != null)
               con.close();
         } 
         catch(SQLException e) {}
      }
   }   
}