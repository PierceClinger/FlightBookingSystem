
/**
 * This class implements the Main Page for the flight booking system that allows the user to interact with
 * the system by choosing what they want to do
 * @author The Water Coolers
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainPage {
	private String[] basicOptions = { "Login", "Create Account", "View as Guest", "Quit program" };
	private String[] mainMenuOptions = { "Search Flights", "Search Hotels", "View Bookings", "Return to Home Page",
			"Logout" };
	private String[] guestMenuOptions = { "Search Flights", "Search Hotels", "Login", "Create Account",
			"Quit program" };
	private static final String WELCOME = "Welcome to TWC Travel Planning!";
	private Scanner keyboard;
	private FlightSystem system;
	private boolean guestUser = true;

	/**
	 * Default Constructor: creates the scanner and the system class
	 */
	public MainPage() {
		keyboard = new Scanner(System.in);
		system = new FlightSystem();
	}

	/**
	 * Runs the entire program 
	 */
	public void run() {
		System.out.println(WELCOME);

		// Loop as long as the user wants to interact with the system
		while (true) {
			displayBasicOptions();
			int userInputBasic = selectBasicOptions(basicOptions.length);
			switch (userInputBasic) {
			case (0):
				login();
				break;
			case (1):
				createAccount();
				break;
			case (2):
				viewAsGuest();
				break;
			case (3):
				System.out.println("\nThank you for your service, Goodbye!");
				System.exit(0);
				break;
			default:
				System.out.println("Not Valid, Try again!\n");
				break;
			}
		}
	}

	/**
	 * Displays the Home Page for the system 
	 */
	public void displayBasicOptions() {
		for (int i = 0; i < basicOptions.length; i++) {
			System.out.println((i + 1) + ". " + basicOptions[i]);
		}
		System.out.println("\n");
	}

	/**
	 * Allows user to select the basic options to login, create account, view as
	 * guest, or quit (done & it works) Also returning -1 means that the number the
	 * user enters is not valid
	 * 
	 * @param num The number of basic options
	 * @return The number the user enters
	 * @return -1
	 */
	private int selectBasicOptions(int num) {
		int command = -1;
		boolean invalid = true;
		while (invalid) {
			System.out.println("Enter the corresponding number for your desired action:");
			String input = keyboard.nextLine();
			try {
				command = Integer.parseInt(input) - 1;
			} catch (NumberFormatException ime) {
				System.out.println("Invalid input");
				command = -1;
			}
			if (command >= 0 && command <= num - 1) {
				return command;
			}
		}

		return -1;
	}

	/**
	 * Allows the user to login to their account 
	 */
	public void login() {
		System.out.println("\n************ Login ************");
		System.out.println("Please enter your account username and password:");
		System.out.println("Username: ");
		String userName = keyboard.nextLine();
		System.out.println("\nPassword: ");
		String password = keyboard.nextLine();
		System.out.println("\nType '1' to submit, '2' to go back or '3' to quit: ");
		int response = Integer.parseInt(keyboard.nextLine());
		if (response == 1) {
			if (system.login(userName, password)) {
				System.out.println("\nYou have successfully logged in!");
				guestUser = false;
				mainMenu();

			} else {
				System.out.println("\nError: No account found. Returning to home page...\n");
				run();
			}
		} else if (response == 2) {
			run();
		} else {
			System.out.println("\nThank you for your service, Goodbye!");
			system.logout();
			System.exit(0);
		}
	}

	/**
	 * Creates an account for the user 
	 */
	public void createAccount() {
		System.out.println("************ Create Account ************");
		System.out.println("Please enter your following information:");
		System.out.println("Full Name: ");
		String name = keyboard.nextLine();
		System.out.println("\nUsername: ");
		String userName = keyboard.nextLine();
		System.out.println("\nEmail: ");
		String email = keyboard.nextLine();
		System.out.println("\nPassword: ");
		String password = keyboard.nextLine();
		System.out.println("\nConfirm Password: ");
		String passConfirm = keyboard.nextLine();

		// will loop if the passwords don't match
		while (!password.equals(passConfirm)) {
			System.out.println("Password doesnt match! Try again!");
			System.out.println("\nConfirm Password: ");
			passConfirm = keyboard.nextLine();
		}
		System.out.println("\nDate Of Birth (MO/DA/YEAR): ");
		String dateOfBirth = keyboard.nextLine();
		System.out.println("\nType '1' to submit, '2' to go back or '3' to quit: ");
		int response = Integer.parseInt(keyboard.nextLine());
		if (response == 1) {
			system.createAccount(name, userName, email, passConfirm, dateOfBirth, false);
			System.out.println("\nYour account has been successfully created!");
			guestUser = false;
			mainMenu();
		} else if (response == 2) {
			run();
		} else {
			System.out.println("\nThank you for your service, Goodbye!");
			System.exit(0);
		}
	}

	/**
	 * If the user decides to browse without making an account or login, they will
	 * be a guest but can't book without making an account (must login first) 
	 */
	public void viewAsGuest() {
		guestMenu();
	}

	/**
	 * Displays the main menu options for the system 
	 */
	public void displayMainMenu() {
		system.resetSearch();
		System.out.println("\n$$$$$$$$ Main Menu $$$$$$$$");
		for (int i = 0; i < mainMenuOptions.length; i++) {
			System.out.println((i + 1) + ". " + mainMenuOptions[i]);
		}
		System.out.println("\n");
	}

	/**
	 * Displays the main menu as a guest for the system 
	 */
	public void displayGuestMenu() {
		system.resetSearch();
		System.out.println("\n$$$$$$$$ Main Menu $$$$$$$$");
		for (int i = 0; i < guestMenuOptions.length; i++) {
			System.out.println((i + 1) + ". " + guestMenuOptions[i]);
		}
		System.out.println("\n");
	}

	/**
	 * Allows the user to interact with the main menu as many times as they like
	 * 
	 */
	public void mainMenu(){
		while (true) {
			displayMainMenu();
			int userMainMenu = selectBasicOptions(mainMenuOptions.length);
			switch (userMainMenu) {
			case (0):
				searchFlights();
				break;
			case (1):
				searchHotels();
				break;
			case (2):
				viewBookings();
				break;
			case (3):
				run();
				break;
			case (4):
				system.logout();
				System.out.println("\nThank you for your service, Goodbye!");
				System.exit(0);
				break;
			default:
				System.out.println("Not Valid, try again!\n");
				break;
			}
		}
	}

	/**
	 * Allows the user to interact with the guest menu as many times as they like (as long as they are a guest)
	 */
	public void guestMenu() {
		while (true) {
			displayGuestMenu();
			int userMainMenu = selectBasicOptions(guestMenuOptions.length);
			switch (userMainMenu) {
			case (0):
				searchFlights();
				break;
			case (1):
				searchHotels();
				break;
			case (2):
				login();
				break;
			case (3):
				createAccount();
				break;
			case (4):
				system.logout();
				System.out.println("\nThank you for your service, Goodbye!");
				System.exit(0);
				break;
			default:
				System.out.println("Not Valid, try again!\n");
				break;
			}
		}
	}

	/**
	 * Allows the user to search for their desired flight
	 */
	public void searchFlights() {
		System.out.println("\n$$$$$$$$ Search Flights $$$$$$$$");
		System.out.println("Please fill in the following information(Leave blank to return all):");
		System.out.println("\nStarting airport code: ");
		String departAirport = keyboard.nextLine();
		System.out.println("Destination airport code: ");
		String arrAirport = keyboard.nextLine();
		System.out.println("Departure Date (MO/DA/YEAR): ");
		String departDate = keyboard.nextLine();
		System.out.println("\nType '1' to search, 2' to go back to the main menu, or '3' to quit:");
		System.out.print("Option: ");
		int response = Integer.parseInt(keyboard.nextLine());
		if (response == 1) {
			System.out.println("\n$$$$$$$$ Flight Results $$$$$$$$\n");
			system.setFlightFilter("departingAirportCode", departAirport);
			system.setFlightFilter("arrivingAirportCode", arrAirport);
			system.setFlightFilter("departureDate", departDate);
			system.searchFlights();
		} else if (response == 2 && !guestUser) {
			mainMenu();
		} else if (response == 2 && guestUser) {
			guestMenu();
		} else {
			System.out.println("\nThank you for your service, Goodbye!");
			// If the user decides to quit, log them out first
			system.logout();
			System.exit(0);
		}
		while (true) {
			System.out.println(
					"\nType  '1' to add filters, '2' to book a flight, '3' to go back to the main menu, or '4' to quit: ");
			int response2 = Integer.parseInt(keyboard.nextLine());
			if (response2 == 1) {
				searchFiltersFlight(departAirport, arrAirport, departDate);
				continue;
			} else if (response2 == 2) {
				if (!guestUser) {
					bookFlight();
					break;
				}
				System.out.println("\nMust be logged in to do this action");
				guestMenu();
				break;
			} else if (response2 == 3 && !guestUser) {
				mainMenu();
				break;
			} else if (response2 == 3 && guestUser) {
				guestMenu();
				break;
			} else {
				System.out.println("\nThank you for your service, Goodbye!");
				// If the user decides to quit, log them out first
				system.logout();
				System.exit(0);
			}
		}
	}

	/**
	 * Allows the user to search for their desired hotel
	 */
	public void searchHotels() {
		System.out.println("\n$$$$$$$$ Search Hotels $$$$$$$$");
		System.out.println("Please fill in the following information.  Leave blank to return all: ");
		System.out.println("\nLocation (<<city>>, <<state>>): ");
		String location = keyboard.nextLine();
		String arrivalDate;
		String departDate;
		while(true) {
			System.out.println("\nArrival Date (MO/DA/YEAR): ");
			arrivalDate = keyboard.nextLine();
			if(arrivalDate.equals("")) {
				System.out.println("\nInvalid input. Must enter an arrival date for your stay!");
				continue;
			} else {
				break;
			}
		}
		while(true) {
			System.out.println("\nDeparture Date (MO/DA/YEAR): ");
			departDate = keyboard.nextLine();
			if(departDate.equals("")) {
				System.out.println("\nInvalid input. Must enter a departure date for your stay!");
				continue;
			} else {
				break;
			}
		}
		int response = 0;
		boolean inv = true;
		while (inv)
			try {
				System.out.println("\nType '1' to search, '2' to go to the main menu, or '3' to quit:");
				System.out.print("Option: ");
				response = Integer.parseInt(keyboard.nextLine());
				inv = false;
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid input!");
				response = 0;
			}
		if (response == 1) {
			System.out.println("\n$$$$$$$$ Hotel Results $$$$$$$$");
			system.setHotelFilter("location", location);
			system.setHotelFilter("hotelArrivalDate", arrivalDate);
			system.setHotelFilter("hotelDepartureDate", departDate);
			system.searchHotels();
		} else if (response == 2 && !guestUser) {
			mainMenu();

		} else if (response == 2 && guestUser) {
			guestMenu();
		} else {
			System.out.println("\nThank you for your service, Goodbye!");
			// If the user decides to quit, log them out first
			system.logout();
			System.exit(0);
		}
		while (true) {
			System.out.println(
					"\nType '1' to add filters, '2' to book a hotel, '3' to return to the main menu, or 4 to quit: ");
			System.out.println("Option: ");
			int response2 = Integer.parseInt(keyboard.nextLine());
			if (response2 == 1) {
				searchFiltersHotel(location, arrivalDate, departDate);
				continue;
			} else if (response2 == 2) {
				if (!guestUser) {
					bookHotel(arrivalDate, departDate);
					break;
				}
				System.out.println("Must be logged in to do this action");
				guestMenu();
				break;
			} else if (response2 == 3 && !guestUser) {
				mainMenu();
				break;
			} else if (response2 == 3 && guestUser) {
				guestMenu();
				break;
			} else {
				System.out.println("\nThank you for your service, Goodbye!");
				// if the user decides to quit, log them out
				system.logout();
				System.exit(0);
			}
		}
	}

	/**
	 * Allows the user to view their current bookings if they have any
	 */
	public void viewBookings() {
		try {
		System.out.println("\n$$$$$$$$ View Bookings $$$$$$$$");
		system.printTicket();
		System.out.println("Print bookings? y/n");
		while(true){
			if(keyboard.nextLine().equalsIgnoreCase("y")){
				system.printOutTicket();
				break;
			}
			else{
				break;
			}
		}
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Allows the user to book a flight of their choosing
	 */
	private void bookFlight() {
		System.out.println("\n$$$$$$$$ Book Flight $$$$$$$$");
		System.out.println(
				"What is the number of the flight option you would like to book? (Listed as 'FLIGHT OPTION <<#>>'): ");
		String flightNumber = keyboard.nextLine();
		String id = system.getFlightID(Integer.parseInt(flightNumber));
		Flight selectedFlight = FlightList.getFlight(id);
		System.out.println("\n********************************\n" + "Selected flight: "
				+ selectedFlight.getDepartingAirportCode() + " to " + selectedFlight.getArrivingAirportCode() + "\n"
				+ "Departing on " + selectedFlight.getDepartureDate() + " at " + selectedFlight.getDepartureTime()
				+ "\n********************************\n" + "Available seat map: \n"
				+ "********************************\n");
		selectedFlight.displaySeatMap();
		ArrayList<Seat> temp = selectedFlight.getSeats();
		int seat = 1;
		boolean inv = true;
		while (inv) {
			try {
				System.out.println("Which seat would you like? (Enter seat number): ");
				seat = Integer.parseInt(keyboard.nextLine());
				if (seat <= temp.size() && seat > 0) {
					if (temp.get(seat - 1).getAvailability()) {
						inv = false;
						System.out.println("Seat " + seat + " has been selected!");
						temp.get(seat - 1).setAvailability(false);
						selectedFlight.setSeats(temp);
					} else {
						System.out.println("Invalid seat option!");
					}
				} else {
					System.out.println("Invalid seat option!");
				}
			} catch (NumberFormatException nfe) {
				seat = 1;
			}
		}
		System.out.println("Name of the person boaring (must match ID brought to boarding): ");
		String name = keyboard.nextLine();
		System.out.println("His/her date of birth (MO/DA/YYYY): ");
		String dateOfBirth = keyboard.nextLine();
		System.out.println("Type '1' to book the flight, '2' to return to the main menu or '3' to quit: ");
		int response = Integer.parseInt(keyboard.nextLine());
		if (response == 1) {
			system.bookFlight(seat, name, id, dateOfBirth);
			System.out.println("\nYour booking has been successfully completed and added to your bookings!");
		} else if (response == 2) {
			mainMenu();
		} else {
			System.out.println("\nThank you for your service, Goodbye!");
			system.logout();
			System.exit(0);
		}
	}

	/**
	 * Allows the user to book a hotel of their choosing
	 * @param arrivalDate The date that the user plans on arriving
	 * @param departDate The date that the user plans on departing
	 */
	public void bookHotel(String arrivalDate, String departDate) {
		System.out.println("\n$$$$$$$$ Book Hotel $$$$$$$$");
		System.out.println("What is the number of the hotel you would like to book? (Listed as 'HOTEL OPTION <<#>>'): ");
		String hotelNumber = keyboard.nextLine();
		String id2 = system.getHotelID(Integer.parseInt(hotelNumber));
		Hotel selectedHotel = HotelList.getHotel(id2);
		System.out.println("\n********************************\n" + "Selected hotel: "
				+ selectedHotel.getName()+ " in " + selectedHotel.getLocation() + "\n"
				+ "For " + arrivalDate + " to " + departDate
				+ "\n********************************\n" + "Available room map: \n"
				+ "********************************\n");
		selectedHotel.displayRooms();
		ArrayList<Room> temp = selectedHotel.getRooms();
		int roomNum = 101;
		boolean inv =true;
		while(inv) {
			try {
				System.out.println("What room would you like? (Enter room number): ");
				roomNum = Integer.parseInt(keyboard.nextLine());
				for(int i = 0; i< temp.size(); i++) {
					if(temp.get(i).getRoomNumber() == roomNum && temp.get(i).getAvailability()){
						inv = false;
						System.out.println("Room " + roomNum + " has been selected!");
						temp.get(i).setAvailability(false);
						selectedHotel.setRooms(temp);
						break;
					}
				}
			} catch (NumberFormatException nfe) {
				roomNum = 101;
				System.out.println("Invalid room option!");
			}
		}
		System.out.println("Name of the occupant (must match ID brought to hotel): ");
		String name = keyboard.nextLine();
		System.out.println("His/her date of birth (MO/DA/YYYY): ");
		String dateOfBirth = keyboard.nextLine();
		System.out.println("Type '1' to book the hotel, '2' to return to the main menu or '3' to quit: ");
		int response = Integer.parseInt(keyboard.nextLine());
		if (response == 1) {
			system.bookHotel(roomNum, name, id2, dateOfBirth, arrivalDate, departDate);
			System.out.println("\nYour booking has been successfully completed and added to your bookings!");
		} else if (response == 2) {
			mainMenu();
		} else {
			System.out.println("\nThank you for your service, Goodbye!");
			system.logout();
			System.exit(0);
		}
	}

	/**
	 * Allows te user to filter through the system to find their desired flight
	 * @param departAirCode The airport code that the flights starts
	 * @param arrAirCode The airport code that the flight will arrive
	 * @param departDate The date the flight departs
	 */
	public void searchFiltersFlight(String departAirCode, String arrAirCode, String departDate) {
		System.out.println("\n$$$$$$$$ Search Filters $$$$$$$$");
		String carryOn = "";
		boolean invalid = true;
		while (invalid) {
			System.out.println("Carry on allowed (true/false): ");
			try {
				carryOn = keyboard.nextLine();
				invalid = false;
			} catch (InputMismatchException ime) {
				System.out.println("Invalid input");
				keyboard.nextLine();
			}
		}
		String minCheckedBags = "";
		do {
			System.out.println("Number of checked bags: ");
			try {
				minCheckedBags = keyboard.nextLine();
				invalid = false;
			} catch (InputMismatchException ime) {
				System.out.println("Invalid input");
				keyboard.nextLine();
				invalid = true;
			}
		} while (invalid);
		String numOfTransfers = "";
		do {
			System.out.println("Maximum number of transfers: ");
			try {
				numOfTransfers = keyboard.nextLine();
				invalid = false;
			} catch (InputMismatchException ime) {
				System.out.println("Invalid input");
				keyboard.nextLine();
				invalid = true;
			}
		} while (invalid);
		System.out.println("Seat Class Preference: ");
		String seatClass = keyboard.nextLine();
		System.out.println("\nType '1' to search again");
		int response = 1;
		do {
			try {
				System.out.print("Option: ");
				keyboard.nextInt();
				invalid = false;
				keyboard.nextLine();
			} catch (InputMismatchException ime) {
				System.out.println("Invalid input");
				keyboard.nextLine();
				invalid = true;
			}
		} while (invalid);
		if (response == 1) {
			system.setFlightFilter("departingAirportCode", departAirCode);
			system.setFlightFilter("arrivingAirportCode", arrAirCode);
			system.setFlightFilter("departureDate", departDate);
			system.setFlightFilter("carryOn", String.valueOf(carryOn));
			system.setFlightFilter("numOfCheckedBags", String.valueOf(minCheckedBags));
			system.setFlightFilter("numOfLayovers", String.valueOf(numOfTransfers));
			system.setFlightFilter("seatClass", seatClass);
			system.searchFlights();
		} else {
			System.out.println("Invalid Response");
			searchFiltersFlight(departAirCode, arrAirCode, departDate);
		}
	}

	/**
	 * Allows te user to filter through the system to find their desired hotel
	 * @param location The location of the hotel
	 * @param arrivalDate The date the user arrives at the hotel
	 * @param departDate The date the user plans on leaving the hotel
	 */
	public void searchFiltersHotel(String location, String arrivalDate, String departDate) {
		System.out.println("\n$$$$$$$$ Search Filters $$$$$$$$");
		int room = -1;
		boolean invalid = true;
		do {
			try {
				System.out.println("How many rooms to book: ");
				String temp = keyboard.nextLine();
				if (temp.equalsIgnoreCase("")) {
					invalid = false;
				} else {
					room = Integer.parseInt(temp);
					invalid = false;
				}
			} catch (InputMismatchException ime) {
				System.out.println("Invalid input");
				keyboard.nextLine();
				invalid = true;
			}
		} while (invalid);
		System.out.println("Minimum rating (out of 5): ");
		int rating = -1;
		do {
			try {
				String temp = keyboard.nextLine();
				if (temp.equalsIgnoreCase("")) {
					invalid = false;
				} else {
					rating = Integer.parseInt(temp);
					if (rating >= 1 && rating <= 5) {
						invalid = false;
					} else {
						System.out.println("Please enter a value between 1 and 5!");
						invalid = true;
					}
				}
			} catch (NumberFormatException ime) {
				System.out.println("Invalid input");
				rating = 1;
				invalid = true;
			}
		} while (invalid);
		int smoking = 0;
		do {
			System.out.println("Smoking allowed (true/false): ");
			String temp = keyboard.nextLine();
			if (temp.equalsIgnoreCase("")) {
				invalid = false;
			} else {
				try {
					if (temp.equalsIgnoreCase("true")) {
						smoking = 1;
					}
					else if (temp.equalsIgnoreCase("false")) {
						smoking = -1;
					}
					invalid = false;
				} catch (InputMismatchException ime) {
					smoking = 0;
				}
			}
		} while (invalid);
		System.out.println("Number of Beds: ");
		int beds = -1;
		do {
			try {
				String temp = keyboard.nextLine();
				if (temp.equalsIgnoreCase("")) {
					invalid = false;
				} else {
					beds = Integer.parseInt(temp);
					invalid = false;
				}
			} catch (NumberFormatException ime) {
				System.out.println("Invalid input");
				beds = 1;
				invalid = true;
			}
		} while (invalid);
		System.out.println("Bed Type (King, Queen or Double): ");
		String bedType = "";
		do {
			try {
				String temp = keyboard.nextLine();
				if (temp.equalsIgnoreCase("")) {
					invalid = false;
				} else {
					bedType = temp;
					invalid = false;
				}
			} catch (InputMismatchException ime) {
				System.out.println("Invalid input");
				keyboard.nextLine();
				invalid = true;
			}
		} while (invalid);
		System.out.println("\nType '1' to search again");
		int response = 1;
		do {
			try {
				System.out.print("Option: ");
				keyboard.nextInt();
				invalid = false;
				keyboard.nextLine();
			} catch (InputMismatchException ime) {
				System.out.println("Invalid input");
				keyboard.nextLine();
				invalid = true;
			}
		} while (invalid);
		if (response == 1) {
			system.setHotelFilter("location", location);
			system.setHotelFilter("hotelArrivalDate", arrivalDate);
			system.setHotelFilter("hotelDepartureDate", departDate);
			system.setHotelFilter("roomsToBook", String.valueOf(room));
			system.setHotelFilter("rating", String.valueOf(rating));
			system.setHotelFilter("smokingStatus", String.valueOf(smoking));
			system.setHotelFilter("numOfBeds", String.valueOf(beds));
			system.setHotelFilter("bedType", bedType);
			system.searchHotels();
		} else {
			System.out.println("Invalid response");
			searchFiltersHotel(location, arrivalDate, departDate);
		}
	}

	public static void main(String[] args) {
		MainPage mainInterface = new MainPage();
		mainInterface.run();
	}
}
