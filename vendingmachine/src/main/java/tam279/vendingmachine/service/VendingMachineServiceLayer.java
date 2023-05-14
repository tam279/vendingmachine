
package tam279.vendingmachine.service;

import java.math.BigDecimal;
import java.util.List;
import tam279.vendingmachine.exception.InsufficientFundsException;
import tam279.vendingmachine.exception.NoItemInventoryException;
import tam279.vendingmachine.exception.VendingMachinePersistenceException;
import tam279.vendingmachine.dto.VendingMachine;

public interface VendingMachineServiceLayer {
    
    
    
    List<VendingMachine> getAllItems() throws VendingMachinePersistenceException;
    
    public VendingMachine purchaseItem(VendingMachine vendingMachine) throws VendingMachinePersistenceException,NoItemInventoryException,InsufficientFundsException ;
    
    VendingMachine getItem(String itemID) throws VendingMachinePersistenceException,NoItemInventoryException;
    
    void insertCoin(BigDecimal userMonney) throws VendingMachinePersistenceException,InsufficientFundsException ;
    
    BigDecimal getUserMoney() throws VendingMachinePersistenceException,InsufficientFundsException;;
    
    public void addItem(String itemID, VendingMachine vendingmachine) throws VendingMachinePersistenceException,NoItemInventoryException;
    
    
    
    
}
