package com.projectx.khatabook.services;

import com.projectx.khatabook.commons.AlreadyExistsException;
import com.projectx.khatabook.commons.EntityIdDto;
import com.projectx.khatabook.commons.InvalidDataException;
import com.projectx.khatabook.commons.ResourceNotFoundException;
import com.projectx.khatabook.dtos.ExpenseDto;
import com.projectx.khatabook.dtos.ExpenseItemDto;
import com.projectx.khatabook.dtos.ViewExpenseItemsDto;
import com.projectx.khatabook.dtos.ViewExpensesDto;
import com.projectx.khatabook.entities.ExpensesDetails;

import java.util.List;
import java.util.Set;

public interface ExpenseService {
    Boolean createExpense(ExpenseDto expenseDto)throws AlreadyExistsException,
            InvalidDataException;
    Boolean updateExpense(ExpenseDto expenseDto)throws AlreadyExistsException,
            ResourceNotFoundException,InvalidDataException;
    ExpensesDetails getById(EntityIdDto entityIdDto)throws ResourceNotFoundException;
    List<ViewExpensesDto> getAllExpensesOfMonths();
    List<ViewExpensesDto> getAllExpenses();
    List<ViewExpenseItemsDto> getExpensesItemsByExpenseId(EntityIdDto entityIdDto)throws ResourceNotFoundException;
    Boolean updateStatus(EntityIdDto entityIdDto)throws ResourceNotFoundException;
}
