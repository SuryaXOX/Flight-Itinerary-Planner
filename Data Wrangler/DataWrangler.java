
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;

/*
 * DataWrangler creates the CS400Graph where
 * the vertices are Aiports and the edges
 * are weighted by distance in time
 */
public class DataWrangler {

    //list of the airports that are in the graph
    public static ArrayList<Airport> airportList;

    //graph that is traversed vis Dijkstra's shortest path algorithm
    public static CS400Graph<Airport> airportGraph;

    //text file containing the edges
    public static String mapText = "airportGraph.txt";

    //text file containing the vertices
    public static String listText = "airportsList.txt";

    /*
     * Loads the text files and reads the
     * airports and builds the graph
     * that serves as the map for flying
     * to different airports.
     */
    public static void CreateMap() throws NoSuchElementException, FileNotFoundException{
	airportList = new ArrayList<Airport>();
	airportGraph = new CS400Graph<>();
	
	//try to use the aiportList.txt file for Scanner input
	File listFile = new File(listText);
	Scanner reader = null;
	try{
	  reader = new Scanner(listFile);
	}catch(FileNotFoundException e){
	    throw new FileNotFoundException(listText + " not found");
	}
	if(reader == null) {
	  return;
	}

	//create new airports and add them to the list and insert them into
	//the graph as a vertices
	while(reader.hasNextLine()){
	    Airport addAirport = new Airport(reader.nextLine().trim());
	    airportList.add(addAirport);
	    airportGraph.insertVertex(addAirport);
	}

	//try to use the airportGraph.tct file for scanner input
	File mapFile = new File(mapText);
	reader = null;
	try{
	    reader = new Scanner(mapFile);
	}catch(FileNotFoundException e){
	    throw new FileNotFoundException(mapText + " not found");
	}
	if (reader == null){
	    return;
	}

	//insert the edges specified in airportGraph.tct into the graph 
	while(reader.hasNextLine()){
	    String[] line = reader.nextLine().split(",");
	    String start = line[0].trim();
	    String end = line[1].trim();
	    int weight = Integer.parseInt(line[2].trim());
	    Airport startAirport = null;
	    Airport endAirport = null;
	    
	    //getting the right Airport objects from the airportList
	    for( Airport a : airportList){
		if(a.getName().equals(start)){
		    startAirport = a;
		}else if(a.getName().equals(end)){
		    endAirport = a;
		}
	    }
	    if(startAirport == null || endAirport == null){
		throw new NoSuchElementException("name: " + start + " or " + end + " could not be found");
	    }
	    
	    //insertion step
	    airportGraph.insertEdge(startAirport, endAirport, weight);
	}
	
    }
    
}
