package holiday.domain;

import holiday.exceptions.StringNotConvertibleToDateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class PackageHolidayTest {


    private static final String START_DATE_STRING= "2021/2/2";
    private static final String END_DATE_STRING= "2021/2/2";
    private static final int PRICE = 300;
    private static final Long CLIENT_ID = 1L;
    private static final Long HOTEL_ID = 1L;
    private static final Long NEW_CLIENT_ID = 2L;
    private static final Long NEW_HOTEL_ID = 1L;

    private PackageHoliday packageHoliday;
    @Before
    public void setUp() throws StringNotConvertibleToDateException {
        packageHoliday = new PackageHoliday(CLIENT_ID, HOTEL_ID, START_DATE_STRING, END_DATE_STRING, PRICE);
    }

    @After
    public void tearDown() {
        packageHoliday = null;
    }

    @Test
    public void testGetClientId() {
        assertEquals("Ids should be equal!", CLIENT_ID, packageHoliday.getClientId());
    }

    @Test
    public void testGetHotelId() {
        assertEquals("Ids should be equal!", HOTEL_ID, packageHoliday.getHotelId());
    }

    @Test
    public void testSetClientId() {
        packageHoliday.setClientId(NEW_CLIENT_ID);
        assertEquals("Ids should be equal!", NEW_CLIENT_ID, packageHoliday.getClientId());
    }

    @Test
    public void testSetHotelId() {
        packageHoliday.setHotelId(NEW_HOTEL_ID);
        assertEquals("Ids should be equal!", NEW_HOTEL_ID, packageHoliday.getHotelId());
    }

    @Test
    public void testGetStartDateYear() {
        assertEquals("Years should be equal", 2021, packageHoliday.getStartDate().get(Calendar.YEAR));
    }

    @Test
    public void testGetEndDateYear() {
        assertEquals("Years should be equal", 2021, packageHoliday.getEndDate().get(Calendar.YEAR));
    }

    @Test
    public void testSetStartDateYear() throws StringNotConvertibleToDateException {
        packageHoliday.setStartDate("2022/1/1");
        assertEquals("Years should be equal", 2022, packageHoliday.getStartDate().get(Calendar.YEAR));
    }

    @Test
    public void testSetEndDateYear() throws StringNotConvertibleToDateException {
        packageHoliday.setEndDate("2022/1/1");
        assertEquals("Years should be equal", 2022, packageHoliday.getEndDate().get(Calendar.YEAR));
    }

    @Test
    public void testSetStartDateYear_throwsError() {
        Assertions.assertThrows(StringNotConvertibleToDateException.class, () -> {
            packageHoliday.setStartDate("2000/1/1/1/2");
        });
    }

    @Test
    public void testSetEndDateYear_throwsError() {
        Assertions.assertThrows(StringNotConvertibleToDateException.class, () -> {
            packageHoliday.setEndDate("2000/1/1/1/2");
        });
    }
}
