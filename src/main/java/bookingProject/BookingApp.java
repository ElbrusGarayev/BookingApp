package bookingProject;

import bookingProject.controller.BookingController;
import bookingProject.controller.FlightController;
import bookingProject.controller.UserController;
import bookingProject.io.Console;
import bookingProject.io.UnixConsole;
import bookingProject.service.BookingService;
import bookingProject.service.FlightService;
import bookingProject.service.UserService;

import java.util.Scanner;

public class BookingApp {
    public static void main(String[] args) {
        Console console = new UnixConsole(new Scanner(System.in));
        Database db = new Database();
        UserService userService = new UserService(db);
        FlightService flightService = new FlightService(db);
        BookingService bookingService = new BookingService(db, flightService, userService);
        FlightController flightControl = new FlightController(console, flightService);
        BookingController bookingControl = new BookingController(console, bookingService, flightService, userService);
        UserController userController = new UserController(console,userService);

        StringBuilder menu = new StringBuilder();
        menu
                .append("||=====================================||\n")
                .append("||             BOOKING APP             ||\n")
                .append("||=====================================||\n")
                .append("||     1. Online-board.                ||\n")
                .append("||     2. Show the flight info.        ||\n")
                .append("||     3. Search and book a flight.    ||\n")
                .append("||     4. Cancel the booking.          ||\n")
                .append("||     5. My flights.                  ||\n")
                .append("||     6. Log out                      ||\n")
                .append("||     7. Exit                         ||\n")
                .append("||=====================================||\n")
                .append("Enter your choice: ");

        StringBuilder logMenu = new StringBuilder();
        logMenu
                .append("||=====================||\n")
                .append("||    Entrance Menu    ||\n")
                .append("||=====================||\n")
                .append("||      1. Login       ||\n")
                .append("||      2. Register    ||\n")
                .append("||      3. Exit        ||\n")
                .append("||=====================||\n")
                .append("Enter your choice: ");

        boolean log = false;
        while(true){
            while(!log){
                console.print(logMenu.toString());
                String choice = console.readLn();
                switch (choice){
                    case "1": log = userController.login(); break;
                    case "2": userController.register(); break;
                    case "3": console.printLn("Bye"); System.exit(0); break;
                    default: console.printLn("Wrong choice!");
                }
            }
            while (log) {
                console.print(menu.toString());
                String choice = console.readLn();
                switch (choice) {
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
                        log = false;
                        break;
                    case "7":
                        console.printLn("Shut downed!");
                        System.exit(0);
                        break;
                    default:
                        console.printLn("Wrong choice");
                }
            }
        }
    }
}
