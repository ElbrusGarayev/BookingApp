package bookingProject.service;

import bookingProject.Database;
import bookingProject.entity.Booking;
import bookingProject.entity.Flight;
import bookingProject.entity.Passenger;
import bookingProject.io.Console;

import java.util.Collection;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BookingService {

    private Database db;
    private Console console;
    private FlightService flightService;
    private UserService userService;

    public BookingService(Database db) {
        this.db = db;
    }

    public BookingService(Database db, FlightService flightService, UserService userService) {
        this.db = db;
        this.flightService = flightService;
        this.userService = userService;
    }

    public String makeBooking(Booking booking,List<Long> avaiableFlightsID, String passCount, String id ) {
        if (avaiableFlightsID.contains((long) parseInt(id))) {
            db.bookings.create(booking);
            decreaseSeats(parseInt(id), parseInt(passCount));
            return "The flight was booked!";
        } else {
            return "Wrong ID!";
        }
    }

    public Collection<Booking> getAllBookings() {
        return db.bookings.getAll();
    }

    public String cancelBooking(int idMax, int id) {
        if (!(idMax < id || id <= (idMax - getAllBookings().size()))) {
            for (Booking b : getAllBookings()) {
                if (b.getId() ==(long) id && b.getUser_id() == userService.loggedID) {
                    increaseSeats(b);
                    db.bookings.delete(id);
                    return "Booking canceled!";
                }
            }
        } return "Wrong ID!";
    }

    public String showAvaiableBookings(Collection<Flight> flights){
        StringBuilder sb = new StringBuilder();
        for (Booking b : getAllBookings()) {
            for (Flight f : flights) {
                if (b.getFlight_id() == f.getId() && b.getUser_id() == userService.loggedID) {
                    sb.append("->Booking ID: ").append(b.getId()).append("\n").append(f.represent());
                }
            }
        }
        return sb.toString();
    }

    public void updateSeatsCount(Collection<Flight> flights) {
        db.flights.update(flights);
    }

    public void decreaseSeats(int flightID, int passCount) {
        Collection<Flight> allFlights = flightService.getAllFlights();
        for (Flight flight : allFlights) {
            if (flight.getId() == flightID) {
                flight.setSeats(flight.getSeats() - passCount);
            }
        }
        updateSeatsCount(allFlights);
    }

    public void increaseSeats(Booking b) {
        Collection<Flight> allFlights = flightService.getAllFlights();
        for (Flight flight : allFlights) {
            if (flight.getId() == b.getFlight_id()) {
                flight.setSeats(flight.getSeats() + b.getPassengers().size());
            }
        }
        updateSeatsCount(allFlights);
    }
}
