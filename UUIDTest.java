import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Pierce Clinger
 */
public class UUIDTest {
    
    private UUID uuid;

    @BeforeEach
    public void setup() {
        uuid = new UUID("f0f9e774-3c08-4232-8106-8105f5fcad97");
    }

    @AfterEach
    public void tearDown() {
        uuid = new UUID();
    }

    @Test
    public void testGetUUID() {
        String test = uuid.getUUID();
        assertEquals("f0f9e774-3c08-4232-8106-8105f5fcad97", test);
    }
}