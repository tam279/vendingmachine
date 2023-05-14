package tam279.vendingmachine.exception;

/**
 * Exception for when there is no item inventory.
 */
public class NoItemInventoryException extends Exception{
    /**
     * Constructor for NoItemInventoryException.
     *
     * @param message The exception message.
     */
    public NoItemInventoryException(String message) {
        super(message);
    }

    /**
     * Constructor for NoItemInventoryException.
     *
     * @param message The exception message.
     * @param cause The cause of the exception.
     */
    public NoItemInventoryException(String message, Throwable cause){
        super(message, cause);
    }
}
