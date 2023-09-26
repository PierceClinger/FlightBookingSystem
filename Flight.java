// ArrayList used to hold flight's seats
import java.util.ArrayList;

/**
 * Flight class. Data class containing attributes of a flight
 * @author The Water Coolers
 */
public class Flight {
    
    /**
     * Declaring private class variables
     * initializing seats ArrayList
     */
    private UUID UUID;
    private String startLocation;
    private String departingAirportCode;
    private String arrivingAirportCode;
    private String destination;
    private int numOfLayovers;
    private String airline;
    private String departureDate;
    private String arriveDate;
    private String departureTime;
    private String arriveTime;
    private double totalTravelTime;
    private boolean carryOn;
    private int numOfCheckedBags;
    private int capacity;
    private String transferLocation;
    private ArrayList<Seat> seats = new ArrayList<Seat>();
    private String[][] seatMap;

    /**
     * Parameterized constructor for Flight Class
     * @param UUID sets local UUID
     * @param startLocation sets local startLocation
     * @param departingAirportCode sets local departingAirportCode
     * @param arrivingAirportCode sets local arrivingAirportCode
     * @param destination sets local destination
     * @param numOfLayovers sets local numOfLayover
     * @param airline sets local airline
     * @param departureDate sets local departureDate
     * @param arriveDate sets local arriveDate
     * @param departureTime sets local departureTime
     * @param arrivalTime sets local arrivalTime
     * @param totalTravelTime sets local totalTravelTime
     * @param carryOn sets local Carryon
     * @param numOfCheckedBags sets local numOfCheckedBags
     * @param capacity sets local capacity
     * @param transferLocation sets local transferLocation
     * @param seats sets local seats
     */
    public Flight(String UUID, String startLocation, String departingAirportCode, String arrivingAirportCode, String destination, int numOfLayovers, String airline, String departureDate, String arriveDate, String departureTime, String arrivalTime, double totalTravelTime, boolean carryOn, int numOfCheckedBags, int capacity, String transferLocation, ArrayList<Seat> seats) {
        this.UUID = new UUID(UUID);
        this.startLocation = startLocation;
        this.departingAirportCode = departingAirportCode;
        this.arrivingAirportCode = arrivingAirportCode;
        this.destination = destination;
        this.numOfLayovers = numOfLayovers;
        this.airline = airline;
        this.departureDate = departureDate;
        this.arriveDate = arriveDate;
        this.departureTime = departureTime;
        this.arriveTime = arrivalTime;
        this.totalTravelTime = totalTravelTime;
        this.carryOn = carryOn;
        this.numOfCheckedBags = numOfCheckedBags;
        this.capacity = capacity;
        this.transferLocation = transferLocation;
        this.seats = seats;
        this.seatMap = new String[3][5];
    }

    /**
     * @return Flight information as a string
     */
    public String toString() {
        String ret;
        ret = "Start Location : " + startLocation + "\n";
        ret = ret + "Departing Airport Code: " + departingAirportCode + "\n";
        ret = ret + "Arriving Airport Code: " + arrivingAirportCode + "\n";
        ret = ret + "Destination: " + destination + "\n";
        ret = ret + "Number of Transfers: " + numOfLayovers + "\n";
        ret = ret + "Airline: " + airline + "\n";
        ret = ret + "Departure Date: " + departureDate + "\n";
        ret = ret + "Departure Time: " + departureTime + "\n";
        ret = ret + "Arrive Time: " + arriveTime + "\n";
        ret = ret + "Total Estimated Travel Time: " + totalTravelTime + "\n";
        ret = ret + "Carry On: " + carryOn + "\n";
        ret = ret + "Number of Checked Bags: " + numOfCheckedBags + "\n";
        ret = ret + "Capacity: " + capacity + "\n";
        ret = ret + "Transfer Location(s): " + transferLocation + "\n";

        return ret;
    }

    /**
     * Returns basic flight information
     * @param i is the flight option
     * @return basic flight information as a string
     */
    public String displayToUser(int i) {
        String ret;
        ret = "\nFLIGHT OPTION " + i + "\n";
        ret = ret + "Start Location : " + startLocation + "\n";
        ret = ret + "Destination: " + destination + "\n";
        ret = ret + "Departure Date: " + departureDate + "\n";
        ret = ret + "Departure Time: " + departureTime + "\n";

        return ret;
    }

