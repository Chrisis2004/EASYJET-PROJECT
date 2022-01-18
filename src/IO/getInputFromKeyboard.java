package IO;

import java.lang.Exception;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
/*
Utilities: 
    - getInt
    - getByte
    - getLong
    - getChar
    - getString
    - getDouble
    - getFloat
How that work:
    dataType variableName = previousMethods();
    In this way you can fill a variable using the input from console
*/

public class getInputFromKeyboard {
    // COSTRUCTOR
    public getInputFromKeyboard() {
    }

    protected class ConsoleInput {
        BufferedReader reader;

        protected ConsoleInput() {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }

        protected char readChar() throws IOException {
            String inputString = reader.readLine();
            char charToReturn = inputString.charAt(0);
            return charToReturn;
        }

        protected int readInt() throws IOException {
            String inpuString = reader.readLine();
            int intToReturn = Integer.parseInt(inpuString);
            return intToReturn;
        }

        protected double readDouble() throws IOException {
            String inpuString = reader.readLine();
            double doubleToReturn = Double.parseDouble(inpuString);
            return doubleToReturn;
        }

        protected float readFloat() throws IOException {
            String inpuString = reader.readLine();
            float floatToReturn = Float.parseFloat(inpuString);
            return floatToReturn;
        }

        protected long readLong() throws IOException {
            String inpuString = reader.readLine();
            long longToReturn = Long.parseLong(inpuString);
            return longToReturn;
        }

        protected byte readByte() throws IOException {
            String inpuString = reader.readLine();
            byte byteToReturn = Byte.parseByte(inpuString);
            return byteToReturn;
        }

        protected short readShort() throws IOException {
            String inpuString = reader.readLine();
            short shortToReturn = Short.parseShort(inpuString);
            return shortToReturn;
        }

        protected boolean readBoolean() throws IOException {
            String inpuString = reader.readLine();
            boolean booleanToReturn = Boolean.parseBoolean(inpuString);
            return booleanToReturn;
        }

        protected String readString() throws IOException {
            String stringToReturn = reader.readLine();
            return stringToReturn;
        }

    }

    // METHODS
    ConsoleInput reader = new ConsoleInput();

    public int getInt() {
        int intToReturn = 0;
        try {
            intToReturn = reader.readInt();
        } catch (Exception e) {
            System.out.println("The insertion isn't corret, will be setted a default value (0)");
        }
        return intToReturn;
    }

    public long getLong() {
        long longToReturn = 0;
        try {
            longToReturn = reader.readLong();
        } catch (Exception e) {
            System.out.println("The insertion isn't corret, will be setted a default value (0)");
        }
        return longToReturn;
    }

    public long getShort() {
        short shortToReturn = 0;
        try {
            shortToReturn = reader.readShort();
        } catch (Exception e) {
            System.out.println("The insertion isn't corret, will be setted a default value (0)");
        }
        return shortToReturn;
    }

    public float getFloat() {
        float floatToReturn = 0;
        try {
            floatToReturn = reader.readFloat();
        } catch (Exception e) {
            System.out.println("The insertion isn't corret, will be setted a default value (0)");
        }
        return floatToReturn;
    }

    public double getDouble() {
        double doubleToReturn = 0;
        try {
            doubleToReturn = reader.readDouble();
        } catch (Exception e) {
            System.out.println("The insertion isn't corret, will be setted a default value (0)");
        }
        return doubleToReturn;
    }

    public byte getByte() {
        byte byteToReturn = 0;
        try {
            byteToReturn = reader.readByte();
        } catch (Exception e) {
            System.out.println("The insertion isn't corret, will be setted a default value (0)");
        }
        return byteToReturn;
    }

    public boolean getBoolean() {
        boolean booleanToReturn = false;
        try {
            booleanToReturn = reader.readBoolean();
        } catch (Exception e) {
            System.out.println("The insertion isn't corret, will be setted a default value (0)");
        }
        return booleanToReturn;
    }

    public String getString() {
        String stringToReturn = "";
        try {
            stringToReturn = reader.readString();
        } catch (Exception e) {
            System.out.println("The insertion isn't corret, will be setted a default value (0)");
        }
        return stringToReturn;
    }

    public String getMail() throws mailException, IOException {
        String mailToReturn = "";
        try {
            mailToReturn = reader.readString();
            mailValidation(mailToReturn);
        } catch (mailException e) {
            e.mailError(mailToReturn);
        }
        return mailToReturn;
    }

    public void mailValidation(String mail) throws mailException {
        if (!mailSyntaxCheck(mail)) {
            throw new mailException();
        }
    }

    public static boolean mailSyntaxCheck(String mail) {
        // Create the Pattern using the regex
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+"); // espessione regolare

        // Match the given string with the pattern
        Matcher m = p.matcher(mail);

        // check whether match is found
        boolean matchFound = m.matches();

        StringTokenizer st = new StringTokenizer(mail, ".");
        String lastToken = null;
        while (st.hasMoreTokens()) {
            lastToken = st.nextToken();
        }

        // validate the country code
        if (matchFound && lastToken.length() >= 2
                && mail.length() - 1 != lastToken.length()) {

            return true;
        } else {
            return false;
        }

    }

    public char getChar() {
        char charToReturn = 0;
        try {
            charToReturn = reader.readChar();
        } catch (Exception e) {
            System.out.println("The insertion isn't corret, will be setted a default value (0)");
        }
        return charToReturn;
    }

    public String getPassword() throws passwordException, IOException {
        String passwordToReturn = "";
        try {
            passwordToReturn = reader.readString();
            passwordValidation(passwordToReturn);
        } catch (passwordException e) {
            passwordToReturn = e.passwordError(passwordToReturn);
        }
        return passwordToReturn;
    }

    public void passwordValidation(String password) throws passwordException {
        if (!passwordSyntaxCheck(password)) {
            throw new passwordException();
        }
    }

    public static boolean passwordSyntaxCheck(String password) {
        // return true if and only if password:
        // 1. have at least eight characters.
        // 2. consists of only letters and digits.
        // 3. must contain at least two digits.
        if (password.length() < 8) {
            return false;
        } else {
            char c;
            int count = 1;
            for (int i = 0; i < password.length() - 1; i++) {
                c = password.charAt(i);
                if (!Character.isLetterOrDigit(c)) {
                    return false;
                } else if (Character.isDigit(c)) {
                    if (count < 2) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
