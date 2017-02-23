public class Course implements Comparable<Course> {

   private String dept;
   private String level;
   private String building;
   private String room;
   private String instructor;
   
   public Course(String d, String l, String b, String r, String i)
   {
     dept = d;
	  level = l;
	  building = b;
	  room = r;
	  instructor = i;
   }
   
   public String getDept()
   {
       return dept;
   }
   
   public String getLevel()
   {
       return level;
   }
   
   public String getBuilding()
   {
       return building;
   }
   
   public String getRoom()
   {
       return room;
   }
   
   public String getInstructor()
   {
       return instructor;
   }
   
   public int compareTo(Course c)
   {       
       
       String s1 = this.getDept() + this.getLevel();   
       String s2 = c.getDept() + c.getLevel();        
       
       return s1.compareTo(s2);   //compare two courses by department and number 
       
   }
}