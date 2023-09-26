/**
 * @author Hayden Bunce
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HotelTest {
    private ArrayList<Hotel> hotelList = HotelList.getInstance().getAllHotels();
    private Hotel hotel;
    @BeforeEach
    public void setup(){
        hotel = hotelList.get(0);
    }
    @AfterEach
    public void tearDown(){
        hotelList = HotelList.getInstance().getAllHotels();
    }
    @Test
    void testToString(){
        String testString = hotel.toString();
        assertEquals("Name : The Overlook Hotel\nTotal Rooms: 15\nLocation: Boulder, Colorado\nPrice per Night: 135.0\nRating: 4\n", testString);
    }
    @Test
    void testDisplayToUser(){
        String testString = hotel.displayToUser(0);
        assertEquals("\nHOTEL OPTION 0\nName : The Overlook Hotel\nLocation: Boulder, Colorado\nRating: 4\nTotal Rooms: 15\n", testString);
    }
    @Test
    void testDisplayWithDetails(){
        String testString = hotel.displayWithDetails(15, 0);
        assertEquals("\nHOTEL OPTION 0\nName : The Overlook Hotel\nLocation: Boulder, Colorado\nRating: 4\nPrice per Night: 135.0\nAmenities: \n  - Pool\n  - Gym\n  - Continental Breakfast\n  - Free Wifi\n\nNumber of Available Rooms (based on your criteria): 15\n", testString);
    }
    @Test
    void testAddRoom(){
        Room temp = new Room(420, true, 1, true, "king");
        hotel.addRoom(temp);
        ArrayList<Room> rooms= hotel.getRooms();
        Room testRoom = rooms.get(rooms.size()-1);
        String testString = testRoom.toString();
        assertEquals("Room Number: 420\nSmoking Status: Smoking\nNumber of Beds: 1\nType of Bed: king\n", testString);
        }

}