package Users;

import java.io.IOException;

import IO.getInputFromKeyboard;
import IO.mailException;
import IO.passwordException;
import DB.Users.IOUsers;

public class Pearson {
    private String name;
    private String surname;
    private String mail;
    private String password;
    private String userType;

    IOUsers usersManager;
    getInputFromKeyboard input = new IO.getInputFromKeyboard();

    public Pearson() throws IOException {
        usersManager = new IOUsers();
    }

    public Pearson(String name, String surname, String mail, String password, String userType) throws IOException {
        usersManager = new IOUsers();
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean logIn() throws passwordException, IOException, mailException {
        input.clearConsole();

        System.out.print("Insert mail: ");
        String mail = input.getMail();
        System.out.print("Insert password: ");
        String password = input.getPassword();

        return verifyCredentials(mail, password);
    }

    public boolean logIn(String mail) throws passwordException, IOException, mailException {
        input.clearConsole();

        System.out.print("Insert mail: " + mail + "\n");
        System.out.print("Insert password: ");
        String password = input.getPassword();

        return verifyCredentials(mail, password);
    }

    public boolean logIn(String mail, String password) throws passwordException, IOException, mailException {
        return verifyCredentials(mail, password);
    }

    public boolean costumerRegistration() throws passwordException, IOException, mailException, InterruptedException {
        input.clearConsole();
        System.out.print("Insert name: ");
        String name = input.getString();
        System.out.print("Insert surname: ");
        String surname = input.getString();
        System.out.print("Insert mail: ");
        String mail = input.getMail();

        boolean reinsertAppened = true;
        int idUser = usersManager.verifyMailUser(mail);

        if (idUser != -1) {
            while (usersManager.verifyMailUser(mail) != -1) {
                if (usersManager.verifyUserType(idUser).equals("C")) {
                    input.clearConsole();
                    System.out.println(
                            "The entered email has already been assigned to a user, now you can perform the following actions: ");
                    System.out.println("1. log in with the credentials entered");
                    System.out.println("2. change your account password and then use your credentials to log in");
                    System.out.println("3. change mail insered");
                    System.out.print("Your choose: ");

                    int choose;
                    choose = input.getInt();

                    switch (choose) {
                        case 1:
                            reinsertAppened = false;
                            return logIn(mail);
                        case 2:
                            System.out.print("Insert old password: ");
                            String oldPassword = input.getPassword();
                            System.out.print("Insert new password: ");
                            String newPassword = input.getPassword();
                            usersManager.changePasswordUser(idUser, oldPassword, newPassword);
                            reinsertAppened = false;
                            return logIn(mail, newPassword);
                        case 3:
                            input.clearConsole();
                            System.out.print("Reinsert mail: ");
                            mail = input.getMail();
                            break;
                    }
                } else if (usersManager.verifyUserType(idUser).equals("A")) {
                    input.clearConsole();
                    System.out.println(" !!! Mail insered has already assigned to an administrator !!!");
                    System.out.print("Reinsert a valid mail: ");
                    mail = input.getMail();
                }
            }
        }
        if (reinsertAppened) {
            System.out.print("Insert password: ");
            String password = input.getPassword();
            usersManager.addUser(name, surname, mail, password, "C");
            return logIn(mail, password);
        } else
            return false;
    }

    public boolean verifyCredentials(String mail, String password) throws IOException {
        int idUsers = usersManager.verifyMailUser(mail);
        if (idUsers != -1) {
            if (usersManager.verifyPasswordUser(mail, password)) {
                this.name = usersManager.getNameUser(idUsers);
                this.surname = usersManager.getSurnameUser(idUsers);
                this.mail = mail;
                this.password = password;
                if (usersManager.verifyUserType(idUsers).equals("C")) {
                    this.userType = "Costumer";
                } else if (usersManager.verifyUserType(idUsers).equals("A")) {
                    this.userType = "Administrator";
                } else {
                    return false;
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String toString() {
        return "name: " + this.name + " surname: " + this.surname + " Mail: " + this.mail + " Password: "
                + this.password + " userType: " + this.userType;
    }

}