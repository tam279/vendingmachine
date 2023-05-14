package tam279.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * This class represents an item in the vending machine.
 */
public class VendingMachine {

    private String itemName;
    private BigDecimal itemPrice;
    private int itemInventory;
    private String itemID;

    /**
     * Constructor for an item in the vending machine.
     *
     * @param itemName the name of the item
     */
    public VendingMachine(String itemName) {
        this.itemName = itemName;
    }

    // Getter and Setter methods
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(int itemInventory) {
        this.itemInventory = itemInventory;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    // Overrides hashCode and equals for correct comparison and usage in collections
    @Override
    public int hashCode() {
        return Objects.hash(itemName, itemPrice, itemInventory, itemID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VendingMachine other = (VendingMachine) obj;
        return itemInventory == other.itemInventory &&
                Objects.equals(itemName, other.itemName) &&
                Objects.equals(itemPrice, other.itemPrice) &&
                Objects.equals(itemID, other.itemID);
    }

    // Overrides toString for meaningful representation of object
    @Override
    public String toString() {
        return "VendingMachineItem{" +
                "itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemInventory=" + itemInventory +
                ", itemID='" + itemID + '\'' +
                '}';
    }
}
