package com.design.solution.vending.machine.states;

import com.design.solution.vending.machine.SnacksVendingMachine;
import com.design.solution.vending.machine.exceptions.UnableToPerformOperationException;
import com.design.solution.vending.machine.services.INRValidationService;
import com.design.solution.vending.machine.services.MoneyValidationService;

import java.math.BigDecimal;

public class Ready implements State
{
    private final SnacksVendingMachine vendingMachine;
    private final MoneyValidationService moneyValidationService;

    public Ready(SnacksVendingMachine vendingMachine)
    {
        this.vendingMachine = vendingMachine;
        this.moneyValidationService = new INRValidationService();
    }

    @Override
    public void collectCash(BigDecimal cash)
    {
        if (moneyValidationService.validateCash(cash))
            vendingMachine.addCash(cash);
        else
            throw new UnableToPerformOperationException("Invalid cash. Amount should be in table of 10 and between 10 and 500. E.g: 10, 20, 30 etc.");
    }

    @Override
    public void dispenseItem(String itemCode)
    {
        vendingMachine.switchState(new DispensingItem(vendingMachine));
        vendingMachine.dispenseItem(itemCode);
    }

    @Override
    public void cancelTransaction()
    {
        vendingMachine.switchState(new TransactionCancelled(vendingMachine));
        vendingMachine.cancelTransaction();
    }
}
