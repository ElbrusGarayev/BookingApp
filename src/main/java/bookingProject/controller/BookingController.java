package bookingProject.controller;

import bookingProject.entity.Booking;
import bookingProject.entity.Flight;
import bookingProject.entity.Passenger;
import bookingProject.io.Console;
import bookingProject.service.BookingService;
import bookingProject.service.FlightService;
import bookingProject.service.UserService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BookingController {
    private Console console;
    private BookingService bookingService;
    private FlightService flightService;
    private UserService userService;

    public BookingController(Console console, BookingService service, FlightService flightService, UserService userService) {
        this.console = console;
        this.bookingService = service;
        this.flightService = flightService;
        this.userService = userService;
    }

    public BookingController(BookingService bookingService, FlightService flightService) {
        this.flightService = flightService;
        this.bookingService = bookingService;
    }

    public void myBookings() {
        console.print("Please enter your name: ");
        String name = console.readLn();
        console.print("Please enter your surname: ");
        String surname = console.readLn();
        Collection<Booking> allBookings = bookingService.getAllBookings();
        Collection<Flight> allFlights = flightService.getAllFlights();
        if (!allBookings.isEmpty()) {
            console.printLn("||=================================================================||\n" +
                    "||                           My Flights                            ||\n" +
                    "||=================================================================||\n");
            for (Booking b : allBookings) {
                for (Flight f : allFlights) {
                    if (b.getPassengers().stream().anyMatch(
                            o -> o.getFirstname().equals(name)
                                    && o.getLastname().equals(surname))
                            && b.getFlight_id() == f.getId()
                            && b.getUser_id() == userService.loggedID) {
                        console.printLn(f.represent() + "\n" + b.represent() + "\n");
                        break;
                    }
                }
            }
        } else console.printLn("You don't have any bookings!");
    }

    public void searchAndMakeBooking() {
        List<Passenger> passengers = new ArrayList<>();
        String location = "";
        String destination = "";
        String date = "";
        String passCount = "";
        try{
            console.print("Enter the location: ");
            location = console.readLn();
            console.print("Enter the destination: ");
            destination = console.readLn();
            console.print("Enter the date: ");
            date = console.readLn();
            console.print("Enter the passengers' count: ");
            passCount = console.readLn();
        }catch (Exception e){
            console.printLn("Invalid input!");
        }
        String avaiableFlights = flightService.showAvaiableFlights(location, destination, date, passCount);

        if (avaiableFlights.isEmpty()) console.printLn("No flight were found matching these criteria!");
        else {
            console.printLn(avaiableFlights);
            console.print("1. Book a flight\n" +
                    "2. Exit\n" + "Make your choice: ");
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
                    Booking booking = new Booking(userService.loggedID, parseInt(id), passengers);
                    List<Long> avaiableFlightsID = flightService.avaiableFlightsID(destination, date, passCount);

                    console.printLn(bookingService.makeBooking(booking, avaiableFlightsID, passCount, id));
                    break;
                case "2":
                    System.gc();
                    break;
                default:
                    console.printLn("Wrong choice!");
            }
        }
    }

    public void cancelBooking() {
        ArrayList<Booking> bookings = (ArrayList<Booking>) bookingService.getAllBookings();
        int maxBookID = 0;
        if(!bookings.isEmpty())
            maxBookID = (int) bookings.get(bookings.size() - 1).getId();
        Collection<Flight> allFlights = flightService.getAllFlights();

        if (!bookingService.showAvaiableBookings(allFlights).isEmpty()) {
            console.printLn(bookingService.showAvaiableBookings(allFlights));
            console.print("Enter the ID of you want cancel: ");
            try {
                String id = console.readLn();
                console.printLn(bookingService.cancelBooking(maxBookID, parseInt(id)));
            } catch (Exception e) {
                console.printLn("Invalid input!");
            }
        } else
            console.printLn("You don't have any bookings!");
    }


}
