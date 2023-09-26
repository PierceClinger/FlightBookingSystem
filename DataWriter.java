import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.*;

/**
 * Data interaction class which writes back to the json files
 * @author The Water Coolers
 */
public class DataWriter extends DataConstants {
    /**
     * Writes the current contents of the userList to the users.json file
     */
    public static void saveUsers() {
        UserList users = UserList.getInstance();
        ArrayList<RegisteredUser> userList = users.getAllUsers();
        JSONArray jsonUsers = new JSONArray();

        for(int i = 0; i < userList.size(); i++) {
            jsonUsers.add(getUserJSON(userList.get(i)));
        }

        try(FileWriter file = new FileWriter(USERS_FILENAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the current content of the bookingList to the bookings.json file. Also writes the flightList and hotelList content to their respective json files
     */
    public static void saveBookings() {
        BookingList bookings = BookingList.getInstance();
        FlightList flights = FlightList.getInstance();
        HotelList hotels = HotelList.getInstance();
        ArrayList<Booking> bookingList = bookings.getAllBookings();
        ArrayList<Flight> flightList = flights.getAllFlights();
        ArrayList<Hotel> hotelList = hotels.getAllHotels();
        ArrayList<Seat> seatList = new ArrayList<Seat>();
        ArrayList<Room> roomList = new ArrayList<Room>();
        ArrayList<String> amenities = new ArrayList<String>();
        JSONArray jsonBookings = new JSONArray();
        JSONArray jsonFlights = new JSONArray();
        JSONArray jsonHotels = new JSONArray();

        for(int i = 0; i < bookingList.size(); i++) {
            jsonBookings.add(getBookingJSON(bookingList.get(i)));
        }
        for(int j = 0; j < flightList.size(); j++) {
            seatList = flightList.get(j).getSeats();
            jsonFlights.add(getFlightJSON(flightList.get(j), seatList));
        }
        for(int k = 0; k < hotelList.size(); k++) {
            roomList = hotelList.get(k).getRooms();
            amenities = hotelList.get(k).getAmenities();
            jsonHotels.add(getHotelJSON(hotelList.get(k), roomList, amenities));
        }

        try(FileWriter file = new FileWriter(BOOKINGS_FILENAME)) {
            file.write(jsonBookings.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(FileWriter file = new FileWriter(FLIGHTS_FILENAME)) {
            file.write(jsonFlights.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(FileWriter file = new FileWriter(HOTELS_FILENAME)) {
            file.write(jsonHotels.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a JSONObject which contains all the necessary attributes for a specific user to be written to the file
     * @param user The user to obtain attributes from
     * @return A JSONObject storing all the details
     */
    public static JSONObject getUserJSON(RegisteredUser user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(PERSONAL_USER_ID, user.getUUID());
        userDetails.put(NAME, user.getName());
        userDetails.put(USERNAME, user.getUsername());
        userDetails.put(EMAIL, user.getEmail());
        userDetails.put(PASSWORD, user.getPassword());
        userDetails.put(DATE_OF_BIRTH, user.getDateOfBirth());
        userDetails.put(FREQ_FLYER, user.getFreqFlyer());

        return userDetails;
    }

    /**
     * Returns a JSONObject which contains all the necessary attributes for a specific booking to be written to the file
     * @param booking The booking to obtain attributes from
     * @return A JSONObject storing all the details
     */
    public static JSONObject getBookingJSON(Booking booking) {
        JSONObject bookingDetails = new JSONObject();
        String tempStr = "" + booking.getSeatNumber();
        String tempStr2 = "" + booking.getRoomNumber();
        bookingDetails.put(BOOKINGS_ID, booking.getUUID());
        bookingDetails.put(USER_ID, booking.getUserID());
        bookingDetails.put(FLIGHT_ID, booking.getFlightID());
        bookingDetails.put(BOOKING_SEAT_NUMBER, tempStr);
        bookingDetails.put(HOTEL_ID, booking.getHotelID());
        bookingDetails.put(BOOKING_ROOM_NUMBER, tempStr2);
        bookingDetails.put(DATE_OF_BOOKING, booking.getDateOfBooking());
        bookingDetails.put(BOOKING_NAME, booking.getBookingName());
        bookingDetails.put(BOOKING_DATE_OF_BIRTH, booking.getBookingDateOfBirth());
        bookingDetails.put(BOOKING_HOTEL_CHECKIN, booking.getHotelCheckin());
        bookingDetails.put(BOOKING_HOTEL_CHECKOUT, booking.getHotelCheckout());

        return bookingDetails;
    }

    /**
     * Returns a JSONObject which contains all the necessary attributes for a specific flight to be written to the file
     * @param flight The flight to obtain attributes from
     * @param seats The ArrayList of seats which pertains to the specific flight that is passed in
     * @return A JSONObject which contains all the details for a flight
     */
    public static JSONObject getFlightJSON(Flight flight, ArrayList<Seat> seats) {
        JSONObject flightDetails = new JSONObject();
        JSONArray jsonSeats = new JSONArray();
        String tempStr = "" + flight.getNumOfLayovers();
        String tempStr2 = "" + flight.getNumOfCheckedBags();
        String tempStr3 = "" + flight.getCapacity();
        String tempStr4 = "" + flight.getTotalTravelTime();
        flightDetails.put(FLIGHTS_ID, flight.getUUID());
        flightDetails.put(FLIGHT_START_LOCATION, flight.getStartLocation());
        flightDetails.put(FLIGHT_DEPARTING_AIRPORT_CODE, flight.getDepartingAirportCode());
        flightDetails.put(FLIGHT_ARRIVING_AIRPORT_CODE, flight.getArrivingAirportCode());
        flightDetails.put(FLIGHT_DESTINATION, flight.getDestination());
        flightDetails.put(FLIGHT_LAYOVER, tempStr);
        flightDetails.put(TRANSFER_LOCATION, flight.getTransferLocation());
        flightDetails.put(FLIGHT_AIRLINE, flight.getAirline());
        flightDetails.put(FLIGHT_DEPARTURE_DATE, flight.getDepartureDate());
        flightDetails.put(FLIGHT_ARRIVAL_DATE, flight.getArriveDate());
        flightDetails.put(FLIGHT_DEPARTURE_TIME, flight.getDepartureTime());
        flightDetails.put(FLIGHT_ARRIVAL_TIME, flight.getArriveTime());
        flightDetails.put(TOTAL_TRAVEL_TIME, tempStr4);
        flightDetails.put(FLIGHT_CARRY_ON, flight.getCarryOn());
        flightDetails.put(FLIGHT_NUM_OF_CHECKED_BAGS, tempStr2);
        flightDetails.put(FLIGHT_CAPACITY, tempStr3);
        for(int i = 0; i < seats.size(); i++) {
            String temporary = "" + seats.get(i).getSeatNumber();
            JSONObject seatDetails = new JSONObject();
            seatDetails.put(SEAT_AVAILABILITY, seats.get(i).getAvailability());
            seatDetails.put(SEAT_NUMBER, temporary);
            seatDetails.put(SEAT_CLASS, seats.get(i).getSeatClass());
            jsonSeats.add(seatDetails);
        }
        flightDetails.put(FLIGHT_SEAT, jsonSeats);

        return flightDetails;
    }

    /**
     * Returns a JSONObject that contains all the necessary attributes for a specific hotel to be written to the file
     * @param hotel The hotel to obtain attributes from
     * @param rooms The ArrayList of rooms that pertains to the specific hotel passed in
     * @param amenities The ArrayList of Strings that contains the list of amenities (specific to the hotel)
     * @return A JSONObject that contains all the details for a hotel
     */
    public static JSONObject getHotelJSON(Hotel hotel, ArrayList<Room> rooms, ArrayList<String> amenities) {
        String temp1 = "" + hotel.getTotalRooms();
        String temp2 = "" + hotel.getRating();
        String temp3 = "" + hotel.getPricePerNight();
        JSONObject hotelDetails = new JSONObject();
        JSONArray jsonRooms = new JSONArray();
        JSONArray jsonAmenities = new JSONArray();
        hotelDetails.put(HOTELS_ID, hotel.getUUID());
        hotelDetails.put(HOTEL_NAME, hotel.getName());
        hotelDetails.put(HOTEL_TOTAL_ROOMS, temp1);
        hotelDetails.put(HOTEL_LOCATION, hotel.getLocation());
        hotelDetails.put(HOTEL_PRICE_PER_NIGHT, temp3);
        hotelDetails.put(HOTEL_RATING, temp2);
        for(int i = 0; i < amenities.size(); i++) {
            jsonAmenities.add(amenities.get(i));
        }
        hotelDetails.put(AMENITIES, jsonAmenities);
        for(int i = 0; i < rooms.size(); i++) {
            String temp4 = "" + rooms.get(i).getRoomNumber();
            String temp5 = "" + rooms.get(i).getNumberOfBeds();
            JSONObject roomDetails = new JSONObject();
            roomDetails.put(ROOM_AVAILABILITY, rooms.get(i).getAvailability());
            roomDetails.put(ROOM_NUMBER, temp4);
            roomDetails.put(SMOKING_STATUS, rooms.get(i).getSmokingStatus());
            roomDetails.put(NUM_OF_BEDS, temp5);
            roomDetails.put(TYPE_OF_BED, rooms.get(i).getTypeOfBed());
            jsonRooms.add(roomDetails);
        }
        hotelDetails.put(ROOM, jsonRooms);

        return hotelDetails;
    }
}
