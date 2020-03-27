package bookingProject.entity;

import bookingProject.dao.Identifiable;

import java.io.Serializable;

public class User implements Identifiable, Serializable {
  private long id;
  private String username;
  private String password;

  static long counter;

  private static final long serialVersionUID = 1L;

  public User(String username, String password) {
    this(++counter, username, password);
  }

  public User(long id, String username, String password) {
    this.username = username;
    this.password = password;
    this.id = id;
  }

  public static void setCounter(long counter) {
    User.counter = counter;
  }

  @Override
  public long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String represent() {
    return String.format("User{id=%d, login='%s', passwd='%s'}", id, username, password);
  }
}