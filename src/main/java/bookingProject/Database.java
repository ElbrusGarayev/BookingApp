package bookingProject;

import bookingProject.dao.DAO;
import bookingProject.dao.DAOAbstractFileBin;
import bookingProject.entity.Booking;
import bookingProject.entity.Flight;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bookingProject.util.GenerateRandomData.*;

public class Database {
    public final DAO<Flight> flights;
    public final DAO<Booking> bookings;

    public Database() {

        this.flights = new DAOAbstractFileBin<>("flights.bin");
        this.bookings = new DAOAbstractFileBin<>("bookings.bin");

        setMaxCounters();
        if (flights.getAll().isEmpty()) createInitialData();
    }

    private void createInitialData() {
        List<Flight> flight = IntStream.range(0, 300).mapToObj((n) -> new Flight(
                randomCityFrom(),
                randomCityTo(),
                randomData(),
                randomSeats()
        )).collect(Collectors.toList());
        flight.forEach(flights::create);
    }

    private void setMaxCounters() {
        long maxf = flights.getAll().stream().mapToLong(Flight::getId).max().orElse(0);
        Flight.setCounter(maxf);
        long maxb = bookings.getAll().stream().mapToLong(Booking::getId).max().orElse(0);
        Booking.setCounter(maxb);
    }
}
