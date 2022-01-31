package DB.Flights;

import java.io.IOException;
import IO.FileIO;
import IO.getInputFromKeyboard;

public class IOFlights {
    private FileIO fileFlights;

    public IOFlights() throws IOException {
        fileFlights = new FileIO("src\\DB\\Flights\\Flights.CSV");
    }

    getInputFromKeyboard input = new getInputFromKeyboard();
    public void printFlights() throws IOException
    {
    	String fileContent[][] = fileFlights.readFromCSV(";");
    	String[] toPrint = new String[7];
    	for(int i=0; i<fileFlights.findLineAmount(); i++)
    	{
    		for(int z=0; z<fileFlights.howManySplit(";"); z++){
    			toPrint[z] = fileContent[i][z];
            }
            System.out.println("Flight: " + toPrint[0]);
            System.out.println("Departure: " + toPrint[1] + " - " + toPrint[2]);
            System.out.println("Arrival: " + toPrint[3] + " - " + toPrint[4]);
            System.out.println("Prize: " + toPrint[5]);
            System.out.println("Seats: " + toPrint[6]);
    	}
    }

    public int searchFlights(String idFlight) throws IOException{
    	String fileContent[][] = fileFlights.readFromCSV(";");
            for(int i=0; i<fileContent.length; i++){
                if(fileContent[i][0].equals(idFlight)) {
                    return i;
                }
    		}
    	return -1;
    }
    
    public int searchFlights(String departure , String arrival) throws IOException{
    	String fileContent[][] = fileFlights.readFromCSV(";");
    	    for(int i=0; i<fileContent.length; i++){
    		    if(fileContent[i][1].equals(departure) && fileContent[i][2].equals(arrival)) {
    			    return i;
    			}
    		}
    	return -1;
    }

    public void modifyFlight() throws IOException{
        //nicola
    }
    public void delateFlight() throws IOException{
        System.out.print("Insert ID of the flight: ");
        String idFlight = input.getString();
        int flightToDelate = searchFlights(idFlight);
        fileFlights.delateLineFromCSV(flightToDelate);
    }
    public void addFlight() throws IOException{
        //nicola
    }

}
