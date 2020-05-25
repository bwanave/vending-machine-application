package com.design.solution.vending.machine.states;

import java.math.BigDecimal;

public interface State
{
    void collectCash(BigDecimal cash);

    void dispenseItem(String itemCode);

    void cancelTransaction();
}
