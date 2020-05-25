package com.design.solution.vending.machine.services;

import com.design.solution.vending.machine.exceptions.DataNotFoundException;
import com.design.solution.vending.machine.models.Item;
import com.design.solution.vending.machine.util.StaticDataLoader;

import java.util.List;
import java.util.Map;

public class StaticItemService implements ItemService
{
    private final static Map<String, Item> itemMap;

    static
    {
        itemMap = StaticDataLoader.loadItems();
    }

    public List<Item> getItems()
    {
        return List.copyOf(itemMap.values());
    }

    @Override
    public Item getItem(String itemCode)
    {
        Item item = itemMap.get(itemCode);
        if (item == null)
            throw new DataNotFoundException("Invalid item code");
        return item;
    }

    @Override
    public void removeItem(Item item)
    {
        itemMap.remove(item.getCode());
    }
}
