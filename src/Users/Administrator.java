package Users;

import java.io.IOException;

import DB.Flights.IOFlights;
import DB.Users.IOUsers;
import IO.getInputFromKeyboard;
import IO.mailException;
import IO.passwordException;

public class Administrator extends Pearson {

    IOFlights flightsManager;
    IOUsers userManager;
    getInputFromKeyboard input = new getInputFromKeyboard();

    public Administrator(String name, String surname, String mail, String password, String userType)
            throws IOException {
        super(name, surname, mail, password, userType);
        flightsManager = new IOFlights();
        usersManager = new IOUsers();
    }

    public void menuAdmin() throws IOException, passwordException, mailException {
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
                    flightsManager.modifyFlight();
                    break;
                case 2:
                    flightsManager.delateFlight();
                    break;
                case 3:
                    flightsManager.addFlight();
                    break;
                case 4:
                    adminRegistration();
                    break;
                case 5:
                    //funzione dei biglietti
                    break;
                case 6:
                    flightsManager.printFlight();
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

    public void adminRegistration() throws passwordException, IOException, mailException {
        input.clearConsole();
        System.out.print("Insert name: ");
        String name = input.getString();
        System.out.print("Insert surname: ");
        String surname = input.getString();
        System.out.print("Insert mail: ");
        String mail = input.getMail();

        boolean reinsertAppened = false;
        int idUser = usersManager.verifyMailUser(mail);

        if (idUser != -1) {
            while (usersManager.verifyMailUser(mail) != -1) {
                if (usersManager.verifyUserType(idUser).equals("C")) {
                    input.clearConsole();
                    System.out.println(
                            "The entered email has already been assigned to a costumer, now you can perform the following actions: ");
                    System.out.println("1. Uptade the type of user to a Administrator");
                    System.out.println("2. Change the mail insered");
                    System.out.print("Your choose: ");

                    int choose;
                    choose = input.getInt();

                    switch (choose) {
                        case 1:
                        userManager.changeUserType(idUser, "A");
                            reinsertAppened = true;
                            break;
                        case 2:
                            input.clearConsole();
                            System.out.print("Reinsert mail: ");
                            mail = input.getMail();
                            break;
                    }
                } 
                else if (usersManager.verifyUserType(idUser).equals("A")) {
                    input.clearConsole();
                    System.out.println(
                            "The entered email has already been assigned to a administrator, now you can perform the following actions: ");
                        System.out.println("1. Uptade the type of user to a Costumer");
                        System.out.println("2. Change the mail insered");
                        System.out.print("Your choose: ");
    
                        int choose;
                        choose = input.getInt();
    
                        switch (choose) {
                            case 1:
                                userManager.changeUserType(idUser, "C");
                                reinsertAppened = true;
                                break;
                            case 2:
                                input.clearConsole();
                                System.out.print("Reinsert mail: ");
                                mail = input.getMail();
                                break;
                        }
                }
                if (reinsertAppened)
                    break;
            }
        }
        if (!reinsertAppened) {
            System.out.print("Insert password: ");
            String password = input.getPassword();
            usersManager.addUser(name, surname, mail, password, "A");
        }
        else 
            System.out.println("Something has gone wrong, the registation has failed!");
    }

}
