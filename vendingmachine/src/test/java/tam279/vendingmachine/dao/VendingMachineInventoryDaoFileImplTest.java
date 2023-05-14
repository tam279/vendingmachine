package tam279.vendingmachine.dao;

import tam279.vendingmachine.dto.VendingMachine;
import tam279.vendingmachine.exception.InsufficientFundsException;
import tam279.vendingmachine.exception.NoItemInventoryException;
import tam279.vendingmachine.exception.VendingMachinePersistenceException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for VendingMachineInventoryDAO class.
 */
public class VendingMachineInventoryDaoFileImplTest {

    private VendingMachineInventoryDAO testDao;

    public VendingMachineInventoryDaoFileImplTest() {
    }

    @BeforeEach
    public void setUp() {
        String testFile = "testvendingmachine.txt";
        testDao = new VendingMachineInventoryDaoFileImpl(testFile);

        try (PrintWriter out = new PrintWriter(new FileWriter(testFile))) {
            out.flush();
        } catch (IOException e) {
            fail("Setup failed: unable to clear test file");
        }
    }

    /**
     * Tests add and retrieve item operations.
     */
    @Test
    public void testAddandGetItem() {
        VendingMachine itemTest1 = new VendingMachine("Coke");
        itemTest1.setItemID("T1");
        itemTest1.setItemInventory(10);
        itemTest1.setItemPrice(BigDecimal.valueOf(1.5));

        try {
            testDao.addItem("T1", itemTest1);
            VendingMachine retrievedItem = testDao.getItem("T1");

            assertEquals(itemTest1.getItemID(), retrievedItem.getItemID(), "Checking item ID.");
            assertEquals(itemTest1.getItemName(), retrievedItem.getItemName(), "Checking item name.");
            assertEquals(itemTest1.getItemInventory(), retrievedItem.getItemInventory(), "Checking Item Inventory.");
            assertEquals(itemTest1.getItemPrice(), retrievedItem.getItemPrice(), "Checking Item Price.");
        } catch (VendingMachinePersistenceException | NoItemInventoryException ex) {
            fail("Exception thrown during test: " + ex.getMessage());
        }
    }

    /**
     * Tests add and retrieve all items operations.
     */
    @Test
    public void testAddandGetAllItem() {
        VendingMachine itemTest2 = new VendingMachine("Pepsi");
        itemTest2.setItemID("T2");
        itemTest2.setItemInventory(10);
        itemTest2.setItemPrice(BigDecimal.valueOf(1.45));

        VendingMachine itemTest3 = new VendingMachine("M&M");
        itemTest3.setItemID("T3");
        itemTest3.setItemInventory(10);
        itemTest3.setItemPrice(BigDecimal.valueOf(1.55));

        try {
            testDao.addItem(itemTest2.getItemID(), itemTest2);
            testDao.addItem(itemTest3.getItemID(), itemTest3);

            List<VendingMachine> allItems = testDao.getAllItems();

            assertNotNull(allItems, "The list of items must not null");
            assertEquals(2, allItems.size(), "List of items should have 2 items.");
            assertTrue(allItems.contains(itemTest2), "The list of items should include T2.");
            assertTrue(allItems.contains(itemTest3), "The list of items should include T3.");
        } catch (VendingMachinePersistenceException ex) {
            fail("Exception thrown during test: " + ex.getMessage());
        }
    }

    /**
     * Tests the purchase item operation.
     */
    @Test
    public void testPurchaseItem() {
        VendingMachine itemTest4 = new VendingMachine("Ginger Ale");
        itemTest4.setItemID("T4");
        itemTest4.setItemInventory(1);
        itemTest4.setItemPrice(BigDecimal.valueOf(1.75));

        try {
            testDao.addItem(itemTest4.getItemID(), itemTest4);
            VendingMachine purchaseItem = testDao.purchaseItem(itemTest4);

            assertEquals(0, purchaseItem.getItemInventory(), "The itemInventory should be 0.");
        } catch (VendingMachinePersistenceException | NoItemInventoryException | InsufficientFundsException ex) {
            fail("Exception thrown during test: " + ex.getMessage());
        }
    }
}
