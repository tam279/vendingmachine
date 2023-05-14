package tam279.vendingmachine.dao;

import tam279.vendingmachine.exception.VendingMachinePersistenceException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Implementation for the VendingMachineAuditDAO
 */
public class VendingMachineAuditDAOImpl implements VendingMachineAuditDAO {
    private static final String AUDIT_FILE = "audit.txt";

    /**
     * Method to write an audit entry.
     *
     * @param entry The audit entry.
     * @throws VendingMachinePersistenceException If there is an issue with persistence.
     */
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        try (PrintWriter out = new PrintWriter(new FileWriter(AUDIT_FILE, true))) {
            LocalDateTime timestamp = LocalDateTime.now();
            out.println(timestamp + " : " + entry);
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not persist audit information.", e);
        }
    }
}
