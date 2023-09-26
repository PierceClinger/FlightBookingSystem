public class Booking {
    
    // Declaring private variables
    private UUID UUID;
    private String userID;
    private String flightID;
    private String hotelID;
    private int seatNumber;
    private int roomNumber;
    private String dateOfBooking;
    private String bookingName;
    private String bookingDateOfBirth;
    private String hotelCheckin;
    private String hotelCheckout;

    /**
     * Parametized constructor for class if UUID is already created
     * @param UUID sets local UUID
     * @param userID sets local userID
     * @param flightID sets local flightID
     * @param hotelID sets local hotelID
     * @param seatNumber sets local seatNumber
     * @param roomNumber sets local roomNumber
     * @param dateOfBooking sets local dateOfBooking
     * @param bookingName sets local bookingName
     * @param bookingDateOfBirth sets local bookingDateOfBirth 
     * @param hotelCheckin sets local hotelCheckIn
     * @param hotelCheckout sets local hotelCheckout
     */
    public Booking(String UUID, String userID, String flightID, String hotelID, int seatNumber, int roomNumber, String dateOfBooking, String bookingName, String bookingDateOfBirth, String hotelCheckin, String hotelCheckout) {
        this.UUID = new UUID(UUID);
        this.userID = userID;
        this.flightID = flightID;
        this.hotelID = hotelID;
        this.seatNumber = seatNumber;
        this.roomNumber = roomNumber;
        this.dateOfBooking = dateOfBooking;
        this.bookingName = bookingName;
        this.bookingDateOfBirth = bookingDateOfBirth;
        this.hotelCheckin = hotelCheckin;
        this.hotelCheckout = hotelCheckout;
    }

    /**
     * Parameterized constructor for booking class which generates new UUID
     * @param userID sets local userID
     * @param flightID sets local flightID
     * @param hotelID sets local hotelID
     * @param seatNumber sets local seatNumber
     * @param roomNumber sets local roomNumber
     * @param dateOfBooking sets local dateOfBooking
     * @param bookingName sets local bookingName
     * @param bookingDateOfBirth sets local bookingDateOfBirth
     * @param hotelCheckin sets local hotelCheckIn
     * @param hotelCheckout sets local hotelCheckout
     */
    public Booking(String userID, String flightID, String hotelID, int seatNumber, int roomNumber, String dateOfBooking, String bookingName, String bookingDateOfBirth, String hotelCheckin, String hotelCheckout) {
        this.UUID = new UUID();
        this.userID = userID;
        this.flightID = flightID;
        this.hotelID = hotelID;
        this.seatNumber = seatNumber;
        this.roomNumber = roomNumber;
        this.dateOfBooking = dateOfBooking;
        this.bookingName = bookingName;
        this.bookingDateOfBirth = bookingDateOfBirth;
        this.hotelCheckin = hotelCheckin;
        this.hotelCheckout = hotelCheckout;
    }

    /**
     * prints out booking information
     * Prints out both flight and hotel information if not null
     */
    public void getBooking() {
		for (int i = 0; i < FlightList.flightList.size(); i++) {
			if (FlightList.flightList.get(i).getUUID().equalsIgnoreCase(flightID)) {
				System.out.println("Flight Booking Information: \n" + FlightList.flightList.get(i).toString());
				break;
			}
		}

		if (getHotelID() != null) {
			for (int i = 0; i < HotelList.hotels.size(); i++) {
				if (HotelList.hotels.get(i).getUUID().equalsIgnoreCase(hotelID)) {
					System.out.println("Hotel Booking Information: \n" + HotelList.hotels.get(i).toString());
					break;
				}
			}
		}
	}
    
    /**
     * @return booking information in String format
     */
    public String toString() {
        String ret;
        ret = "\nSeat Number: " + seatNumber + "\n";
        ret = ret + "Room Number: " + roomNumber + "\n";
        ret = ret + "Date of Booking: " + dateOfBooking + "\n";
        ret = ret + "Name: " + bookingName + "\n";
        ret = ret + "Date of Birth: " + bookingDateOfBirth + "\n";

        return ret;
    }
    
    /**
     * Prints out more detailed booking information
     * @param flightList is used to pull flight information
     * @param hotelList is used to pull hotel information
     * @return detailed information of hotel and or flight booking
     */
    public String printInfo(FlightList flightList, HotelList hotelList) {
        String ret;
        String seatClass = "";
        if(hotelID.equalsIgnoreCase("None")) {
            for(int i = 0; i < flightList.getFlight(flightID).getSeats().size(); i++) {
                if(flightList.getFlight(flightID).getSeats().get(i).getSeatNumber() == seatNumber) {
                    seatClass = flightList.getFlight(flightID).getSeats().get(i).getSeatClass();
                }
            }
            ret = "\nName: " + bookingName + "\n";
            ret += "Booking ID: " + UUID.getUUID() + "\n";
            ret += "Airline: " + flightList.getFlight(flightID).getAirline() + "\n";
            ret += "Departure Date: " + flightList.getFlight(flightID).getDepartureDate() + "\n";
            ret += "Departure Time: " + flightList.getFlight(flightID).getDepartureTime() + "\n";
            ret += "Arrival Time: " + flightList.getFlight(flightID).getArriveTime() + "\n";
            ret += "Estimated Flight Time: " + flightList.getFlight(flightID).getTotalTravelTime() + "\n";
            ret += "Leaving From: " + flightList.getFlight(flightID).getStartLocation() + "\n";
            ret += "Going To: " + flightList.getFlight(flightID).getDestination() + "\n";
            ret += "Number of Transfers: " + flightList.getFlight(flightID).getNumOfLayovers() + "\n";
            ret += "Transfer Location(s): " + flightList.getFlight(flightID).getTransferLocation() + "\n";
            ret += "Seat Number: " + seatNumber + "\n";
            ret += "Seat Class: " + seatClass + "\n";
            ret += "Date of Booking: " + dateOfBooking + "\n";

            return ret;
        } else if(flightID.equalsIgnoreCase("None")) {
            ret = "\nName: " + bookingName + "\n";
            ret += "Booking ID: " + UUID.getUUID() + "\n";
            ret += "Hotel Name: " + hotelList.getHotel(hotelID).getName() + "\n";
            ret += "Location: " + hotelList.getHotel(hotelID).getLocation() + "\n";
            ret += "Check-In Date: " + hotelCheckin + "\n";
            ret += "Check-Out Date: " + hotelCheckout + "\n";
            ret += "Room Number: " + roomNumber + "\n";
            ret += "Date of Booking: " + dateOfBooking + "\n";

            return ret;
        }

        return null;
    }

    // return UUID
    public String getUUID() {
        return UUID.getUUID();
    }
    
    // return local userID
    public String getUserID() {
        return userID;
    }

    // return local flightID
    public String getFlightID() {
        return flightID;
    }

    // return local hotelID
    public String getHotelID() {
        return hotelID;
    }

    // return local seatNumber
    public int getSeatNumber() {
        return seatNumber;
    }

    // return local roomNumber
    public int getRoomNumber() {
        return roomNumber;
    }

    // return local dateOfBooking
    public String getDateOfBooking() {
        return dateOfBooking;
    }

    // return local bookingName
    public String getBookingName() {
        return bookingName;
    }

    // return local bookingDateOfBirth
    public String getBookingDateOfBirth() {
        return bookingDateOfBirth;
    }

    // return local hotelCheckin
    public String getHotelCheckin() {
        return hotelCheckin;
    }

    // return local hotelCheckout
    public String getHotelCheckout() {
        return hotelCheckout;
    }
}
