package holiday;

import holiday.domain.Client;
import holiday.domain.Hotel;
import holiday.domain.PackageHoliday;
import holiday.domain.PairIds;
import holiday.repository.InMemoryRepository;
import holiday.service.Service;
import holiday.ui.Console;
import holiday.ui.RunConsole;
import holiday.validators.ClientValidator;
import holiday.validators.HotelValidator;
import holiday.validators.PackageHolidayValidator;
import holiday.validators.Validator;

public class Main {
    public static void main(String[] args){
        Validator<Client> clientValidator = new ClientValidator();
        Validator<Hotel> hotelValidator = new HotelValidator();
        Validator<PackageHoliday> packageHolidayValidator = new PackageHolidayValidator();

        InMemoryRepository<Long, Client> clientRepository = new InMemoryRepository<>(clientValidator);
        InMemoryRepository<Long, Hotel> hotelRepository = new InMemoryRepository<>(hotelValidator);
        InMemoryRepository<PairIds<Long, Long>, PackageHoliday> packageHolidayRepository = new InMemoryRepository<>(packageHolidayValidator);

        Service service = new Service(clientRepository, hotelRepository, packageHolidayRepository);
        RunConsole runConsole = new RunConsole(service);

        runConsole.runConsole();
    }
}
