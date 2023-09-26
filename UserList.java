import java.util.ArrayList;

/**
 * @author The Water Coolers
 * Data storage class that connects to the users.json, pulling the data into an ArrayList of type RegisteredUser
 */
public class UserList {
	private static UserList userList;
	private ArrayList<RegisteredUser> users;

	/**
     * Default constructor: Calls the DataLoader and populates an ArrayList of type RegisteredUser with all the current user data out of registered users
     */
	private UserList() {
		users = DataLoader.getUsers();
	}

	/**
     * Singleton method which allows for only one instance of the UserList to exist. Checks if an instance exists, if it does then return it, if not make a new one
     * @return The instance of UserList
     */
	public static UserList getInstance() {
		if (userList == null) {
			userList = new UserList();
		}
		return userList;
	}

	/**
	 * Checks if there exists a registered user in the system with a specific username
	 * @param userName The username that should be checked in the system
	 * @return True if a user with that username already exists, false if not
	 */
	public boolean haveUser(String userName) {
		for (RegisteredUser user : users) {
			if (user.getUsername().equals(userName)) {
				return true;
			}
		}
		return false;
	}

	/**
     * Returns a specific user from the database of RegisteredUsers
     * @param userName The username of the user that should be returned
     * @return The user
     */
	public RegisteredUser getUser(String userName) {
		for (RegisteredUser user : users) {
			if (user.getUsername().equals(userName)) {
				return user;
			}
		}
		return null;
	}

	/**
     * Returns the ArrayList of user data
     * @return The ArrayList of current user data
     */
	public ArrayList<RegisteredUser> getAllUsers() {
		return users;
	}

	/**
     * Adds a new RegisteredUser to the ArrayList of users. First checks if a user already exists with the username that is trying to be used
     * @param name The user's full name
     * @param userName The user's username
	 * @param password The user's password
	 * @param email The user's email
	 * @param dateOfBirth The user's date of birth input as (MO/DA/YYYY)
	 * @param freqFlyer The frequent flyer status of the user
	 * @return True if user was added successfully, false if a user already existed with the desired username
     */
	public boolean addUser(String name, String userName, String password, String email, String dateOfBirth, boolean freqFlyer) {
		if (haveUser(userName)) {
			return false;
		} else {
			users.add(new RegisteredUser("Registered", name, userName, email, password, dateOfBirth, freqFlyer));
			return true;
		}
	}
	
	/**
     * Calls the DataWriter to save all changes to the user data
     */
	public void saveUsers() {
		DataWriter.saveUsers();
	}
}
