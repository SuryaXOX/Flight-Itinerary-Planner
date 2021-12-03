
import java.util.List;

/**
 * Back End methods for the airport route search
 *
 */
public class Backend {

  public CS400Graph<Airport> flightMap = new CS400Graph<>();

  public void addAirport(Airport airport) {
    flightMap.insertVertex(airport);
  }

  public void addRoute(Airport start, Airport end, int cost) {
    flightMap.insertEdge(start, end, cost);
  }

  /**
   * Returns a string of airports between the start and end
   * 
   * @param start
   * @param end
   * @return
   */
  public String getShortestRouteAirports(Airport start, Airport end) {
    List<Airport> shortestPath = flightMap.shortestPath(start, end);
    String output = "";
    for(Airport airport: shortestPath) {
      output += airport.getName() + " ";
    }
    
    return output.trim();
  }


  public int getShortestRouteCost(Airport start, Airport end) {
    return flightMap.getPathCost(start, end);
  }

}
