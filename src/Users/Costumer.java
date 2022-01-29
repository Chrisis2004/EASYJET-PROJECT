package Users;

import java.io.IOException;

public class Costumer extends Pearson{
    public Costumer(String name, String surname, String mail, String password, String userType) throws IOException {
        super(name, surname, mail, password, userType);
    }
}
