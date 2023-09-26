/**
 * @author Hayden Bunce
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class RoomTest {
    private ArrayList<Hotel> hotelList;
    private Hotel hotel;
    private ArrayList<Room> rooms;
    private Room room;
    @BeforeEach
    public void setup(){
        hotelList = HotelList.getInstance().getAllHotels();
        hotel = hotelList.get(0);
        rooms = hotel.getRooms();
        room = rooms.get(0);

    }
    @AfterEach
    public void tearDown(){
        hotelList.clear();
        rooms.clear();
    }
    @Test
    void testToString(){
        String testString = room.toString();
        assertEquals("Room Number: 101\nSmoking Status: Non Smoking\nNumber of Beds: 1\nType of Bed: queen\n", testString);

    }
}