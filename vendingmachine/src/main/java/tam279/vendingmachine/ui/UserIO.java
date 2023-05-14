package tam279.vendingmachine.ui;

import java.math.BigDecimal;

/**
 * UserIO interface declares methods for handling user input/output operations.
 * These methods cover various data types and range checks.
 */
public interface UserIO {

    /**
     * Prints a message to the console.
     *
     * @param msg The message to print.
     */
    void print(String msg);

    /**
     * Prompts the user for a double value.
     *
     * @param prompt The message to display to the user.
     * @return The double value input by the user.
     */
    double readDouble(String prompt);

    /**
     * Prompts the user for a double value within a specified range.
     *
     * @param prompt The message to display to the user.
     * @param min The minimum acceptable value.
     * @param max The maximum acceptable value.
     * @return The double value input by the user.
     */
    double readDouble(String prompt, double min, double max);

    /**
     * Prompts the user for a float value.
     *
     * @param prompt The message to display to the user.
     * @return The float value input by the user.
     */
    float readFloat(String prompt);

    /**
     * Prompts the user for a float value within a specified range.
     *
     * @param prompt The message to display to the user.
     * @param min The minimum acceptable value.
     * @param max The maximum acceptable value.
     * @return The float value input by the user.
     */
    float readFloat(String prompt, float min, float max);

    /**
     * Prompts the user for an integer value.
     *
     * @param prompt The message to display to the user.
     * @return The integer value input by the user.
     */
    int readInt(String prompt);

    /**
     * Prompts the user for an integer value within a specified range.
     *
     * @param prompt The message to display to the user.
     * @param min The minimum acceptable value.
     * @param max The maximum acceptable value.
     * @return The integer value input by the user.
     */
    int readInt(String prompt, int min, int max);

    /**
     * Prompts the user for a long value.
     *
     * @param prompt The message to display to the user.
     * @return The long value input by the user.
     */
    long readLong(String prompt);

    /**
     * Prompts the user for a long value within a specified range.
     *
     * @param prompt The message to display to the user.
     * @param min The minimum acceptable value.
     * @param max The maximum acceptable value.
     * @return The long value input by the user.
     */
    long readLong(String prompt, long min, long max);

    /**
     * Prompts the user for a string value.
     *
     * @param prompt The message to display to the user.
     * @return The string value input by the user.
     */
    String readString(String prompt);

    /**
     * Prompts the user for a BigDecimal value.
     *
     * @param prompt The message to display to the user.
     * @return The BigDecimal value input by the user.
     */
    BigDecimal readBigDecimal(String prompt);
}
