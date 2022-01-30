import IO.getInputFromKeyboard;
public class App {
    public static Users.Pearson pearson;
    public static boolean fistActionDone;
    public static void main(String[] args) throws Exception {
        getInputFromKeyboard input = new getInputFromKeyboard();
        pearson = new Users.Pearson();

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
                fistActionDone = pearson.logIn();
                break;
            case 2:
                fistActionDone = pearson.costumerRegistration();
                break;
            default: 
        }
    }
    public static void application(){
        if ((fistActionDone)&&(pearson.getUserType().equals("Costumer"))){
            
        }
        else if ((fistActionDone)&&(pearson.getUserType().equals("Costumer"))){
        }
        else {}
    }
    
    
}

