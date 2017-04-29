import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Vertex
{

  public String name;
 
  public boolean known = false;
  public Vertex prev;
  public ArrayList<Vertex> adjacentVertices;

  public Vertex(String name)
  {
    this.name = name;
    adjacentVertices = new ArrayList<Vertex>();
    prev = null;
  }

  public String getName()
  {
    return name;
  }

}