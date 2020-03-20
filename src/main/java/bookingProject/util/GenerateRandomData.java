package bookingProject.util;

import java.time.LocalDateTime;

public class GenerateRandomData {

    private final static String[] cities = {"Baku", "Lviv", "Kharkiv",
            "Delaware", "Abu-Dabi", "Dubai", "Ankara", "New-York", "Istanbul", "Oslo",
            "London", "Brasil", "Tokio", "Paris"};


    public static String randomCityFrom() {
        return "Kiev";
    }

    public static String randomCityTo() {
        return cities[rnd(cities.length - 1, 0)];
    }

    private static int rnd(int max, int min) {
        int range = max - min + 1;
        return (int) (Math.random() * range) + min;
    }

    public static int randomSeats() {
        return rnd(20, 0) * 10 + 10;
    }

    public static LocalDateTime randomData() {
        return LocalDateTime.of(2020,
                rnd(LocalDateTime.now().getMonth().getValue() + 1, LocalDateTime.now().getMonth().getValue()),
                rnd(28, LocalDateTime.now().getDayOfMonth()),
                6 + rnd(17, 0),
                rnd(11, 0) * 5);
    }
}
