import java.io.FileReader;
import java.util.ArrayList;
import org.json.simple.*;
import org.json.simple.parser.*;

/**
 * This class reads in each JSON file and stores the JSON information into its respective ArrayList
 * @author The Water Coolers
 */
public class DataLoader extends DataConstants {
    private static ArrayList<Flight> flights = new ArrayList<Flight>();
    private static ArrayList<Hotel> hotels = new ArrayList<Hotel>();
    private static ArrayList<RegisteredUser> users = new ArrayList<RegisteredUser>();
    private static ArrayList<Booking> bookings = new ArrayList<Booking>();

    public DataLoader() {}

    /**
     * Loads flights.json into the Flights ArrayList
     */
    private static void loadFlights() {
        try {
            FileReader reader = new FileReader(FLIGHTS_FILENAME);
            JSONArray flightsJSON = (JSONArray) new JSONParser().parse(reader);
            
            for (int i = 0; i < flightsJSON.size(); i++) {
                JSONObject flightJSON = (JSONObject)flightsJSON.get(i);
                String id = (String)flightJSON.get(FLIGHTS_ID);
                String startLocation = (String)flightJSON.get(FLIGHT_START_LOCATION);
                String departingAirportCode = (String)flightJSON.get(FLIGHT_DEPARTING_AIRPORT_CODE);
                String arrivingAirportCode = (String)flightJSON.get(FLIGHT_ARRIVING_AIRPORT_CODE);
                String destination = (String)flightJSON.get(FLIGHT_DESTINATION);
                String layover = (String)flightJSON.get(FLIGHT_LAYOVER);
                String airline = (String)flightJSON.get(FLIGHT_AIRLINE);
                String departureDate = (String)flightJSON.get(FLIGHT_DEPARTURE_DATE);
                String arrivalDate = (String)flightJSON.get(FLIGHT_ARRIVAL_DATE);
                String departureTime = (String)flightJSON.get(FLIGHT_DEPARTURE_TIME);
                String arrivalTime = (String)flightJSON.get(FLIGHT_ARRIVAL_TIME);
                String travelTime = (String)flightJSON.get(TOTAL_TRAVEL_TIME);
                double totalTime = Double.parseDouble(travelTime);
                boolean carryOn = (boolean)flightJSON.get(FLIGHT_CARRY_ON);
                int numOfLayovers = Integer.parseInt(layover);
                String numOfCheckedBags = (String)flightJSON.get(FLIGHT_NUM_OF_CHECKED_BAGS);
                String capacity = (String)flightJSON.get(FLIGHT_CAPACITY);
                int checkBags = Integer.parseInt(numOfCheckedBags);
                int cap = Integer.parseInt(capacity);
                String transferLocation = (String)flightJSON.get(TRANSFER_LOCATION);
                
                ArrayList<Seat> seats = new ArrayList<Seat>();
                JSONArray seatsJSON = (JSONArray) flightJSON.get(FLIGHT_SEAT);
                for (int j = 0; j < seatsJSON.size(); j++) {
                    JSONObject seatJSON = (JSONObject)seatsJSON.get(j);
                    String seatNumber = (String)seatJSON.get(SEAT_NUMBER);
                    int number = Integer.parseInt(seatNumber);
                    String seatClass = (String)seatJSON.get(SEAT_CLASS);
                    boolean seatAvailability = (boolean)seatJSON.get(SEAT_AVAILABILITY);

                    Seat seat = new Seat(number, seatClass, seatAvailability);
                    seats.add(seat);
                }
               
                Flight flight = new Flight(id, startLocation, departingAirportCode, arrivingAirportCode, destination, numOfLayovers, airline, departureDate, arrivalDate, departureTime, arrivalTime, totalTime, carryOn, checkBags, cap, transferLocation, seats);
                flights.add(flight);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads hotels.json into the Hotels ArrayList
     */
    private static void loadHotels() {
        try {
            FileReader reader = new FileReader(HOTELS_FILENAME);
            JSONArray hotelsJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < hotelsJSON.size(); i++) {
                JSONObject hotelJSON = (JSONObject)hotelsJSON.get(i);
                String id = (String)hotelJSON.get(HOTELS_ID);
                String name = (String)hotelJSON.get(HOTEL_NAME);
                String totalRooms = (String)hotelJSON.get(HOTEL_TOTAL_ROOMS);
                int numRooms = Integer.parseInt(totalRooms);
                String location = (String)hotelJSON.get(HOTEL_LOCATION);
                String pricePerNight = (String)hotelJSON.get(HOTEL_PRICE_PER_NIGHT);
                double price = Double.parseDouble(pricePerNight);
                String hotelRating = (String)hotelJSON.get(HOTEL_RATING);
                int rating = Integer.parseInt(hotelRating);

                ArrayList<String> amenities = new ArrayList<String>();
                JSONArray amenitiesJSON = (JSONArray)hotelJSON.get(AMENITIES);
                for (int k = 0; k < amenitiesJSON.size(); k++) {
                    String amenity = (String)amenitiesJSON.get(k);

                    amenities.add(amenity);
                }

                ArrayList<Room> rooms = new ArrayList<Room>();
                JSONArray roomsJSON = (JSONArray) hotelJSON.get(ROOM);
                for (int j = 0; j < roomsJSON.size(); j++) {
                    JSONObject roomJSON = (JSONObject)roomsJSON.get(j);
                    String roomNumber = (String)roomJSON.get(ROOM_NUMBER);
                    int number = Integer.parseInt(roomNumber);
                    boolean smokingStatus = (boolean)roomJSON.get(SMOKING_STATUS);
                    String numOfBeds = (String)roomJSON.get(NUM_OF_BEDS);
                    int beds = Integer.parseInt(numOfBeds);
                    boolean availability = (boolean)roomJSON.get(ROOM_AVAILABILITY);
                    String typeOfBed = (String)roomJSON.get(TYPE_OF_BED);

                    Room room = new Room(number, smokingStatus, beds, availability, typeOfBed);
                    rooms.add(room);
                }

                Hotel hotel = new Hotel(id, name, numRooms, location, price, rating, rooms, amenities);
                hotels.add(hotel);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads users.json into the RegisteredUsers ArrayList
     */
    private static void loadUsers() {
        try {
            FileReader reader = new FileReader(USERS_FILENAME);
            JSONArray usersJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < usersJSON.size(); i++) {
                JSONObject userJSON = (JSONObject)usersJSON.get(i);
                String id = (String)userJSON.get(PERSONAL_USER_ID);
                String name = (String)userJSON.get(NAME);
                String username = (String)userJSON.get(USERNAME);
                String email = (String)userJSON.get(EMAIL);
                String password = (String)userJSON.get(PASSWORD);
                String dateOfBirth = (String)userJSON.get(DATE_OF_BIRTH);
                boolean freqFlyer = (boolean)userJSON.get(FREQ_FLYER);

                RegisteredUser user = new RegisteredUser(id, "Registered", name, username, email, password, dateOfBirth, freqFlyer);
                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads bookings.json into the Bookings ArrayList
     */
    private static void loadBookings() {
        try {
            FileReader reader = new FileReader(BOOKINGS_FILENAME);
            JSONArray bookingsJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < bookingsJSON.size(); i++) {
                JSONObject bookingJSON = (JSONObject)bookingsJSON.get(i);
                String id = (String)bookingJSON.get(BOOKINGS_ID);
                String userId = (String)bookingJSON.get(USER_ID);
                String flightId = (String)bookingJSON.get(FLIGHT_ID);
                String hotelId = (String)bookingJSON.get(HOTEL_ID);
                String seatNumber = (String)bookingJSON.get(BOOKING_SEAT_NUMBER);
                String roomNumber = (String)bookingJSON.get(BOOKING_ROOM_NUMBER);
                String dateOfBooking = (String)bookingJSON.get(DATE_OF_BOOKING);
                String bookingName = (String)bookingJSON.get(BOOKING_NAME);
                String bookingDateOfBirth = (String)bookingJSON.get(BOOKING_DATE_OF_BIRTH);
                String hotelCheckinDate = (String)bookingJSON.get(BOOKING_HOTEL_CHECKIN);
                String hotelCheckoutDate = (String)bookingJSON.get(BOOKING_HOTEL_CHECKOUT);

                int seatNum;
                int roomNum;

                if (seatNumber.equals("None")) {
                    seatNum = -1;
                } else {
                    seatNum = Integer.parseInt(seatNumber);
                }

                if (roomNumber.equals("None")) {
                    roomNum = -1;
                } else {
                    roomNum = Integer.parseInt(roomNumber);
                }

                Booking booking = new Booking(id, userId, flightId, hotelId, seatNum, roomNum, dateOfBooking, bookingName, bookingDateOfBirth, hotelCheckinDate, hotelCheckoutDate);
                bookings.add(booking);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a populated Flight ArrayList
     * @return Populated Flight ArrayList
     */
    public static ArrayList<Flight> getFlights() {
        loadFlights();
        return flights;
    }

    /**
     * Returns a populated Hotel ArrayList
     * @return Populated Hotel ArrayList
     */
    public static ArrayList<Hotel> getHotels() {
        loadHotels();
        return hotels;
    }

    /**
     * Returns a populated RegisteredUser ArrayList
     * @return Populated RegisteredUser ArrayList
     */
    public static ArrayList<RegisteredUser> getUsers() {
        loadUsers();
        return users;
    }

    /**
     * Returns a populated Bookings ArrayList
     * @return Populated Bookings ArrayList
     */
    public static ArrayList<Booking> getBookings() {
        loadBookings();
        return bookings;
    }
}
