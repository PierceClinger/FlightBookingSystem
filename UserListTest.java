/**
 * @author Hayden Boozer
 * Tested by Hayden Boozer
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class UserListTest {

    private UserList userList = UserList.getInstance();

    public UserListTest() {
        //NOTHING
    }

    @BeforeEach
    public void setup() {
        userList.getAllUsers().clear();
    }

    @AfterEach
    public void tearDown() {
        userList.getAllUsers().clear();
    }

    @Test
    public void testAddNullUser() {
        userList.addUser(null, null, null, null, null, true);
        assertTrue(userList.haveUser(null));
    }

    @Test
    public void testAddEmptyUser() {
        userList.addUser("","","","","",true);
        assertTrue(userList.haveUser(""));
    }

    @Test
    public void testAddUserCaseSensitivity() {
        userList.addUser("Rachel Lofurno", "rlofurno", "susanproject","rlofurno@gmail.com", "04/05/2002",false);
        assertFalse(userList.addUser("Rachel Lofurno", "Rlofurno", "susanproject","rlofurno@gmail.com", "04/05/2002",false));
    }

    @Test
    public void testGetUserWithNoUsers() {
        assertEquals(null, userList.getUser("hb789"));
    }

    @Test 
    public void testGetUser() {
        RegisteredUser rachel = new RegisteredUser("registered", "Rachel Lofurno", "rlofurno", "rlofurno@gmail.com","susanproject", "04/05/2002",false);
        userList.addUser("Rachel Lofurno", "rlofurno", "susanproject","rlofurno@gmail.com", "04/05/2002",false);
        assertEquals(rachel.getUsername(), userList.getUser("rlofurno").getUsername());
    }
}
