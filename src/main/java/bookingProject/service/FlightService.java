package bookingProject.service;

import bookingProject.Database;
import bookingProject.entity.Flight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Integer.parseInt;

public class FlightService {

    private Database db;

    public FlightService(Database db) {
        this.db = db;
    }

    public Collection<Flight> getAllFlightsByDaily() {
        return db.flights.getAllBy(f -> (
                f.getDate().isAfter(LocalDateTime.now())
                && f.getDate().isBefore(LocalDateTime.now().plusHours(12)))
        );
    }

    public Collection<Flight> getAllFlights() {
        return db.flights.getAll();
    }

    public Flight getFlight(long id) {
        return db.flights.get((int) id).get();
    }

    public String showAvaiableFlights(String location, String destination, String date, String passCount){
        StringBuilder sb = new StringBuilder();
        Collection<Flight> allFlights = getAllFlights();
        for (Flight f : allFlights) {
            if (
                    f.getCityFrom().equals(location)
                    && f.getCityTo().equals(destination)
                    && (f.getDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")).equals(date)
                    || f.getDate().format(DateTimeFormatter.ofPattern("YYYY-MM")).equals(date))
                    && f.getSeats() >= parseInt(passCount) && f.getDate().isAfter(LocalDateTime.now())
            ) {
                sb.append(f.represent());
            }
        }

        if(sb.toString().isEmpty()){
            for (Flight f : allFlights) {
                if (
                        f.getCityFrom().equals(location)
                        && (f.getDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")).equals(date)
                        || f.getDate().format(DateTimeFormatter.ofPattern("YYYY-MM")).equals(date))
                        && f.getSeats() >= parseInt(passCount) && f.getDate().isAfter(LocalDateTime.now())
                ) {
                    for (Flight f2: allFlights) {
                        if(
                                f.getCityTo().equals(f2.getCityFrom()) && f2.getCityTo().equals(destination)
                                && f2.getSeats() >= parseInt(passCount)
                                && f2.getDate().isBefore(f.getDate().plusHours(12))
                                && f2.getDate().isAfter(f.getDate().plusHours(0))
                        )
                            sb.append(f.represent()).append(f2.represent());
                    }
                }
            }
        }
        return sb.toString();
    }

    public List avaiableFlightsID(String destination, String date, String passCount){
        Collection<Flight> allFlights = getAllFlights();
        List<Long> fitFlightIDs = new ArrayList<>();
        for (Flight f : allFlights) {
            if (
                    f.getCityTo().equals(destination)
                    && (f.getDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")).equals(date)
                    || f.getDate().format(DateTimeFormatter.ofPattern("YYYY-MM")).equals(date))
                    && f.getSeats() >= parseInt(passCount)
            ){
                fitFlightIDs.add(f.getId());
            }
        }
        return fitFlightIDs;
    }
}
