import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.DirectoryStream.Filter;
import java.io.FileOutputStream;
import java.io.FileDescriptor;
import java.util.regex.*;

public class searchDriverTest {

    public static void main(String[] args) {
        ArrayList<Flight> flightList = FlightList.getInstance().getAllFlights();
        ByteArrayOutputStream systemOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOut));
        Search fSearch = new Search("flight");

        // fSearch.setFlightFilter("seatClass", 5);

        fSearch.displaySearchResults();

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(systemOut.toString());
    }
}
