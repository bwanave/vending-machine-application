package com.design.solution.vending.machine.util;

import com.design.solution.vending.machine.exceptions.DataLoadException;
import com.design.solution.vending.machine.models.Item;

import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class StaticDataLoader
{
    private StaticDataLoader()
    {
    }

    public static Map<String, Item> loadItems()
    {
        try
        {
            String staticItemsFile = "items.txt";
            URL resource = StaticDataLoader.class.getClassLoader().getResource(staticItemsFile);
            if (resource == null)
                throw new DataLoadException("Resource: " + staticItemsFile + " not found");

            Path filePath = Paths.get(resource.getPath());
            return Files.lines(filePath)
                    .skip(2)
                    .map(StaticDataLoader::toItem)
                    .collect(toMap(Item::getCode, Function.identity(), (oldValue, newValue) -> newValue));
        }
        catch (Exception e)
        {
            throw new DataLoadException("Failed to load static items", e);
        }
    }

    private static Item toItem(String line)
    {
        String[] attributes = line.split("\\|");
        String itemCode = attributes[0].trim();
        String itemName = attributes[1].trim();
        BigDecimal itemPrice = new BigDecimal(attributes[2].trim());
        return new Item(itemCode, itemName, itemPrice);
    }
}
