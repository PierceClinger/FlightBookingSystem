import java.util.ArrayList;

/**
 * @author The Water Coolers
 * Data storage class that connects to the flights.json, pulling the data into an ArrayList of type Flight
 */
public class FlightList extends DataLoader {
    private static FlightList flights;
    public static ArrayList<Flight> flightList;
    
    /**
     * Default constructor: Calls the DataLoader and populates an ArrayList of type Flight with all the current flight data
     */
    private FlightList() {
        flightList = DataLoader.getFlights();
    }

    /**
     * Singleton method which allows for only one instance of the FlightList to exist. Checks if an instance exists, if it does then return it, if not make a new one
     * @return The instance of FlightList
     */
    public static FlightList getInstance() {
        if (flights == null) {
            flights = new FlightList();
        }
        return flights;
    }

    /**
     * Returns a specific flight from the database of flights
     * @param flightID The ID of the flight that should be returned
     * @return The flight
     */
    public static Flight getFlight(String flightID) {
        for(Flight flight : flightList) {
            if(flight.getUUID().equalsIgnoreCase(flightID)) {
                return flight;
            }
        }
        return null;
    }

    /**
     * Returns the ArrayList of flight data
     * @return The ArrayList of current flight data
     */
    public ArrayList<Flight> getAllFlights() {
        return flightList;
    }
}
