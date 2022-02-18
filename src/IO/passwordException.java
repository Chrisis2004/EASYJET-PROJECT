package IO;

public class passwordException extends Exception {
    getInputFromKeyboard input = new getInputFromKeyboard();

    public passwordException() {
    }

    public String passwordError(String password) {
        do {
            System.out.println("The password insered is uncorrect, try to reinsert it: ");
            password = input.getString();
        } while (!getInputFromKeyboard.passwordSyntaxCheck(password));
        return password;
    }
}
