package com.design.solution.vending.machine.factory;

import com.design.solution.vending.machine.SnacksVendingMachine;
import com.design.solution.vending.machine.VendingMachine;
import com.design.solution.vending.machine.services.ItemService;
import com.design.solution.vending.machine.services.StaticItemService;

public class VendingMachineFactory
{
    public VendingMachine create()
    {
        ItemService itemService = new StaticItemService();
        return new SnacksVendingMachine(itemService);
    }
}
