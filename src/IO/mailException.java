package IO;

public class mailException extends Exception {
    getInputFromKeyboard input = new getInputFromKeyboard();

    public mailException() {
    }

    public String mailError(String mail) {
        do {
            System.out.println("The mail insered is uncorrect, try to reinsert it: ");
            mail = input.getString();
        } while (!getInputFromKeyboard.mailSyntaxCheck(mail));
        return mail;
    }
}
