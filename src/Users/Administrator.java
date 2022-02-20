package Users;

import java.io.IOException;

import DB.Flights.IOFlights;
import DB.Tickets.IOTickets;
import DB.Users.IOUsers;
import IO.getInputFromKeyboard;
import IO.mailException;
import IO.passwordException;

public class Administrator extends Pearson {

    IOFlights flightsManager;
    IOUsers userManager;
    IOTickets ticketsManager;
    getInputFromKeyboard input = new getInputFromKeyboard();

    public Administrator(String name, String surname, String mail, String password, String userType)
            throws IOException {
        super(name, surname, mail, password, userType);
        flightsManager = new IOFlights();
        usersManager = new IOUsers();
        ticketsManager = new IOTickets();
    }

    public void menuAdmin() throws IOException, passwordException, mailException {
        boolean exitToMenu = false;
        int choose;
        while (exitToMenu == false) {
            input.clearConsole();

            System.out.println("Hi " + getName() + " " + getSurname() + " choose the action that you want do: ");
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
                    input.clearConsole();
                    flightsManager.printFlights();
                    break;
                case 1:
                    input.clearConsole();
                    flightsManager.modifyFlight();
                    break;
                case 2:
                    input.clearConsole();
                    flightsManager.delateFlight();
                    break;
                case 3:
                    input.clearConsole();
                    flightsManager.addFlight();
                    break;
                case 4:
                    input.clearConsole();
                    adminRegistration();
                    break;
                case 5:
                    input.clearConsole();
                    ticketsManager.printAllTickets();
                    break;
                case 6:
                    input.clearConsole();
                    flightsManager.printAFlight();
                    break;
                default:
                    System.out.println("You haven't insered a correct number!!\n");

            }

            System.out.println(
                    "Do you need something again?\n If you write \"yes\" you will return to the menu\n else write all other words for exit.");
            System.out.print("Your choose: ");
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
                } else if (usersManager.verifyUserType(idUser).equals("A")) {
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
        } else
            System.out.println("Something has gone wrong, the registation has failed!");
    }

    public String printFlightForAdmin(String flight) {
        String[] splitted = flight.split(";");
        return "Id: " + splitted[0] + " Departure: " + splitted[1] + " " + input.printDataTime(splitted[2]) + " "
                + " Arrival: " + splitted[3] + " " + input.printDataTime(splitted[4])
                + " Availeble seats: " + splitted[5] + " Price for ticket: " + splitted[6] + "Availeble seats: "
                + splitted[7];
    }

}
