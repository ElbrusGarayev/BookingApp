package bookingProject;

import bookingProject.controller.AppController;
import bookingProject.io.Console;
import bookingProject.io.UnixConsole;
import bookingProject.service.AppService;

import java.util.Scanner;

public class BookingApp {
    public static void main(String[] args) {
        Console console = new UnixConsole(new Scanner(System.in));
        Database db = new Database();
        AppService service = new AppService(db);
        AppController controller = new AppController(console, service);
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
                    controller.showAllFlights();
                    break;
                case "2":
                    controller.getFlightByID();
                    break;
                case "3":
                    controller.searchAndMakeBooking();
                    break;
                case "4":
                    controller.cancelBooking();
                    break;
                case "5":
                    controller.myBookings();
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
