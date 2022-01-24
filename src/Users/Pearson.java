package Users;

import java.io.IOException;
import IO.mailException;
import IO.passwordException;
import DB.Users.IOUsers;

public class Pearson {
    private String name;
    private String surname;
    private String mail;
    private String password;
    private String userType;

    private IOUsers usersManager;
    IO.getInputFromKeyboard input = new IO.getInputFromKeyboard();

    public Pearson() throws IOException {
        usersManager = new IOUsers();
    }

    public Pearson(String name, String surname, String mail, String password) throws IOException {
        usersManager = new IOUsers();
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
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

    public void logIn() throws passwordException, IOException, mailException {
        input.clearConsole();

        System.out.print("Insert mail: ");
        String mail = input.getMail();
        System.out.print("Insert password: ");
        String password = input.getPassword();

        verifyCredentials(mail, password);
    }

    public void logIn(String mail) throws passwordException, IOException, mailException {
        input.clearConsole();

        System.out.print("Insert mail: " + mail + "\n");
        System.out.print("Insert password: ");
        String password = input.getPassword();

        verifyCredentials(mail, password);
    }

    public void logIn(String mail, String password) throws passwordException, IOException, mailException {
        verifyCredentials(mail, password);
    }

    public void costumerRegistration() throws passwordException, IOException, mailException {
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
                boolean mustGoOut = false;
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
                            logIn(mail);
                            mustGoOut = true;
                            reinsertAppened = false;
                            break;
                        case 2:
                            System.out.print("Insert old password: ");
                            String oldPassword = input.getPassword();
                            System.out.print("Insert new password: ");
                            String newPassword = input.getPassword();
                            usersManager.changePasswordUser(idUser, oldPassword, newPassword);
                            logIn(mail, newPassword);
                            mustGoOut = true;
                            reinsertAppened = false;
                            break;
                        case 3:
                            input.clearConsole();
                            System.out.print("Reinsert mail: ");
                            mail = input.getMail();
                            break;
                    }
                    if (mustGoOut)
                        break;
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

            this.name = name;
            this.surname = surname;
            this.mail = mail;
            this.password = password;
            this.userType = "Costumer";
            usersManager.addUser(name, surname, mail, password, "C");
        }
    }

    public void verifyCredentials(String mail, String password) throws IOException {
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
                } else
                    System.out.println("Login failed\n");
                System.out.println("LogIn successful\n" + toString());
            } else {
                System.out.println("Login failed\n");
            }
        } else
            System.out.println("Login failed\n");
    }

    public String toString() {
        return "name: " + this.name + " surname: " + this.surname + " Mail: " + this.mail + " Password: "
                + this.password + " userType: " + this.userType;
    }

}