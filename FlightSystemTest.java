import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the flight system class
 * @author Danielle Higgins
 */
public class FlightSystemTest {

    // NOTE: Can't really test void methods since they don't return anything
    
    private FlightSystem flight = new FlightSystem();
    private UserList users = UserList.getInstance();
    private ArrayList<RegisteredUser> userList = users.getAllUsers();

    // Clears users, adds one user and saves it
    @BeforeEach
    public void setup() {
        userList.clear();
        userList.add(new RegisteredUser("registered", "Danny Higgins", "higdan", "danny@gmail.com", "dan123", "09/12/1995", true));
        DataWriter.saveUsers();
    }

    // Clears all the users and saves
    @AfterEach
    public void tearDown() {
        UserList.getInstance().getAllUsers().clear();
        DataWriter.saveUsers();
    }

    // assertEquals(val1, val2): These two things will be equal .equal()
    // assertFalse(val): better be the value of false
    // assertTrue(val)
    // assertSame(val1, val2): ==
    // assertNotSame(val1, val2)
    // assertNull(val): better be null
    // assertNotNull(val)

    // LOGIN METHOD
    // Test to see if user can login with there account already created
    @Test
    public void testAbleToLogin() {
        flight.login("higdan", "dan123");
        RegisteredUser myUser = flight.getCurrentUser();
        //assertEquals("higdan", myUser.getUsername(), myUser.getPassword());
        boolean loggedIn = myUser.getUsername().equals("higdan");
        assertTrue(loggedIn);
    }

    // Test to see if the expected user name matches the actual user name
    @Test
    public void testCorrectUserName() {
        flight.login("higdan", "dan123");
        RegisteredUser myUser = flight.getCurrentUser();
        assertEquals("higdan", myUser.getUsername(), "The user names match!");
    }

    // Test to see if the expected password matches the actual passworrd
    @Test
    public void testCorrectPassword() {
        flight.login("higdan", "dan123");
        RegisteredUser myUser = flight.getCurrentUser();
        assertEquals("dan123", myUser.getPassword(), "The passwords match!");
    }

    // Test to make sure arrayList is not empty
    @Test
    public void testNotEmpty() {
        assertFalse(userList.isEmpty());
    }

    // Test to see if arrayList is empty after being cleared
    @Test
    public void testIsEmpty() {
        userList.clear();
        assertTrue(userList.isEmpty());
    }

    // CREATE ACCOUNT METHOD
    // Test if the user makes a valid account
    @Test
    public void testCreateValidAccount() {
        userList.clear();
        flight.createAccount("Wendy Son", "wenwen", "wen@email.com", "wen4321", "02/21/1995", false);
        flight.login("wenwen", "wen4321");
        RegisteredUser myUser = flight.getCurrentUser();
        assertEquals("wenwen", myUser.getUsername());
    }

    // Test to see if user is saved after account creation
    @Test
    public void testSavedAccount() {
        userList.clear();
        flight.createAccount("Taeyeon", "taetae", "tae@outlook.com", "taengoo", "03/17/1996", true);
        flight.logout();
        flight = new FlightSystem();
        flight.login("taetae", "taengoo");
        RegisteredUser myUser = flight.getCurrentUser();
        assertEquals("taetae", myUser.getUsername());
    }

    // Test to make sure duplicate user names are NOT allowed
    @Test
    public void testDuplicateUserName() {
        userList.clear();
        flight.createAccount("Rosanne", "rose", "rosey@yahoo.com", "roserose", "10/04/1998", false);
        boolean create = flight.createAccount("John", "rose", "chicken@email.com", "chick", "08/01/2000", true);
        assertFalse(create);
    }

    // Test to make sure duplicate passwords ARE allowed
    @Test
    public void testDuplicatePassword() {
        userList.clear();
        flight.createAccount("Damien", "dd123", "damien@gmail.com", "ddawg", "04/12/2001", false);
        boolean create = flight.createAccount("Dan", "yoyo", "yup@email.com", "ddawg", "01/01/2000", true);
        assertTrue(create);
    }

    // Test to see if a empty user can be created
    @Test
    public void testEmptyUser() {
        userList.clear();
        boolean create = flight.createAccount("", "", "", "", "", false);
        assertFalse(create);
    }

    // Test to see if a null user can be created
    @Test
    public void testNullUser() {
        userList.clear();
        boolean create = flight.createAccount("", null, "", "", "", true);
        assertFalse(create);
    }
}