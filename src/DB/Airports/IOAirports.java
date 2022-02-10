package DB.Airports;

import java.io.IOException;

import IO.FileIO;
import IO.getInputFromKeyboard;

public class IOAirports{
    private FileIO fileAirports;
    getInputFromKeyboard input = new getInputFromKeyboard();

    public IOAirports() throws IOException {
        fileAirports = new FileIO("src\\DB\\Airports\\Airports.CSV");
    } 
    public String[] getAllAirports() throws IOException{
        String[] fileContent = fileAirports.readFromFile();
        return fileContent;
    }
    public void addAirport(String airport) throws IOException{
        if (verifyExisting(airport)!=-1){
            System.out.println("The airport you wanted insert already exists!\n");
        }
        else{
            String[] toWrite = new String[1];
            toWrite[0] = airport;
            fileAirports.writeOnFileAddOnly(toWrite);
        }
    }
    public int verifyExisting(String airport) throws IOException{
        String[] fileContent = fileAirports.readFromFile();
        for (int i=0; i<fileContent.length; i++){
            if(fileContent[i].equals(airport))
                return i;
        }
        return -1;
    }
    public void delateAirport(String airport) throws IOException{
        if (verifyExisting(airport)==-1){
            System.out.println("The airport you wanted delete doesn't exists!\n");
        }
        else{
            fileAirports.delateLineFromFile(verifyExisting(airport));
        }
    }
}