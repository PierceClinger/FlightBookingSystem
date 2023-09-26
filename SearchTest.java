/**
 * @author Ryan Capron
 */
import java.util.ArrayList;
import java.util.regex.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SearchTest {
    
    private Search fSearch;
    private Search hSearch;
    private ArrayList<Flight> flightList = FlightList.getInstance().getAllFlights();
    private ArrayList<Hotel> hotelList = HotelList.getInstance().getAllHotels();

    private final ByteArrayOutputStream systemOut = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(systemOut));
        fSearch = new Search("flight");
        hSearch = new Search("hotel");
    }

    @AfterEach
    public void tearDown() {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        fSearch = new Search("flight");
        hSearch = new Search("hotel");
    }

    @Test
    void testDisplaySearchResultsFlights() {
        String flightString = "";
        int i = 0;

        for (Flight f : flightList) {
            flightString += f.displayToUser(i) + "\n";
            i++;
        }

        fSearch.displaySearchResults();
        assertEquals(flightString, systemOut.toString());
    }

    @Test
    void testDisplaySearchResultsHotels() {
        String hotelString = "";
        int i = 0;

        for (Hotel h : hotelList) {
            hotelString += h.displayToUser(i) + "\n";
            i++;
        }

        hSearch.displaySearchResults();
        assertEquals(hotelString, systemOut.toString());
    }

    @Test
    void testGetFlightID() {
        boolean same = true;
        fSearch.displaySearchResults();

        for (int i = 0;i < flightList.size();i++) {
            if (!fSearch.getFlightID(i).equalsIgnoreCase(flightList.get(i).getUUID())) 
                same = false;
        }
        assertTrue(same);
    }

    @Test
    void testGetHotelID() {
        boolean same = true;
        hSearch.displaySearchResults();

        for (int i = 0;i < hotelList.size();i++) {
            if (!hSearch.getHotelID(i).equalsIgnoreCase(hotelList.get(i).getUUID())) 
                same = false;
        }
        assertTrue(same);
    }

    @Test
    void testSetFlightFilter() {
        boolean pass = true;
        fSearch.setFlightFilter("carryOn", "false");

        if (fSearch.getCarryOn() != false) 
            pass = false;
        assertTrue(pass);
    }

    @Test
    void testSetHotelFilter() {
        boolean pass = true;
        fSearch.setHotelFilter("smokingStatus", "false");

        if (fSearch.getCarryOn() != false) 
            pass = false;
        assertTrue(pass);
    }

    @Test
    void showFlightTest() {
        for (int i = 0;i < 4;i++) {
            fSearch.setFlightFilter("numOfCheckedBags", "2");
        }
        fSearch.displaySearchResults();

        boolean fail = false;

        Pattern p = Pattern.compile("Maximum Number of Checked Bags Allowed: ([0-9])");
        Matcher m = p.matcher(systemOut.toString());

        while (m.find()) {
            if (Integer.parseInt(m.group(1)) < 2)
                fail = true;
        }
        assertFalse(fail);
    }

    @Test
    void showHotelTest() {
        for (int i = 0;i < 4;i++) {
            hSearch.setHotelFilter("rating", "4");
        }
        hSearch.displaySearchResults();

        boolean fail = false;

        Pattern p = Pattern.compile("Rating: ([0-9])");
        Matcher m = p.matcher(systemOut.toString());

        while (m.find()) {
            if (Integer.parseInt(m.group(1)) < 2)
                fail = true;
        }
        assertFalse(fail);
    }

    @Test
    void showDetailsTest() {
        hSearch.displaySearchResults();
        String basicView = systemOut.toString();
        systemOut.reset();

        for (int i = 0;i <= 4;i++) {
            hSearch.setHotelFilter("rating", "4");
        }
        hSearch.displaySearchResults();

        assertFalse(systemOut.toString().equals(basicView));
    }

    @Test
    void nullFilterInputTest() {
        fSearch.displaySearchResults();
        String NFilterSearch = "\n\n" + systemOut.toString();
        systemOut.reset();

        fSearch.setFlightFilter("carryOn", null);
        fSearch.setFlightFilter("numOfCheckedBags", null);
        fSearch.setFlightFilter("departureDate", null);

        fSearch.displaySearchResults();
        String FilterSearch = systemOut.toString();
        Pattern pattern = Pattern.compile("java.*\nIncorrect parameter inputted");
        Matcher matcher = pattern.matcher(FilterSearch);
        FilterSearch = matcher.replaceAll("");

        assertEquals(FilterSearch, NFilterSearch);
    }

    @Test
    void validFilterTypeTest() {
        // TODO input non string parametter and get error write about it in bug report
        fSearch.setFlightFilter("seatClass", "5");
        fSearch.setFlightFilter("fakeFilter", "badParameter");
        fSearch.setFlightFilter("departureDate", "invalidParameter");

        fSearch.displaySearchResults();
        assertFalse(systemOut.toString().isBlank());
    }

    @Test
    void filterClearTest() {
        // TODO add filters and display then construct a new and cehck that they are not equal
        fSearch.setFlightFilter("numOfCheckedBags", "2");
        fSearch.setFlightFilter("arrivingAirportCode", "CAE");
        fSearch.setFlightFilter("seatClass", "business");
        fSearch.displaySearchResults();
        String preClearSearch = systemOut.toString();

        systemOut.reset();
        fSearch = new Search("flight");
        fSearch.displaySearchResults();

        assertFalse(systemOut.toString().equals(preClearSearch));
    }
}