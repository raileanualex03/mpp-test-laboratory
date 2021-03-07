package holiday.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ClientTest {
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;

    private static final String NAME = "c1";
    private static final String NEW_NAME = "c2";

    private static final int AGE = 23;
    private static final int NEW_AGE = 24;

    private Client client;

    @Before
    public void setUp() {
        client = new Client(NAME, AGE);
        client.setId(ID);
    }

    @After
    public void tearDown() {
        client = null;
    }

    @Test
    public void testGetId() {
        assertEquals("Ids should be equal!", ID, client.getId());
    }

    @Test
    public void testSetId() {
        client.setId(NEW_ID);
        assertEquals("Ids should be equal!", NEW_ID, client.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Names are supposed to be equal!", NAME, client.getName());
    }

    @Test
    public void testSetName() {
        client.setName(NEW_NAME);
        assertEquals("Names are supposed to be equal", NEW_NAME, client.getName());
    }

    @Test
    public void testGetAge() {
        assertEquals("Ages should be equal", AGE, client.getAge());
    }

    @Test
    public void testSetAge() {
        client.setAge(NEW_AGE);
        assertEquals("Ages should be to be equal", NEW_AGE, client.getAge());
    }

}
