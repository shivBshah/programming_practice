/**
   @author Shiv Shah
   Date: 5/6/2016
   Description: The purpose of this class is explore the link between different states. Given the file containing information about adjacent states, 
                this class stores the states as an object of Vertex class, associate a unique number to each of them, and store their links 
                using another class called Graph.java. This another class stores the links between states in an adjacency list. 
                Given the source and destination, this class uses breadth-first-search technique to explore the link between states, 
                meanwhile finding the path between given states. It also find the set of states that can be reached by travelling certain distance. 
*/

import java.util.*;
import java.io.*;

public class PathFinder {

   /**Graph class object to maintain edges between states*/
   private Graph stateGraph;       
     
   /**List to store all the objects that represent states */
   private List<Vertex> stateList; 
   
   /**Set to store states that are visited or are reachable from a certain state */
   private Set<String> visitedSet; 
   
   /**
      The constructor method to read inputs from the given file,
      store them in the list, and also add their links in the graph.
      @param filename name of the file containing list of edges between states
   */
   
   public PathFinder (String filename)
   { 
      //initialization of instances
      stateGraph = new Graph();
      visitedSet = new HashSet<String>();
      stateList = new ArrayList<Vertex>();
     
      try {
         Scanner in = new Scanner(new File(filename));     
         while(in.hasNext())
         {
            Vertex state1 = new Vertex(in.next()); 
            Vertex state2 = new Vertex(in.next());  
         
            int index1 = stateList.indexOf(state1); 
            int index2 = stateList.indexOf(state2);
         
            if(index1 == -1)                        //for every pair of states in the file
            {                                      //ensure that they have been stored
               stateList.add(state1);             //and added to the graph
               index1 = stateList.indexOf(state1); 
               stateGraph.addVertex(index1);            
            }        
            if(index2 == -1)
            {
               stateList.add(state2);
               index2 = stateList.indexOf(state2);
               stateGraph.addVertex(index2);     
            }        
            stateGraph.addEdge(index1, index2);  //add edge between the two vertices/states in the graph
         }                                      //using their index number in the list
      }
      catch (FileNotFoundException ex)
      {
         System.out.println("ERROR: FILE NOT FOUND !!!");
      }
   }
   
   /**
     This method checks whether the given vertex is valid,
     throws an exception if it is invalid.
     @param vertex the vertex that is to be validated
   */
   
   private void validate(String vertex)
   {
      if(indexOf(vertex) == -1)
         throw new IllegalArgumentException("Invalid vertex " + vertex);  
   }
   
   /**
     This method returns the index of the object 
     that represents the given state in the list stateList.
     @param state the name of the state whose index is being queried
     @return the index of the object representing that state
   */
   
   private int indexOf(String state)
   {
      Vertex v = new Vertex(state);
      return stateList.indexOf(v); 
   }
   
   /**
     This method sets the level and parent attributes of every state object 
     in the list stateList to their default values.
   */
   
   private void resetVertices()
   {
      for(Vertex v: stateList)
      {
         v.setLevel(-1);
         v.setParent("");
      }   
   }
   
   /**
     This private helper method does the breadth-first-search traversal of the state vertices
     in the graph to explore path between different states. 
     The traversal is based on criteria such as source, destination, and maximum level to traverse.
     @param source the state acting as the starting point for the path search
     @param destination the state acting as the ending point for the path search
     @param maxLevel the integer representing the maximum distance of the path
   */
   
   private void pathSearch(String source, String destination, int maxLevel)
   {
      Queue<Vertex> stateQueue = new LinkedList<Vertex>();
      visitedSet.clear();     //ensure that no vertex is visited at the begining
      resetVertices();        //ensure that every vertex object is unchanged to start with
      
      Vertex root = stateList.get(indexOf(source)); //source is the root
      root.setLevel(0);      //level of the root/source is 0
      stateQueue.add(root);  
      
      boolean done = false;
      
      while(!stateQueue.isEmpty() && !done)
      {
         root = stateQueue.remove();     //next root to be explored is at the front of the queue
         if(root.level() == maxLevel)
            done = true;
            
         Iterator<Integer> iter = stateGraph.getAdjacent(stateList.indexOf(root)); //get the iterator over vertices adjacent to root
         
         while(iter.hasNext() && !done)            //visit each child of the root until it has no child or
         {                                         //the goal is not reached
            Vertex child = stateList.get(iter.next()); 
            if(child.level() == -1)              //make sure that vertex is not already visited
            {                                  
               visitedSet.add(child.name());    //for the unvisited vertex, visit it
               child.setParent(root.name());     //update its attributes, and add it to the queue
               child.setLevel(root.level() + 1); 
               stateQueue.add(child);
            }
            if(child.name().equals(destination)) 
               done = true;           
         }     
      }
   }
   
   /**
     This method provides the smallest number of edges to be traveled in the path from source to destination.
     @param source state representing the start point of the path
     @param destination state representing the end point of the path  
     @return pathLength - integer representing the number of edges between source and destination
                          returns -1 if there is no path
   */
   
   public int getPathLength(String source, String destination)
   {
      validate(source);
      validate(destination);
      pathSearch(source, destination, stateGraph.vertices());     //do the breath-first traversal of the graph
                                                                   //and get the pathlength for the destination
      int pathLength = stateList.get(indexOf(destination)).level(); //which is -1 if there is no path
   
      return pathLength;
   }
   
   /**
      This method returns the vertices in the shortest path from source to destination.
      @param source state representing the start point of the path
      @param destination state representing the end point of the path
      @return path - the string representing the shortest path from source to destination,
              returns NONE if there is no path.
   */
   
   public String getPath(String source, String destination)
   {     
      int pathLength = getPathLength(source, destination); //call to get the path length
       
      if(pathLength == -1) //-1 means there is no path   
        return "NONE";
              
      String path = destination;
      String previous = destination;
      
      for(int i = 1; i <= pathLength; ++i)   
      {
         int j = indexOf(previous);             //starting from destination, build path by
         previous = stateList.get(j).parent();  //getting its parent, then parent of parent
         path = previous + " --> " + path;      //and so on... until source is reached 
      }
      return "[" + path + "]";
   }
   
   /**
      This method returns a set of strings containing vertices reachable by traveling 
      no more than ‘distance’ edges from the source. 
      @param source state name indicating the start point for travel
      @param distance interger representing the maximum edges to be travelled
      @return visitedSet - set of states that can be reached from source remaining under the given distance
   */
   
   public Set<String> reachable(String source, int distance)
   { 
      validate(source);
      pathSearch(source, "", distance); //call to traverse vertices in the graph in the breadh-first manner
      return visitedSet;
   } 
}