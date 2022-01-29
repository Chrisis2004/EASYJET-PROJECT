package DB.Flights;

import java.io.IOException;
import IO.FileIO;
import IO.getInputFromKeyboard;

public class IOFlights {
    private FileIO fileUsers;

    public IOFlights() throws IOException {
        fileUsers = new FileIO("src\\DB\\Flights\\Flights.CSV");
    }

    getInputFromKeyboard input = new getInputFromKeyboard();

    public void printFlights(){}

    public int searchFlights(String idFlight){return 1;}
    public int searchFlights(String departure , String arrival){return 1;}

    public void modifyFlight() throws IOException{
        //nicola
    }
    public void delateFlight() throws IOException{
        System.out.print("Insert ID of the flight: ");
        String idFlight = input.getString();
        int flightToDelate = searchFlights(idFlight);
        fileUsers.delateLineFromCSV(flightToDelate);
    }
    public void addFlight() throws IOException{
        //nicola
    }

}
