package tam279.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Represents a set of change in a vending machine system.
 * This class encapsulates the concept of a set of change, which includes pennies, nickels, dimes, and quarters.
 */
public class Change {

    private BigDecimal penny;
    private BigDecimal nickel;
    private BigDecimal dime;
    private BigDecimal quarter;

    /**
     * Constructor with parameters.
     */
    public Change(BigDecimal penny, BigDecimal nickel, BigDecimal dime, BigDecimal quarter) {
        this.penny = penny;
        this.nickel = nickel;
        this.dime = dime;
        this.quarter = quarter;
    }

    /**
     * No-args constructor, initializing the coin values to 0.
     */
    public Change() {
        this.penny = BigDecimal.ZERO;
        this.nickel = BigDecimal.ZERO;
        this.dime = BigDecimal.ZERO;
        this.quarter = BigDecimal.ZERO;
    }

    // Getters and Setters

    public BigDecimal getPenny() {
        return penny;
    }

    public void setPenny(BigDecimal penny) {
        this.penny = penny;
    }

    public BigDecimal getNickel() {
        return nickel;
    }

    public void setNickel(BigDecimal nickel) {
        this.nickel = nickel;
    }

    public BigDecimal getDime() {
        return dime;
    }

    public void setDime(BigDecimal dime) {
        this.dime = dime;
    }

    public BigDecimal getQuarter() {
        return quarter;
    }

    public void setQuarter(BigDecimal quarter) {
        this.quarter = quarter;
    }

    public void getChange(BigDecimal changeBack, BigDecimal itemPrice) {
        BigDecimal itemPriceCompare = itemPrice;
        BigDecimal change = changeBack;


        //BigDecimals for each coin
        BigDecimal pennyDivider = new BigDecimal("0.01");
        BigDecimal nickelDivider = new BigDecimal("0.05");
        BigDecimal dimeDivider = new BigDecimal("0.10");
        BigDecimal quarterDivider = new BigDecimal("0.25");

        // To store remainder
        BigDecimal remainderCoins;

        //determine change due by comparing item cost to user change
        BigDecimal changeDue = change.subtract(itemPriceCompare);

        if (changeDue.compareTo(BigDecimal.ZERO) > 0) {
            remainderCoins = changeDue;

            if (remainderCoins.compareTo(BigDecimal.ZERO) > -1) {
                setQuarter(remainderCoins.divide(quarterDivider,RoundingMode.DOWN).setScale(0, RoundingMode.DOWN));
                BigDecimal term = (getQuarter().multiply(quarterDivider).setScale(2, RoundingMode.HALF_UP));
                remainderCoins = remainderCoins.subtract(term);

            } else {
                setQuarter(BigDecimal.ZERO);
            }

            //determine amount of dimes due based on remaining coins

            if ( remainderCoins.compareTo(dimeDivider) > -1) {
                setDime(remainderCoins.divide(dimeDivider,RoundingMode.DOWN).setScale(0, RoundingMode.DOWN));
                remainderCoins = remainderCoins.subtract(getDime().multiply(dimeDivider).setScale(2, RoundingMode.HALF_UP));
            } else {
                setDime(BigDecimal.ZERO);
            }

            //determine amount of nickels due based on remaining coins
            if ( remainderCoins.compareTo(nickelDivider) > -1) {
                setNickel(remainderCoins.divide(nickelDivider,RoundingMode.DOWN).setScale(0, RoundingMode.DOWN));
                remainderCoins = remainderCoins.subtract(getNickel().multiply(nickelDivider).setScale(2, RoundingMode.HALF_UP));
            } else {
                setNickel(BigDecimal.ZERO);
            }

            //determine amount of penny due/left



            penny = remainderCoins.setScale(0) ;

        } else {
            //no change is due back to user
            changeDue = BigDecimal.ZERO;
        }
    }

}
