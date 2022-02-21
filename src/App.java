import java.io.IOException;

import IO.getInputFromKeyboard;
import IO.mailException;
import IO.passwordException;

public class App {
    public static Users.Pearson pearson;
    public static boolean firstActionDone;

    public static void main(String[] args) throws Exception {
        getInputFromKeyboard input = new getInputFromKeyboard();
        pearson = new Users.Pearson();

        input.clearConsole();

        input.printLogo();
        System.out.println("Choose the action to do: ");
        System.out.println("1: login");
        System.out.println("2: registration");
        System.out.println("3. exit");
        System.out.print("Choose: ");
        int choose = input.getInt();

        switch (choose) {
            case 1:
                firstActionDone = pearson.logIn();
                application();
                break;
            case 2:
                firstActionDone = pearson.costumerRegistration();
                application();
                break;
            default:
        }
    }

    public static void application() throws IOException, passwordException, mailException {
        if ((firstActionDone) && (pearson.getUserType().equals("Costumer"))) {
            Users.Costumer costumer = new Users.Costumer(pearson.getName(), pearson.getSurname(), pearson.getMail(),
                    pearson.getPassword(), pearson.getUserType());
            costumer.menuCostumer();
        } else if ((firstActionDone) && (pearson.getUserType().equals("Administrator"))) {
            Users.Administrator admin = new Users.Administrator(pearson.getName(), pearson.getSurname(),
                    pearson.getMail(), pearson.getPassword(), pearson.getUserType());
            admin.menuAdmin();
        } else {
            System.out.println("Something was wrong sorry try to restart the application");
        }
    }
}
