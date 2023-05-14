package tam279.vendingmachine.exception;

/**
 * This class represents a custom exception that is thrown when
 * there are issues with persisting the state of the vending machine.
 */
public class VendingMachinePersistenceException extends Exception {

    /**
     * Constructor to create an exception with a custom message.
     * @param message The custom message.
     */
    public VendingMachinePersistenceException(String message) {
        super(message);
    }

    /**
     * Constructor to create an exception with a custom message and a cause.
     * @param message The custom message.
     * @param cause The cause of the exception.
     */
    public VendingMachinePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
