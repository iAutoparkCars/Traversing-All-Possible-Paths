import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Main
{
	
	public static Vertex start;
	public static Vertex end;
	static ArrayList<String> allPaths;
	static ArrayList<String> pathList = new ArrayList<String>();

	
	//static HashMap<Vertex, ArrayList<Vertex>> graph = new HashMap<Vertex, ArrayList<Vertex>>();
	static ArrayList<Vertex> graph = new ArrayList<Vertex>();
	
	//key is name of vertex, value is the vertex's adjacency list
	
	public static void main (String args[]) throws IOException, InterruptedException
	{
			//read in text file from command line
		String filename = args[0];
		FileReader filereader = new FileReader(filename);
		BufferedReader buffer = new BufferedReader(filereader);
		
			//Parse through each each line and create graph
		String line;
		while((line = buffer.readLine()) != null)
		{
			line = line.replaceAll("\\s+", " ");
			createGraph(line);
		}
		
		//System.out.println(start.getName() + " " + end.getName() + "\n");
		
		ArrayList<String> path = new ArrayList<String>();
		allPaths = findAllPaths(start.getName(), path);
		
		for (String i : allPaths)
		{
			System.out.println(i);
		}
		//printVertices();
	}
	
	public static int counter = 0;
	public static Boolean backtrack = false;
	public static String diverge = "";
	
	public static ArrayList<String> findAllPaths(String currVertex, ArrayList<String> currPath) throws InterruptedException
	{
		//counter++;
		
			//if current vertex is destination, current vertex is marked known; return path
		if (currVertex.equals(end.getName()))
		{
			//getVertex(currVertex).known = true;
			currPath.add(currVertex);
			String result = String.join("", currPath);
			pathList.add(result);
			System.out.println("possible path is: " + result);
			
			//don't clear, but remove a certain amount of vertices to "backtrack" on your path
			
			return pathList;
		}
		
		
		for(Vertex neighbor : getVertex(currVertex).adjacentVertices)
		{
			//System.out.println(" is current vertex " + currVertex + " in current path " + currPath + "? "+ currPath.contains(currVertex) );
			while(currPath.contains(currVertex))
			{
				currPath.remove(currPath.size()-1);
			}
			//System.out.println(" is current vertex " + currVertex + " in current path " + currPath + "? "+ currPath.contains(currVertex) );

			
			/*if (currPath.contains(currVertex))
			{
				findAllPaths(neighbor.getName(),currPath);
			}*/
			
			if ((currPath.contains(currVertex) == false) && getVertex(neighbor.getName()).known == false)
			{
				neighbor.known = true;
				//System.out.println("current vertex is " + currVertex + "|| neighbor: " + neighbor.getName());
				currPath.add(currVertex);
				System.out.println("calling getPath with new current " + neighbor.getName() + " and current path " + currPath + "\n________");
				findAllPaths(neighbor.getName(),currPath);
			}
		}
		
		
		
		return pathList;
	}
	
	public static Vertex getVertex(String name)
	{
		Vertex found = null;
		for (Vertex v: graph)
		{
			if (v.getName().equals(name))
			{
				found = v;
			}
		}
		
		if (found == null)
		{
			Vertex v1 = new Vertex(name);
			graph.add(v1);
			found = getVertex(v1.name);
		}
		return found;
	}
	
	public static void createGraph(String line)
	{
		ArrayList<String> unique = new ArrayList<String>();
		Boolean isVertex = line.contains(":");
		String[] adjacent;
		String[] startEnd;
		
		//if line is for vertex, add it to graph with adjacent vertices
		if (isVertex)
		{
			adjacent = line.split(" ");
			Vertex v1 = new Vertex(adjacent[0]);
			//System.out.print(adjacent[0] + " : ");
			for (int j = 2; j < adjacent.length; j++)
			{
				v1.adjacentVertices.add(new Vertex(adjacent[j]));
				/*if (getVertex(adjacent[j]) == null)
				{
					graph.add(new Vertex(adjacent[j]));
				}*/
				//System.out.print(adjacent[j] + " " );
			}
			//System.out.println("\n_________________");
			graph.add(v1);
		}
		else 
		{
			startEnd = line.split(" ");
			if (startEnd.length>1)
			{
				start = new Vertex(startEnd[0]);
				end = new Vertex(startEnd[1]);
			}
		}
		
		
	}
	
	public static void printVertices()
	{
		for (Vertex v : graph)
		{
			System.out.print(v.getName() + " " + v.known + " : ");
			for (Vertex adj : v.adjacentVertices)
				System.out.print(" " + adj.getName());
			System.out.println("");
		}
	}



}

