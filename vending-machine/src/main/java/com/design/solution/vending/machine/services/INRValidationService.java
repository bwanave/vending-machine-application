package com.design.solution.vending.machine.services;

import java.math.BigDecimal;

public class INRValidationService implements MoneyValidationService
{
    @Override
    public boolean validateCash(BigDecimal cash)
    {
        boolean inRange = cash.compareTo(BigDecimal.valueOf(10)) >= 0 && cash.compareTo(BigDecimal.valueOf(500)) <= 0;
        if (inRange)
            return cash.remainder(BigDecimal.valueOf(10)).equals(BigDecimal.ZERO);
        return false;
    }
}
