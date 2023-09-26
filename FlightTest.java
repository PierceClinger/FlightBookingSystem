/**
 * @author Hayden Bunce
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class FlightTest {
    private ArrayList<Flight> flightList = FlightList.getInstance().getAllFlights();
    private Flight flight;
    @BeforeEach
    public void setup(){
        flight = flightList.get(0);
    }
    @AfterEach
    public void tearDown(){
        flightList = FlightList.getInstance().getAllFlights();
    }
    @Test
    void testToString(){
        String testString = flight.toString();
        assertEquals("Start Location : Columbia, South Carolina\nDeparting Airport Code: KCAE\nArriving Airport Code: KSEA\nDestination: Seattle, Washington\nNumber of Transfers: 0\nAirline: Delta\nDeparture Date: 04/14/2022\nDeparture Time: 12:00 AM\nArrive Time: 5:00 AM\nTotal Estimated Travel Time: 5.0\nCarry On: true\nNumber of Checked Bags: 2\nCapacity: 11\nTransfer Location(s): None\n", testString);
    }
    @Test
    void testDisplayToUser(){
        String testString = flight.displayToUser(0);
        assertEquals("\nFLIGHT OPTION 0\nStart Location : Columbia, South Carolina\nDestination: Seattle, Washington\nDeparture Date: 04/14/2022\nDeparture Time: 12:00 AM\n", testString);
    }
    @Test
    void testDisplayWithDetails(){
        String testString = flight.displayWithDetails(0);
        assertEquals("\nFLIGHT OPTION 0\nStart Location : Columbia, South Carolina\nDeparting Airport Code: KCAE\nArriving Airport Code: KSEA\nDestination: Seattle, Washington\nNumber of Transfers: 0\nTransfer Location(s): None\nAirline: Delta\nDeparture Date: 04/14/2022\nDeparture Time: 12:00 AM\nArrive Time: 5:00 AM\nTotal Estimated Travel Time: 5.0\nCarry On: Allowed\nMaximum Number of Checked Bags Allowed: 2\nCapacity: 11\n", testString);
    }
    @Test
    void testAddSeat(){
        Seat temp = new Seat(12, "First Class", false);
        flight.addSeat(temp);
        ArrayList<Seat> seats = flight.getSeats();
        Seat testSeat = seats.get(seats.size()-1);
        String testString = testSeat.toString();
        assertEquals("Seat Number: 12\nSeat Class: First Class\nAvailability: false\n", testString);
    }

}
