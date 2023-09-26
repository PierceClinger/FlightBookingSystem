/**
 * Object class which is used to create a room object
 * @author The Water Coolers 
 */
public class Room {
    
    private int roomNumber;
    private boolean smokingStatus;
    private int numberOfBeds;
    private boolean availability;
    private String typeOfBed;

    /**
     * Constructs an instance of room
     * @param roomNumber Room number
     * @param smokingStatus Room smoking status
     * @param numberOfBeds Number of beds in room
     * @param avilability Room availability
     * @param typeOfBed Type of bed in room
     */
    public Room(int roomNumber, boolean smokingStatus, int numberOfBeds,boolean avilability, String typeOfBed) {
        this.roomNumber = roomNumber;
        this.smokingStatus = smokingStatus;
        this.numberOfBeds = numberOfBeds;
        this.availability = avilability;
        this.typeOfBed = typeOfBed;
    }

    /**
     * Returns room number
     * @return room number
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Returns smoking status
     * @return Smoking status
     */
    public boolean getSmokingStatus() {
        return smokingStatus;
    }

    /**
     * Returns number of beds
     * @return number of beds
     */
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    /**
     * Returns room availability
     * @return Room availability
     */
    public boolean getAvailability() {
        return availability;
    }

    /**
     * Returns type of bed
     * @return Type of bed
     */
    public String getTypeOfBed() {
        return typeOfBed;
    }

    /**
     * Sets the room availability to the parameter given
     * @param availability Desired room availability
     * @return The new room availability
     */
    public boolean setAvailability(boolean availability) {
        this.availability = availability;
        return this.availability;
    }

    /**
     * Converts the instance of room information into a string
     */
    public String toString() {
        String ret;
        ret = "Room Number: " + roomNumber + "\n";
        if(smokingStatus == true) {
            ret += "Smoking Status: Smoking\n";
        } else {
            ret += "Smoking Status: Non Smoking\n";
        }
        ret = ret + "Number of Beds: " + numberOfBeds + "\n";
        ret = ret + "Type of Bed: " + typeOfBed + "\n";

        return ret;
    }
}
