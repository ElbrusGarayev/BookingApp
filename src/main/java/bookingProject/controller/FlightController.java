package bookingProject.controller;

import bookingProject.entity.Flight;
import bookingProject.io.Console;
import bookingProject.service.FlightService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Integer.parseInt;

public class FlightController {
    private Console console;
    private FlightService service;

    public FlightController(Console console, FlightService service) {
        this.console = console;
        this.service = service;
    }

    public void showAllFlights() {
        Collection<Flight> allFlights = service.getAllFlightsByDaily();
        allFlights.forEach(f -> console.printLn(f.represent()));
    }

    public void getFlightByID() {
        List<Flight> allFlights = new ArrayList<>(service.getAllFlights());
        int minFlightID = (int) allFlights.get(0).getId();
        int maxFlightID = (int) allFlights.get(allFlights.size() - 1 ).getId();

        console.print("Enter the flight ID: ");
        String id = console.readLn();
        if(!(parseInt(id) > maxFlightID || parseInt(id) < minFlightID))
            console.printLn(service.getFlight(parseInt(id)).represent());
        else console.printLn("Wrong Flight ID!");
    }
}

