package tam279.vendingmachine.service;

import tam279.vendingmachine.dao.VendingMachineAuditDAO;
import tam279.vendingmachine.exception.VendingMachinePersistenceException;

/**
 * A stub implementation of the VendingMachineAuditDAO interface.
 * This class is meant to be used in contexts where you need an instance of VendingMachineAuditDAO,
 * but don't want any behavior (e.g., for testing purposes).
 */
public class VendingMachineAuditDaoStubImpl implements VendingMachineAuditDAO {

    /**
     * Overrides writeAuditEntry method of VendingMachineAuditDAO.
     * As this is a stub, this method doesn't perform any operation.
     *
     * @param entry A string representing the entry to be written to the audit log.
     * @throws VendingMachinePersistenceException If an error occurs while writing to the audit log.
     */
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        // do nothing
    }
}
