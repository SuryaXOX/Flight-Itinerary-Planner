

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Provides the front end interface for the user to use the application.
 */
public class Front_End_1 {

    private static final String startupMsg = "Welcome to the Flight Itinerary Planner. Please " +
            "choose from the options below to start:";
    private static final String menuMsg = "[p] : Plan a new flight \n[g] : Get valid " +
            "destinations \n[x] : Exit application";
    private static final String invalidOption = "Invalid option. Please enter a valid option:";
    private static Backend backend = new Backend();


    /**
     * Prompts the user to choose an option from the menu
     *
     * @param scnr scanner for recording user's inputs
     */
    public static void userMenu(Scanner scnr) {
        System.out.println(menuMsg);
        String userInput = scnr.nextLine();

        if(userInput.equals("p"))
            newFlight(scnr);
        else if(userInput.equals("g"))
            getValidDesinations();
        else if(userInput.equals("x")) {
            System.out.println("Exiting Application.");
            System.exit(0);
        }
        else
            System.out.println(invalidOption);
    }

    /**
     * Presents the user with a list of valid destinations
     */
    private static void getValidDesinations() {
        for(int i = 0; i < DataWrangler.airportList.size(); i++)
            System.out.println(DataWrangler.airportList.get(i).getName());
    }

    /**
     * Prompts the user for a new flight plan and finds the shortest route
     *
     * @param scnr scanner for recording user's inputs
     */
    private static void newFlight(Scanner scnr) {
        System.out.println("Please enter a valid origin:");
        Airport origin = null;
        Airport destination = null;

        // gets airport origin by name
        while (true) {
            String userInput = scnr.nextLine();
            if(userInput.equals("x"))
                return;
            else {
                for(int i = 0; i < DataWrangler.airportList.size(); i++)
                    if(DataWrangler.airportList.get(i).getName().equals(userInput)) {
                        origin = DataWrangler.airportList.get(i);
                        break;
                    }
                if(origin == null) {
                    System.out.println("Invalid airport. Please enter a valid airport:");
                    continue;
                }
                break;
            }
        }

        System.out.println("Origin: " + origin.getName());
        System.out.println("Please enter a valid destination:");

        // gets airport destination by name
        while (true) {
            String userInput = scnr.nextLine();
            if(userInput.equals("x"))
                return;
            else {
                for(int i = 0; i < DataWrangler.airportList.size(); i++)
                    if(DataWrangler.airportList.get(i).getName().equals(userInput)) {
                        destination = DataWrangler.airportList.get(i);
                        break;
                    }
                if(destination == null) {
                    System.out.println("Invalid airport. Please enter a valid airport:");
                    continue;
                }
                break;
            }
        }
        System.out.println("Route: " + backend.getShortestRouteAirports(origin, destination));
        System.out.println("Shortest Path: " + backend.getShortestRouteCost(origin, destination));
    }

    /**
     * Main application
     *
     * @param args n/a
     */
    public static void main(String args[]) {
        try {
            DataWrangler.CreateMap();
        }
        catch (FileNotFoundException e) {
            System.out.println("Airports could not be loaded.");
        }
        backend.flightMap = DataWrangler.airportGraph;
        System.out.println(startupMsg);
        while(true) {
            Scanner scnr = new Scanner(System.in);
            userMenu(scnr);
        }
    }
}