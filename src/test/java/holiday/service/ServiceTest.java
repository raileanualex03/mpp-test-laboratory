package holiday.service;

import holiday.domain.Client;
import holiday.domain.Hotel;
import holiday.domain.PackageHoliday;
import holiday.domain.PairIds;
import holiday.exceptions.StringNotConvertibleToDateException;
import holiday.exceptions.ValidatorException;
import holiday.repository.InMemoryRepository;
import holiday.validators.ClientValidator;
import holiday.validators.HotelValidator;
import holiday.validators.PackageHolidayValidator;
import holiday.validators.Validator;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ServiceTest {
        Validator<Client> clientValidator = new ClientValidator();
        Validator<Hotel> hotelValidator = new HotelValidator();
        Validator<PackageHoliday> packageHolidayValidator = new PackageHolidayValidator();

        InMemoryRepository<Long, Client> clientRepository = new InMemoryRepository<>(clientValidator);
        InMemoryRepository<Long, Hotel> hotelRepository = new InMemoryRepository<>(hotelValidator);
        InMemoryRepository<PairIds<Long, Long>, PackageHoliday> packageHolidayRepository = new InMemoryRepository<>(packageHolidayValidator);

        Service service = new Service(clientRepository, hotelRepository, packageHolidayRepository);

    /**
     * Tests for Client
     */
    @Test
    public void testAddClientService_checkSize_True() {
         Long ID = 1L;
         String NAME = "c1";
         int AGE = 20;

        Client client;
        client = new Client(NAME, AGE);
        client.setId(ID);

        service.addClient(client);
        assertEquals("The sizes should be equal!", 1, service.getAllClients().size());
    }

    @Test
    public void testAddClientService_checkAddedElement_True() {
        Long ID = 1L;
        String NAME = "c1";
        int AGE = 20;

        Client client;
        client = new Client(NAME, AGE);
        client.setId(ID);

        service.addClient(client);
        assertEquals("Clients should be equal!", client, service.getAllClients().stream().findFirst().get());
    }

    @Test
    public void testAddClientService_invalidAge_False() {
        Long ID = 1L;
        String NAME = "c1";
        int AGE = 7;

        Client client;
        client = new Client(NAME, AGE);
        client.setId(ID);

        Assertions.assertThrows(ValidatorException.class, () -> {
            service.addClient(client);
        });
    }


    /**
     * Tests for Hotel
     */
    @Test
    public void testAddHotelService_checkSize_True() {
        Long ID = 1L;
        String NAME = "h1";
        String LOCATION = "l1";
        int STARS = 3;
        int CAPACITY = 200;

        Long ID2 = 2L;
        String NAME2 = "h2";
        String LOCATIO2 = "l2";
        int STARS2 = 4;
        int CAPACITY2 = 200;

        Hotel hotel1;
        hotel1 = new Hotel(NAME,LOCATION,STARS,CAPACITY);
        hotel1.setId(ID);

        Hotel hotel2;
        hotel2 = new Hotel(NAME2,LOCATIO2,STARS2,CAPACITY2);
        hotel2.setId(ID2);

        service.addHotel(hotel1);
        service.addHotel(hotel2);
        assertEquals("The sizes should be equal!", 2, service.getAllHotels().size());
    }

    @Test
    public void testAddHotelService_checkAddedElement_True() {
        Long ID = 1L;
        String NAME = "h1";
        String LOCATION = "l1";
        int STARS = 3;
        int CAPACITY = 200;

        Hotel hotel;
        hotel = new Hotel(NAME,LOCATION,STARS,CAPACITY);
        hotel.setId(ID);

        service.addHotel(hotel);
        assertEquals("The hotels should be equal!", hotel, service.getAllHotels().stream().findFirst().get());
    }

    @Test
    public void testAddHotelService_invalidStars_False() {
        Long ID = 1L;
        String NAME = "h1";
        String LOCATION = "l1";
        int STARS = 6;
        int CAPACITY = 200;


        Hotel hotel;
        hotel = new Hotel(NAME,LOCATION,STARS,CAPACITY);
        hotel.setId(ID);

        Assertions.assertThrows(ValidatorException.class, () -> {
            service.addHotel(hotel);
        });
    }

    @Test
    public void testAddPackageHolidayService_checkSize_True() throws StringNotConvertibleToDateException {
        Long HOTEL_ID = 1L;
        String HOTEL_NAME = "h1";
        String HOTEL_LOCATION = "l1";
        int HOTEL_STARS = 3;
        int HOTEL_CAPACITY = 200;

        Hotel hotel;
        hotel = new Hotel(HOTEL_NAME,HOTEL_LOCATION,HOTEL_STARS,HOTEL_CAPACITY);
        hotel.setId(HOTEL_ID);

        service.addHotel(hotel);

        Long CLIENT_ID = 1L;
        String CLIENT_NAME = "c1";
        int CLIENT_AGE = 20;

        Client client;
        client = new Client(CLIENT_NAME, CLIENT_AGE);
        client.setId(CLIENT_ID);

        service.addClient(client);

        String START_DATE_STRING= "2021/2/2";
        String END_DATE_STRING= "2021/2/2";
        int PRICE = 300;
        PackageHoliday packageHoliday = new PackageHoliday(CLIENT_ID, HOTEL_ID, START_DATE_STRING, END_DATE_STRING, PRICE);
        service.addPackageHoliday(packageHoliday);
        assertEquals("the number of packages should be 1", 1, service.getAllPackages().size());
    }

    @Test
    public void testAddPackageHolidayService_hotelNotFound_throwsException() throws StringNotConvertibleToDateException {
        Long CLIENT_ID = 1L;
        String CLIENT_NAME = "c1";
        int CLIENT_AGE = 20;

        Client client;
        client = new Client(CLIENT_NAME, CLIENT_AGE);
        client.setId(CLIENT_ID);

        service.addClient(client);

        String START_DATE_STRING= "2021/2/2";
        String END_DATE_STRING= "2021/2/2";
        int PRICE = 300;
        PackageHoliday packageHoliday = new PackageHoliday(CLIENT_ID, 1L, START_DATE_STRING, END_DATE_STRING, PRICE);
        Assertions.assertThrows(ValidatorException.class, () -> {
            service.addPackageHoliday(packageHoliday);
        });
    }

    @Test
    public void testAddPackageHolidayService_clientNotFound_throwsException() throws StringNotConvertibleToDateException {
        Long HOTEL_ID = 1L;
        String HOTEL_NAME = "h1";
        String HOTEL_LOCATION = "l1";
        int HOTEL_STARS = 3;
        int HOTEL_CAPACITY = 200;

        Hotel hotel;
        hotel = new Hotel(HOTEL_NAME,HOTEL_LOCATION,HOTEL_STARS,HOTEL_CAPACITY);
        hotel.setId(HOTEL_ID);

        service.addHotel(hotel);

        String START_DATE_STRING= "2021/2/2";
        String END_DATE_STRING= "2021/2/2";
        int PRICE = 300;
        PackageHoliday packageHoliday = new PackageHoliday(1L, HOTEL_ID, START_DATE_STRING, END_DATE_STRING, PRICE);
        Assertions.assertThrows(ValidatorException.class, () -> {
            service.addPackageHoliday(packageHoliday);
        });
    }
}
