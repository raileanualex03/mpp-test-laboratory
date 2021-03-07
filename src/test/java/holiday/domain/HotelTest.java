package holiday.domain;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HotelTest {
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;

    private static final String NAME = "h1";
    private static final String NEW_NAME = "h2";

    private static final String LOCATION = "l1";
    private static final String NEW_LOCATION = "l2";

    private static final int STARS = 3;
    private static final int NEW_STARS = 4;

    private static final int CAPACITY = 100;
    private static final int NEW_CAPACITY = 120;

    private Hotel hotel;

    @Before
    public void setUp() {
        hotel = new Hotel(NAME, LOCATION, STARS, CAPACITY);
        hotel.setId(ID);
    }

    @After
    public void tearDown() {
        hotel = null;
    }

    @Test
    public void testGetId() {
        assertEquals("Ids should be equal!", ID, hotel.getId());
    }

    @Test
    public void testSetId() {
        hotel.setId(NEW_ID);
        assertEquals("Ids should be equal!", NEW_ID, hotel.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Names should be equal!", NAME, hotel.getName());
    }

    @Test
    public void testSetName() {
        hotel.setName(NEW_NAME);
        assertEquals("Names should be equal!", NEW_NAME, hotel.getName());
    }

    @Test
    public void testGetLocation() {
        assertEquals("Locations should be equal!", LOCATION, hotel.getLocation());
    }

    @Test
    public void testSetLocation() {
        hotel.setLocation(NEW_LOCATION);
        assertEquals("Locations should be equal!", NEW_LOCATION, hotel.getLocation());
    }

    @Test
    public void testGetStars() {
        assertEquals("Stars should be equal!", STARS, hotel.getStars());
    }

    @Test
    public void testSetStars() {
        hotel.setStars(NEW_STARS);
        assertEquals("Stars should be equal!", NEW_STARS, hotel.getStars());
    }

    @Test
    public void testGetCapacity() {
        assertEquals("Capacities should be equal!", CAPACITY, hotel.getCapacity());
    }

    @Test
    public void testSetCapacity() {
        hotel.setCapacity(NEW_CAPACITY);
        assertEquals("Capacities should be equal!", NEW_CAPACITY, hotel.getCapacity());
    }
}
