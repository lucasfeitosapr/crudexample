package com.gebotech.crudexample.security.services;

import com.gebotech.crudexample.model.Expenses;
import com.gebotech.crudexample.model.User;
import com.gebotech.crudexample.model.request.ExpenseRequest;
import com.gebotech.crudexample.model.response.ExpenseResponse;
import com.gebotech.crudexample.model.response.TotalAmountDueResumeResponse;
import com.gebotech.crudexample.repository.ExpensesRepository;
import com.gebotech.crudexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpensesService {

    @Autowired
    private ExpensesRepository expensesRepository;

    @Autowired
    private UserRepository userRepository;

    public ExpenseResponse saveExpense(ExpenseRequest request) {
        User user = userRepository.findById(request.getUserId()).get();
        Expenses expenseToSave = Expenses.toEntity(request);
        expenseToSave.setUser(user);

        Expenses savedExpense = expensesRepository.save(expenseToSave);
        ExpenseResponse response = Expenses.toResponse(savedExpense);

        return response;
    }

    public void saveAll(List<ExpenseRequest> requestList) {

        List<Expenses> expensesToSave = new ArrayList<>();

        //@TODO: Change this for multiple users or just accept same user. DO NOT SEND ANOTHER USER_ID, THINGS WILL MESS UP.
        User user = userRepository.findById(requestList.get(0).getUserId()).get();

        for (ExpenseRequest request: requestList) {
            Expenses expense = Expenses.toEntity(request);
            expense.setUser(user);
            expensesToSave.add(expense);
        }

        expensesRepository.saveAll(expensesToSave);

    }

    public ArrayList<Expenses> getAllUnpayedExpenses(Boolean unpayed) {

        return expensesRepository.getExpensesByPayed(unpayed);
    }

    //@TODO: Break this in two steps: paid and unpaid.
    public TotalAmountDueResumeResponse getTotalAmountDueResume() {

        List<Expenses> unpaidExpenses = expensesRepository.getExpensesByPayed(false);
        List<Expenses> paidExpenses = expensesRepository.getExpensesByPayed(true);

        HashMap<String, BigDecimal> unpaidNames = new HashMap<>();
        HashMap<String, BigDecimal> paid = new HashMap<>();

        BigDecimal totalAmountDue = new BigDecimal(0);
        BigDecimal totalPaid = new BigDecimal(0);

        for (Expenses expense: unpaidExpenses) {
            totalAmountDue = totalAmountDue.add(expense.getValue());
            unpaidNames.put(expense.getName(), expense.getValue());
        }

        for (Expenses expense: paidExpenses) {
            totalPaid = totalPaid.add(expense.getValue());
            paid.put(expense.getName(), expense.getValue());
        }

        return TotalAmountDueResumeResponse.builder()
                .totalDue(totalAmountDue)
                .totalPaid(totalPaid)
                .paid(paid)
                .unpaid(unpaidNames).build();

    }
}
