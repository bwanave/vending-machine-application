package com.design.solution.vending.machine.services;

import com.design.solution.vending.machine.models.Item;

import java.util.List;

public interface ItemService
{
    List<Item> getItems();

    Item getItem(String itemCode);

    void removeItem(Item item);
}
