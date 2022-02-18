package Users;

import java.io.IOException;

import DB.Airports.IOAirports;
import DB.Flights.IOFlights;
import DB.Tickets.IOTickets;
import IO.getInputFromKeyboard;

public class Costumer extends Pearson {

    IOFlights flightsManager;
    IOAirports airportManager;
    IOTickets ticketsManager;
    getInputFromKeyboard input = new getInputFromKeyboard();

    public Costumer(String name, String surname, String mail, String password, String userType) throws IOException {
        super(name, surname, mail, password, userType);
        flightsManager = new IOFlights();
        airportManager = new IOAirports();
        ticketsManager = new IOTickets();
    }

    public void menuCostumer() throws IOException {
        boolean exitToMenu = false;
        int choose;
        while (exitToMenu == false) {
            input.clearConsole();

            System.out.println("Hi " + getName() + " " + getSurname() + " choose the action that you want do: ");
            System.out.println("0. See all flight");
            System.out.println("1. Search a flight for booking");
            System.out.println("2. See your booked flight");
            System.out.println("3. Cancel a reservation");
            System.out.print("Choose: ");
            choose = input.getInt();

            switch (choose) {
                case 0:
                    flightsManager.printFlights();
                    break;
                case 1:
                    input.clearConsole();
                    System.out.println("How do you want search a flight ?");
                    System.out.println("0. Using departure and arrival");
                    System.out.println("1. Using data of departure and data of arrival ?");
                    System.out.print("Choose: ");
                    int chooseSearch = input.getInt();
                    switch (chooseSearch) {
                        case 0:
                            System.out.println("Insert the city of departure and of arrival: ");
                            System.out.print("Departure: ");
                            String departure = input.getString();
                            int verifyExistingDeparture = airportManager.verifyExisting(departure);
                            System.out.print("Arrival: ");
                            String arrival = input.getString();
                            int verifyExistingArrival = airportManager.verifyExisting(arrival);
                            if ((verifyExistingArrival == -1) || (verifyExistingDeparture == -1)) {
                                System.out.println("The airport is not managed by our company yet, sorry");
                                break;
                            } else {
                                String[] flightFound = flightsManager.searchFlightsCostumer(departure, arrival,
                                        "airport");
                                if (flightFound == null)
                                    System.out.println("This route is not managed by our company yet, sorry");
                                else {
                                    int i;
                                    for (i = 0; i < flightFound.length; i++)
                                        System.out.println(i + ". " + printFlightForCostumer(flightFound[i]));
                                    System.out.println("Do you want book a flight of them ?");
                                    System.out.println("0. yes");
                                    System.out.print("1. no\nYour choose: ");
                                    int bookFlight = input.getInt();
                                    if (bookFlight != 0)
                                        break;
                                    System.out.print(
                                            "Which one do you want book?\nInsert the number of the flight (take it on the list of flight found): ");
                                    int flightToBook = input.getInt(); // inserire controllo su lunghezza array
                                    bookTicket(flightFound[flightToBook]);
                                }
                            }
                            break;
                        case 1:
                            System.out.println("Insert the date of departure and of arrival: ");
                            System.out.print("Departure: ");
                            String dateDeparture = input.getDataTime();
                            System.out.print("\nArrival: ");
                            String dateArrival = input.getDataTime();
                            // inserire in qualche modod controllo sulle date, devono esssre stringhe non
                            // datatime per ora
                            String[] flightFound = flightsManager.searchFlightsCostumer(dateDeparture,
                                    dateArrival, "date");
                            if (flightFound == null) {
                                System.out.println("The airport is not managed by our company yet, sorry");
                                break;
                            } else {
                                int i;
                                for (i = 0; i < flightFound.length; i++)
                                    System.out.println(i + ". " + printFlightForCostumer(flightFound[i]));
                                System.out.println("Do you want book a flight of them ?");
                                System.out.println("0. yes");
                                System.out.print("1. no\nYour choose: ");
                                int bookFlight = input.getInt();
                                if (bookFlight != 0)
                                    break;
                                System.out.print(
                                        "Which one do you want book?\nInsert the number of the flight (take it on the list of flight found): ");
                                int flightToBook = input.getInt();
                                bookTicket(flightFound[flightToBook]);
                            }
                            break;
                        default:
                            System.out.println("You have missed the correct insertion\n");
                    }
                    break;
                case 2:
                    input.clearConsole();
                    System.out.println("Your booked tickets: ");
                    ticketsManager.printBoughtTickets(getMail());
                    break;
                case 3:
                    input.clearConsole();
                    System.out.println("Your booked tickets: ");
                    String[] ticketsArray = ticketsManager.getBoughtTickets(getMail());
                    for (int i = 0; i < ticketsArray.length; i++)
                        System.out.println(i + ". " + ticketsArray[i]);
                    System.out.println("Which ticket do you want to cancel a reservation for?");
                    System.out.println("Insert the number of the flight: ");
                    int ticketArrayLine = input.getInt();
                    deleteTicket(ticketsArray[ticketArrayLine]);
                    break;
                default:
            }
            System.out.print(
                    "\nDo you need something again?\nIf you write \"yes\" you will return to the menu else write all other words for exit.");
            String menuExit = input.getString();
            if (!menuExit.equals("yes"))
                exitToMenu = true;
        }
    }

    public void bookTicket(String flight) throws IOException {
        String[] flightToBook = flight.split(";");
        String idToBook = flightToBook[0];
        System.out.println("How many tickets do you want buy ?");
        System.out.print("Number of tickets: ");
        int nTickets = input.getInt();
        ticketsManager.addTicket(idToBook, getMail(), nTickets);
    }

    public void deleteTicket(String flight) {

    }

    public String printFlightForCostumer(String flight) {
        String[] splitted = flight.split(";");
        return "Departure: " + splitted[1] + " " + input.printDataTime(splitted[2]) + " "
                + " Arrival: " + splitted[3] + " " + input.printDataTime(splitted[4])
                + " Availeble seats: " + splitted[7] + " Price for ticket: " + splitted[6];
    }
}
