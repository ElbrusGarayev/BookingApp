package bookingProject;

import bookingProject.entity.Flight;
import bookingProject.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
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
        String actual = flightService.showAvaiableFlights("Dubai", "Baku","2021-03","1");
        assertTrue(actual.isEmpty());
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
