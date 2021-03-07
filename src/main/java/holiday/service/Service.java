package holiday.service;

import holiday.domain.Client;
import holiday.domain.Hotel;
import holiday.domain.PackageHoliday;
import holiday.domain.PairIds;
import holiday.exceptions.ValidatorException;
import holiday.repository.InMemoryRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * This class has the following attributes:
 *      -> clientRepository : InMemoryRepository<Long, Client>
 *      -> hotelRepository: InMemoryRepository<Long, Hotel>
 *      -> packageHolidayRepository: InMemoryRepository<PairIds<Long, Long>, PackageHoliday>
 *
 *  This class has:
 *      -> a constructor
 *      -> getters and setters for all attributes
 *      -> addClient, addHotel, addPackageHoliday functions
 *      -> getAllClients, getAllHotels, addAllPackages functions
 */

public class Service {
    private InMemoryRepository<Long, Client> clientRepository;
    private InMemoryRepository<Long, Hotel> hotelRepository;
    private InMemoryRepository<PairIds<Long, Long>, PackageHoliday> packageHolidayRepository;

    public Service(InMemoryRepository<Long, Client> clientRepository, InMemoryRepository<Long, Hotel> hotelRepository,
                   InMemoryRepository<PairIds<Long, Long>, PackageHoliday> packageHolidayRepository) {
        this.clientRepository = clientRepository;
        this.hotelRepository = hotelRepository;
        this.packageHolidayRepository = packageHolidayRepository;
    }

    public InMemoryRepository<Long, Client> getClientRepository() {
        return clientRepository;
    }

    public InMemoryRepository<Long, Hotel> getHotelRepository() {
        return hotelRepository;
    }

    public InMemoryRepository<PairIds<Long, Long>, PackageHoliday> getPackageHolidayRepository() {
        return packageHolidayRepository;
    }

    public void setClientRepository(InMemoryRepository<Long, Client> clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void setHotelRepository(InMemoryRepository<Long, Hotel> hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public void setPackageHolidayRepository(InMemoryRepository<PairIds<Long, Long>, PackageHoliday> packageHolidayRepository) {
        this.packageHolidayRepository = packageHolidayRepository;
    }

    /**
     * This function adds a client to a repository for clients.
     * @param client : an object of type Client
     * @throws ValidatorException :
     */
    public void addClient(Client client) throws ValidatorException {
        clientRepository.save(client);
    }

    /**
     * This function takes all clients from the repository for clients.
     * @return : a set of clients
     */
    public Set<Client> getAllClients(){
        Iterable<Client> allClients = clientRepository.findAll();
        // return (Set<Client>) allClients;
        return StreamSupport.stream(allClients.spliterator(), false)
                .collect(Collectors.toSet());
    }

    /**
     * This function adds a hotel to a repository for hotels.
     * @param hotel : an object of type Hotel
     * @throws ValidatorException :
     */
    public void addHotel(Hotel hotel) throws ValidatorException {
        hotelRepository.save(hotel);
    }

    /**
     * This function takes all hotels from the repository for hotels.
     * @return : a set of hotels
     */
    public Set<Hotel> getAllHotels(){
        Iterable<Hotel> allHotels = hotelRepository.findAll();
        return (Set<Hotel>) allHotels;
    }

    /**
     * This function adds a package to a repository for packages.
     * @param packageHoliday : an object of type PackageHoliday
     * @throws ValidatorException :
     */
    public void addPackageHoliday(PackageHoliday packageHoliday) throws ValidatorException {
        PairIds<Long, Long> ids = packageHoliday.getId();

        boolean verifyIfClientExists = clientRepository.findOne(ids.getFirstId()).isEmpty();
        boolean verifyIfHotelExists = hotelRepository.findOne(ids.getSecondId()).isEmpty();

        if (!verifyIfClientExists) {
            if (!verifyIfHotelExists) {
                packageHolidayRepository.save(packageHoliday);
            } else throw new ValidatorException("Hotel does not exist!");
        } else throw new ValidatorException("Client does not exist!");
    }

    /**
     * This function takes all packages from the repository for packages.
     * @return : a set of packages
     */
    public Set<PackageHoliday> getAllPackages(){
        Iterable<PackageHoliday> allPackages = packageHolidayRepository.findAll();
        return (Set<PackageHoliday>) allPackages;
    }
}
