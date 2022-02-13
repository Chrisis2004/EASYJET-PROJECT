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
    }

    public void menuAdmin() throws IOException {
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
                    System.out.println("Hw do you want search a flight ?");
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
                                String[] flightFound = flightsManager.searchFlightsCostumerCity(departure, arrival);
                                if (flightFound == null)
                                    System.out.println("This route is not managed by our company yet, sorry");
                                else {
                                    int i;
                                    for (i = 0; i < flightFound.length; i++)
                                        System.out.println(i + ". " + flightFound[i]);
                                    System.out.println("Do you want book a flight of them ?");
                                    System.out.print("0. yes");
                                    System.out.print("1. no");
                                    int bookFlight = input.getInt();
                                    if (bookFlight != 1)
                                        break;
                                    System.out.print("Which one do you want book?\nInssert the number of the flight: ");
                                    int flightToBook = input.getInt();
                                    bookTicket(flightFound[flightToBook]);
                                }
                            }
                            break;
                        case 1:
                            System.out.println("Insert the city of departure and of arrival: ");
                            System.out.print("Departure: ");
                            String dateDeparture = input.getDataTime();
                            System.out.print("\nArrival: ");
                            String dateArrival = input.getDataTime();
                            String[] flightFound = flightsManager.searchFlightsCostumerDateTime(dateDeparture,
                                    dateArrival);
                            if (flightFound == null) {
                                System.out.println("The airport is not managed by our company yet, sorry");
                                break;
                            } else {
                                flightFound = flightsManager.searchFlightsCostumerCity(dateDeparture, dateArrival);
                                if (flightFound == null)
                                    System.out.println("This route is not managed by our company yet, sorry");
                                else {
                                    int i;
                                    for (i = 0; i < flightFound.length; i++)
                                        System.out.println(i + ". " + flightFound[i]);
                                    System.out.println("Do you want book a flight of them ?");
                                    System.out.print("0. yes");
                                    System.out.print("1. no");
                                    int bookFlight = input.getInt();
                                    if (bookFlight != 1)
                                        break;
                                    System.out.print("Which one do you want book?\nInssert the number of the flight: ");
                                    int flightToBook = input.getInt();
                                    bookTicket(flightFound[flightToBook]);
                                }
                            }
                            break;
                        default:
                    }
                    break;
                case 2:

                    break;
                case 3:

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

    public void bookTicket(String flight) {
        
    }
}
