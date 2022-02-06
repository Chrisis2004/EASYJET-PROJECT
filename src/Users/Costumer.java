package Users;

import java.io.IOException;
import DB.Flights.IOFlights;
import IO.getInputFromKeyboard;

public class Costumer extends Pearson{

    IOFlights flightsCostumer;
    getInputFromKeyboard input = new getInputFromKeyboard();

    public Costumer(String name, String surname, String mail, String password, String userType) throws IOException {
        super(name, surname, mail, password, userType);
        flightsCostumer = new IOFlights();
    }

    public void menuCostumer() throws IOException {
        boolean exitToMenu = false;
        int choose;
        while (exitToMenu == false) {
            input.clearConsole();

            System.out.println("Hi " + getName() + " "+ getSurname() + " choose the action that you want do: ");
            System.out.println("0. Buy a ticket");
            System.out.println("1. Watch purchased tickets");
            System.out.println("All other digit will exit from the app.");
            System.out.print("Choose: ");
            choose = input.getInt();

            switch (choose) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
            }

            System.out.print(
                    "Do you need something again?\n If you write \"yes\" you will return to the menu\n else write all other words for exit.");
            String menuExit = input.getString();
            if (!menuExit.equals("yes"))
                exitToMenu = true;
        }
    }
}