/*import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PathFinder
{

    *//**
     * Tests the method parseFile with an example test-case.
     * This method is completely for your own testing and is NOT
     * called/used while grading your answer.
     *//*
    public static void main(String[] args)
            throws FileNotFoundException, IOException {
        String filename = "C:\\Users\\Steven\\Lytmus\\candidate_files\\input_0.txt";
        if (args.length > 0) {
        	filename = args[0];
        }
        
        
        
        
        List<String> answer = parseFile(filename);
        System.out.println(answer);
    }
    
    static List<String> parseFile(String filename)
    		throws FileNotFoundException, IOException {
    	
    	 *  Don't modify this function
    	 
        BufferedReader input = new BufferedReader(new FileReader(filename));
        List<String> allLines = new ArrayList<String>();
        String line;
        while ((line = input.readLine()) != null) {
        	allLines.add(line);
        }
        input.close();

        return parseLines(allLines);    	
    }
    
    static ArrayList<String> result = new ArrayList<String>();
    public static Vertex start;
    public static Vertex end;
    
    static ArrayList<Vertex> graph = new ArrayList<Vertex>();
    
    static List<String> parseLines(List<String> lines)
    {
    	//generates graph
    	for (String i : lines)
    	{
    		createGraph(i);
    	}
    	
    	//now start finding paths
    	ArrayList<String> initPath = new ArrayList<String>();
    	getPaths(start.getName(), initPath);
    		
    	
    	
    	
    	//System.out.println(start.getName() + " " + end.getName());
    	getVertex("C");
    	printGraph();
    	return result;
    }
    
    static void getPaths(String currVertex, ArrayList<String> currPath)
    {
    	//when you find a path, dump current path into result.add(currPath)
    	
    	//if current is end, add current vertex to currPath, and return currPath
    	if (currVertex.equals(end.getName()))
		{
			currPath.add(currVertex);
			String pathString = String.join("", currPath);
			result.add(pathString);
			//System.out.println("possible path is: " + result);
		}
    
    	
    	for (Vertex adjacent : getVertex(currVertex).adjacentVertices)
    	{
    		while(currPath.contains(currVertex))
    		{
    			currPath.remove(currPath.size()-1);
    		}
    	
    		//if current path doesn't have current vertex, and it isn't yet visited
    		if (!(currPath.contains(currVertex)) && !getVertex(adjacent.getName()).known)
			{
    			adjacent.known = true;
				currPath.add(currVertex);
				getPaths(adjacent.getName(),currPath);
			}
    	
    	}
    
    
    }
    
    static Vertex getVertex(String name)
    {
    	Vertex found = null;
		for (Vertex v: graph)
		{
			if (v.getName().equals(name))
			{
				found = v;
			}
		}
		
		if (found == null)
		{
			Vertex v1 = new Vertex(name);
			graph.add(v1);
			found = getVertex(v1.name);
		}
		return found;
    }
   
    static void createGraph(String line)
    {
    	String[] arr;
    	if (!line.contains(":"))
    	{
    		line = line.replaceAll("\\s+", " ");
    		arr = line.split(" ");
    		start = new Vertex(arr[0]);
    		end = new Vertex(arr[1]);
    	}
    	else if (line.contains(":"))
    	{
    		arr = line.split(" ");
			Vertex v1 = new Vertex(arr[0]);
			
			for (int i = 2; i < arr.length; i++)
			{
				v1.adjacentVertices.add(new Vertex(arr[i]));
			}
			//System.out.println("\n_________________");
			graph.add(v1);
    	}
    	
    }
    
    static void printGraph()
    {
    	for (Vertex v : graph)
		{
			System.out.print(v.getName() + " " + v.known + " : ");
			for (Vertex adj : v.adjacentVertices)
				System.out.print(" " + adj.getName());
			System.out.println("");
		}
    }
}

*/