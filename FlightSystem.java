import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;

public class FlightSystem {
    private FlightList flightList;
    private HotelList hotelList;
    private UserList userList;
    private BookingList bookingList;
    private RegisteredUser currentUser;
    private Search flightSearch;
    private Search hotelSearch;
/**
 * This class handles all the different methods and classes for booking hotels and flights as well as search
 * @author The Water Coolers
 */
    public FlightSystem() {
        flightList = FlightList.getInstance();
        hotelList = HotelList.getInstance();
        userList = UserList.getInstance();
        bookingList = BookingList.getInstance();
        flightSearch = new Search("flight");
        hotelSearch = new Search("hotel");
    }
    /** 
     * Class constructor
     */

    public boolean login(String userName, String password) {
        if(userList.haveUser(userName)) {
            if(userList.getUser(userName).getPassword().equals(password)) {
                currentUser = userList.getUser(userName);
                return true;
            }
            System.out.println("Incorrect password");
            return false;
        }
        return false;
    }
    /**
     * Method for verifying credentials and selecting correct user
     * @return boolean for successful login 
     * 
     */

    public void resetSearch() {
    	flightSearch = new Search("flight");
        hotelSearch = new Search("hotel");
    }
    /**
     * Reinitializes search for each new search
     */

    public boolean createAccount(String name, String userName, String email, String password, String dateOfBirth, boolean freqFlyer) {
        return userList.addUser(name, userName, password, email, dateOfBirth, freqFlyer);
    }
    /**
     * Adds new user to the database
     * @return boolean for successful attempt
     */

    public RegisteredUser getCurrentUser() {
        return currentUser;
    }
    /**
     * @return current user
     */

    public void searchFlights() {
        flightSearch.displaySearchResults();
    }
    /**
     * Calls the display search method to print the results of the flight search
     */

    public void setFlightFilter(String filter, String parameter) {
        flightSearch.setFlightFilter(filter, parameter);
        
    }
    /**
     * Sets filters
     */

    public void searchHotels() {
        hotelSearch.displaySearchResults();
    }
    /**
     * Calls the display method for hotel search to print results
     */
     
    public void setHotelFilter(String filter, String parameter) {
        hotelSearch.setHotelFilter(filter, parameter);
       
    }
    /**
     * Sets hotel filters
     */

    public String getFlightID(int i) {
        return flightSearch.getFlightID(i);
    }
    /**
     * @return flight ID
     */

    public String getHotelID(int i) {
        return hotelSearch.getHotelID(i);
    }
    /**
     * @return hotel ID
     */

    public void bookFlight(int seatNumber, String bookingName, String flightID, String bookingDateOfBirth) {
        bookingList.addBooking(currentUser.getUUID(), flightID, "None", seatNumber, -1, getCurrentDate(), bookingName, bookingDateOfBirth, null, null);
        for(Flight flight : flightList.getAllFlights()) {
            if(flight.getUUID().equalsIgnoreCase(flightID)) {
                for(Seat seat : flight.getSeats()) {
                    if(seat.getSeatNumber() == seatNumber) {
                        seat.setAvailability(false);
                    }
                }
            }
        }
        checkFreqFlyer();
    }
    /**
     * Adds flight booking to the booking list and flips the availability of the selected seat
     */

    public void bookHotel(int roomNumber, String bookingName, String hotelID, String bookingDateOfBirth, String hotelCheckin, String hotelCheckout) {
        bookingList.addBooking(currentUser.getUUID(), "None", hotelID, -1, roomNumber, getCurrentDate(), bookingName, bookingDateOfBirth, hotelCheckin, hotelCheckout);
        for(Hotel hotel : hotelList.getAllHotels()) {
            if(hotel.getUUID().equalsIgnoreCase(hotelID)) {
                for(Room room : hotel.getRooms()) {
                    if(room.getRoomNumber() == roomNumber) {
                        room.setAvailability(false);
                    }
                }
            }
        }
    }
    /**
     * Adds hotel booking to the booking list
     */

    private void checkFreqFlyer() {
        int freqFlyerCounter = 0;
        for(Booking booking : bookingList.getAllBookings()) {
            if(booking.getUserID().equalsIgnoreCase(currentUser.getUUID())) {
                if(!booking.getFlightID().equalsIgnoreCase("None")) {
                    freqFlyerCounter++;
                }
            }
        }
        if(freqFlyerCounter >= 3) {
            currentUser.setFreqFlyer(true);
        }
    }
    /**
     * method for checking the status of and updating frequent flyers
     */

    public void printTicket() {
        int counter = 0;
        for(Booking booking : bookingList.getAllBookings()) {
            if(currentUser.getUUID().equalsIgnoreCase(booking.getUserID())) {
                System.out.println(booking.printInfo(flightList, hotelList) + "\n");
                counter++;
            }
        }
        if(counter == 0) {
            System.out.println("No bookings found under this account!");
        }
    }
    /**
     * Displays the ticket to the user
     */

    public void printOutTicket() throws Exception{
        int counter = 0;
        File file = new File("Booking.txt");
        FileWriter fW = new FileWriter(file);
        for(Booking booking : bookingList.getAllBookings()) {
            if(currentUser.getUUID().equalsIgnoreCase(booking.getUserID())) {
                fW.write(booking.printInfo(flightList, hotelList) + "\n");
                fW.flush();
                counter++;
            }

        }
        if(counter == 0) {
            System.out.println("No bookings found under this account!");
        }
    }
    /**
     * prints the ticket to a file called booking.txt
     * @throws Exception
     */

    public void logout() {
        userList.saveUsers();
        bookingList.saveBookings();
    }
    /**
     * Saves changes to lists before logout of account
     * 
     */

    public String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/YYYY");
        Date date = new Date();
        String day = formatter.format(date);
        return day;
    }
    /**
     * @return current date
     */
}