package DB.Tickets;

import java.io.IOException;
import java.util.Vector;

import DB.Flights.IOFlights;
import IO.FileIO;
import IO.getInputFromKeyboard;

public class IOTickets {
    private FileIO fileTickets;
    private FileIO fileFlights;
    private IOFlights flighManager;
    getInputFromKeyboard input = new getInputFromKeyboard();

    public IOTickets() throws IOException {
        fileTickets = new FileIO("src\\DB\\Tickets\\Tickets.CSV");
        fileFlights = new FileIO("src\\DB\\Flights\\Flights.CSV");
        flighManager = new IOFlights();
    }

    public String[][] getAllTickets() throws IOException {
        String[][] fileContent = fileTickets.readFromCSV(";");
        return fileContent;
    }

    public void printAllTickets() throws IOException {
        String[][] fileContent = getAllTickets();
        for (int i = 0; i < fileContent.length; i++) {
            System.out.println("Tickets ID: " + fileContent[i][0]
                    + " bought  " + fileContent[i][2]
                    + " tickets by " + fileContent[i][1]);
        }
    }

    public void addTicket(String idFlight, String userMail, int ticketsToBuy) throws IOException {
        int availableSeats = flighManager.getAvailableSeats(idFlight);
        if (ticketsToBuy==0)
        System.out.println("Sorry the booking was unsuccessful");
        else if (ticketsToBuy>availableSeats)
            System.out.println("Our company disposes only " + availableSeats + " seats on this flight, sorry the booking was unsuccessful");
        else {
            flighManager.setAvailableSeats(idFlight, (availableSeats - ticketsToBuy));
            String[][] toAdd = new String[1][3];
            toAdd[0][0] = idFlight;
            toAdd[0][1] = userMail;
            toAdd[0][2] = Integer.toString(ticketsToBuy);
            fileTickets.writeOnCSVAddOnly(toAdd, 1, 3, ";");
            System.out.println("The booking was succesful");
        }
    }

    public boolean verifyExisting(String[] ticket, int nTicket) throws IOException {
        String[][] fileContent = getAllTickets();
        for (int i = 0; i < fileContent.length; i++) {
            if ((fileContent[i][0].equals(ticket[0])) && (fileContent[i][1].equals(ticket[1]))
                    && (fileContent[i][2].equals(ticket[2])))
                return true;
        }
        return false;
    }

    public boolean verifyExisting(String id, String userMail, String nBought) throws IOException {
        String[][] fileContent = getAllTickets();
        for (int i = 0; i < fileContent.length; i++) {
            if ((fileContent[i][0].equals(id)) && (fileContent[i][1].equals(userMail))
                    && (fileContent[i][2].equals(nBought)))
                return true;
        }
        return false;
    }

    public void deleteTicket(String ticketToDelate) throws IOException {
        String[] fileContent = fileTickets.readFromFile();
        boolean flag = false;
        for (int i = 0; i < fileContent.length; i++) {
            if (fileContent[i].equals(ticketToDelate)){
                flag=true;
                fileTickets.delateLineFromCSV(i);
                System.out.println("The canceletion was succesful");
                break;
            }     
        }
        if (!flag)
            System.out.println("The canceletion was unsuccesful");
    }

    public String[] getBoughtTickets(String mail) throws IOException {
        String[][] fileContent = fileTickets.readFromCSV(";");
        String[] fileToPrint = fileTickets.readFromFile();
        Vector<String> vector = new Vector<>();
        boolean flag = false;
        for (int i = 0; i < fileContent.length; i++) 
            if (fileContent[i][1].equals(mail)){
                vector.add(fileToPrint[i]);
                flag=true;
            }       
        if (!flag)
            return null;
        else 
            return vector.toArray(new String[vector.size()]);
    }

    public void printBoughtTickets(String mail) throws IOException {
        String[][] fileContent = getAllTickets();
        boolean flag = false;
        for (int i = 0; i < fileContent.length; i++) {
            if (fileContent[i][1].equals(mail)){
                flag = true;
                System.out.println((i+1) + ". " + fileContent[i][2] + " of " + getFlightToPrint(fileContent[i][0]));
            }
        }
        if (!flag)
            System.out.println("You haven't booked any flight\n");
    }

    public String getFlightToPrint(String id) throws IOException{
        String[][] fileContent = fileFlights.readFromCSV(";");
        for (int i = 0; i < fileContent.length; i++) {
            if (fileContent[i][0].equals(id)) {
                return fileContent[i][1] + " - " + fileContent[i][3] + " " + fileContent[i][2] + " - " + fileContent[i][4];
            }
        }
        return null;
    }
    public String printTicket(String ticket) throws IOException{
        String[] splitted = ticket.split(";");
        return splitted[2] + " of " + getFlightToPrint(splitted[0]);
    }
}