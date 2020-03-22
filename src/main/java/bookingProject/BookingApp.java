package bookingProject;

import bookingProject.controller.BookingController;
import bookingProject.controller.FlightController;
import bookingProject.io.Console;
import bookingProject.io.UnixConsole;
import bookingProject.service.BookingService;
import bookingProject.service.FlightService;

import java.util.Scanner;

public class BookingApp {
    public static void main(String[] args) {
        Console console = new UnixConsole(new Scanner(System.in));
        Database db = new Database();
        FlightService flightService = new FlightService(db);
        BookingService bookingService = new BookingService(db);
        FlightController flightControl = new FlightController(console, flightService);
        BookingController bookingControl = new BookingController(console, bookingService);

        int u = -1;
        boolean exit = false;
        while (!exit) {
            console.printLn("||=====================================||\n" +
                    "||             BOOKING APP             ||\n" +
                    "||=====================================||\n" +
                    "||     1. Online-board.                ||\n" +
                    "||     2. Show the flight info.        ||\n" +
                    "||     3. Search and book a flight.    ||\n" +
                    "||     4. Cancel the booking.          ||\n" +
                    "||     5. My flights.                  ||\n" +
                    "||     6. Exit                         ||\n" +
                    "||_____________________________________||");
            console.print("Enter your choice:");
            String line = console.readLn();
            switch (line) {
                case "1":
                    console.printLn("||=================================================================||\n" +
                            "||                           Online Board                          ||");
                    flightControl.showAllFlights();
                    break;
                case "2":
                    flightControl.getFlightByID();
                    break;
                case "3":
                    bookingControl.searchAndMakeBooking();
                    break;
                case "4":
                    bookingControl.cancelBooking();
                    break;
                case "5":
                    bookingControl.myBookings();
                    break;
                case "6":
                    console.printLn("Shut downed!");
                    exit = true;
                    break;
                default:
                    console.printLn("Wrong choice");
            }
        }
    }
}
