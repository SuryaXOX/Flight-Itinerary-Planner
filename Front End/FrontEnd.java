
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class FrontEnd {
    private static Backend flightCreator = new Backend(); //backend object to make flights

  /**
   * Prompts user to enter starting and ending cities and creates flight plan using Dijkstras shortest path 
   * @param scnr
   */
  private static void bookFlight(Scanner scnr) {
    Airport start = null; // user starting airport city
    Airport end = null; // user ending airport city
    while (true) { // continous until valid input is entered
      System.out.println("Please enter your origin");
     String  user = scnr.nextLine().trim();
      if (user.equals("x")) { // exit program
        scnr.close();
        System.exit(0);
        break;
      } else {
        for (int i = 0; i < DataWrangler.airportList.size(); i++) {
          if(DataWrangler.airportList.get(i).getName().equals(user)) { // checks if input was valid city airport
            start = DataWrangler.airportList.get(i);
            break;
	  }
	}
          if (start == null) {
            System.out.println(
			       "BG Airlines does not operate out of " + user + " yet, please enter a valid airport.");
            continue; // rerun loop and allow user to enter valid airport city
          }
          break;
        }
      
    }
      
    while (true) { // for flight destination
      System.out.println("Please enter your destination.");
      String userIn = scnr.nextLine().trim();
      if (userIn.equals("x")) {
        scnr.close();
        System.exit(0);
        break;
      } else {
        for (int i = 0; i < DataWrangler.airportList.size(); i++) {
          if(DataWrangler.airportList.get(i).getName().equals(userIn)) {
            end = DataWrangler.airportList.get(i); // matches with valid airport
            break;
      }
	}
          if (end == null) {
            System.out.println(
			       "BG Airlines does not operate out of " + userIn + " yet, please enter a valid airport.");
            continue; // reruns and allows user to enter valid destination
          }
          break;
      }
    }
      System.out.println("******* System processing: Finding shortest route *******");
      Random rand = new Random(); // genereates random booking #
      Long bookingNum = Math.abs(rand.nextLong()); 
      System.out.println(
          "Thank you for booking with us. For reference your booking number is " + bookingNum + ".");
      System.out
          .println("Your flight is departing out of " + start.getName() + " and flying into " + end.getName() + ".");
      System.out.println(
			 "The route your flight is taking is " + flightCreator.getShortestRouteAirports(start, end) + "."); // shows flight with least connections
      System.out.println(
			 "Your flight time will be approximitely " + flightCreator.getShortestRouteCost(start, end) + " hours."); // flight time
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println(); // new lines to seperate option of creating new flight
    
  }
    /**
     * Iterates through airport list text file and returns possible airports
     */
    private static void getDestinations() {
    for (int i = 0; i < DataWrangler.airportList.size(); i++) {
        System.out.println(DataWrangler.airportList.get(i).getName()); // print possible airport ci\
ties                                                                                                
    }
  }

  public static void main(String args[]) {
    try {
      DataWrangler.CreateMap(); // getts valid maap
    }
    catch (FileNotFoundException e) {
      System.out.println("Error: Airports not loaded");
    }
    flightCreator.flightMap = DataWrangler.airportGraph;
    Scanner scnr = new Scanner(System.in);
    System.out.println("Hello! Shalom! Hola! Thank you for choose Team BG Airlines.");
    while (true) { // continous to run until user exits
      System.out.println("Please enter 'b' to book a new flight");
      System.out.println("Please enter 'd' to see possible destinations");
      System.out.println("Please enter 'x' to exit out of application");
      String userInput = scnr.nextLine().trim().toLowerCase();
      if (userInput.equals("b")) { // book flight
        bookFlight(scnr);
      } else if (userInput.equals("d")) { // get destinations
        getDestinations();
      } else if (userInput.equals("x")) { // exit
        System.out.println("Thank you and we hope to have you fly again with us soon!");
        scnr.close();
        System.exit(0);
        break;
      } else {
        System.out.println("Invalid input... please enter a valid option."); // re prompt for valid input
        continue;
      }
    }

  }

}
