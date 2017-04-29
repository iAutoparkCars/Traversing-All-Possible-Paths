public class Edge
{

  public Vertex source;
  public Vertex target;

  public Edge(Vertex vertex1, Vertex vertex2)
  {
    source = vertex1;
    target = vertex2;
  }

  public String toString()
  {
    return source + " - " + target;
  }
}