package tam279.vendingmachine.dao;

import java.util.List;
import tam279.vendingmachine.dto.VendingMachine;
import tam279.vendingmachine.exception.InsufficientFundsException;
import tam279.vendingmachine.exception.NoItemInventoryException;
import tam279.vendingmachine.exception.VendingMachinePersistenceException;

/**
 * Interface for vending machine inventory data access object.
 */
public interface VendingMachineInventoryDAO {
    /**
     * Returns a list of all items in the vending machine.
     *
     * @return a list of all items in the vending machine
     * @throws VendingMachinePersistenceException if there is an error accessing the data
     */
    List<VendingMachine> getAllItems() throws VendingMachinePersistenceException;

    /**
     * Purchases an item from the vending machine.
     *
     * @param vendingMachine the item to be purchased
     * @return the item that was purchased
     * @throws VendingMachinePersistenceException if there is an error accessing the data
     * @throws NoItemInventoryException if there is no inventory for the item
     * @throws InsufficientFundsException if there are not enough funds to purchase the item
     */
    VendingMachine purchaseItem(VendingMachine vendingMachine)
            throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundsException;

    /**
     * Adds an item to the vending machine.
     *
     * @param itemID the ID of the item to be added
     * @param vendingmachine the item to be added
     * @return the item that was added
     * @throws VendingMachinePersistenceException if there is an error accessing the data
     */
    VendingMachine addItem(String itemID, VendingMachine vendingmachine) throws VendingMachinePersistenceException;

    /**
     * Returns the item with the given ID.
     *
     * @param itemID the ID of the item
     * @return the item with the given ID
     * @throws VendingMachinePersistenceException if there is an error accessing the data
     * @throws NoItemInventoryException if there is no inventory for the item
     */
    VendingMachine getItem(String itemID) throws VendingMachinePersistenceException, NoItemInventoryException;
}
