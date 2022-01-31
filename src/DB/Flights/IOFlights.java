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
        String[][] fileContent = fileFlights.readFromCSV(";");
        System.out.print("Insert ID of the flight: ");
        String idFlight = input.getString();
        int id = searchFlights(idFlight);
        if(id!=-1) {
        	System.out.println("Volo trovato, cosa vuoi modificare?");
        	System.out.println("1)ID\n"
        			+ "2)Aeroporto di Partenza\n"
        			+ "3)Giorno e orario di partenza\n"
        			+ "4)Aeroporto di Arrivo\n"
        			+ "5)Giorno e orario di arrivo\n"
        			+ "6)Prezzo del biglietto\n"
        			+ "7)Biglietti disponibili");
        	int n=input.getInt();
            System.out.print("Insert your change: ");
        	fileContent[id][n-1] = input.getString();
            fileFlights.writeOnCSV(fileContent, fileFlights.findLineAmount(), fileFlights.howManySplit(";"), ";");
        }
    }
    public void delateFlight() throws IOException{
        System.out.print("Insert ID of the flight: ");
        String idFlight = input.getString();
        int flightToDelate = searchFlights(idFlight);
        fileFlights.delateLineFromCSV(flightToDelate);
    }
    public void addFlight() throws IOException{
    	
    	System.out.println("Insert ID of the flight: ");
        String id = input.getString();
        System.out.println("Enter the name of the departure airport: ");
        String airportDeparture = input.getString();
        System.out.println("Insert year, mouth, day, hour and minute of departure: ");
        String year = input.getString();
        String month = input.getString();
        String day = input.getString();
        String hour = input.getString();
        String min = input.getString();
        String depInfo = year+"-"+month+"-"+day+"T"+hour+":"+min;
        
        System.out.println("Enter the name of the Arrival airport: ");
        String airportArrivals = input.getString();
        System.out.println("Insert year, mouth, day, hour and minute of Arrival: ");
        year = input.getString();
        month = input.getString();
        day = input.getString();
        hour = input.getString();
        min = input.getString();
        String arrInfo = year+"-"+month+"-"+day+"T"+hour+":"+min;
        System.out.println("Insert price of a ticket: ");
        String price=input.getString();
        System.out.println("Enter maximum tickets for the plane: ");
        String ticket=input.getString();;
        
        String add[][];
        add = new String[1][7];
        add[0][0]=id;
        add[0][1]=airportDeparture;
        add[0][2]=depInfo;
        add[0][3]=airportArrivals;
        add[0][4]=arrInfo;
        add[0][5]=price;
        add[0][6]=ticket;

        
        fileFlights.writeOnCSV(add,fileFlights.findLineAmount()+1,fileFlights.howManySplit(";"),";");
    }

}
