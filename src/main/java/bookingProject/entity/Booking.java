package bookingProject.entity;

import bookingProject.dao.Identifiable;

import java.io.Serializable;
import java.util.List;

public class Booking implements Serializable, Identifiable {
    private long id;
    private long flight_id;
    private List<Passenger> passengers;

    static long counter = 0;

    private static final long serialVersionUID = 1L;

    public static void setCounter(long counter) {
        Booking.counter = counter;
    }

    public Booking(long flight_id, List<Passenger> passengers) {
        this(++counter, flight_id, passengers);
    }

    public Booking(long id, long flight_id, List<Passenger> passengers) {
        this.id = id;
        this.flight_id = flight_id;
        this.passengers = passengers;
    }

    @Override
    public long getId() {
        return id;
    }

    public long getFlight_id() {
        return flight_id;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public String represent() {
        StringBuilder sb = new StringBuilder();
        sb.append("Passengers:\n");
        for (Passenger p : passengers) {
            sb.append(p.getFirstname() + " " + p.getLastname() + "\n");
        }
        return sb.toString();
    }
}
