package com.design.solution.vending.machine;

import com.design.solution.vending.machine.services.StaticItemService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class SnacksVendingMachineTest
{
    private static SnacksVendingMachine vendingMachine;

    @BeforeAll
    static void beforeAll()
    {
        vendingMachine = new SnacksVendingMachine(new StaticItemService());
    }

    @Test
    void collectValidCashTest()
    {
        BigDecimal oneHundred = BigDecimal.valueOf(100);
        vendingMachine.collectCash(oneHundred);
        Assertions.assertEquals(oneHundred, vendingMachine.getCollectedCash());
        BigDecimal twoHundred = BigDecimal.valueOf(200);
        vendingMachine.collectCash(twoHundred);
        Assertions.assertEquals(BigDecimal.valueOf(300), vendingMachine.getCollectedCash());
    }

    @Test
    void dispenseItemTest()
    {
        vendingMachine.collectCash(BigDecimal.valueOf(30));
        vendingMachine.dispenseItem("P-01");
        Assertions.assertEquals(2, vendingMachine.getAvailableItems().size());
        vendingMachine.collectCash(BigDecimal.valueOf(50));
        vendingMachine.dispenseItem("CC-01");
        Assertions.assertEquals(1, vendingMachine.getAvailableItems().size());
    }

    @Test
    void cancelTransaction()
    {
        vendingMachine.collectCash(BigDecimal.valueOf(100));
        vendingMachine.cancelTransaction();
        Assertions.assertEquals(BigDecimal.ZERO, vendingMachine.getCollectedCash());
    }
    
    @AfterEach
    void afterEach()
    {
        vendingMachine.reset();
    }
}
