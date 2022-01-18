package DB.Users;

import java.io.IOException;
import IO.FileIO;
import IO.getInputFromKeyboard;
import IO.passwordException;

public class IOUsers {
    private FileIO fileUsers;

    public IOUsers() throws IOException {
        fileUsers = new FileIO("src\\DB\\Users\\Users.CSV");
    }

    getInputFromKeyboard input = new getInputFromKeyboard();

    public void stampaUsers() throws IOException {
        String[][] toPrint = fileUsers.readFromCSV(";");
        for (int z = 0; z < fileUsers.nLines; z++) {
            System.out.println("User " + z + 1 + ": ");
            System.out.println("Name: " + toPrint[z][0]);
            System.out.println("Surname: " + toPrint[z][1]);
            System.out.println("Mail: " + toPrint[z][2]);
            System.out.println("Password: " + toPrint[z][3]);
        }
    }

    public String[][] getUsers() throws IOException {
        String[][] toReturn = fileUsers.readFromCSV(";");
        return toReturn;
    }

    public String getNameUser(int nLine) throws IOException {
        String[][] file = fileUsers.readFromCSV(";");
        String toReturn = file[nLine][0];
        return toReturn;

    }

    public String getSurnameUser(int nLine) throws IOException {
        String[][] file = fileUsers.readFromCSV(";");
        String toReturn = file[nLine][1];
        return toReturn;
    }

    public String getMailUser(int nLine) throws IOException {
        String[][] file = fileUsers.readFromCSV(";");
        String toReturn = file[nLine][2];
        return toReturn;
    }

    public String getPasswordUser(int nLine) throws IOException {
        String[][] file = fileUsers.readFromCSV(";");
        String toReturn = file[nLine][3];
        return toReturn;
    }

    public int verifyMailUser(String mail) throws IOException {
        if (fileUsers.thereIsOnCSV(mail, 2) != -1)
            return fileUsers.thereIsOnCSV(mail, 2);
        else
            return -1;
    }

    public boolean verifyPasswordUser(String mail, String password) throws IOException {
        if (fileUsers.thereIsInLineCSV(password, verifyMailUser(mail), 3))
            return true;
        else
            return false;
    }

    public String verifyUserType(int nLine) throws IOException {
        String[][] fileContent = fileUsers.readFromCSV(";");
        if (fileContent[nLine][4].equals("A")) {
            return "A";
        } else if (fileContent[nLine][4].equals("C")) {
            return "C";
        } else
            return "-1";
    }

    public void changePasswordUser(int nLineUser, String oldPassword, String newPassword)
            throws IOException, passwordException {
        String[][] file = fileUsers.readFromCSV(";");
        int nTry = 3;
        while ((!oldPassword.equals(file[nLineUser][3])) && (nTry > 0)) {
            System.out.println("You have missed the correct password, you have " + nTry + " attemps yet do correct it." + file[nLineUser][3]);
            System.out.print("Insert old password: ");
            oldPassword = input.getPassword();
            nTry--;
        }
        if (oldPassword.equals(file[nLineUser][3])) {
            file[nLineUser][3] = newPassword;
            fileUsers.writeOnCSV(file, fileUsers.findLineAmount(), fileUsers.howManySplit(";"), ";");
        }
        else 
            System.out.print("Operation failed");
    }

    public void addUser(String name, String surname, String mail, String password, String userType) throws IOException {
        String[][] toWrite = new String[1][5];
        toWrite[0][0] = name;
        toWrite[0][1] = surname;
        toWrite[0][2] = mail;
        toWrite[0][3] = password;
        toWrite[0][4] = userType;

        fileUsers.writeOnCSVAddOnly(toWrite, 1, 5, ";");
    }

}
