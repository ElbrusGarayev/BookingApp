package bookingProject;

import bookingProject.entity.User;
import bookingProject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    UserService userService;
    Database db;
    @BeforeEach
    public void initialize() {
        this.db = new Database();
        this.userService = new UserService(db);
    }

    @Test
    void loginFalse(){
        assertFalse(userService.login(new User("user1","admin")));
    }

    @Test
    void loginTrue(){
        userService.register(new User("user","pass"));
        assertTrue(userService.login(new User("user","pass")));
    }

    @Test
    void registered(){
        Random random = new Random();
        String generatedString = random.ints(97, 122 + 1)
                .limit(5)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        assertEquals("You are registered!", userService.register(new User(generatedString,generatedString)));
    }

    @Test
    void notRegistered(){
        userService.register(new User("user1","123"));
        assertEquals("Username has already defined!", userService.register(new User("user1","123")));
    }
}
