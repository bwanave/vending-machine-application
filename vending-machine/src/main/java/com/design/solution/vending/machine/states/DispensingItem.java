package com.design.solution.vending.machine.states;

import com.design.solution.vending.machine.SnacksVendingMachine;
import com.design.solution.vending.machine.exceptions.UnableToPerformOperationException;
import com.design.solution.vending.machine.models.Item;

import java.math.BigDecimal;

public class DispensingItem implements State
{
    private final SnacksVendingMachine vendingMachine;

    DispensingItem(SnacksVendingMachine vendingMachine)
    {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void collectCash(BigDecimal cash)
    {
        throw new UnableToPerformOperationException("Unable to collect cash. Dispensing already selected item");
    }

    @Override
    public void dispenseItem(String itemCode)
    {
        Item item = vendingMachine.getItemService().getItem(itemCode);
        if (vendingMachine.getCollectedCash().compareTo(item.getPrice()) < 0)
        {
            vendingMachine.switchState(new Ready(vendingMachine));
            throw new UnableToPerformOperationException("Unable to Dispense item. No enough cash. Insert minimum " + item.getPrice() + " INR to proceed");
        }

        BigDecimal change = vendingMachine.getCollectedCash().subtract(item.getPrice());
        if (change.compareTo(BigDecimal.ZERO) > 0)
            System.out.println("==> Dispensed change: " + change + " INR");
        System.out.println("==> Dispensed " + item);
        vendingMachine.getItemService().removeItem(item);
        vendingMachine.reset();
    }

    @Override
    public void cancelTransaction()
    {
        throw new UnableToPerformOperationException("Unable to cancel transaction. Item dispensing is in progress");
    }
}
