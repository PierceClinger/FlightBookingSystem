/**
 * Search class. Contains all functionality of the search procedure
 * @author The Water Coolers
 */

// Used to hold hotel and flights
import java.util.ArrayList;

public class Search {

    // Shared variables
    private ArrayList<Hotel> hotelOptions = new ArrayList<Hotel>();
    private ArrayList<Flight> flightOptions = new ArrayList<Flight>();
    private String type;
    
    // Flight specific variables
    private int flightFilterCounter;
    private String departingAirportCode;
    private String arrivingAirportCode;
    private String departureDate;
    private int numOfLayovers;
    private int carryOn;
    private int numOfCheckedBags;
    private String seatClass;
    private ArrayList<Flight> filteredFlights;

    // Hotel specific variables
    private int hotelFilterCounter;
    private int roomsToBook;
    private String location;
    private double pricePerNight;
    private int rating;
    private int smoking;
    private int numBeds;
    private String bedType;
    private int critAvailableRooms;
    private ArrayList<Room> roomsAvailable;
    private ArrayList<Hotel> filteredHotels;

    /**
     * Class constructor  
     * @param type refers to whether this is a hotel or flight search
     */
    public Search(String type) {
        this.type = type;
        this.departingAirportCode = "";
        this.arrivingAirportCode = "";
        this.departureDate = "";
        this.numOfLayovers = -1;
        this.carryOn = 0;
        this.numOfCheckedBags = -1;
        this.seatClass = "";
        this.roomsToBook = -1;
        this.location = "";
        this.pricePerNight = -1.0;
        this.rating = -1;
        this.smoking = 0;
        this.numBeds = -1;
        this.bedType = "";

        // Check whether to pull flightList or hotelList based on Search Type when constructed
        if (type.equalsIgnoreCase("flight")) {
            flightOptions = FlightList.getInstance().getAllFlights();
            this.flightFilterCounter = 0;
        } else if (type.equalsIgnoreCase("hotel")) {
            hotelOptions = HotelList.getInstance().getAllHotels();
            this.hotelFilterCounter = 0;
        }
    }

    /**
     * Method to display search results
     */
    public void displaySearchResults() {
        filteredFlights = new ArrayList<Flight>();
        filteredHotels = new ArrayList<Hotel>();

        // index of flights shown
        int index = 0;
        int index2 = 0;

        // flight case result displayer
        if (type.equalsIgnoreCase("flight")) {
            for (int i = 0;i < flightOptions.size();i++) {
                // Checking if flight passes filters
                if (showFlight(flightOptions.get(i))) {
                    filteredFlights.add(flightOptions.get(i));
                    /**
                     * Print out flights
                     * If less than 3 filters than print regular view
                     * else print detailed view
                     */
                    if (flightFilterCounter <= 3) {
                        System.out.println(flightOptions.get(i).displayToUser(index));
                        index++;
                    } else {
                        System.out.println(flightOptions.get(i).displayWithDetails(index));
                        flightOptions.get(i).displaySeatMap();
                        index++;
                    }
                }            
            }

            // If case if no results were found
            if (index == 0) {
                System.out.println("\nNo results found!");  
            } 
        }

        // hotel case result displayer
        else if (type.equalsIgnoreCase("hotel")) {
            for (int i = 0;i < hotelOptions.size();i++) {
                // Checking if hotel passes filters
                if (showHotel(hotelOptions.get(i))) {
                    filteredHotels.add(hotelOptions.get(i));

                    /**
                     * Print out hotels
                     * If less than 3 filters than print regular view
                     * else print detailed view
                     */
                    if (hotelFilterCounter <= 3) {
                        System.out.println(hotelOptions.get(i).displayToUser(index2));
                        index2++;
                    } else {
                        System.out.println(hotelOptions.get(i).displayWithDetails(this.critAvailableRooms, index2));
                        displayAvailableRooms(roomsAvailable);
                        System.out.println("\n\n");
                        index2++;
                    }
                }             
            }

            // If case if no results were found
            if (index2 == 0) {
                System.out.println("\nNo results found!");
            }
        }
    }

