package bookingProject;

import bookingProject.controller.AppController;
import bookingProject.dao.DAOAbstractFileBin;
import bookingProject.entity.Booking;
import bookingProject.entity.Flight;
import bookingProject.service.AppService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTests {

    private AppController appCont;
    private AppService appser;
    private DAOAbstractFileBin dao;
    private Flight flight;


    @BeforeEach
    public void initialize(){
        this.appCont = new AppController();
        this.appser = new AppService();
        this.dao = new DAOAbstractFileBin();
    }

    @Test
    public void controllerTest(){

    }
}
