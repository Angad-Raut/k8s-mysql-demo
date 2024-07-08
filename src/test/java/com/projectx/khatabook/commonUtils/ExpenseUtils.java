package com.projectx.khatabook.commonUtils;

import com.projectx.khatabook.dtos.ExpenseDto;
import com.projectx.khatabook.dtos.ExpenseItemDto;
import com.projectx.khatabook.dtos.ViewExpenseItemsDto;
import com.projectx.khatabook.dtos.ViewExpensesDto;
import com.projectx.khatabook.entities.ExpenseItems;
import com.projectx.khatabook.entities.ExpensesDetails;

import java.util.*;

public class ExpenseUtils {

    public static ExpensesDetails toCreateExpenses() {
        return ExpensesDetails.builder()
                .id(1L)
                .expenseItems(toBuildItems())
                .totalAmount(calculateTotal())
                .insertedTime(new Date())
                .status(true)
                .build();
    }
    public static ExpensesDetails toCreateExpensesTwo() {
        return ExpensesDetails.builder()
                .id(2L)
                .expenseItems(toBuildItems())
                .totalAmount(calculateTotalTwo())
                .insertedTime(new Date())
                .status(true)
                .build();
    }
    private static Set<ExpenseItems> toBuildItems() {
        Set<ExpenseItems> expenseItems = new HashSet<>();
        expenseItems.add(ExpenseItems.builder()
                        .itemName("Milk")
                        .itemPrice(28.0)
                        .paymentType("Cash")
                .build());
        expenseItems.add(ExpenseItems.builder()
                .itemName("Biscuits")
                .itemPrice(20.0)
                .paymentType("Cash")
                .build());
        return expenseItems;
    }

    private static Set<ExpenseItems> toBuildItemsTwo() {
        Set<ExpenseItems> expenseItems = new HashSet<>();
        expenseItems.add(ExpenseItems.builder()
                .itemName("Sugar")
                .itemPrice(40.0)
                .paymentType("Cash")
                .build());
        expenseItems.add(ExpenseItems.builder()
                .itemName("Khari")
                .itemPrice(20.0)
                .paymentType("Cash")
                .build());
        return expenseItems;
    }
    private static Double calculateTotal() {
        return toBuildItems().stream()
                .map(ExpenseItems::getItemPrice)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
    private static Double calculateTotalTwo() {
        double total = toBuildItems().stream()
                .map(ExpenseItems::getItemPrice)
                .mapToDouble(Double::doubleValue)
                .sum();
        return total+toBuildItemsTwo().stream()
                .map(ExpenseItems::getItemPrice)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
    public static ExpenseDto toExpenseDto() {
        return ExpenseDto.builder()
                .expenseItemDtos(expenseItemDtoUpdate())
                .id(1L)
                .build();
    }
    public static Set<ExpenseItemDto> expenseItemDto() {
        Set<ExpenseItemDto> itemDtos = new HashSet<>();
        itemDtos.add(ExpenseItemDto.builder()
                        .itemName("Milk")
                        .itemPrice(28.0)
                        .paymentType("Cash")
                .build());
        itemDtos.add(ExpenseItemDto.builder()
                .itemName("Biscuits")
                .itemPrice(20.0)
                .paymentType("Cash")
                .build());
        return itemDtos;
    }
    public static Set<ExpenseItemDto> expenseItemDtoUpdate() {
        Set<ExpenseItemDto> itemDtos = new HashSet<>();
        itemDtos.add(ExpenseItemDto.builder()
                .itemName("Chocolate")
                .itemPrice(50.0)
                .paymentType("Cash")
                .build());
        return itemDtos;
    }

    public static List<ViewExpensesDto> viewExpensesList() {
        List<ViewExpensesDto> fetchList = new ArrayList<>();
        fetchList.add(ViewExpensesDto.builder()
                        .srNo(1)
                        .expenseId(1L)
                        .totalAmount(48.0)
                        .expenseDate("8 July 2024")
                .build());
        return fetchList;
    }

    public static List<ViewExpenseItemsDto> toExpenseItemsList() {
        List<ViewExpenseItemsDto> fetchList = new ArrayList<>();
        fetchList.add(ViewExpenseItemsDto.builder()
                   .srNo(1)
                   .itemName("Milk")
                   .itemPrice(28.0)
                   .paymentWith("Cash")
                .build());
        fetchList.add(ViewExpenseItemsDto.builder()
                .srNo(2)
                .itemName("Biscuits")
                .itemPrice(20.0)
                .paymentWith("Cash")
                .build());
        return fetchList;
    }
}
