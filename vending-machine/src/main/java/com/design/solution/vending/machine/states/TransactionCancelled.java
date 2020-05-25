package com.design.solution.vending.machine.states;

import com.design.solution.vending.machine.SnacksVendingMachine;
import com.design.solution.vending.machine.exceptions.UnableToPerformOperationException;

import java.math.BigDecimal;

public class TransactionCancelled implements State
{
    private final SnacksVendingMachine vendingMachine;

    TransactionCancelled(SnacksVendingMachine vendingMachine)
    {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void collectCash(BigDecimal cash)
    {
        throw new UnableToPerformOperationException("Unable to collect cash. Transaction has been cancelled");
    }

    @Override
    public void dispenseItem(String itemCode)
    {
        throw new UnableToPerformOperationException("Unable to dispense item. Transaction has been cancelled");
    }

    @Override
    public void cancelTransaction()
    {
        BigDecimal collectedCash = vendingMachine.getCollectedCash();
        if (collectedCash.compareTo(BigDecimal.ZERO) > 0)
        {
            System.out.println("Transaction cancelled");
            System.out.println("==> Refunded " + collectedCash + " INR");
            vendingMachine.reset();
        }
        else
        {
            vendingMachine.reset();
            throw new UnableToPerformOperationException("Transaction not initiated");
        }
    }
}
