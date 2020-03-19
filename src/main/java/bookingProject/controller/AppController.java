package bookingProject.controller;

import bookingProject.entity.Booking;
import bookingProject.entity.Flight;
import bookingProject.entity.Passenger;
import bookingProject.io.Console;
import bookingProject.service.AppService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Integer.parseInt;

public class AppController {
    private final Console console;
    private final AppService service;

    public AppController(Console console, AppService service) {
        this.console = console;
        this.service = service;
    }

    public void cancelBooking() {
        Collection<Booking> allBookings = service.getAllBookings();
        int maxBookID = 0;
        if (!allBookings.isEmpty()) {
            Collection<Flight> allFlights = service.getAllFlights();
            for (Booking b : allBookings) {
                for (Flight f : allFlights) {
                    if (b.getFlight_id() == f.getId()) {
                        console.printLn("->Booking ID: " + b.getId() + "\n" + f.represent());
                    }
                }
                maxBookID = (int) b.getId();
            }

            console.print("Enter the ID of you want cancel: ");
            String id = console.readLn();
            if (!(maxBookID < parseInt(id) || parseInt(id) <= (maxBookID - allBookings.size()))) {
                service.cancelBooking(parseInt(id));
                console.printLn("Booking canceled!");
            } else console.printLn("Wrong ID!");

        } else console.printLn("You don't have any bookings!");
    }

    public void myBookings() {
        console.print("Please enter your name: ");
        String name = console.readLn();
        console.print("Please enter your surname: ");
        String surname = console.readLn();
        Passenger pas = new Passenger(name, surname);
        Collection<Booking> allBookings = service.getAllBookings();
        Collection<Flight> allFlights = service.getAllFlights();
        if(!allBookings.isEmpty()){
            console.printLn("||=================================================================||\n" +
                    "||                           My Flights                            ||\n" +
                    "||=================================================================||\n");
            for (Booking b : allBookings) {
                for (Flight f : allFlights) {
                    if (b.getFlight_id() == f.getId() || b.getPassengers().stream().anyMatch(o ->
                            o.getFirstname().equals(name) && o.getLastname().equals(surname))) {
                        console.printLn(f.represent() + "\n" + b.represent() + "\n");
                        break;
                    }
                }
            }
        }
        else console.printLn("You don't have any bookings!");

    }

    public void searchAndMakeBooking() {
        Collection<Flight> allFlights = service.getAllFlights();
        List<Passenger> passengers = new ArrayList<>();
        console.print("Enter the destination: ");
        String destination = console.readLn();
        console.print("Enter the date: ");
        String date = console.readLn();
        console.print("Enter the passengers' count: ");
        String passCount = console.readLn();

        int size = allFlights.size();
        for (Flight f : allFlights) {
            if (f.getCityTo().equals(destination) && (f.getDate()
                    .format(DateTimeFormatter.ofPattern("YYYY-MM-dd")).equals(date)
                    || f.getDate().format(DateTimeFormatter.ofPattern("YYYY-MM")).equals(date))
                    && f.getSeats() >= parseInt(passCount)) {
                console.printLn(f.represent());
            } else size--;
        }
        if (size == 0) console.printLn("No flight were found matching these criteria!");
        else {
            console.printLn("1. Book a flight\n" +
                    "2. Exit");
            String choice = console.readLn();
            switch (choice) {
                case "1":
                    for (int i = 1; i <= parseInt(passCount); i++) {
                        console.print("Enter the " + i + "th passenger name: ");
                        String name = console.readLn();
                        console.print("Enter the " + i + "th passenger surname: ");
                        String surname = console.readLn();
                        passengers.add(new Passenger(name, surname));
                    }
                    console.print("Enter the flight ID which you want book: ");
                    String id = console.readLn();
                    service.makeBooking(parseInt(id), passengers);
                    decreaseSeats(parseInt(id), parseInt(passCount));
                    console.printLn("The flight was booked!");
                    break;
                case "2":
                    System.gc();
                    break;
                default:
                    console.printLn("Wrong choice!");
            }
        }
    }

    public void decreaseSeats(int flightID, int passCount) {
        Collection<Flight> allFlights = service.getAllFlights();
        for (Flight flight : allFlights) {
            if (flight.getId() == flightID) {
                flight.setSeats(flight.getSeats() - passCount);
            }
        }
        service.updateSeatsCount(allFlights);
    }

    public void showAllFlights() {
        Collection<Flight> allFlights = service.getAllFlightsByDaily();
        allFlights.forEach(f -> console.printLn(f.represent()));
    }

    public void getFlightByID() {
        console.print("Enter the flight ID: ");
        String id = console.readLn();
        console.printLn(service.getFlight(parseInt(id)).represent());
    }
}
