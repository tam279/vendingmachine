package tam279.vendingmachine.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import tam279.vendingmachine.exception.InsufficientFundsException;
import tam279.vendingmachine.exception.NoItemInventoryException;
import tam279.vendingmachine.dao.VendingMachineAuditDAO;
import tam279.vendingmachine.dao.VendingMachineInventoryDAO;
import tam279.vendingmachine.exception.VendingMachinePersistenceException;
import tam279.vendingmachine.dto.VendingMachine;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    // Double check the validation and throws exeption. Should have in service layer and controller. 

    VendingMachineInventoryDAO dao;
    private Map<String, VendingMachine> vendingmachines = new HashMap<>();

    private VendingMachineAuditDAO auditDao;

    BigDecimal userMoney;

    public VendingMachineServiceLayerImpl(VendingMachineInventoryDAO dao, VendingMachineAuditDAO auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<VendingMachine> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

    @Override
    public VendingMachine purchaseItem(VendingMachine vendingMachine) throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundsException {
        // VendingMachine item = dao.purchaseItem(vendingMachine);
        VendingMachine item = vendingmachines.get(vendingMachine.getItemID());

        Iterator i = vendingmachines.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry pair = (Map.Entry) i.next();
            if (pair.getKey().equals(item.getItemID()) && item.getItemInventory() > 0) {
                item.setItemInventory(vendingMachine.getItemInventory() - 1);
            }
        }

        // Write to audit log
        //  auditDao.writeAuditEntry(1 + " of " + vendingMachine + " REMOVED.");
        return dao.purchaseItem(vendingMachine);
    }

    @Override
    public VendingMachine getItem(String itemID) throws VendingMachinePersistenceException, NoItemInventoryException {
        return dao.getItem(itemID);
    }

    @Override
    public void insertCoin(BigDecimal userMoney) throws VendingMachinePersistenceException, InsufficientFundsException {
        this.userMoney = userMoney;

    }
// Store the money from user here but using Big decimal. 

    @Override
    public BigDecimal getUserMoney() throws VendingMachinePersistenceException, InsufficientFundsException {

        return userMoney;

    }

    @Override
    public void addItem(String itemID, VendingMachine vendingmachine) throws VendingMachinePersistenceException, NoItemInventoryException {

        if (dao.getItem(vendingmachine.getItemID()) != null) {
            System.out.println("Error - item already exists.");
        }

        dao.addItem(vendingmachine.getItemID(), vendingmachine);

        //auditDao.writeAuditEntry("Item " + allItems.getItemID() + " Added.");
    }
}
