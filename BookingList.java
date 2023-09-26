import java.util.ArrayList;

/**
 * @author The Water Coolers
 * Data storage class that connects to the bookings.json, pulling the data into an ArrayList of type Booking
 */
public class BookingList extends DataLoader {
    private static BookingList bookings;
    private ArrayList<Booking> bookingList;
    
    /**
     * Default constructor: Calls the DataLoader and populates an ArrayList of type Booking with all the current booking data
     */
    public BookingList() {
        bookingList = DataLoader.getBookings();
    }

    /**
     * Singleton method which allows for only one instance of the BookingList to exist. Checks if an instance exists, if it does then return it, if not make a new one
     * @return The instance of BookingList
     */
    public static BookingList getInstance() {
        if (bookings == null) {
            bookings = new BookingList();
        }
        return bookings;
    }

    /**
     * Returns the ArrayList of booking data
     * @return The ArrayList of current booking data
     */
    public ArrayList<Booking> getAllBookings() {
        return bookingList;
    }

    /**
     * Calls the DataWriter to save all changes to the booking data
     */
    public void saveBookings() {
        DataWriter.saveBookings();
    }

    /**
     * Adds a new booking to the ArrayList of booking data
     * @param userID The user's ID, whomever booked the flight
     * @param flightID The ID of the flight being booked (will be "None" if booking is for a hotel)
     * @param hotelID The ID of the hotel being booked (will be "None" if booking is for a flight)
     * @param seatNum The seat number being booked (will be -1 if booking is for a hotel)
     * @param roomNum The room number being booked (will be -1 if booking is for a flight)
     * @param date The date that the booking was completed
     * @param bookingName Name of the person who will be on the ticket/ name of the person the booking is for. Allows for the user to book for a dependent, minor, or a friend under their account
     * @param bookingDateOfBirth Date of birth of the person that the booking is for
     * @param hotelCheckin Hotel check-in date
     * @param hotelCheckout Hotel check-out date
     */
    public void addBooking(String userID, String flightID, String hotelID, int seatNum, int roomNum, String date, String bookingName, String bookingDateOfBirth, String hotelCheckin, String hotelCheckout) {
        bookingList.add(new Booking(userID, flightID, hotelID, seatNum, roomNum, date, bookingName, bookingDateOfBirth, hotelCheckin, hotelCheckout));
    }
}
