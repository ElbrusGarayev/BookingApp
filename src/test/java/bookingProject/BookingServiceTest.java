package bookingProject;

import bookingProject.entity.Booking;
import bookingProject.entity.Passenger;
import bookingProject.service.BookingService;
import bookingProject.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceTest {
    BookingService bookingService;
    FlightService flightService;
    Database db;
    @BeforeEach
    public void initialize() {
        this.db = new Database();
        this.flightService = new FlightService(db);
        this.bookingService = new BookingService(db,flightService);
        
    }

    @Test
    void getMyBookings(){
        ArrayList<Passenger> passengers = new ArrayList<>(Collections.singletonList(new Passenger("Elbrus", "Garayev")));
        List<Long> flightsID = flightService.avaiableFlightsID("Baku","2020-03","1");
        long index = (long) (Math.random() * flightsID.size()) ;
        long id = flightService.getFlight(flightsID.get((int) index)).getId();
        Booking booking = (new Booking(8, id, passengers));
        bookingService.makeBooking(booking,flightsID,"1",String.valueOf(id));
        ArrayList<Booking> bookings = (ArrayList<Booking>) bookingService.getAllBookings();
        assertEquals(bookings.get(bookings.size() - 1).represent(), booking.represent());
    }
    @Test
    void makeBooking() {
        ArrayList<Passenger> passengers = new ArrayList<>(Collections.singletonList(new Passenger("Elbrus", "Garayev")));
        List<Long> flightsID = flightService.avaiableFlightsID("Baku","2020-03","1");
        long index = (long) (Math.random() * flightsID.size()) ;
        long id = flightService.getFlight(flightsID.get((int) index)).getId();
        Booking booking = (new Booking(8, id, passengers));
        String book = bookingService.makeBooking(booking,flightsID,"1",String.valueOf(id));
        assertEquals("The flight was booked!", book);
    }
    @Test
    void cancelBooking1() {
        ArrayList<Passenger> passengers = new ArrayList<>(Collections.singletonList(new Passenger("Elbrus", "Garayev")));
        List<Long> flightsID = flightService.avaiableFlightsID("Baku","2020-03","1");
        long index = (long) (Math.random() * flightsID.size()) ;
        long id = flightService.getFlight(flightsID.get((int) index)).getId();
        Booking booking = (new Booking(8, id, passengers));
        bookingService.makeBooking(booking,flightsID,"1",String.valueOf(id));
        ArrayList<Booking> bookings = (ArrayList<Booking>) bookingService.getAllBookings();
        int maxBookID = (int) bookings.get(bookings.size() - 1).getId();
        assertEquals("Booking canceled!", bookingService.cancelBooking(maxBookID,8));
    }
    @Test
    void cancelBooking2() {
        assertEquals("Wrong ID!", bookingService.cancelBooking(600,8));
    }
}