import java.io.IOException;

import IO.FileIO;
import IO.getInputFromKeyboard;

public class App {
    public static void main(String[] args) throws Exception {
        getInputFromKeyboard input = new getInputFromKeyboard();
        Users.Pearson pearson = new Users.Pearson();

        input.clearConsole();

        printLogo();
        System.out.println("Choose the action to do: ");
        System.out.println("1: logIn");
        System.out.println("2: registration");
        System.out.println("3. exit");
        System.out.print("Coose: ");
        int choose = input.getInt();

        switch(choose){
            case 1:
            pearson.logIn();
                break;
            case 2:
                pearson.costumerRegistration();
                break;
            default: 
        }
        
        
    }
    public static void printLogo() throws IOException{
        FileIO fileReader = new FileIO("logo.txt");
        String[] logoToPrint = fileReader.readFromFile();
        for (int i=0; i<logoToPrint.length; i++)
            System.out.println(logoToPrint[i]);
    }
}

