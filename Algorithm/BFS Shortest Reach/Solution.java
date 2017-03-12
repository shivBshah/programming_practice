import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int lines = in.nextInt();
        for(int i = 1; i <= lines; ++i)
        {
            int numNodes = in.nextInt();
            int numEdges = in.nextInt();
            
            Map<Integer, Set<Integer>> adjMap = new HashMap<>();
            for(int j = 1; j <= numEdges; ++j)
            {   
                int v1 = in.nextInt();
                int v2 = in.nextInt();
                if(!adjMap.containsKey(v1))
                {
                    Set<Integer> s = new HashSet<>();
                    adjMap.put(v1, s);
                }
                
                if(!adjMap.containsKey(v2))
                {
                    Set<Integer> s = new HashSet<>();
                    adjMap.put(v2, s);
                }
                
                adjMap.get(v1).add(v2);
                adjMap.get(v2).add(v1);
            }
            
            int start = in.nextInt();
            
            breadth_first_search(adjMap, start, numNodes);
            System.out.println();
            
        }
        
      
    }
    
    private static void breadth_first_search(Map<Integer, Set<Integer>> adj, int start, int numNodes)
    {
        int[] distance = new int[numNodes+1];

        for(int i = 1; i < distance.length; ++i)
           distance[i] = -1;
        
        if(adj.containsKey(start))
        {
            Queue<Integer> q = new LinkedList<Integer>();
            q.add(start);
            distance[start] = 0;

            while(!q.isEmpty())
            {
                int root = q.remove();
                Iterator<Integer> iter = adj.get(root).iterator();
            
                while(iter.hasNext())
                {
                    int child = iter.next();
                    if(distance[child] == -1)
                    {
                        distance[child] = distance[root] + 6;
                        q.add(child);
                    }
                }
            }
        }
        
        for(int i = 1; i < distance.length; ++i)
            if(start != i)
                  System.out.print(distance[i] + " ");
    }
}