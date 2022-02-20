package DB.Flights;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Vector;

import IO.FileIO;
import IO.getInputFromKeyboard;

public class IOFlights {
    private FileIO fileFlights;

    public IOFlights() throws IOException {
        fileFlights = new FileIO("src\\DB\\Flights\\Flights.CSV");
    }

    getInputFromKeyboard input = new getInputFromKeyboard();

    public void printFlights() throws IOException {
        String fileContent[][] = fileFlights.readFromCSV(";");
        String[] toPrint = new String[8];
        for (int i = 0; i < fileFlights.findLineAmount(); i++) {
            for (int z = 0; z < fileFlights.howManySplit(";"); z++) {
                toPrint[z] = fileContent[i][z];
            }
            System.out.println("Flight: " + toPrint[0]);
            System.out.println("Departure: " + toPrint[1] + " - " + input.printDataTime(toPrint[2]));
            System.out.println("Arrival: " + toPrint[3] + " - " + input.printDataTime(toPrint[4]));
            System.out.println("Prize: " + toPrint[5]);
            System.out.println("Seats: " + toPrint[6]);
            System.out.println("Available seats: " + toPrint[7] + "\n");
        }
    }

    public int searchFlights(String idFlight) throws IOException {
        String fileContent[][] = fileFlights.readFromCSV(";");
        for (int i = 0; i < fileContent.length; i++) {
            if (fileContent[i][0].equals(idFlight)) {
                return i;
            }
        }
        return -1;
    }

    public int searchFlights(String departure, String arrival) throws IOException {
        String fileContent[][] = fileFlights.readFromCSV(";");
        for (int i = 0; i < fileContent.length; i++) {
            if (fileContent[i][1].equals(departure) && fileContent[i][3].equals(arrival)) {
                return i;
            }
        }
        return -1;
    }

    public void modifyFlight() throws IOException {
        String[][] fileContent = fileFlights.readFromCSV(";");
        System.out.print("Insert ID of the flight: ");
        String idFlight = input.getString();
        int id = searchFlights(idFlight);
        if (id != -1) {
            System.out.println("Flight found, what do you want to change?");
            System.out.println("1)ID\n"
                    + "2)Departure airport\n"
                    + "3)Departure day and time\n"
                    + "4)Arrival airport\n"
                    + "5)Arrival day and time\n"
                    + "6)Ticket price\n"
                    + "7)Seats available on the plane");
            int n = input.getInt();
            switch (n) {
                case 1:
                    fileContent[id][0] = input.getString();
                    fileFlights.writeOnCSV(fileContent, fileFlights.findLineAmount(), fileFlights.howManySplit(";"),";");
                    break;
                case 2:
                    fileContent[id][1] = input.getString();
                    fileFlights.writeOnCSV(fileContent, fileFlights.findLineAmount(), fileFlights.howManySplit(";"),
                            ";");
                    break;
                case 3: {
                    int year = input.getInt();
                    int month = input.getInt();
                    int day = input.getInt();
                    int hour = input.getInt();
                    int min = input.getInt();
                    LocalDateTime depInfo = LocalDateTime.of(year, month, day, hour, min);
                    String sDepInfo = depInfo.getYear() + "-" + depInfo.getMonthValue() + "-" + depInfo.getDayOfMonth()
                            + "T" + depInfo.getHour() + ":" + depInfo.getMinute();

                    String s = fileContent[id][4];
                    String sep[] = s.split(";");
                    String sepH[] = sep[4].split("-");
                    int y = Integer.parseInt(sepH[0]);
                    int mo = Integer.parseInt(sepH[1]);
                    int gi = Integer.parseInt(sepH[2]);
                    int h = Integer.parseInt(sepH[3]);
                    int m = Integer.parseInt(sepH[4]);
                    LocalDateTime info = LocalDateTime.of(y, mo, gi, h, m);

                    if (depInfo.isBefore(info)) {
                        fileContent[id][2] = sDepInfo;
                        fileFlights.writeOnCSV(fileContent, fileFlights.findLineAmount(), fileFlights.howManySplit(";"),
                                ";");
                    }
                }
                    break;
                case 4:
                    fileContent[id][3] = input.getString();
                    fileFlights.writeOnCSV(fileContent, fileFlights.findLineAmount(), fileFlights.howManySplit(";"),
                            ";");
                    break;
                case 5: {
                    int year = input.getInt();
                    int month = input.getInt();
                    int day = input.getInt();
                    int hour = input.getInt();
                    int min = input.getInt();
                    LocalDateTime depInfo = LocalDateTime.of(year, month, day, hour, min);
                    String sDepInfo = depInfo.getYear() + "-" + depInfo.getMonthValue() + "-" + depInfo.getDayOfMonth()
                            + "T" + depInfo.getHour() + ":" + depInfo.getMinute();

                    String s = fileContent[id][2];
                    String sep[] = s.split(";");
                    String sepH[] = sep[2].split("-");
                    int y = Integer.parseInt(sepH[0]);
                    int mo = Integer.parseInt(sepH[1]);
                    int gi = Integer.parseInt(sepH[2]);
                    int h = Integer.parseInt(sepH[3]);
                    int m = Integer.parseInt(sepH[4]);
                    LocalDateTime info = LocalDateTime.of(y, mo, gi, h, m);

                    if (depInfo.isAfter(info)) {
                        fileContent[id][4] = sDepInfo;
                        fileFlights.writeOnCSV(fileContent, fileFlights.findLineAmount(), fileFlights.howManySplit(";"),
                                ";");
                    }
                    break;
                }
                case 6:
                    double d = input.getDouble();
                    if (d >= 0) {
                        String sd = Double.toString(d);
                        fileContent[id][5] = sd;
                        fileFlights.writeOnCSV(fileContent, fileFlights.findLineAmount(), fileFlights.howManySplit(";"),
                                ";");
                    }
                    break;
                case 7:
                    int t = input.getInt();
                    int c = getAvailableSeats(idFlight);
                    if (t<=c) {
                        String st = Integer.toString(t);
                        fileContent[id][6] = st;
                        fileFlights.writeOnCSV(fileContent, fileFlights.findLineAmount(), fileFlights.howManySplit(";"),";");
                    }

            }
        }
    }

