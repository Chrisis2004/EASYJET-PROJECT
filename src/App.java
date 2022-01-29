import IO.getInputFromKeyboard;
public class App {
    public static void main(String[] args) throws Exception {
        getInputFromKeyboard input = new getInputFromKeyboard();
        Users.Pearson pearson = new Users.Pearson();

        input.clearConsole();

        input.printLogo();
        System.out.println("Choose the action to do: ");
        System.out.println("1: logIn");
        System.out.println("2: registration");
        System.out.println("3. exit");
        System.out.print("Choose: ");
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
    
    
}

