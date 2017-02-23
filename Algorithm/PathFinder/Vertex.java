/**
  @author Shiv Shah
   Date: 5/6/2016
   Description: This class is used to represent object for state, and the object is used by class PathFinder.java 
                as a vertex during graph traversal. It is used to store and access attributes related to state 
                such as name, level, and its parent state. 
                This class also overrides equals method so that two state objects with same name are equal.
*/

public class Vertex {

  /**Name of the vertex/state*/
  private String name;  
  /**Level or height of the vertex in graph*/ 
  private int level;    
  /**Parent of the vertex */
  private String parent;
  
  /**
    This contructor method initilaizes the instance variables.
    @param name string representing the name of the vertex or state
  */
  
  public Vertex(String name)
  {
    this.name = name;
    level = -1;
	 parent = "";
  }
  
  /**
    This method returns the name of the vertex or state
    @return name - string representing the name of the state
  */
  
  public String name()
  {
     return name;
  }
  
  /**
    This method returns the level or height of the vertex in the graph traversal
    @return level - interger representing the level or height of the vertex
  */
  
  public int level()
  {
     return level;
  }
  
  /**
    This method returns the parent name of the vertex in the graph traversal
    @return parent - string representing the name of the parent
  */
  
  public String parent()
  {
     return parent;
  }
  
  /**
    This method updates the name of the vertex with the given name
    @param name string representing the new name of the vertex
  */
  
  public void setName(String name)
  {
     this.name = name;
  }
  
  /**
    This method updates the level of the vertex with the provided level
    @param level integer representing the new level of the vertex
  */
  
  public void setLevel(int level)
  {
     this.level = level;
  }
  
  /**
    This method updates the parent of the vertex
    @param parent new parent of the vertex
  */
  
  public void setParent(String parent)
  {
     this.parent = parent;
  }
  
  /**
    This method overrides the equals method and determine the equality of 
    two vertices on the basis of their name.
    @return true if two vertices are equal, false otherwise
  */
  
  @Override
  public boolean equals(Object o)
  {
     Vertex v = (Vertex) o;
	 if(v instanceof Vertex)
	    return this.name.equals(v.name);
	 return false;
  }
}