import java.util.ArrayList;

/**
 * Object class which is used to create a hotel object
 * @author The Water Coolers
 */
public class Hotel {
    
    private UUID UUID;
    private String name;
    private int totalRooms;
    private String location;
    private double pricePerNight;
    private int rating;
    private ArrayList<Room> rooms = new ArrayList<Room>();
    private ArrayList<String> amenities = new ArrayList<String>();

    /**
     * Creates an instance of hotel
     * @param UUID Hotel unique ID
     * @param name Hotel name
     * @param totalRooms Total rooms in Hotel
     * @param location Hotel location
     * @param pricePerNight Price per night
     * @param rating Hotel rating
     * @param rooms ArrayList that contains room objects in hotel
     * @param amenities ArrayList which contains hotel amenities
     */
    public Hotel(String UUID, String name, int totalRooms, String location, double pricePerNight, int rating, ArrayList<Room> rooms, ArrayList<String> amenities) {
        this.UUID = new UUID(UUID);
        this.name = name;
        this.totalRooms = totalRooms;
        this.location = location;
        this.pricePerNight = pricePerNight;
        this.rating = rating;
        this.rooms = rooms;
        this.amenities = amenities;
    }
    
    /**
     * Returns hotel unique ID
     * @return Hotel unique ID
     */
    public String getUUID() {
        return UUID.getUUID();
    }
    
    /**
     * Returns hotel name
     * @return Hotel name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns total rooms in hotel
     * @return Total rooms in hotel
     */
    public int getTotalRooms() {
        return totalRooms;
    }

    /**
     * Returns location of hotel
     * @return Location of hotel
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns price per night
     * @return Price per night
     */
    public double getPricePerNight() {
        return pricePerNight;
    }

    /**
     * Returns rating of hotel
     * @return Rating of hotel
     */
    public int getRating() {
        return rating;
    }

    /**
     * Returns ArrayList of room objects
     * @return ArrayList of room objects
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * Returns ArrayList of room amenities
     * @return ArrayList of room amenities
     */
    public ArrayList<String> getAmenities() {
        return amenities;
    }

    /**
     * Sets the ArrayList of rooms to the given parameter
     * @param rooms ArrayList of rooms
     */
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * Sets the ArrayList of amentites to the given parameter
     * @param amenities ArrayList of amentites
     */
    public void setAmenities(ArrayList<String> amenities) {
        this.amenities = amenities;
    }

    /**
     * Adds a room to the rooms ArrayList
     * @param room Room object
     */
    public void addRoom(Room room) {
        rooms.add(room);
    }

    /**
     * Adds a amenity to the amenities ArrayList
     * @param amenity Amenity
     */
    public void addAmenity(String amenity) {
        amenities.add(amenity);
    }

    /**
     * Converts instance of hotel information into a string
     * @return Hotel information
     */
    public String toString() {
        String ret;
        ret = "Name : " + name + "\n";
        ret = ret + "Total Rooms: " + totalRooms + "\n";
        ret = ret + "Location: " + location + "\n";
        ret = ret + "Price per Night: " + pricePerNight + "\n";
        ret = ret + "Rating: " + rating + "\n";

        return ret;
    }

    /**
     * Converts instance of hotel information into a string
     * Used during search
     * @param i Index of hotel in ArrayLsit
     * @return Hotel information
     */
    public String displayToUser(int i) {
        String ret;
        ret = "\nHOTEL OPTION " + i + "\n";
        ret += "Name : " + name + "\n";
        ret += "Location: " + location + "\n";
        ret += "Rating: " + rating + "\n";
        ret += "Total Rooms: " + totalRooms + "\n";

        return ret;
    }

    /**
     * Converts instance of hotel information into a string with details
     * Used during search
     * @param counter Number of available rooms based of filter
     * @param i Index of hotel in ArrayLsit
     * @return Hotel information
     */
    public String displayWithDetails(int counter, int i) {
        String ret;
        ret = "\nHOTEL OPTION " + i + "\n";
        ret = ret + "Name : " + name + "\n";
        ret = ret + "Location: " + location + "\n";
        ret = ret + "Rating: " + rating + "\n";
        ret = ret + "Price per Night: " + pricePerNight + "\n";
        ret = ret + "Amenities: " + "\n";
        for(String amenity : amenities) {
            ret = ret + "  - " + amenity + "\n";
        }
        ret = ret + "\nNumber of Available Rooms (based on your criteria): " + counter + "\n";
        
        return ret;
    }
    
    /**
     * Displays a room map of the hotel
     */
    public void displayRooms() {
    	for(int i = 0; i < rooms.size(); i++) {
    		if(rooms.get(i).getRoomNumber()%100 == 1) {
    			System.out.println();
    		}
    		if(rooms.get(i).getAvailability()) {
    		System.out.print(rooms.get(i).getRoomNumber());
    		}
    		else {
    			System.out.print("X  ");
    		}
    		System.out.print("   ");
    	}
    	System.out.println();
    }
}
