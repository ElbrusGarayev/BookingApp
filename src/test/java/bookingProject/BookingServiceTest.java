//package bookingProject;
//
//import bookingProject.entity.Booking;
//import bookingProject.entity.Passenger;
//import bookingProject.service.BookingService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class BookingServiceTest {
//    BookingService bookingService;
//    @BeforeEach
//    public void initialize() throws IOException {
//        this.bookingService = new BookingService();
//    }
//    @Test
//    void getAllBookings() {
//        int bookings = 3;
//        assertEquals(bookings, bookingService.getAllBookings().size());
//    }
//    @Test
//    void getMyBookings(){
//        Passenger mary = new Passenger("Mary", "Black");
//        Passenger michael = new Passenger("Michael", "Black");
//        bookingService.makeBooking(100, new ArrayList<>(Arrays.asList(mary,michael)));
//        assertTrue(bookingService.getAllBookings("Mary", "Black").contains(new Booking(4, 100,
//                new ArrayList<>(Arrays.asList(mary, michael)))));
//    }
//    @Test
//    void createBooking() {
//        ArrayList<Person> people = new ArrayList<>(Collections.singletonList(new Person("Turkan", "Ismayilova")));
//        Booking booking = (new Booking(4, 3, people));
//        bookingService.createBooking(3, people);
//        ArrayList<Booking> bookings = (ArrayList<Booking>) bookingService.daoBooking.getAll();
//        assertEquals(booking.toString(), bookings.get(3).toString());
//    }
//    @Test
//    void deleteBooking() {
//        Booking booking = new Booking(1, 1, null);
//        bookingService.daoBooking.create(booking);
//        bookingService.deleteBooking(0);
//        assertEquals("Booking deleted", bookingService.deleteBooking(1));
//    }
//    @Test
//    void getBookingById() {
//        Booking booking = bookingService.getAllBookings().get(0);
//        assertEquals(booking, bookingService.getBookingById(1));
//        try {
//            assertEquals(booking, bookingService.getBookingById(15));
//        } catch (Exception ex){
//            assertEquals("Booking was not found", ex.getMessage());
//        }
//    }
//    @Test
//    void getNumOfSeats() {
//        Person mary = new Person("Mary", "Black");
//        Person michael = new Person("Michael", "Black");
//        Booking booking = new Booking(1, 1, new ArrayList<>(Arrays.asList(mary, michael)));
//        bookingService.daoBooking.create(booking);
//        int x = bookingService.getNumOfSeats(1);
//        assertEquals(x, 2);
//    }
//}