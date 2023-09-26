/**
 * @author Hayden Boozer
 * Tested by Hayden Boozer
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class DataWriterTest {
    private UserList users = UserList.getInstance();
    private BookingList bookings = BookingList.getInstance(); 
    private ArrayList<RegisteredUser> userList = users.getAllUsers();
    private ArrayList<Booking> bookingList = bookings.getAllBookings();

    /**
     * Default Constructor: Needed to avoid error thrown by JUNIT
     */
    public DataWriterTest() {
        //NOThing
    }
    
    /**
     * Called before each test, erases the list class data and saves it to the jsons
     */
    @BeforeEach
    public void setup() {
        UserList.getInstance().getAllUsers().clear();
        BookingList.getInstance().getAllBookings().clear();
        HotelList.getInstance().getAllHotels().clear();
        FlightList.getInstance().getAllFlights().clear();
        DataWriter.saveUsers();
    }

    /**
     * Called after each test, erases the list class data and saves it to the jsons
     */
    @AfterEach
    public void tearDown() {
        UserList.getInstance().getAllUsers().clear();
        BookingList.getInstance().getAllBookings().clear();
        HotelList.getInstance().getAllHotels().clear();
        FlightList.getInstance().getAllFlights().clear();
        DataWriter.saveUsers();
    }

    /**
     * Testing if no users are added to the json files
     */
    @Test
    public void writingTestZeroUsers() {
        userList = DataLoader.getUsers();
        assertEquals(0, userList.size());
    }

    /**
     * Testing if one user is added to the json file
     */
    @Test 
    public void writingTestOneUser() {
        RegisteredUser user = new RegisteredUser("registered", "Billy Corgan", "wpc29", "bcorgan@email.com", "smashingpumpkinsrule", "03/17/1968", false);
        userList.add(user);
        DataWriter.saveUsers();
        assertEquals("Billy Corgan", DataLoader.getUsers().get(0).getName());
    }

    /**
     * Testing if 4 users are added to the json file, will the program read an arbitrary index correctly
     */
    @Test
    public void writingTestFourUsers() {
        userList.add(new RegisteredUser("registered", "Kurt Cobain", "kdc34", "kcobain@email.com", "inutero", "09/23/2002", true));
        userList.add(new RegisteredUser("registered", "Scott Weiland", "scw23", "sweiland@email.com", "purple", "08/13/2002", true));
        userList.add(new RegisteredUser("registered", "Layne Staley", "lstales3", "lstaley@email.com", "dirt", "10/30/1976", false));
        userList.add(new RegisteredUser("registered", "Gene Ween", "gween90", "gween@email.com", "boognish", "02/02/1956", true));
        DataWriter.saveUsers();
        assertEquals("Gene Ween", DataLoader.getUsers().get(3).getName());
    }

    /**
     * Testing if a user is added with empty strings
     */
    @Test
    public void writingTestEmptyStringUser() {
        userList.add(new RegisteredUser("", "", "", "", "", "", false));
        DataWriter.saveUsers();
        assertEquals("", DataLoader.getUsers().get(0).getUsername());
    }

    /**
     * Testing if a user is added with a null value
     */
    @Test
    public void writingTestNullUser() {
        userList.add(new RegisteredUser("", null, "", "", "", "", true));
        DataWriter.saveUsers();
        assertEquals(null, DataLoader.getUsers().get(0).getName());
    }

    /**
     * Testing that the users are saved in the correct order
     */
    @Test
    public void writingTestUserOrder() {
        userList.add(new RegisteredUser("registered", "Kurt Cobain", "kdc34", "kcobain@email.com", "inutero", "09/23/2002", true));
        userList.add(new RegisteredUser("registered", "Scott Weiland", "scw23", "sweiland@email.com", "purple", "08/13/2002", true));
        userList.add(new RegisteredUser("registered", "Layne Staley", "lstales3", "lstaley@email.com", "dirt", "10/30/1976", false));
        DataWriter.saveUsers();
        assertEquals("Scott Weiland", DataLoader.getUsers().get(1).getName());
        assertEquals("Layne Staley", DataLoader.getUsers().get(2).getName());
    }

    /**
     * Testing if a booking is added with empty strings
     */
    @Test
    public void writingTestEmptyBooking() {
        bookingList.add(new Booking("", "", "", -1, -1, "", "", "", "", ""));
        DataWriter.saveBookings();
        assertEquals("", DataLoader.getBookings().get(0).getBookingName());
    }

    /**
     * Testing if a booking is added with null values
     */
    @Test
    public void writingTestNullBooking() {
        bookingList.add(new Booking(null, null, null, -1, -1, null, null, null, null, null));
        DataWriter.saveBookings();
        assertEquals(null, DataLoader.getBookings().get(0).getFlightID());
    }

    /**
     * Testing if no bookings are saved to the json file
     */
    @Test
    public void writingTestWriteWithNoBookings() {
        bookingList = DataLoader.getBookings();
        assertEquals(0, bookingList.size());
    }
}
