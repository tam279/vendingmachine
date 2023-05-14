package tam279.vendingmachine.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import tam279.vendingmachine.dto.VendingMachine;
import tam279.vendingmachine.exception.InsufficientFundsException;
import tam279.vendingmachine.exception.NoItemInventoryException;
import tam279.vendingmachine.exception.VendingMachinePersistenceException;

/**
 * Stub implementation of VendingMachineInventoryDAO for testing.
 */
public class VendingMachineServiceStubImpl implements VendingMachineInventoryDAO {
    private VendingMachine onlyItem;
    private List<VendingMachine> allItems = new ArrayList<>();

    /**
     * Default constructor initializing with default item.
     */
    public VendingMachineServiceStubImpl() {
        onlyItem = new VendingMachine("Cacao");
        onlyItem.setItemID("T6");
        onlyItem.setItemInventory(10);
        onlyItem.setItemPrice(BigDecimal.valueOf(1.40));

        allItems.add(onlyItem);
    }

    /**
     * Constructor with specific VendingMachine item.
     *
     * @param testVendingMachine VendingMachine to be used in this stub.
     */
    public VendingMachineServiceStubImpl(VendingMachine testVendingMachine) {
        this.onlyItem = testVendingMachine;
    }

    /**
     * Returns all items in the inventory.
     *
     * @return List of VendingMachine items.
     */
    @Override
    public List<VendingMachine> getAllItems() throws VendingMachinePersistenceException {
        return new ArrayList<>(allItems);
    }

    /**
     * Purchases item and reduces the inventory by one.
     *
     * @return VendingMachine item purchased.
     */
    @Override
    public VendingMachine purchaseItem(VendingMachine vendingMachine)
            throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundsException {
        if (vendingMachine != null && vendingMachine.getItemID().equals(onlyItem.getItemID())) {
            onlyItem.setItemInventory(vendingMachine.getItemInventory() - 1);
            return onlyItem;
        } else {
            return null;
        }
    }

    /**
     * Adds item to the inventory.
     *
     * @return VendingMachine item added.
     */
    @Override
    public VendingMachine addItem(String itemID, VendingMachine allItems) throws VendingMachinePersistenceException {
        return itemID.equals(onlyItem.getItemID()) ? onlyItem : null;
    }

    /**
     * Retrieves item from the inventory.
     *
     * @return VendingMachine item retrieved.
     */
    @Override
    public VendingMachine getItem(String itemID) throws VendingMachinePersistenceException, NoItemInventoryException {
        return itemID.equals(onlyItem.getItemID()) ? onlyItem : null;
    }
}
