package com.design.solution.vending.machine;

import com.design.solution.vending.machine.factory.VendingMachineFactory;
import com.design.solution.vending.machine.models.Item;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class ConsoleInterface
{
    public static void main(String[] args)
    {
        VendingMachineFactory factory = new VendingMachineFactory();
        VendingMachine vendingMachine = factory.create();
        Scanner scanner = new Scanner(System.in);
        int option;
        do
        {
            try
            {
                System.out.println(drawMachine(vendingMachine));
                System.out.println(createMenu());
                System.out.print("Enter option: ");
                option = scanner.nextInt();
                executeMenuOperation(vendingMachine, scanner, option);
            }
            catch (Exception e)
            {
                System.err.println("Input error: " + e.toString());
                option = -1;
            }
        } while (option != 0);
    }

    private static String drawMachine(VendingMachine vendingMachine)
    {
        String items = vendingMachine.getAvailableItems()
                .stream()
                .map(Item::toString)
                .collect(Collectors.joining("\n\t"));
        StringJoiner machine = new StringJoiner("\n", "\n", "\n");
        machine.add("########## VENDING MACHINE ##########");
        machine.add("---------------------------------------");
        machine.add("Available items:\n\t" + items);
        machine.add("Collected cash: " + vendingMachine.getCollectedCash());
        return machine.toString();
    }

    private static String createMenu()
    {
        StringJoiner menu = new StringJoiner("\n", "Menu\n", "\n");
        menu.add("1. Insert cash");
        menu.add("2. Select item");
        menu.add("3. Cancel transaction");
        menu.add("4. Exit");
        return menu.toString();
    }

    private static void executeMenuOperation(VendingMachine vendingMachine, Scanner scanner, int option)
    {
        switch (option)
        {
            case 1:
                System.out.print("Enter cash: ");
                int cash = scanner.nextInt();
                System.out.println();
                vendingMachine.collectCash(BigDecimal.valueOf(cash));
                break;

            case 2:
                System.out.print("Enter item code: ");
                String itemCode = scanner.next();
                System.out.println();
                vendingMachine.dispenseItem(itemCode);
                break;

            case 3:
                System.out.println();
                vendingMachine.cancelTransaction();
                break;

            default:
                System.err.println("Invalid option: " + option);
        }
    }
}
