package holiday.domain;

import java.util.Objects;

/**
 * The Hotel class extends BaseEntity where it stores the ID.
 * This class has the following attributes:
 *      the id of the hotel   -> id   : Long ( stored in BaseEntity )
 *      the name of the hotel -> name : String
 *      the location of the hotel -> location : String
 *      the number of stars of the hotel  -> stars  : Integer
 *      the capacity of the hotel -> capacity : Integer
 *
 * The class contains a constructor, getters and setters for all fields, a toString function and an equals function.
 **/

public class Hotel extends BaseEntity<Long> {
    String name;
    String location;
    int stars;
    int capacity;

    public Hotel(String name, String location, int stars, int capacity) {
        this.name = name;
        this.location = location;
        this.stars = stars;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getStars() {
        return stars;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return stars == hotel.stars && capacity == hotel.capacity && Objects.equals(name, hotel.name) && Objects.equals(location, hotel.location);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", stars=" + stars +
                ", capacity=" + capacity +
                super.toString() + "}";

    }
}
