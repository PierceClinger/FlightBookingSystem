/**
 * Object class which is used to create a seat object
 * @author The Water Coolers 
 */
public class Seat {
    
    private int seatNumber;
    private String seatClass;
    private boolean availability;

    /**
     * Constructs an instance of seat
     * @param seatNumber Seat number
     * @param seatClass Seat class
     * @param availability Seat availability
     */
    public Seat(int seatNumber, String seatClass, boolean availability) {
        this.seatNumber = seatNumber;
        this.seatClass = seatClass;
        this.availability = availability;
    }

    /**
     * Returns the seat number
     * @return Seat number
     */
    public int getSeatNumber() {
        return seatNumber;
    }

    /**
     * Returns seat class
     * @return Seat class
     */
    public String getSeatClass() {
        return seatClass;
    }

    /**
     * Returns seat availability
     * @return seat availability
     */
    public boolean getAvailability() {
        return availability;
    }

    /**
     * Sets the seat availability to the parameter given
     * @param availability Desired seat availability
     * @return The new seat availability
     */
    public boolean setAvailability(boolean availability) {
        this.availability = availability;
        return this.availability;
    }

    /**
     * Converts the instance of seat information into a string
     */
    public String toString() {
        String ret;
        ret = "Seat Number: " + seatNumber + "\n";
        ret = ret + "Seat Class: " + seatClass + "\n";
        ret = ret + "Availability: " + availability + "\n";

        return ret;
    }
}
