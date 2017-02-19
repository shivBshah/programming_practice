import java.lang.*;

public class OS_NThreadsTest 
{
   public static void main (String[] args) 
   {
      int n;
      for (n=0; n<3; n++)
      {
         new OS_SimpleThread("" + n).start();
      }
   }
}
