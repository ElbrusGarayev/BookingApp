package bookingProject.entity;

import java.io.Serializable;

public class Passenger implements Serializable {
    private final String firstname;
    private final String lastname;

    private static final long serialVersionUID = 1L;

    public Passenger(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

}
