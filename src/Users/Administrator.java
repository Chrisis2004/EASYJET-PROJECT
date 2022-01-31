package Users;

import java.io.IOException;

import DB.Flights.IOFlights;
import IO.getInputFromKeyboard;

public class Administrator extends Pearson {

    IOFlights flightsManager;
    getInputFromKeyboard input = new getInputFromKeyboard();

    public Administrator(String name, String surname, String mail, String password, String userType)
            throws IOException {
        super(name, surname, mail, password, userType);
        flightsManager = new IOFlights();
    }

    public void menuAdmin() throws IOException {
        boolean exitToMenu = false;
        int choose;
        while (exitToMenu == false) {
            input.clearConsole();

            System.out.println("Hi " + getName() + getSurname() + " choose the action that you want do: ");
            System.out.println("0. See all flight");
            System.out.println("1. Modify a flight");
            System.out.println("2. Delate a flight");
            System.out.println("3. Add a new flight");
            System.out.println("4. Register a new admin");
            System.out.println("5. See all selled tickets");
            System.out.println("6. Read all information about a flight");
            System.out.println("All other digit will exit from the app.");
            System.out.print("Choose: ");
            choose = input.getInt();

            switch (choose) {
                case 0:
                    flightsManager.printFlights();
                    break;
                case 1:
                    break;
                case 2:
                    flightsManager.delateFlight();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
            
            }

            System.out.print(
                    "Do you need something again?\n If you write \"yes\" you will return to the menu\n else write all other words for exit.");
            String menuExit = input.getString();
            if (!menuExit.equals("yes"))
                exitToMenu = true;
        }
    }

    public void adminRegistration() {
    }

}
