package DB.Tickets;

import java.io.IOException;

import IO.FileIO;
import IO.getInputFromKeyboard;

public class IOTickets {
    private FileIO fileTickets;
    private FileIO fileFlights;
    getInputFromKeyboard input = new getInputFromKeyboard();

    public IOTickets() throws IOException {
        fileTickets = new FileIO("src\\DB\\Tickets\\Tickets.CSV");
        fileFlights = new FileIO("src\\DB\\Flights\\Flights.CSV");
    }

    public String[][] getAllTickets() throws IOException {
        String[][] fileContent = fileTickets.readFromCSV(";");
        return fileContent;
    }

    public void printAllTickets() throws IOException {
        String[][] fileContent = getAllTickets();
        for (int i = 0; i < fileContent.length; i++) {
            System.out.println("Tickets ID: " + fileContent[i][0]
                    + " buy by" + fileContent[i][1]
                    + " taking" + fileContent[i][2]);
        }
    }

    public void addTickets(String idFlight, String userMail, String ticketsToBuy) throws IOException {
        String[][] toAdd = new String[1][3];
        fileTickets.writeOnCSVAddOnly(toAdd, 1, 3, ";");
    }

    public boolean verifyExisting(String[] ticket) throws IOException {
        String[][] fileContent = getAllTickets();
        for (int i = 0; i < fileContent.length; i++) {
            if ((fileContent[i][0].equals(ticket[0]))&&(fileContent[i][1].equals(ticket[1]))&&(fileContent[i][2].equals(ticket[2])))
                return true;
        }
        return false;
    }
    public boolean verifyExisting(String id, String userMail, String nBought) throws IOException {
        String[][] fileContent = getAllTickets();
        for (int i = 0; i < fileContent.length; i++) {
            if ((fileContent[i][0].equals(id))&&(fileContent[i][1].equals(userMail))&&(fileContent[i][2].equals(nBought)))
                return true;
        }
        return false;
    }

    public void deleteTicket(String airport) throws IOException {
        // funzione di elimina prenotazione
    }
    public String[] seeBoughtTickets(){
    }
}