import java.lang.*;

public class OS_SimpleThread extends Thread 
{
   public OS_SimpleThread(String str) 
   {
      super(str);
   }

   public void run() 
   {
      for (int i = 0; i < 5; i++) 
      {
         System.out.println("Thread[" + getName() + "]: count = " + i);
         try 
         {
            sleep((long)(Math.random() * 1000));
         } 
         catch (InterruptedException e) {}
      }
      System.out.println("Thread[" + getName() + "]: DONE!");
   }
}
