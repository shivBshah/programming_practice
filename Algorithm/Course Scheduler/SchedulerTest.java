public class SchedulerTest
{
   public static void main (String[] args)
   {
     CourseScheduler schedule1 = new CourseScheduler("CBAClasses10.txt");
     
      CourseScheduler schedule2 = new CourseScheduler("CBAClasses20.txt");
      
      System.out.print("Schedule 1 requires " + schedule1.minSlots() + " slots:");
      System.out.println(schedule1.getSchedule());
      
      CourseScheduler schedule3 = new CourseScheduler("CBAClasses10b.txt");
      System.out.print("Schedule 1 requires " + schedule3.minSlots() + " slots:");
      System.out.println(schedule3.getSchedule());
      
      System.out.print("\nSchedule 2 requires " + schedule2.minSlots() + " slots:");
       System.out.println(schedule2.getSchedule());
    }
}
