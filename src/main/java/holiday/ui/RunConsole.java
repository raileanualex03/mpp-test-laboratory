package holiday.ui;

import holiday.service.Service;
import java.util.Scanner;

/**
 * This class has the following attributes:
 *      console: an object of type Console
 *      menu: an object of type Menu
 *
 * The class has:
 *      -> a constructor
 *      -> runClients, runHotels, runPackages, runConsole functions
 */

public class RunConsole {
    private final Console console;
    private final Menu menu;

    public RunConsole(Service service) {
        this.console = new Console(service);
        menu = new Menu();
    }

    /**
     *  The functions runClients(), runHotels(), runPackages() and runConsole() prints on the console all the options
     *          the user has for the management of the clients, hotels and packages.
     *  Reads and processes the inputted commands from the users.
     *  Executes the corresponding functions.
     */
    public void runConsole(){
        Scanner scanner = new Scanner(System.in);

        boolean verify = true;
        while(verify) {
            menu.TextMenu();
            System.out.println("Input the option: ");
            int key = Integer.parseInt(scanner.nextLine());

            verify = (key == 1 ? runClients() :
                    (key == 2 ? runHotels() :
                            (key == 3 && runPackages())));
        }
    }

    public boolean runClients(){
        Scanner scanner = new Scanner(System.in);

        menu.TextMenuClients();
        System.out.println("Input the option: ");
        int keyForClientOperations = Integer.parseInt(scanner.nextLine());

        return (keyForClientOperations == 1 ? console.addNewClient() :
                (keyForClientOperations != 2 || console.printAllClients()));
    }

    public boolean runHotels(){
        Scanner scanner = new Scanner(System.in);

        menu.TextMenuHotels();
        System.out.println("Input the option: ");
        int keyForHotelOperations = Integer.parseInt(scanner.nextLine());

        return (keyForHotelOperations == 1 ? console.addNewHotel() :
                (keyForHotelOperations != 2 || console.printAllHotels()));
    }

    public boolean runPackages(){
        Scanner scanner = new Scanner(System.in);

        menu.TextMenuPackages();
        System.out.println("Input the option: ");
        int keyForPackagesOperations = Integer.parseInt(scanner.nextLine());

        return (keyForPackagesOperations == 1 ? console.addNewPackageHoliday() :
                (keyForPackagesOperations != 2 || console.printAllPackages()));
    }
}
