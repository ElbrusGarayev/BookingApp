package bookingProject.service;

import bookingProject.Database;
import bookingProject.entity.Flight;

import java.time.LocalDateTime;
import java.util.Collection;

public class FlightService {

    private Database db;

    public FlightService(Database db) {
        this.db = db;
    }

    public Collection<Flight> getAllFlightsByDaily() {
        return db.flights.getAllBy(f -> (f.getDate().isAfter(LocalDateTime.now())
                && f.getDate().isBefore(LocalDateTime.now().plusHours(24))));
    }

    public Collection<Flight> getAllFlights() {
        return db.flights.getAll();
    }

    public Flight getFlight(int id) {
        return db.flights.get(id).get();
    }
}
