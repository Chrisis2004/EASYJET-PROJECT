package DB.Flights;

import java.io.IOException;
import java.time.LocalDateTime;
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
        	System.out.println("Flight found, what do you want to change?");
        	System.out.println("1)ID\n"
        			+ "2)Departure airport\n"
        			+ "3)Departure day and time\n"
        			+ "4)Arrival airport\n"
        			+ "5)Arrival day and time\n"
        			+ "6)Ticket price\n"
        			+ "7)Tickets available");
        	int n=input.getInt();
        	switch (n) {
        	case 1:
        		fileContent[id][0]=input.getString();
        		fileFlights.writeOnCSV(fileContent, fileFlights.findLineAmount(), fileFlights.howManySplit(";"), ";");
        		break;
        	case 2:
        		fileContent[id][1]=input.getString();
        		fileFlights.writeOnCSV(fileContent, fileFlights.findLineAmount(), fileFlights.howManySplit(";"), ";");
        		break;
        	case 3:{
        		int year = input.getInt();
                int month = input.getInt();
                int day = input.getInt();
                int hour = input.getInt();
                int min = input.getInt();
                LocalDateTime depInfo=LocalDateTime.of(year, month, day, hour, min);
                String sDepInfo=depInfo.getYear()+"-"+depInfo.getMonthValue()+"-"+depInfo.getDayOfMonth()+"T"+depInfo.getHour()+":"+depInfo.getMinute();
                
                String s=fileContent[id][4];
                String sep[]=s.split(";");
                String sepH[]=sep[4].split("-");
                int y=Integer.parseInt(sepH[0]);
                int mo=Integer.parseInt(sepH[1]);
                int gi=Integer.parseInt(sepH[2]);
                int h=Integer.parseInt(sepH[3]);
                int m=Integer.parseInt(sepH[4]);
                LocalDateTime info=LocalDateTime.of(y, mo, gi, h, m);
                
                if(depInfo.isBefore(info)) {
                	fileContent[id][2]=sDepInfo;
                	fileFlights.writeOnCSV(fileContent, fileFlights.findLineAmount(), fileFlights.howManySplit(";"), ";");
                }
        		}
                break;
        	case 4:
        		fileContent[id][3]=input.getString();
        		fileFlights.writeOnCSV(fileContent, fileFlights.findLineAmount(), fileFlights.howManySplit(";"), ";");
        		break; 
        	case 5:{
        		int year = input.getInt();
                int month = input.getInt();
                int day = input.getInt();
                int hour = input.getInt();
                int min = input.getInt();
                LocalDateTime depInfo=LocalDateTime.of(year, month, day, hour, min);
                String sDepInfo=depInfo.getYear()+"-"+depInfo.getMonthValue()+"-"+depInfo.getDayOfMonth()+"T"+depInfo.getHour()+":"+depInfo.getMinute();
                
                String s=fileContent[id][2];
                String sep[]=s.split(";");
                String sepH[]=sep[2].split("-");
                int y=Integer.parseInt(sepH[0]);
                int mo=Integer.parseInt(sepH[1]);
                int gi=Integer.parseInt(sepH[2]);
                int h=Integer.parseInt(sepH[3]);
                int m=Integer.parseInt(sepH[4]);
                LocalDateTime info=LocalDateTime.of(y, mo, gi, h, m);
                
                if(depInfo.isAfter(info)) {
                	fileContent[id][4]=sDepInfo;
                	fileFlights.writeOnCSV(fileContent, fileFlights.findLineAmount(), fileFlights.howManySplit(";"), ";");
                }
                break;
        	}
        	case 6:
        		double d=input.getDouble();
        		if(d>=0) {
        			String sd=Double.toString(d);
        			fileContent[id][5]=sd;
        			fileFlights.writeOnCSV(fileContent, fileFlights.findLineAmount(), fileFlights.howManySplit(";"), ";");
        		}
        		break;
        	case 7:
        		int t=input.getInt();
        		if(t>=0) {
        			String st=Integer.toString(t);
        			fileContent[id][6]=st;
        			fileFlights.writeOnCSV(fileContent, fileFlights.findLineAmount(), fileFlights.howManySplit(";"), ";");
        		}
        		
        			
        }
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
        int year = input.getInt();
        int month = input.getInt();
        int day = input.getInt();
        int hour = input.getInt();
        int min = input.getInt();
        LocalDateTime depInfo=LocalDateTime.of(year, month, day, hour, min);
        
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
        double price=input.getDouble();
        System.out.println("Enter maximum tickets for the plane: ");
        int ticket=input.getInt();;
        
        if(depInfo.isBefore(arrInfo)&&price>=0&&ticket>=0&&searchFlights(id)==-1) {//stampa su file se la partenza è prima di arrivo, se il prezzo e i biglietti sono >=0 e se non si è trovato all'interno del file nessun volo con lo stesso id
        	String sDepInfo=depInfo.getYear()+"-"+depInfo.getMonthValue()+"-"+depInfo.getDayOfMonth()+"T"+depInfo.getHour()+":"+depInfo.getMinute();
        	String sArrInfo=arrInfo.getYear()+"-"+arrInfo.getMonthValue()+"-"+arrInfo.getDayOfMonth()+"T"+arrInfo.getHour()+":"+arrInfo.getMinute();
        	String sPrice = Double.toString(price);
        	String sTicket = Integer.toString(ticket);
	        String add[][];
	        add = new String[1][7];
	        add[0][0]=id;
	        add[0][1]=airportDeparture;
	        add[0][2]=sDepInfo;
	        add[0][3]=airportArrivals;
	        add[0][4]=sArrInfo;
	        add[0][5]=sPrice;
	        add[0][6]=sTicket;
	        fileFlights.writeOnCSVAddOnly(add, 1, 7, ";");
        }
        
       
    }

}
