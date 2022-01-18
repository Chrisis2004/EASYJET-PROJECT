package Users;

import java.io.IOException;

public class Administrator extends Pearson{
    public Administrator(String name, String surname, String mail, String password) throws IOException {
        super(name, surname, mail, password);
    }
}
