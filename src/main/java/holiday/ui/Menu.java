package holiday.ui;

/**
 * The class has:
 *      -> a constructor
 *      -> TextMenu, TextMenuClients, TextMenuHotels and TextMenuPackages functions
 */

public class Menu {
    public Menu() {
    }

    public void TextMenu(){
        System.out.println("1: Manage clients");
        System.out.println("2: Manage hotels");
        System.out.println("3: Manage packages");
        System.out.println("4: Exit");
    }

    public void TextMenuClients(){
        System.out.println("1: Add a new client");
        System.out.println("2: Print all the clients");
        System.out.println("3: Go back");
    }

    public void TextMenuHotels(){
        System.out.println("1: Add a new hotel");
        System.out.println("2: Print all the hotels");
        System.out.println("3: Go back");
    }

    public void TextMenuPackages(){
        System.out.println("1: Add a new package holiday");
        System.out.println("2: Print all the packages");
        System.out.println("3: Go back");
    }
}
