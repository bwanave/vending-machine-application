package com.design.solution.vending.machine;

import com.design.solution.vending.machine.models.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachine
{
    void collectCash(BigDecimal cash);


    void dispenseItem(String itemCode);


    void cancelTransaction();


    BigDecimal getCollectedCash();


    List<Item> getAvailableItems();
}