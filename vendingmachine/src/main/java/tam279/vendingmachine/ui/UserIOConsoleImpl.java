package tam279.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * UserIOConsoleImpl is the console implementation of the UserIO interface.
 * It uses a Scanner object to read user input from the console.
 */
public class UserIOConsoleImpl implements UserIO {

    private final Scanner userInput = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        return Double.parseDouble(userInput.nextLine());
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double userNum;
        do {
            userNum = readDouble(prompt);
        } while (userNum < min || userNum > max);
        return userNum;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        return Integer.parseInt(userInput.nextLine());
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int userNum;
        do {
            userNum = readInt(prompt);
        } while (userNum < min || userNum > max);
        return userNum;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return userInput.nextLine();
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        return Long.parseLong(userInput.nextLine());
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long userNum;
        do {
            userNum = readLong(prompt);
        } while (userNum < min || userNum > max);
        return userNum;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        return Float.parseFloat(userInput.nextLine());
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float userNum;
        do {
            userNum = readFloat(prompt);
        } while (userNum < min || userNum > max);
        return userNum;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        System.out.println(prompt);
        return new BigDecimal(userInput.nextLine());
    }
}
