package tam279.vendingmachine.ui;

import tam279.vendingmachine.dto.VendingMachine;
import java.math.BigDecimal;
import java.util.List;

/**
 * View class handles the user interaction logic, displaying prompts and messages.
 */
public class View {

    private final UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    /**
     * Displays the main menu and returns the user's selection.
     */
    public int printMenuAndGetSelection() {
        io.print("1. List all the items in the vending machine and Insert Coins to buy an item");
        io.print("2. Purchase Item");
        io.print("3. Return Change");
        io.print("4. Exit");
        return io.readInt("Please select from the above choices or Exit. ", 1, 4);
    }

    /**
     * Displays the Vending Machine items.
     */
    public void displayItemList(List<VendingMachine> vendingmachineList) {
        io.print("=== Display All Vending Machine Items ===");
        for (VendingMachine currentVendingMachine : vendingmachineList) {
            String vendingmachineInfo = String.format("%s %s : $%s %s",
                    currentVendingMachine.getItemID(),
                    currentVendingMachine.getItemName(),
                    currentVendingMachine.getItemPrice(),
                    currentVendingMachine.getItemInventory());
            io.print(vendingmachineInfo);
        }
    }

    /**
     * Displays a prompt for the user to insert coins.
     */
    public BigDecimal displayInsertCoinPrompt() {
        return io.readBigDecimal("Please insert coins to continue. ");
    }

    public void displayInsertCoinBanner(BigDecimal userMoney) {
        io.print("You inserted " + userMoney);
    }

    /**
     * Displays a prompt for the user to select an item.
     */
    public String displayUserSelectionPrompt() {
        return io.readString("Please enter the Item ID to make a selection. ");
    }

    public void displayItemRemoved(VendingMachine selectedItem) {
        io.print("1 " + selectedItem.getItemName() + " removed.");
    }

    /**
     * Displays the change due back to the user.
     */
    public void displayChangeDue(BigDecimal quarter, BigDecimal dime, BigDecimal nickel, BigDecimal loonies) {
        io.print("Your change due back is: " + quarter + " quarters, " + dime + " dimes, " + nickel + " nickels, " + loonies + " pennies.");
    }

    public void displayDisplayAllVendingMachineItemBanner() {
        io.print("=== Display All Vending Machine Item Banner ===");
    }
    public void displayChangeBackWithoutPurchase() {
        io.print("Please take your money back ");
    }

    public void displayNoSelectionMade() {
        io.print("You haven't made a selection. ");
    }

    public void displayNoCoinsEntered() {
        io.print("You haven't entered any coins. ");
    }

    /**
     * Displays exit or unknown command or error messages.
     */
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!! Please try again");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayItemNotAvailable() {
        io.print("Please choose a different item. This item will be back in stock soon");
    }
}
