public class RegisteredUser extends User {
    
    /**
    * This class extends the user class to handle the extra fields for registered users
    * @author The Water Coolers
    */
    private UUID uuid;
    private String name;
    private String username;
    private String email;
    private String password;
    private String dateOfBirth;
    private boolean freqFlyer;

    public RegisteredUser(String status, String name, String username, String email, String password, String dateOfBirth, boolean freqFlyer) {
        super(status);
        this.uuid = new UUID();
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.freqFlyer = freqFlyer;
    }
    /**
     * Constructor for the class
     */

    public RegisteredUser(String id, String status, String name, String username, String email, String password, String dateOfBirth, boolean freqFlyer) {
        super(status);
        this.uuid = new UUID(id);
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.freqFlyer = freqFlyer;
    }
    /**
     * Class constructors for specifying UUID for newly made UUID
     *
     */

    public String getUUID() {
        return uuid.getUUID();
    }
    /**
     * @return UUID
     */
    
    public String getName() {
        return name;
    }
    /**
     * @return name
     */

    public String getUsername() {
        return username;
    }
    /**
     * @return username
     */
    public String getEmail() {
        return email;
    }
    /**
     *@return email 
     */

    public String getPassword() {
        return password;
    }
    /**
     * 
     * @return password
     */

    public String getDateOfBirth() {
        return dateOfBirth;
    }
    /**
     * 
     * @return date of birth
     */

    public boolean getFreqFlyer() {
        return freqFlyer;
    }
    /**
     * 
     * @return frequent flyer status
     */

    public void setFreqFlyer(boolean freqFlyer) {
        this.freqFlyer = freqFlyer;
    }
    /**
     * sets frequent flyer status
     */

    public String toString() {
        String ret = "\nName : " + name + "\n"
        + "Username: " + username + "\n"
        + "Email: " + email + "\n"
        + "Password: " + password + "\n"
        + "Date of Birth: " + dateOfBirth + "\n"
        + "Frequent Flyer Status: " + freqFlyer + "\n";

        return ret;
    }
    /**
     * returns the fields of the current user
     */
}
