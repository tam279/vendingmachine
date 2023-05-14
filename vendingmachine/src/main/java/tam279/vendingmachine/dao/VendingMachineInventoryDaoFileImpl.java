package tam279.vendingmachine.dao;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import tam279.vendingmachine.dto.VendingMachine;
import tam279.vendingmachine.exception.*;

/**
 * A class implementing VendingMachineInventoryDAO.
 * It uses a file to persist the data.
 */
public class VendingMachineInventoryDaoFileImpl implements VendingMachineInventoryDAO {
    private static final String DELIMITER = "::";
    private String vendingMachineFile;
    private Map<String, VendingMachine> vendingmachines = new HashMap<>();

    /**
     * Default constructor using "vendingmachine.txt" as the default file name.
     */
    public VendingMachineInventoryDaoFileImpl() {
        this("vendingmachine.txt");
    }

    /**
     * Constructor with a specific file name.
     *
     * @param vendingMachineFile the file name for data persistence
     */
    public VendingMachineInventoryDaoFileImpl(String vendingMachineFile) {
        this.vendingMachineFile = vendingMachineFile;
    }

    @Override
    public List<VendingMachine> getAllItems() throws VendingMachinePersistenceException {
        loadVendingMachine();
        return new ArrayList<>(vendingmachines.values());
    }

    @Override
    public VendingMachine purchaseItem(VendingMachine vendingMachine) throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundsException {
        loadVendingMachine();
        VendingMachine item = vendingmachines.get(vendingMachine.getItemID());
        if (item != null && item.getItemInventory() > 0) {
            item.setItemInventory(item.getItemInventory() - 1);
            writeVendingMachine();
        }
        return item;
    }

    @Override
    public VendingMachine addItem(String itemID, VendingMachine vendingMachine) throws VendingMachinePersistenceException {
        loadVendingMachine();
        VendingMachine newItem = vendingmachines.put(itemID, vendingMachine);
        writeVendingMachine();
        return newItem;
    }

    @Override
    public VendingMachine getItem(String itemID) throws VendingMachinePersistenceException, NoItemInventoryException {
        loadVendingMachine();
        return vendingmachines.get(itemID);
    }

    /**
     * Load data from file into memory.
     *
     * @throws VendingMachinePersistenceException if there's an error accessing the file
     */
    private void loadVendingMachine() throws VendingMachinePersistenceException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(vendingMachineFile)))) {
            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(DELIMITER);
                VendingMachine machine = new VendingMachine(tokens[1]);
                machine.setItemID(tokens[0]);
                machine.setItemPrice(new BigDecimal(tokens[2]));
                machine.setItemInventory(Integer.parseInt(tokens[3]));
                vendingmachines.put(machine.getItemID(), machine);
            }
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("-_- Could not load roster data into memory.", e);
        }
    }

    /**
     * Write data from memory into file.
     *
     * @throws VendingMachinePersistenceException if there's an error accessing the file
     */
    private void writeVendingMachine() throws VendingMachinePersistenceException {
        try (PrintWriter out = new PrintWriter(new FileWriter(vendingMachineFile))) {
            for (VendingMachine machine : getAllItems()) {
                out.println(machine.getItemID() + DELIMITER
                        + machine.getItemName() + DELIMITER
                        + machine.getItemPrice() + DELIMITER
                        + machine.getItemInventory());
                out.flush();
            }
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save item data.", e);
        }
    }
}
