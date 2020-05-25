package com.design.solution.vending.machine;

import com.design.solution.vending.machine.models.Item;
import com.design.solution.vending.machine.services.ItemService;
import com.design.solution.vending.machine.states.Ready;
import com.design.solution.vending.machine.states.State;

import java.math.BigDecimal;
import java.util.List;

public class SnacksVendingMachine implements VendingMachine
{
    private State state;
    private BigDecimal collectedCash;
    private final ItemService itemService;

    public SnacksVendingMachine(ItemService itemService)
    {
        this.state = new Ready(this);
        this.collectedCash = BigDecimal.ZERO;
        this.itemService = itemService;
    }

    @Override
    public void collectCash(BigDecimal cash)
    {
        state.collectCash(cash);
    }

    @Override
    public void dispenseItem(String itemCode)
    {
        state.dispenseItem(itemCode);
    }

    @Override
    public void cancelTransaction()
    {
        state.cancelTransaction();
    }

    @Override
    public BigDecimal getCollectedCash()
    {
        return collectedCash;
    }

    @Override
    public List<Item> getAvailableItems()
    {
        return itemService.getItems();
    }


    /* Internal operations */

    public ItemService getItemService()
    {
        return itemService;
    }

    public void switchState(State newState)
    {
        this.state = newState;
    }

    public void addCash(BigDecimal cash)
    {
        this.collectedCash = this.collectedCash.add(cash);
    }

    public void reset()
    {
        this.collectedCash = BigDecimal.ZERO;
        this.state = new Ready(this);
    }
}