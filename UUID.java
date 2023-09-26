import java.util.Random;

/**
 * @author The Water Coolers
 * UUID class which contains data elements of a UUID
 */
public class UUID {
    
    private String uuid;

    /**
     * Default constructor: Calls the generator method to get a random UUID
     */
    public UUID() {
        this.uuid = generateUUID();
    }

    /**
     * Default constructor: Overloaded default constructor, will be triggered if the system needs to create a UUID by passing in a string ID
     * @param id The id that needs to be set to a UUID
     */
    public UUID(String id) {
        this.uuid = id;
    }

    /**
     * Returns the UUID in string format
     * @return The UUID
     */
    public String getUUID() {
        return uuid;
    }

    /**
     * Generates a random UUID from a list of characters
     * @return The random UUID
     */
    private String generateUUID() {
        Random rand = new Random();
        String ret = new String();
        String listChar = "abcdefghijklmnopqrstuvwxyz0123456789";
        
        for (int i = 0; i < 8; i++) {
            char temp = listChar.charAt(rand.nextInt(listChar.length()));
            ret = ret + temp;
        }
        for (int i = 0; i < 3; i++) {
            ret = ret + "-";
            for (int j = 0; j < 4; j++) {
                char temp = listChar.charAt(rand.nextInt(listChar.length()));
                ret = ret + temp;
            }
        }
        ret = ret + "-";
        for (int i = 0; i < 12; i++) {
            char temp = listChar.charAt(rand.nextInt(listChar.length()));
            ret = ret + temp;
        }

        return ret;
    }
}
