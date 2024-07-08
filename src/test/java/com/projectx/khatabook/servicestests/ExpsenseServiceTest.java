package com.projectx.khatabook.servicestests;

import com.projectx.khatabook.commonUtils.ExpenseUtils;
import com.projectx.khatabook.commons.Constants;
import com.projectx.khatabook.commons.EntityIdDto;
import com.projectx.khatabook.dtos.ExpenseDto;
import com.projectx.khatabook.dtos.ExpenseItemDto;
import com.projectx.khatabook.entities.ExpensesDetails;
import com.projectx.khatabook.repositories.ExpensesRepository;
import com.projectx.khatabook.services.ExpenseService;
import com.projectx.khatabook.services.ExpenseServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ExpsenseServiceTest {

    @Mock
    private ExpensesRepository expensesRepository;

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createExpenseTest() {
        when(expensesRepository.expenseExists(Constants.atStartOfDay(),Constants.atEndOfDay())).thenReturn(0);
        when(expensesRepository.save(ExpenseUtils.toCreateExpenses())).thenReturn(ExpenseUtils.toCreateExpenses());
        ExpenseDto expenseDto = ExpenseUtils.toExpenseDto();
        expenseDto.setId(null);
        Boolean message = expenseService.createExpense(expenseDto);
        assertFalse(message);

    }

    @Test
    public void updateExpenseTest() {
        when(expensesRepository.save(ExpenseUtils.toCreateExpenses())).thenReturn(ExpenseUtils.toCreateExpenses());
        when(expensesRepository.getExpensesById(1L)).thenReturn(ExpenseUtils.toCreateExpenses());
        when(expensesRepository.existsByExpenseName(1L,"Milk")).thenReturn(0);
        Boolean message = expenseService.updateExpense(ExpenseUtils.toExpenseDto());
        assertFalse(message);
    }

    @Test
    public  void getByIdTest() {
        when(expensesRepository.save(ExpenseUtils.toCreateExpenses())).thenReturn(ExpenseUtils.toCreateExpenses());
        when(expensesRepository.getExpensesById(1L)).thenReturn(ExpenseUtils.toCreateExpenses());
        ExpensesDetails details = expenseService.getById(EntityIdDto.builder().entityId(1L).build());
        assertEquals(ExpenseUtils.toCreateExpenses().getTotalAmount(),details.getTotalAmount());
    }

    @Test
    public void getAllExpensesOfMonthsTest() {
        when(expensesRepository.save(ExpenseUtils.toCreateExpenses())).thenReturn(ExpenseUtils.toCreateExpenses());
        when(expensesRepository.save(ExpenseUtils.toCreateExpensesTwo())).thenReturn(ExpenseUtils.toCreateExpensesTwo());
        when(expensesRepository.getMonthAllExpenses(Constants.firstDayOfMonth(),Constants.lastDayOfMonth())).thenReturn(new ArrayList<Object[]>());
        assertNotNull(expenseService.getAllExpensesOfMonths());
    }

    @Test
    public void getAllExpensesTest() {
        when(expensesRepository.save(ExpenseUtils.toCreateExpenses())).thenReturn(ExpenseUtils.toCreateExpenses());
        when(expensesRepository.save(ExpenseUtils.toCreateExpensesTwo())).thenReturn(ExpenseUtils.toCreateExpensesTwo());
        when(expensesRepository.getAllExpenses()).thenReturn(new ArrayList<Object[]>());
        assertNotNull(expenseService.getAllExpenses());
    }

    public void getExpensesItemsByExpenseIdTest() {
        when(expensesRepository.save(ExpenseUtils.toCreateExpenses())).thenReturn(ExpenseUtils.toCreateExpenses());
        when(expensesRepository.getExpensesById(1L)).thenReturn(ExpenseUtils.toCreateExpenses());
        when(expensesRepository.getExpenseItemsByExpenseId(1L)).thenReturn(new ArrayList<Object[]>());
        assertNotNull(expenseService.getExpensesItemsByExpenseId(new EntityIdDto(1L)));
    }

    @Test
    public void updateStatusTest() {
        when(expensesRepository.save(ExpenseUtils.toCreateExpenses())).thenReturn(ExpenseUtils.toCreateExpenses());
        when(expensesRepository.getExpensesById(1L)).thenReturn(ExpenseUtils.toCreateExpenses());
        when(expensesRepository.updateStatus(1L,false)).thenReturn(1);
        assertNotNull(expenseService.updateStatus(new EntityIdDto(1L)));
    }
}