    /**
     * method to check if flight passes filters
     * @param flight is flight to check against filters
     * @return true if flight follows all filters
     */
    private boolean showFlight(Flight flight) {
        try {
            if ((this.departureDate != null && !departureDate.equals("")) 
                && !flight.getDepartureDate().equalsIgnoreCase(this.departureDate)) {
                    return false;
            }
            if ((this.arrivingAirportCode != null && !arrivingAirportCode.equals(""))
                && !flight.getArrivingAirportCode().equalsIgnoreCase(this.arrivingAirportCode)) {
                    return false;
            }
            if ((this.departingAirportCode != null && !departingAirportCode.equals(""))
                && !flight.getDepartingAirportCode().equalsIgnoreCase(this.departingAirportCode)) {
                    return false;
            }
            if (flight.getCarryOn() != getCarryOn() && getCarryOn() != false) {
                return false;
            }
            if (this.numOfCheckedBags > flight.getNumOfCheckedBags() && this.numOfCheckedBags != -1) {
                return false;
            }
            if (this.numOfLayovers < flight.getNumOfLayovers() && this.numOfLayovers != -1) {
                return false;
            }
            
            int counter = 0;
            for (int i = 0; i < flight.getSeats().size(); i++) {
                if (!flight.getSeats().get(i).getSeatClass().equalsIgnoreCase(this.seatClass) && (!this.seatClass.equals("") && this.seatClass != null)) {
                    counter++;
                }
            } 
            if (counter == (flight.getSeats().size()-1)) {
                return false;
            }
            
            // return true if no if statements return positive
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * method to check if hotel passes filters
     * @param hotel is hotel to check against filters
     * @return true if hotel follows all filters
     */
    private boolean showHotel(Hotel hotel) {
        try {  
            int availableRooms = 0;
            int bedTypeCounter = 0;
            int numBedsCounter = 0;
            int smokingCounter = 0;
            this.critAvailableRooms = 0;
            roomsAvailable = new ArrayList<Room>();

            for (int i = 0; i < hotel.getRooms().size(); i++) {
                if (hotel.getRooms().get(i).getAvailability() == true) {
                    availableRooms++;
                    if ((getSmoking() == hotel.getRooms().get(i).getSmokingStatus() || this.smoking == 0) 
                        && hotel.getRooms().get(i).getNumberOfBeds() >= this.numBeds
                        && (hotel.getRooms().get(i).getTypeOfBed().equalsIgnoreCase(this.bedType) || this.bedType == "")) {
                            critAvailableRooms++;
                            roomsAvailable.add(hotel.getRooms().get(i));
                    }
                }
                if (hotel.getRooms().get(i).getSmokingStatus() == true && hotel.getRooms().get(i).getAvailability() == true) {
                    smokingCounter++;
                }
                if ((hotel.getRooms().get(i).getTypeOfBed().equalsIgnoreCase(this.bedType) || this.bedType.equals("") || this.bedType == null) && hotel.getRooms().get(i).getAvailability() == true) {
                    bedTypeCounter++;
                }
                if ((hotel.getRooms().get(i).getNumberOfBeds() >= this.numBeds || this.numBeds == -1) && hotel.getRooms().get(i).getAvailability() == true) {
                    numBedsCounter++;
                }
            }

            if (this.roomsToBook > availableRooms && this.roomsToBook != -1) {
                return false;
            }
            if (getSmoking() == false && smokingCounter == availableRooms) {
                return false;
            }
            if (getSmoking() == true && smokingCounter == 0) {
                return false;
            }
            if (numBedsCounter == 0) {
                return false;
            }
            if (bedTypeCounter == 0) {
                return false;
            }
            if ((this.location != null && !this.location.equals("")) && !hotel.getLocation().equalsIgnoreCase(this.location)) {
                return false;
            }
            if (this.pricePerNight < hotel.getPricePerNight() && this.pricePerNight != -1.0) {
                return false;
            }        
            if (hotel.getRating() < this.rating && this.rating != -1) {
                return false;
            }

            // return true if no if statements return positive
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Allows calling class to set filters
     * Bulk setter used to allow for easier calling of initiating classes 
     * @param filter is filter to change
     * @param parameter to be passed onto filter
     */
    public void setFlightFilter(String filter, String parameter) {
        this.flightFilterCounter++;
        try {
            if (filter.equalsIgnoreCase("departureDate")) {
                this.departureDate = parameter;
            }
            else if (filter.equalsIgnoreCase("numOfCheckedBags")) {
                if (!parameter.equals("")) {
                    this.numOfCheckedBags = Integer.parseInt(parameter);
                }
            }
            else if (filter.equalsIgnoreCase("carryOn")) {
                if (!parameter.equals("")) {
                    setCarryOn(Boolean.parseBoolean(parameter));
                }   
            }
            else if (filter.equalsIgnoreCase("numOfLayovers")) {
                if (!parameter.equals("")) {
                    this.numOfLayovers = Integer.parseInt(parameter);
                }
            }
            else if (filter.equalsIgnoreCase("arrivingAirportCode")) {
                this.arrivingAirportCode = parameter;
            }
            else if (filter.equalsIgnoreCase("departingAirportCode")) {
                this.departingAirportCode = parameter;
            } 
            else if (filter.equalsIgnoreCase("seatClass")) {
                this.seatClass = parameter;
            }
        } catch (Exception e) {
            System.out.println(e + "\nIncorrect parameter inputted");
        }
    }

    /**
     * Allows calling class to set filters
     * Bulk setter used to allow for easier calling of initiating classes 
     * @param filter is filter to change
     * @param parameter to be passed onto filter
     */
    public void setHotelFilter(String filter, String parameter) {
        this.hotelFilterCounter++;
        try {
            if (filter.equalsIgnoreCase("rating")) {
                if (!parameter.equals("")) {
                    this.rating = Integer.parseInt(parameter);
                }
            }
            else if (filter.equalsIgnoreCase("pricePerNight")) {
                if (!parameter.equals("")) {
                    this.pricePerNight = Double.parseDouble(parameter);
                }
            }
            else if (filter.equalsIgnoreCase("location")) {
                this.location = parameter;
            }
            else if (filter.equalsIgnoreCase("roomsToBook")) {
                if (!parameter.equals("")) {
                    this.roomsToBook = Integer.parseInt(parameter);
                }
            } 
            else if (filter.equalsIgnoreCase("smokingStatus")) {
                if (!parameter.equals("")) {
                    setSmoking(Integer.parseInt(parameter));
                }
            }
            else if (filter.equalsIgnoreCase("bedType")) {
                this.bedType = parameter;
            }
            else if (filter.equalsIgnoreCase("numOfBeds")) {
                if (!parameter.equals("")) {
                    this.numBeds = Integer.parseInt(parameter);
                }
            }
        } catch (Exception e) {
            System.out.println(e + "\nIncorrect parameter inputted");
        }
    }

    /**
     * Boolean is stored as an integer to allow default values
     * To return a boolean then integer must be run through if chain
     * @return boolean of if carryOn is permitted on flight
     */
    public boolean getCarryOn() {
        if (this.carryOn > 0)
            return true;
        else if (this.carryOn < 0)
            return false;
        else
            return false;
    }

    /**
     * Setting Integer carryOn with if branching statements
     * int values greater than 0 are true
     * int values less than 0 are false
     * int values equal to 0 are default
     * @param parameter is a Boolean
     */
    private void setCarryOn(Boolean parameter) {
        if (parameter == true)
            this.carryOn = 1;
        else if (parameter == false)
            this.carryOn = -1;
        else
            this.carryOn = 0;
    }

    /**
     * Converts integer smoking value to boolean following carryOn's criteria
     * @return smoking status
     */
    public boolean getSmoking() {
        if (this.smoking > 0)
            return true;
        else if (this.smoking < 0)
            return false;
        else
            return false;
    }

    /**
     * set's integer smoking status following carryOn's criteria
     * @param parameter is passed through if chain to convert to a boolean and set local smoking value.
     */
    private void setSmoking(int parameter) {
        if (parameter > 0)
            this.smoking = 1;
        else if (parameter < 0)
            this.smoking = -1;
        else
            this.smoking = 0;
    }

    /**
     * Converts shown search results index into UUID for flight identification
     * @param i is index of flight from filtered Flights
     * @return UUID of flight at index i of filteredFlights ArrayList
     */
    public String getFlightID(int i) {
        return filteredFlights.get(i).getUUID();
    }

    /**
     * Converts shown search results index into UUID for hotel identification
     * @param i is index of hotel from filtered hotels
     * @return UUID of hotel at index i of filteredHotels ArrayList
     */
    public String getHotelID(int i) {
        return filteredHotels.get(i).getUUID();
    }

    /**
     * Prints all rooms available from arrayList<Room> parameter
     * @param rooms is the ArrayList of rooms to display
     */
    private void displayAvailableRooms(ArrayList<Room> rooms) {
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println(rooms.get(i).toString());
        }
    }

}