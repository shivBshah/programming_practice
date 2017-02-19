import java.lang.*;
import java.io.*;

public class OS_SimpleProcess_STDOUT
{
   public static void main(String argv[]) throws IOException
   {
      byte[] bo = new byte[100];
      String[] cmd = {"bash", "-c", "echo $PPID"};
      Process p = Runtime.getRuntime().exec(cmd);
      p.getInputStream().read(bo);
      System.out.println("Process[" + new String(bo).replace("\n","") + "]");
   }
}
