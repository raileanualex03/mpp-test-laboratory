package holiday.ui;

import holiday.domain.BaseEntity;
import holiday.domain.Client;
import holiday.domain.Hotel;
import holiday.domain.PackageHoliday;
import holiday.exceptions.StringNotConvertibleToDateException;
import holiday.exceptions.ValidatorException;
import holiday.service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Set;

/**
 * This class has the following attributes:
 *      -> service : an object of type Service (repository)
 *
 * This class has:
 *      -> a constructor
 *      -> printAllClients, printAllHotels, printAllPackages functions
 *      -> readClient, readHotel, readPackageHoliday functions
 *      -> addNewClient, addNewHotel, addNewPackageHoliday functions
 */

public class Console {
    private final Service service;

    public Console(Service service) {
        this.service = service;
    }

    public boolean printAllClients(){
        Set<Client> clients = service.getAllClients();
        clients.forEach(System.out::println);
        return true;
    }

    public boolean printAllHotels(){
        Set<Hotel> hotels = service.getAllHotels();
        hotels.forEach(System.out::println);
        return true;
    }

    public boolean printAllPackages(){
        Set<PackageHoliday> packages = service.getAllPackages();
        packages.forEach(System.out::println);
        return true;
    }

    /**
     * This function reads the data input from the user and
     *  creates an object of type Client
     *
     * @return : a client
     */
    private Client readClient(){
        System.out.println("Input client data {name, age}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("Insert client name: ");
            String name=bufferRead.readLine();

            System.out.println("Insert client age: ");
            int age=Integer.parseInt(bufferRead.readLine());

            boolean verifyIfPresent = service.getAllClients()
                    .stream()
                    .max(Comparator.comparingLong(BaseEntity::getId)).isPresent();
            Long idClient = (verifyIfPresent ? service.getAllClients()
                    .stream()
                    .max(Comparator.comparingLong(BaseEntity::getId)).get().getId() + 1 : 1L);

            Client client = new Client(name, age);
            client.setId(idClient);

            return client;
        }catch(IOException exception){
            System.out.println(exception.getMessage());
        }

        return null;
    }

    /**
     * This function reads the data input from the user and creates an object of type Hotel
     *
     *  @return : a hotel
     */
    private Hotel readHotel(){
        System.out.println("Input hotel data {name, location, stars, capacity}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("Insert hotel name: ");
            String name=bufferRead.readLine();

            System.out.println("Insert hotel location: ");
            String location=bufferRead.readLine();

            System.out.println("Insert hotel stars: ");
            int stars=Integer.parseInt(bufferRead.readLine());

            System.out.println("Insert hotel capacity: ");
            int capacity=Integer.parseInt(bufferRead.readLine());

            boolean verifyIfPresent = service.getAllHotels()
                    .stream()
                    .max(Comparator.comparingLong(BaseEntity::getId)).isPresent();
            Long idHotel = (verifyIfPresent ? service.getAllHotels()
                    .stream()
                    .max(Comparator.comparingLong(BaseEntity::getId)).get().getId() + 1 : 1L);

            Hotel hotel = new Hotel(name, location, stars, capacity);
            hotel.setId(idHotel);

            return hotel;
        }catch(IOException exception){
            System.out.println(exception.getMessage());
        }

        return null;
    }

    /**
     * This function reads the data input from the user and creates an object of type Package Holiday
     *
     *  @return : a package holiday
     */
    private PackageHoliday readPackageHoliday(){
        System.out.println("Input Package Holiday data {clientId, hotelId, startDate, endDate, price}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("Insert client id: ");
            Long clientId = Long.valueOf(bufferRead.readLine());

            System.out.println("Insert hotel id: ");
            Long hotelId = Long.valueOf(bufferRead.readLine());

            System.out.println("Insert package startDate as 'yyyy/mm/dd/': ");
            String startDateString=bufferRead.readLine();

            System.out.println("Insert package endDate as 'yyyy/mm/dd/' : ");
            String endDateString=bufferRead.readLine();

            System.out.println("Insert package price : ");
            int price=Integer.parseInt(bufferRead.readLine());

            return new PackageHoliday(clientId, hotelId, startDateString, endDateString, price);
        }catch(IOException | StringNotConvertibleToDateException exception){
            System.out.println(exception.getMessage());
        }

        return null;
    }

    /**
     * This function adds a Client to the service.
     * It takes no parameters because the data will be read and processed in the function "readClient()"
     *
     * @throws ValidatorException :
     */
    public boolean addNewClient(){
            Client newClient = readClient();
            if(newClient == null){
                System.out.println("The client could not be initialized!");
                return false;
            }

            try{
                service.addClient(newClient);
            }
            catch(ValidatorException exception){
                System.out.println(exception.getMessage());
            }
            return true;
    }

    /**
     * This function adds a Hotel to the service.
     * It takes no parameters because the data will be read and processed in the function "readHotel()"
     *
     * @throws ValidatorException :
     */
    public boolean addNewHotel(){
            Hotel newHotel = readHotel();
            if(newHotel == null){
                System.out.println("The hotel could not be initialized!");
                return false;
            }

            try{
                service.addHotel(newHotel);
            }
            catch(ValidatorException exception){
                System.out.println(exception.getMessage());
            }
            return true;
    }

    /**
     * This function adds a Package to the service.
     * It takes no parameters because the data will be read and processed in the function "readPackageHoliday()"
     *
     * @throws ValidatorException :
     */
    public boolean addNewPackageHoliday(){
            PackageHoliday newPackageHoliday = readPackageHoliday();
            if(newPackageHoliday == null){
                System.out.println("The package holiday could not be initialized!");
                return false;
            }

            try{
                service.addPackageHoliday(newPackageHoliday);
            }
            catch(ValidatorException exception){
                System.out.println(exception.getMessage());
            }
            return true;
    }
}
