import java.util.ArrayList;

/**
 * @author The Water Coolers
 * Data storage class that connects to the hotels.json, pulling the data into an ArrayList of type Hotel
 */
public class HotelList {
    private static HotelList hotelList;
    public static ArrayList<Hotel> hotels;
    
    /**
     * Default constructor: Calls the DataLoader and populates an ArrayList of type Hotel with all the current hotel data
     */
    private HotelList() {
        hotels = DataLoader.getHotels();
    }

    /**
     * Singleton method which allows for only one instance of the HotelList to exist. Checks if an instance exists, if it does then return it, if not make a new one
     * @return The instance of HotelList
     */
    public static HotelList getInstance() {
        if (hotelList == null) {
            hotelList = new HotelList();
        }
        return hotelList;
    }

    /**
     * Returns the ArrayList of hotel data
     * @return The ArrayList of current hotel data
     */
    public ArrayList<Hotel> getAllHotels() {
        return hotels;
    }

    /**
     * Returns a specific hotel from the database of hotels
     * @param hotelID The ID of the hotel that should be returned
     * @return The hotel
     */
    public static Hotel getHotel(String hotelID) {
        for(Hotel hotel : hotels) {
            if(hotel.getUUID().equalsIgnoreCase(hotelID)) {
                return hotel;
            } 
        }
        return null;
    }
}
