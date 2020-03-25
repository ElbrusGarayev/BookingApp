package bookingProject.dao;

import bookingProject.entity.Flight;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightDAO<Flight extends Identifiable> implements DAO<Flight> {
    private File file;

    public FlightDAO(String filename) {
        this.file = new File(filename);
    }

    @Override
    public Collection<Flight> getAll() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            Object readed = ois.readObject();
            @SuppressWarnings("unchecked")
            List<Flight> as = (ArrayList<Flight>) readed;
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
    public Collection<Flight> getAllBy(Predicate<Flight> p) {
        return getAll().stream().filter(p).collect(Collectors.toList());
    }

    @Override
    public Optional<Flight> get(int id) {
        return getAll().stream().filter(s -> s.getId() == id).findFirst();
    }

    @Override
    public void create(Flight data) {
        Collection<Flight> as = getAll();
        as.add(data);
        write(as);
    }

    @Override
    public void delete(long id) {
        Collection<Flight> as = getAllBy(s -> s.getId() != id);
        write(as);
    }

    @Override
    public void update(Collection<Flight> bookings) {
        write(bookings);
    }

    private void write(Collection<Flight> as) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            oos.writeObject(as);
        } catch (IOException ex) {
            throw new RuntimeException("DAO:write:IOException", ex);
        }
    }
}
