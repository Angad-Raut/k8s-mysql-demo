package com.projectx.khatabook.controllers;

import com.projectx.khatabook.commons.*;
import com.projectx.khatabook.dtos.ExpenseDto;
import com.projectx.khatabook.dtos.ExpenseItemDto;
import com.projectx.khatabook.dtos.ViewExpenseItemsDto;
import com.projectx.khatabook.dtos.ViewExpensesDto;
import com.projectx.khatabook.entities.ExpensesDetails;
import com.projectx.khatabook.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping(value = "/createExpense")
    public ResponseEntity<ResponseDto<Boolean>> createExpense(@RequestBody ExpenseDto expenseDto) {
        try {
            Boolean result = expenseService.createExpense(expenseDto);
            return new ResponseEntity<>(new ResponseDto<>(result,null,null), HttpStatus.CREATED);
        } catch (AlreadyExistsException | InvalidDataException e) {
            return new ResponseEntity<>(new ResponseDto<>(null,e.getMessage(),null), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/updateExpense")
    public ResponseEntity<ResponseDto<Boolean>> updateExpense(@RequestBody ExpenseDto expenseDto) {
        try {
            Boolean result = expenseService.updateExpense(expenseDto);
            return new ResponseEntity<>(new ResponseDto<>(result,null,null), HttpStatus.OK);
        } catch (AlreadyExistsException | InvalidDataException | ResourceNotFoundException e) {
            return new ResponseEntity<>(new ResponseDto<>(null,e.getMessage(),null), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/getExpenseById")
    public ResponseEntity<ResponseDto<ExpensesDetails>> getExpenseById(@RequestBody EntityIdDto entityIdDto) {
        try {
            ExpensesDetails result = expenseService.getById(entityIdDto);
            return new ResponseEntity<>(new ResponseDto<>(result,null,null), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ResponseDto<>(null,e.getMessage(),null), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/updateExpenseStatusById")
    public ResponseEntity<ResponseDto<Boolean>> updateExpenseStatusById(@RequestBody EntityIdDto entityIdDto) {
        try {
            Boolean result = expenseService.updateStatus(entityIdDto);
            return new ResponseEntity<>(new ResponseDto<>(result,null,null), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ResponseDto<>(null,e.getMessage(),null), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getAllExpensesOfMonth")
    public ResponseEntity<ResponseDto<List<ViewExpensesDto>>> getAllExpensesOfMonth() {
        try {
            List<ViewExpensesDto> result = expenseService.getAllExpensesOfMonths();
            return new ResponseEntity<>(new ResponseDto<>(result,null,null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto<>(null,e.getMessage(),null), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getAllExpenses")
    public ResponseEntity<ResponseDto<List<ViewExpensesDto>>> getAllExpenses() {
        try {
            List<ViewExpensesDto> result = expenseService.getAllExpenses();
            return new ResponseEntity<>(new ResponseDto<>(result,null,null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto<>(null,e.getMessage(),null), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/getExpenseItemsByExpenseId")
    public ResponseEntity<ResponseDto<List<ViewExpenseItemsDto>>> getExpenseItemsByExpenseId(@RequestBody EntityIdDto entityIdDto) {
        try {
            List<ViewExpenseItemsDto> result = expenseService.getExpensesItemsByExpenseId(entityIdDto);
            return new ResponseEntity<>(new ResponseDto<>(result,null,null), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ResponseDto<>(null,e.getMessage(),null), HttpStatus.OK);
        }
    }
}
