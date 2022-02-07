package Users;

import java.io.IOException;
import DB.Flights.IOFlights;
import DB.Users.IOUsers;
import IO.getInputFromKeyboard;
import IO.mailException;
import IO.passwordException;

public class Costumer extends Pearson{
    public Costumer(String name, String surname, String mail, String password, String userType) throws IOException {
        super(name, surname, mail, password, userType);
    }
}
