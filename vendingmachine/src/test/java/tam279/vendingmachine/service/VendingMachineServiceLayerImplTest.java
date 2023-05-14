package tam279.vendingmachine.service;

import java.math.BigDecimal;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tam279.vendingmachine.exception.InsufficientFundsException;
import tam279.vendingmachine.exception.NoItemInventoryException;
import tam279.vendingmachine.exception.VendingMachinePersistenceException;
import tam279.vendingmachine.dto.VendingMachine;

/**
 * Unit tests for the VendingMachineServiceLayerImpl class.
 * This class tests the functionality of the service layer implementation.
 */
public class VendingMachineServiceLayerImplTest {

    private VendingMachineServiceLayer service;

    @BeforeAll
    public static void setUpClass() {
        // Executed once before any test methods are run
    }

    @AfterAll
    public static void tearDownClass() {
        // Executed once after all test methods are run
    }

    @BeforeEach
    public void setUp() {
        // Executed before each test method
        // Set up the service layer with necessary dependencies
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
    }

    @AfterEach
    public void tearDown() {
        // Executed after each test method
    }

    @Test
    public void testGetAllItems() {
        try {
            // ARRANGE

            // ACT & ASSERT
            assertEquals(1, service.getAllItems().size(),
                    "Should only have one item.");
        } catch (VendingMachinePersistenceException ex) {
            // Handle exceptions
        }
    }

    @Test
    public void testGetItem() {
        try {
            // ARRANGE

            // ACT
            VendingMachine shouldBeCacao = service.getItem("T6");

            // ASSERT
            assertNotNull(shouldBeCacao, "Getting T6 should not be null");
            assertEquals("T6", shouldBeCacao.getItemID(), "Checking item ID.");
            assertEquals("Cacao", shouldBeCacao.getItemName(), "Checking item name.");
            assertEquals(10, shouldBeCacao.getItemInventory(), "Checking Item Inventory.");
            assertTrue(shouldBeCacao.getItemPrice().compareTo(BigDecimal.valueOf(1.40)) == 0);
        } catch (VendingMachinePersistenceException | NoItemInventoryException ex) {
            // Handle exceptions
        }
    }

    @Test
    public void testPurchaseItem() {
        try {
            // ARRANGE

            // Create a new item
            VendingMachine testClone = service.getItem("T6");

            assertEquals(10, testClone.getItemInventory(), "The itemInventory should be 10 before purchased.");

            // ACTION
            VendingMachine purchaseItem = service.purchaseItem(testClone);

            // ASSERT
            assertEquals(9, purchaseItem.getItemInventory(), "The itemInventory should be 9.");
        } catch (VendingMachinePersistenceException | NoItemInventoryException | InsufficientFundsException ex) {
            // Handle exceptions
        }
    }
}
