package tam279.vendingmachine.exception;

/**
 * Exception for when there are insufficient funds.
 */
public class InsufficientFundsException extends Exception {
    /**
     * Constructor for InsufficientFundsException.
     *
     * @param message The exception message.
     */
    public InsufficientFundsException(String message) {
        super(message);
    }

    /**
     * Constructor for InsufficientFundsException.
     *
     * @param message The exception message.
     * @param cause The cause of the exception.
     */
    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