    public void delateFlight() throws IOException {
        System.out.print("Insert ID of the flight: ");
        String idFlight = input.getString();
        int flightToDelate = searchFlights(idFlight);
        fileFlights.delateLineFromCSV(flightToDelate);
    }

    public void addFlight() throws IOException {

        System.out.println("Insert ID of the flight: ");
        String id = input.getString();
        System.out.println("Enter the name of the departure airport: ");
        String airportDeparture = input.getString();
        System.out.println("Insert year, mouth, day, hour and minute of departure: ");
        int year = input.getInt();
        int month = input.getInt();
        int day = input.getInt();
        int hour = input.getInt();
        int min = input.getInt();
        LocalDateTime depInfo = LocalDateTime.of(year, month, day, hour, min);

        System.out.println("Enter the name of the Arrival airport: ");
        String airportArrivals = input.getString();
        System.out.println("Insert year, mouth, day, hour and minute of Arrival: ");
        year = input.getInt();
        month = input.getInt();
        day = input.getInt();
        hour = input.getInt();
        min = input.getInt();
        LocalDateTime arrInfo = LocalDateTime.of(year, month, month, hour, min);
        System.out.println("Insert price of a ticket: ");
        double price = input.getDouble();
        System.out.println("Enter maximum tickets for the plane: ");
        int seats = input.getInt();
        int aSeats = seats;

        if ((depInfo.isBefore(arrInfo)) && (price >= 0) && (seats >= 0) && (searchFlights(id) == -1)){
            String sDepInfo = depInfo.getYear() + "-" + depInfo.getMonthValue() + "-" + depInfo.getDayOfMonth() + "T"
                    + depInfo.getHour() + ":" + depInfo.getMinute();
            String sArrInfo = arrInfo.getYear() + "-" + arrInfo.getMonthValue() + "-" + arrInfo.getDayOfMonth() + "T"
                    + arrInfo.getHour() + ":" + arrInfo.getMinute();
            String sPrice = Double.toString(price);
            String sSeats = Integer.toString(seats);
            String sASeats= Integer.toString(aSeats);
            String add[][];
            add = new String[1][8];
            add[0][0] = id;
            add[0][1] = airportDeparture;
            add[0][2] = sDepInfo;
            add[0][3] = airportArrivals;
            add[0][4] = sArrInfo;
            add[0][5] = sPrice;
            add[0][6] = sSeats;
            add[0][7] = sASeats;
            fileFlights.writeOnCSVAddOnly(add, 1, 8, ";");
        }
    }

