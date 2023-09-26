/**
 * @author Hayden Bunce
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class BookingListTest {
    private BookingList undo = new BookingList();
    private BookingList bookings = BookingList.getInstance();
    @BeforeEach
    public void setup(){
        bookings.getAllBookings().clear();
    }
    @AfterEach
    public void tearDown(){
        bookings.getAllBookings().clear();
    }
    @Test
    void testAddBooking(){
        bookings.addBooking("hbunce", "flight ID", "hotel ID", 1, 2, "04/10/2022", "Test Booking", "10/21/1999", "01/02/1234", "01/03/1234");
        ArrayList<Booking> bTestArray = bookings.getAllBookings();
        Booking bookingTest = new Booking("hbunce", "flight ID", "hotel ID", 1, 2, "04/10/2022", "Test Booking", "10/21/1999", "01/02/1234", "01/03/1234");
        assertEquals(bookingTest.getBookingName(),bTestArray.get(0).getBookingName());
    }
    @Test
    void testSaveBookings(){
        bookings.addBooking("513d0cbc-a79b-417c-b71d-937478e3a091", "f0f9e774-3c08-4232-8106-8105f5fcad97", "None", 10, -1, "02/20/2022", "Martha Stewart", "01/06/1943", "None", "None");
        bookings.saveBookings();
        BookingList testBookings = BookingList.getInstance();//Get instance populates using the json information
        assertEquals(bookings.getAllBookings(), testBookings.getAllBookings());
    }


}