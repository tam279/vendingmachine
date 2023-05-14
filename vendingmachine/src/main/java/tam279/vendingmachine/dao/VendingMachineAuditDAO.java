package tam279.vendingmachine.dao;

import tam279.vendingmachine.exception.VendingMachinePersistenceException;

/**
 * DAO for vending machine audit.
 */
public interface VendingMachineAuditDAO {

    /**
     * Method to write an audit entry.
     *
     * @param entry The audit entry.
     * @throws VendingMachinePersistenceException If there is an issue with persistence.
     */
    void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
