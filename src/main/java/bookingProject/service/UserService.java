package bookingProject.service;


import bookingProject.Database;
import bookingProject.entity.User;

import java.util.List;


public class UserService {
    private Database db;
    public long loggedID;

    public UserService(Database db) {
        this.db = db;
    }

  public List<User> getAll() {
    return (List<User>) db.users.getAll();
  }

  public String register(User user) {
      for (User u: getAll()) {
          if (u.getUsername().equals(user.getUsername()))
              return "Username has already defined!";
      }
      db.users.create(user);
      return "You are registered!";
  }

  public boolean login(User user){
      for (User u: getAll()) {
          if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())){
              loggedID = u.getId();
              return true;}
      }
      return false;
  }
}