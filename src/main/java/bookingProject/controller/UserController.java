package bookingProject.controller;



import bookingProject.entity.User;
import bookingProject.io.Console;
import bookingProject.service.FlightService;
import bookingProject.service.UserService;

import java.util.List;

public class UserController {
    public User LoggedUser;
    private Console console;
    UserService userService;

    public UserController(Console console, UserService service) {
        this.console = console;
        this.userService = service;
    }

    public List<User> getAll() {
        return userService.getAll();
    }

    public boolean login(){
        boolean bool = false;
        console.print("Enter username: ");
        String username = console.readLn();
        console.print("Enter password: ");
        String password = console.readLn();
        if(userService.login(new User(username, password))){
            console.printLn("Logged in!");
            return true; }
        else{
            console.printLn("Username or Password is wrong!");
            return false;
        }
    }

    public void register() {
        console.print("Enter username: ");
        String username = console.readLn();
        console.print("Enter password: ");
        String password = console.readLn();
        console.printLn(userService.register(new User(username, password)));
    }
}
