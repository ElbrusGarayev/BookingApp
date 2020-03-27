package bookingProject;

import bookingProject.dao.BookingDAO;
import bookingProject.dao.DAO;
import bookingProject.dao.FlightDAO;
import bookingProject.dao.UserDao;
import bookingProject.entity.Booking;
import bookingProject.entity.Flight;
import bookingProject.entity.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bookingProject.util.GenerateRandomData.*;

public class Database {
    public final DAO<User> users;
    public final DAO<Flight> flights;
    public final DAO<Booking> bookings;

    public Database() {
        this.users = new UserDao("users.bin");
        this.flights = new FlightDAO("flights.bin");
        this.bookings = new BookingDAO("bookings.bin");

        setMaxCounters();
        if (flights.getAll().isEmpty()) createInitialData();
    }

    private void createInitialData() {
//        List<Flight> flight = IntStream.range(0, 300).mapToObj((n) -> new Flight(
//                randomCityFrom(),
//                randomCityTo(),
//                randomData(),
//                randomSeats()
//        )).collect(Collectors.toList());
//        flight.forEach(flights::create);

        List<Flight> flight = IntStream.range(0, 300).mapToObj((n) -> new Flight(
                randomCityFrom(),
                randomCityTo(),
                randomData(),
                randomSeats()
        )).filter(flight1 -> !flight1.getCityFrom().equals(flight1.getCityTo()))
                .collect(Collectors.toList());
        flight.forEach(flights::create);
    }

    private void setMaxCounters() {
        long maxu = users.getAll().stream().mapToLong(User::getId).max().orElse(0);
        User.setCounter(maxu);
        long maxf = flights.getAll().stream().mapToLong(Flight::getId).max().orElse(0);
        Flight.setCounter(maxf);
        long maxb = bookings.getAll().stream().mapToLong(Booking::getId).max().orElse(0);
        Booking.setCounter(maxb);
    }
}
