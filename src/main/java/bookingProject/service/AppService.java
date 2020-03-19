package bookingProject.service;

import bookingProject.Database;
import bookingProject.entity.Booking;
import bookingProject.entity.Flight;
import bookingProject.entity.Passenger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

public class AppService {

    private final Database db;

    public AppService(Database db) {
        this.db = db;
    }

    public Collection<Flight> getAllFlightsByDaily() {

        return db.flights.getAllBy(f -> (LocalDateTime.now())
                .format(DateTimeFormatter.ofPattern("YYYY-MM-dd")).equals(f.getDate()
                        .format(DateTimeFormatter.ofPattern("YYYY-MM-dd"))));
    }

    public Collection<Flight> getAllFlights() {
        return db.flights.getAll();
    }

    public Flight getFlight(int id) {
        return db.flights.get(id).get();
    }

    public void makeBooking(int flightId, List<Passenger> passengers) {
        Booking booking = new Booking(flightId, passengers);
        db.bookings.create(booking);
    }

    public Collection<Booking> getAllBookings() {
        return db.bookings.getAll();
    }

    public void cancelBooking(int id) {
        db.bookings.delete(id);
    }

    public void updateSeatsCount(Collection<Flight> flights) {
        db.flights.update(flights);
    }

}
