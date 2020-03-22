package bookingProject.service;

import bookingProject.Database;
import bookingProject.entity.Booking;
import bookingProject.entity.Flight;
import bookingProject.entity.Passenger;

import java.util.Collection;
import java.util.List;

public class BookingService {

    private Database db;

    public BookingService(Database db) {
        this.db = db;
    }

    public BookingService() {

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

    public Collection<Flight> getAllFlights() {
        return db.flights.getAll();
    }
}
