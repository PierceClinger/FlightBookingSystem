import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the Seat class
 * @author Danielle Higgins
 */
public class SeatTest {
    
    private ArrayList<Flight> flightList;
    private Flight flight;
    private ArrayList<Seat> seats;
    private Seat seat;

    @BeforeEach
    public void setup() {
        flightList = FlightList.getInstance().getAllFlights();
        flight = flightList.get(0);
        seats = flight.getSeats();
        seat = seats.get(0);
    }

    @AfterEach
    public void tearDown() {
        flightList.clear();
        seats.clear();
    }

    @Test
    public void testToString() {
        String test = seat.toString();
        assertEquals("Seat Number: 1\nSeat Class: First Class\nAvailability: true\n", test);
    }
}
