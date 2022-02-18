package IO;

import java.lang.Exception;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

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
        return passwordCripter(passwordToReturn, "password");
    }

    public void passwordValidation(String password) throws passwordException {
        if (!passwordSyntaxCheck(password)) {
            throw new passwordException();
        }
    }

    public static boolean passwordSyntaxCheck(String password) {
        // return true if and only if password:
        // 1. have at least 8 characters
        // 2. have at least a number
        // 3. have at least an uppercase character
        // 4. have at least a special charcater
        if (password.length() < 8) {
            return false;
        } else {
            boolean validPassword[] = { false, false, false };
            String number[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
            String upperCase[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                    "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
            String specialChar[] = { "!", "£", "$", "%", "&", "/", "(", ")", "=", "?", "^", "€", "[", "]", "+", "*",
                    "@", "#", "|", "\\", " - ", "_ ", " < ", " > ", "°", "§" };
            for (int i = 0; i < number.length; i++) {
                if (password.indexOf(number[i]) != -1) {
                    validPassword[0] = true;
                    break;
                }
            }
            for (int i = 0; i < upperCase.length; i++) {
                if (password.indexOf(upperCase[i]) != -1) {
                    validPassword[1] = true;
                    break;
                }
            }
            for (int i = 0; i < specialChar.length; i++) {
                if (password.indexOf(specialChar[i]) != -1) {
                    validPassword[2] = true;
                    break;
                }
            }
            if (validPassword[0] && validPassword[1] && validPassword[2])
                return true;
            else
                return false;
        }
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void printLogo() throws IOException {
        FileIO fileReader = new FileIO("logo.txt");
        String[] logoToPrint = fileReader.readFromFile();
        for (int i = 0; i < logoToPrint.length; i++)
            System.out.println(logoToPrint[i]);
    }

    public String passwordCripter(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public String getDataTime() {
        String toReturn;
        System.out.println("Insert year: ");
        String year = getString();
        System.out.println("Insert month: ");
        String month = getString();
        System.out.println("Insert day: ");
        String day = getString();
        System.out.println("Insert hour:minutes : ");
        String time = getString();

        toReturn = year + "-" + month + "-" + day + "T" + time;
        return toReturn;
    }

    public String printDataTime(String dataTime) {
        LocalDateTime data = LocalDateTime.parse(dataTime);
        String toReturn;
        if (data.getMinute() < 10)
            toReturn = data.getDayOfMonth() + "-" + data.getMonthValue() + "-" + data.getYear() + " " + data.getHour()
                    + ":0" + data.getMinute();
        else
            toReturn = data.getDayOfMonth() + "-" + data.getMonthValue() + "-" + data.getYear() + " " + data.getHour()
                    + ":" + data.getMinute();
        return toReturn;
    }
}
