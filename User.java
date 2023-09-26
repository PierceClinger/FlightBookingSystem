/**
 * Abstract user class which keeps track of account status
 * @author The Water Coolers
 */
public abstract class User {
    /**
     * Declaring private variables
     * Status denotes whether user is registered or guest
     * searchType denotes whether search is for hotels or flights
     * search holds private instance of search for user
     */
    private String status;

    // Constructor setting user's status
    public User(String status) {
        this.status = status;
    }
}
