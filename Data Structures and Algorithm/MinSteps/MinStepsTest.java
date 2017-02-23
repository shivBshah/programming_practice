public class MinStepsTest
{
   public static void main(String[] args) 
   {
      MinSteps game1 = new MinSteps(1);
      MinSteps game2 = new MinSteps(4);
      MinSteps game3 = new MinSteps(7);
      MinSteps game4 = new MinSteps(59);
   	
      System.out.println("When the game starts with 1 token ...");
      System.out.println("\tThe recursive solution involves " + game1.recSolution() + " step(s)");
      System.out.println("\tThe dynamic programming solution involves " + game1.dpSolution() + " step(s)");
      System.out.println("\tThe moves required are " + game1.getMoves());
      
      System.out.println("\nWhen the game starts with 4 tokens ...");
      System.out.println("\tThe recursive solution involves " + game2.recSolution() + " step(s)");
      System.out.println("\tThe dynamic programming solution involves " + game2.dpSolution() + " step(s)");
      System.out.println("\tThe moves required are " + game2.getMoves());
      
      System.out.println("\nWhen the game starts with 7 tokens ...");
      System.out.println("\tThe recursive solution involves " + game3.recSolution() + " step(s)");
      System.out.println("\tThe dynamic programming solution involves " + game3.dpSolution() + " step(s)");
      System.out.println("\tThe moves required are " + game3.getMoves());
      
      System.out.println("\nWhen the game starts with 59 tokens ...");
      System.out.println("\tThe recursive solution involves " + game4.recSolution() + " step(s)");
      System.out.println("\tThe dynamic programming solution involves " + game4.dpSolution() + " step(s)");
      System.out.println("\tThe moves required are " + game4.getMoves());
      
      MinSteps game5 = new MinSteps(10);
      System.out.println("\nWhen the game starts with 10 tokens ...");
      System.out.println("\tThe recursive solution involves " + game5.recSolution() + " step(s)");
      System.out.println("\tThe dynamic programming solution involves " + game5.dpSolution() + " step(s)");
      System.out.println("\tThe moves required are " + game5.getMoves());
      
      String[] str = {"a", "b"};
     // MinSteps.main(str);

      /*
        int steps = dpSolution();
        int i = tokens;
        while (steps > 0)
        {
            movesStr += " --> " + moves[i]; 
            i = moves[i]; 
            steps--;
        }
     */
   }
}