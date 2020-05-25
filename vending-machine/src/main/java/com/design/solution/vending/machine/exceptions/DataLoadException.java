package com.design.solution.vending.machine.exceptions;

public class DataLoadException extends RuntimeException
{
    public DataLoadException(String message)
    {
        super(message);
    }

    public DataLoadException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
