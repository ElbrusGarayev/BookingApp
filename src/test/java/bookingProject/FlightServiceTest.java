package bookingProject;

import bookingProject.entity.Flight;
import bookingProject.entity.Passenger;
import bookingProject.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class FlightServiceTest {
    FlightService flightService;
    Database db;

    @BeforeEach
    public void initialize() {
        this.db = new Database();
        this.flightService = new FlightService(db);
    }

    @Test
    void getFlightsByDaily() {
        boolean bool = true;
        Collection<Flight> dailyFlights = flightService.getAllFlightsByDaily();
        for (Flight flight : dailyFlights) {
            if (!(flight.getDate().isAfter(LocalDateTime.now())
                    && flight.getDate().isBefore(LocalDateTime.now().plusHours(24)))) {
                bool = false;
            }
        }
        assertTrue(bool);
    }

    @Test
    void getAllFlights() {
        Collection<Flight> allFlights = flightService.getAllFlights();
        assertFalse(allFlights.isEmpty());
    }

    @Test
    void showAvaiableFlightsNull() {
        String expected = "";
        String actual = flightService.showAvaiableFlights("Baku","2021-03","1");
        assertEquals(expected,actual);
    }

    @Test
    void showAvaiableFlights() {
        String expected = "||=================================================================||\n" +
                "||ID:253 | Kiev       -> Baku       : 2020-03-26 12:00 : Seats: 200||\n" +
                "||_________________________________________________________________||\n";
        String actual = flightService.showAvaiableFlights("Baku","2020-03","200");
        assertEquals(expected,actual);
    }

    @Test
    void avaiableFlightsIDNull(){
        assertTrue(flightService.avaiableFlightsID("Baku","2021-03","5").isEmpty());
    }

    @Test
    void avaiableFlightsID(){
        assertFalse(flightService.avaiableFlightsID("Baku", "2020-03", "5").isEmpty());
    }
}
