package com.design.solution.vending.machine.models;

import java.math.BigDecimal;

public final class Item
{
    private final String code;
    private final String name;
    private final BigDecimal price;

    public Item(String code, String name, BigDecimal price)
    {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode()
    {
        return code;
    }

    public String getName()
    {
        return name;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    @Override
    public String toString()
    {
        return "Item{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
