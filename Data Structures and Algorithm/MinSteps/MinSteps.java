/**
    MinSteps.java
    @author Shiv Bhushan Shah
    Description: Given the input for the number of tokens, this program calulates the minimum number of steps required to reduce 
                 the number of tokens in pile to 1, using given rules. The program uses recursive as well as dynamic programming 
                 approach to calculate the minimum number of steps. It also maintains an array parallel to steps array to record 
                 and build the next move for each token. It also keeps count for the number of recursive calls in recursive solution
                 and loop iterations in dynamic programming solution.               
*/

import java.lang.Math;

public class MinSteps {

   private int tokens;
   protected int countRec; //variable to count recursive calls
   protected int countDp; //variable to count number of loop iterations in dynamic programming
   private int[] moves;  //interger array to store next step for each token number
   private int[] steps; //integer array to store minimum steps for each token number
  
  /**
     The constructor to intialize the number of tokens and other instance variables
     @param n - intial number of tokens in pile
  */
  
   public MinSteps(int n)
   {
      tokens = n;
      countRec = 0;
      countDp = 0;
      moves = new int[tokens+1];
      steps = new int[tokens+1];
   }
  
  /**
    This method calculates the minimum number of steps recursively using a private recursive method.
    @return minimum number of steps   
  */
  
   public int recSolution()
   {
      return steps(tokens); //call to recursive method
   }
  
  /**
     This method computes the minimum number of steps recursively for given tokens
     @param n number of tokens in pile
     @return minimum number of steps required for given tokens
  */
  
   private int steps(int n)
   {  
      countRec++;
      if (n == 1)  //no step for a pile of 1 token
         return 0;
      else 
      {  
         //check every applicable rule and compare the steps for each of them to determine the minimum number of steps
         if (n%3 == 0 && n%2 == 0)  
            return 1 + Math.min(steps(n-1), Math.min(steps(n/2), steps(n/3)));
         
         else if (n%2 == 0)
            return 1 + Math.min(steps(n-1), steps(n/2));
         
         else if (n%3 == 0)
            return 1 + Math.min(steps(n-1), steps(n/3));
         
         else
            return 1 + steps(n-1);           
      }      
   }
  
  /**
     This method computes the minimum number of steps using dynamic programming
     @return minimum number of steps for given tokens 
  */
  
   public int dpSolution()
   {    
      steps[0] = 0;
      moves[0] = 0;
      for (int i = 1; i < steps.length; ++i)
      {
         countDp++;
         if (i == 1) {  
            steps[i] = 0;
            moves[i] = 0;
         }
         else 
         {     
            int nextMove;   //stores the next move that leads to minimum number of steps
            if (i%3 == 0 && i%2 == 0)
               nextMove = min(i-1, min(i/2, i/3));
             
            else if (i%2 == 0) 
               nextMove = min(i-1, i/2);
            
            else if (i%3 == 0)
               nextMove = min(i-1, i/3);
            else
               nextMove = i-1;
         
            moves[i] = nextMove; //for the current token number, store the next move, and
            steps[i] = 1 + steps[nextMove]; //also store the minimum number of steps achieved by that move
         } 
      }
      return steps[tokens];
   }
  
  /**
    This method compares and returns the token value that has minimum steps
    @param x the first token number to be compared
    @param y the second token number to be compared
    @return the token number that leads to minimum number of steps
  */
  
   private int min(int x, int y)
   {
      if (steps[x] < steps[y])
         return x;
      else 
         return y;
   }

  /**
    This method uses moves array to build the sequence of moves 
    @return movesStr - the string containing the sequence of moves
  */
  
   public String getMoves()
   {
      dpSolution();
      String movesStr = "" + tokens;
      int index = tokens;
      while(index > 1)
      {
         movesStr += " --> " + moves[index];
         index = moves[index];
      }
      return movesStr;
   }
  
  /**
    This method returns the number of recursive calls performed to find minimum steps 
    @return countRec - number of recursive calls 
  */
  
   public int getCountRec() {
      return countRec;
   }
  
  /**
    This method returns the number of loop iteration performed 
    to find minimum steps by dynamic programming
    @return countDp - number of loop iterations for dynamic programming
  */
  
   public int getCountDp() {
      return countDp;
   }
   
}

/**
  This class serves to check the efficiency of the recursive solution and dynamic programming solution implemented in MinSteps class.
  It feeds different values of tokens to both solutions and observes the number of recursive calls made during for recursive solution 
  and loop iterations during dynamic programming.   
*/

class Tester {

      public static void main(String[] args)
      {
         MinSteps game = null;
         int i = 2; 
         int n = 1;
         System.out.println("Token Size\tNo. of Recursive Calls\tNo. of iterations");
         while (n < 10)
         {
            game = new MinSteps(i);
            game.recSolution();
            System.out.print(i + "\t\t" + game.getCountRec());
            game.dpSolution();
            System.out.println("\t\t\t" + game.getCountDp());
            i = i * 2;
            n++;
         }    
      }
   }


