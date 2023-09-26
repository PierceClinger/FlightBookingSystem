/**
 * @author Pierce Clinger
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class DataLoaderTest {
    private UserList users = UserList.getInstance();
    private FlightList flights = FlightList.getInstance();
    private ArrayList<RegisteredUser> userList = users.getAllUsers();
    private ArrayList<Flight> flightList = flights.getAllFlights();
    private ArrayList<Seat> seatList;

    /**
     * Default Constructor: Needed to avoid error thrown by JUNIT
     */
    public DataLoaderTest() {}

    /**
     * Called before each test
     */
    @BeforeEach
    public void setup() {
        userList.clear();
        flightList.clear();
        userList.add(new RegisteredUser("registered", "Martha Stewart", "marthas69", "johndoe@email.com", "iammarthastewart", "01/06/1943", true));
        userList.add(new RegisteredUser("registered", "Scott Weiland", "scw23", "sweiland@email.com", "purple", "08/13/2002", true));
        seatList.add(new Seat(1, "First Class", true));
        flightList.add(new Flight("f0f9e774-3c08-4232-8106-8105f5fcad97", "Columbia, South Carolina", "KCAE", "KSEA", "Seattle, Washington", 0 , "Delta", "04/14/2022", "04/14/2022", "12:00 AM", "5:00 AM", 5.0, true, 2, 11, "None", seatList));
        DataWriter.saveUsers();
    }

    /**
     * Called after each test
     */
    @AfterEach
    public void tearDown() {
        UserList.getInstance().getAllUsers().clear();
        DataWriter.saveUsers();
    }

    /**
     * Testing size of populated user list
     */
    @Test
    public void testGetUserSize() {
        userList = DataLoader.getUsers();
        assertEquals(2, userList.size());
    }

    /**
     * Testing size of empty user list
     */
    @Test 
    public void testGetUserSizeZero() {
        UserList.getInstance().getAllUsers().clear();
        DataWriter.saveUsers();
        assertEquals(0, userList.size());
    }

    /**
     * Testing get first username
     */
    @Test 
    public void testGetUserFirstUserName() {
        userList = DataLoader.getUsers();
        assertEquals("marthas69", userList.get(0).getUsername());
    }

    /**
     * Testing get first flight airline
     */
    @Test 
    public void testGetFlightFirstAirline() {
        flightList = DataLoader.getFlights();
        assertEquals("Delta", flightList.get(0).getAirline());
    }

    /**
     * Testing get first seat on first flight class
     */
    @Test 
    public void testGetFlightFirstSeatClass() {
        flightList = DataLoader.getFlights();
        assertEquals("First Class", flightList.get(0).getSeats().get(0).getClass());
    }
}