    /**
     * Returns detailed flight information
     * @param i is the flight option
     * @return detailed flight information as a string
     */
    public String displayWithDetails(int i) {
        String ret;
        ret = "\nFLIGHT OPTION " + i+ "\n";
        ret = ret + "Start Location : " + startLocation + "\n";
        ret = ret + "Departing Airport Code: " + departingAirportCode + "\n";
        ret = ret + "Arriving Airport Code: " + arrivingAirportCode + "\n";
        ret = ret + "Destination: " + destination + "\n";
        ret = ret + "Number of Transfers: " + numOfLayovers + "\n";
        ret = ret + "Transfer Location(s): " + transferLocation + "\n";
        ret = ret + "Airline: " + airline + "\n";
        ret = ret + "Departure Date: " + departureDate + "\n";
        ret = ret + "Departure Time: " + departureTime + "\n";
        ret = ret + "Arrive Time: " + arriveTime + "\n";
        ret = ret + "Total Estimated Travel Time: " + totalTravelTime + "\n";
        if(carryOn == false) {
            ret = ret + "Carry On: Not Allowed" + "\n";
        } else {
            ret = ret + "Carry On: Allowed" + "\n";
        }
        ret = ret + "Maximum Number of Checked Bags Allowed: " + numOfCheckedBags + "\n";
        ret = ret + "Capacity: " + capacity + "\n";

        return ret;
    }

    /**
     * Prints flight's seat map
     */
    public void displaySeatMap() {
        int k = 0;                       
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 5; j++) {
                if(i == 2 && j == 4) {
                    seatMap[i][j] = " ";
                } else if(j == 2) {
                    seatMap[i][j] = " ";
                } else if(seats.get(k).getAvailability() == false) {
                    seatMap[i][j] = "X";
                    k++;
                } else {
                    seatMap[i][j] = String.valueOf(seats.get(k).getSeatNumber());
                    k++;
                }
            }
        }
        //printing the map
        int q = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 5; j++) {
                if(i == 2 && j == 4) {
                    System.out.print("  ");
                } else if(j == 2) {
                    System.out.print("   ");
                } else if(q < 10) {
                    System.out.print(seatMap[i][j] + "  ");
                    q++;
                } else {
                    System.out.print(seatMap[i][j] + " ");
                    q++;
                }
            }
            System.out.println("\n-----------------------------------\n");
        }
    }

    // returns local UUID
    public String getUUID() {
        return UUID.getUUID();
    }

    // returns local startLocation
    public String getStartLocation() {
        return startLocation;
    }

    // returns local departingAirportCode
    public String getDepartingAirportCode() {
        return departingAirportCode;
    }

    // returns local arrivingAirportCode
    public String getArrivingAirportCode() {
        return arrivingAirportCode;
    }

    // returns local destination
    public String getDestination() {
        return destination;
    }

    // returns local numOfLayovers
    public int getNumOfLayovers() {
        return numOfLayovers;
    }

    // returns local airline
    public String getAirline() {
        return airline;
    }

    // returns local departureDate
    public String getDepartureDate() {
        return departureDate;
    }

    // returns local arriveDate
    public String getArriveDate() {
        return arriveDate;
    }

    // returns local departureTime
    public String getDepartureTime() {
        return departureTime;
    }

    // returns local arriveTime
    public String getArriveTime() {
        return arriveTime;
    }

    // returns local totalTravelTime
    public double getTotalTravelTime() {
        return totalTravelTime;
    }

    // returns local carryOn
    public boolean getCarryOn() {
        return carryOn;
    }

    // returns local numOfCheckedBags
    public int getNumOfCheckedBags() {
        return numOfCheckedBags;
    }

    // returns local capacity
    public int getCapacity() {
        return capacity;
    }

    // returns local transferLocation
    public String getTransferLocation() {
        return transferLocation;
    }

    // returns local seats
    public ArrayList<Seat> getSeats() {
        return seats;
    }

    /**
     * sets local seats
     * @param seats is the list to set seats to
     */
    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    // add seat to local variable seats
    public void addSeat(Seat seat) {
        seats.add(seat);
    }
}