package bookingProject.dao;

import bookingProject.entity.Booking;
import bookingProject.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserDao implements DAO<User> {

  private File file;

  public UserDao(String filename) {
    this.file = new File(filename);
  }

  @Override
  public Collection<User> getAll() {
    try (ObjectInputStream ois =
                 new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      Object readed = ois.readObject();
      @SuppressWarnings("unchecked")
      List<User> as = (ArrayList<User>) readed;
      return as;
    } catch (ClassNotFoundException ex) {
      throw new RuntimeException("Deserialization error. Didn't you forget to include 'serialVersionUID field' in your entity?", ex);
    } catch (FileNotFoundException ex) {
      return new ArrayList<>();
    } catch (IOException ex) {
      throw new RuntimeException("Something went wrong", ex);
    }
  }

  @Override
  public Collection<User> getAllBy(Predicate<User> p) {
    return getAll().stream().filter(p).collect(Collectors.toList());
  }

  @Override
  public Optional<User> get(int id) {
    return getAll().stream().filter(s -> s.getId() == id).findFirst();
  }

  @Override
  public void create(User data) {
    Collection<User> as = getAll();
    as.add(data);
    write(as);
  }

  @Override
  public void delete(long id) {
    Collection<User> as = getAllBy(s -> s.getId() != id);
    write(as);
  }

  @Override
  public void update(Collection<User> users) {
    write(users);
  }

  private void write(Collection<User> as) {
    try (ObjectOutputStream oos =
                 new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      oos.writeObject(as);
    } catch (IOException ex) {
      throw new RuntimeException("DAO:write:IOException", ex);
    }
  }

}

