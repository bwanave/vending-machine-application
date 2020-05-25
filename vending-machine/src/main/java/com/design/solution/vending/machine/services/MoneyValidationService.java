package com.design.solution.vending.machine.services;

import java.math.BigDecimal;

public interface MoneyValidationService
{
    boolean validateCash(BigDecimal cash);
}
