package tam279.vendingmachine.controller;


import tam279.vendingmachine.dto.Change;
import tam279.vendingmachine.dto.VendingMachine;
import tam279.vendingmachine.exception.InsufficientFundsException;
import tam279.vendingmachine.exception.NoItemInventoryException;
import tam279.vendingmachine.exception.VendingMachinePersistenceException;
import tam279.vendingmachine.service.VendingMachineServiceLayer;
import tam279.vendingmachine.ui.View;

import java.math.BigDecimal;
import java.util.List;
/**
 * This is the main controller for the Vending Machine.
 */
public class VendingMachineController {

    View view;
    private VendingMachineServiceLayer service;
    private Change bgChange = new Change();

    /**
     * Constructor for VendingMachineController.
     *
     * @param service The service layer.
     * @param view The view layer.
     */
    public VendingMachineController(VendingMachineServiceLayer service, View view) {
        this.service = service;
        this.view = view;
    }
    /**
     * Main execution method.
     *
     * @throws VendingMachinePersistenceException If there is an issue with persistence.
     * @throws InsufficientFundsException If there are insufficient funds.
     * @throws NoItemInventoryException If there is no item inventory.
     */
    public void run() throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException {
        boolean keepGoing = true;
        int menuSelection = 0;
        String userSelection = null;

        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {

                case 1:
                    try {
                        listAllItem();
                        insertCoin();
                    } catch (InsufficientFundsException e) {
                        view.displayNoCoinsEntered();
                    } catch (NullPointerException e) {
                        view.displayUnknownCommandBanner();
                    }
                case 2:
                    try {
                        userSelection = purchaseItem();
                    } catch (NoItemInventoryException e) {
                        view.displayItemNotAvailable();
                    } catch (NullPointerException e) {
                        view.displayUnknownCommandBanner();
                    }
                    break;

                case 3:
                    returnChange(userSelection);
                    break;

                case 4:
                    keepGoing = false;
                    break;

                default:
                    unknownCommand();
            }

        }
        exitMessage();

    }

    /**
     * Method to get the menu selection from the user.
     *
     * @return The menu selection.
     */
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    /**
     * Method to list all items in the vending machine.
     *
     * @throws VendingMachinePersistenceException If there is an issue with persistence.
     */
    private void listAllItem() throws VendingMachinePersistenceException {
        view.displayDisplayAllVendingMachineItemBanner();
        List<VendingMachine> vendingmachineList = service.getAllItems();
        view.displayItemList(vendingmachineList);

    }

    /**
     * Method for the user to insert a coin.
     *
     * @throws VendingMachinePersistenceException If there is an issue with persistence.
     * @throws InsufficientFundsException If there are insufficient funds.
     */
    private void insertCoin() throws VendingMachinePersistenceException, InsufficientFundsException {
        // Check the amount of money that user inserted.
        BigDecimal userMoney = view.displayInsertCoinPrompt();
        service.insertCoin(userMoney);

        // Show the user the amount of money they just inserted.
        // view.displayInsertCoinBanner(userMoney);
    }

    /**
     * Method for the user to purchase an item.
     *
     * @throws VendingMachinePersistenceException If there is an issue with persistence.
     * @throws InsufficientFundsException If there are insufficient funds.
     * @throws NoItemInventoryException If there is no item inventory.
     */
    private String purchaseItem() throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException {
        VendingMachine selectedItem = null;

        BigDecimal userMoney = service.getUserMoney();

        if (userMoney.compareTo(BigDecimal.ZERO) > 0) {
            //show user the coins they put in
            // to get a method from the service layer
            view.displayInsertCoinBanner(userMoney);

            //prompt user for selection and set to inventory object
            String userSelection = view.displayUserSelectionPrompt();

            if (userSelection.length() != 2) {
                view.displayUnknownCommandBanner();
            }
            try {
                selectedItem = service.getItem(userSelection);
            } catch (VendingMachinePersistenceException e) {
                throw new RuntimeException(e);
            } catch (NoItemInventoryException e) {
                throw new RuntimeException(e);
            }
            //compare user selection and amount of coins entered
            if (userSelection.equals(selectedItem.getItemID()) && selectedItem.getItemInventory() > 0) {

                view.displayItemRemoved(selectedItem);
                try {
                    selectedItem = service.purchaseItem(selectedItem);
                } catch (InsufficientFundsException e) {
                    throw new RuntimeException(e);
                }
                return userSelection;
            } else if (!userSelection.equals(selectedItem.getItemID())) {
                // If user entered the wrong itemID
                view.displayUnknownCommandBanner();
            } else if (userSelection.isEmpty()) {
                // If user did not make any selection
                view.displayNoSelectionMade();
            }
        } else {
            //user has not entered any coins
            view.displayNoCoinsEntered();
        }
        return null;

    }

    /**
     * Method to return change.
     *
     * @throws VendingMachinePersistenceException If there is an issue with persistence.
     * @throws InsufficientFundsException If there are insufficient funds.
     * @throws NoItemInventoryException If there is no item inventory.
     */
    void returnChange(String userSelection) throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException {
        Change bgChange = new Change();
        VendingMachine selectedItem = service.getItem(userSelection);
        // Call a method from service layer to do a calculation
        BigDecimal userChange = service.getUserMoney();

        //determine if user entered coins
        if (userChange.compareTo(BigDecimal.ZERO) > 0) {
            //determine if user made a selection
            if (selectedItem != null) {
                //determine change due back to user, if any
                bgChange.getChange(userChange, selectedItem.getItemPrice());
                view.displayChangeDue(bgChange.getQuarter(), bgChange.getDime(), bgChange.getNickel(), bgChange.getPenny());
            } else if (selectedItem == null) {
                //user did not make a selection, but would like change back
                view.displayChangeBackWithoutPurchase();
            } else {
                //user did not make a selection AND did not enter any coins
                view.displayNoSelectionMade();
                view.displayNoCoinsEntered();
            }
        }
        view.displayExitBanner();
        System.exit(0);
    }

    /**
     * Method for unknown command.
     */
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    /**
     * Method to display exit message.
     */
    private void exitMessage() {
        view.displayExitBanner();
    }

}