    public void printAFlight() throws IOException {
        String[][] fileContent = fileFlights.readFromCSV(";");
        System.out.println("You have two choices for search of flight: ");
        System.out.println("0. Use id");
        System.out.println("1. Use departure and arrival");
        System.out.print("Your choose: ");
        int choose = input.getInt();
        switch (choose) {
            case 0:
                System.out.print("Insert id:");
                String id = input.getString();
                int idFound = searchFlights(id);
                if (idFound == -1) {
                    System.out.println("Flight not found!");
                    break;
                }
                String[] toReturn = fileContent[idFound];
                System.out.println("Flight: " + toReturn[0]);
                System.out.println("Departure: " + toReturn[1] + " - " + toReturn[2]);
                System.out.println("Arrival: " + toReturn[3] + " - " + toReturn[4]);
                System.out.println("Prize: " + toReturn[5]);
                System.out.println("Seats: " + toReturn[6]);
                System.out.println("Available seats: " + toReturn[7] + "\n");
                break;

            case 1:
                System.out.print("Insert departure: ");
                String departure = input.getString();
                System.out.print("Insert arrival: ");
                String arrival = input.getString();
                int i = searchFlights(departure, arrival);
                if (i == -1) {
                    System.out.println("Flight not found!");
                    break;
                }
                String[] toPrint = fileContent[i];
                System.out.println("Flight: " + toPrint[0]);
                System.out.println("Departure: " + toPrint[1] + " - " + toPrint[2]);
                System.out.println("Arrival: " + toPrint[3] + " - " + toPrint[4]);
                System.out.println("Prize: " + toPrint[5]);
                System.out.println("Seats: " + toPrint[6]);
                System.out.println("Available seats: " + toPrint[7] + "\n");
                break;
            default:
                System.out.println("You haven't insered a correct value!");
        }
    }

    public String[] searchFlightsCostumer(String departure, String arrival, String typeOfSearch) throws IOException {
        int depInfo = 0;
        int arrInfo = 0;
        if (typeOfSearch.equals("date")) {
            depInfo = 2;
            arrInfo = 4;
        } else if (typeOfSearch.equals("airport")) {
            depInfo = 1;
            arrInfo = 3;
        }

        String[][] fileContent = fileFlights.readFromCSV(";");
        String[] fileToReturn = fileFlights.readFromFile();
        Vector<String> vector = new Vector<>();
        String[] toReturn;
        boolean flag = false;
        for (int i = 0; i < fileContent.length; i++) {
            if ((fileContent[i][depInfo].equals(departure)) && (fileContent[i][arrInfo].equals(arrival))) {
                vector.add(fileToReturn[i]);
                flag = true;
            }
        }
        toReturn = vector.toArray(new String[vector.size()]);
        if (flag)
            return toReturn;
        else
            return null;
    }

    public String printFlight(String[] flight) {
        return "Id: " + flight[0] + " Departure: " + flight[1] + " " + input.printDataTime(flight[2]) + " "
                + " Arrival: " + flight[3] + " " + input.printDataTime(flight[4])
                + " Availeble seats: " + flight[5] + " Price for ticket: " + flight[6] + "Availeble seats: " + flight[7];
    }

    public int getAvailableSeats(String id) throws IOException {
        String fileContent[][] = fileFlights.readFromCSV(";");
        for (int i = 0; i < fileContent.length; i++) {
            if (fileContent[i][0].equals(id)) {
                return Integer.parseInt(fileContent[i][7]);
            }
        }
        return -1;
    }

    public void setAvailableSeats(String id, int availableSeats) throws IOException {
        String[][] fileContent = fileFlights.readFromCSV(";");
        fileContent[searchFlights(id)][7] = Integer.toString(availableSeats);
        fileFlights.writeOnCSV(fileContent, fileFlights.findLineAmount(), fileFlights.howManySplit(";"), ";");
    }

    public String getFlight(String id) throws IOException{
        String[][] fileContent = fileFlights.readFromCSV(";");
        String[] fileToReturn = fileFlights.readFromFile();
        for (int i = 0; i < fileContent.length; i++) {
            if (fileContent[i][0].equals(id)) {
                return fileToReturn[i];
            }
        }
        return null;
    }
}
