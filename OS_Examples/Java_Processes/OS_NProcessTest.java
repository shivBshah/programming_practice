import java.io.*;
import java.lang.*;

public class OS_NProcessTest 
{
   public static void main (String[] args) throws IOException
   {
      int n;
      for (n=0; n<3; n++)
      {
         //new ProcessBuilder("/usr/bin/java", "OS_SimpleProcess_STDOUT").start();
         new ProcessBuilder("/usr/bin/java", "OS_SimpleProcess_FILEIO").start();
      }
   }
}
