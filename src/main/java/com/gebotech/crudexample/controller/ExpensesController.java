package com.gebotech.crudexample.controller;

import com.gebotech.crudexample.model.Expenses;
import com.gebotech.crudexample.model.request.ExpenseRequest;
import com.gebotech.crudexample.model.response.ExpenseResponse;
import com.gebotech.crudexample.model.response.TotalAmountDueResumeResponse;
import com.gebotech.crudexample.payload.response.MessageResponse;
import com.gebotech.crudexample.security.services.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/expenses")
public class ExpensesController {

    @Autowired
    ExpensesService expensesService;

    @PostMapping("/save")
    public ResponseEntity<?> saveExpense(@Valid @RequestBody ExpenseRequest request) {
        try {

            ExpenseResponse response = expensesService.saveExpense(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse("Could not save expense. Error: " + e));
        }

    }

    @PostMapping("/save-all")
    public ResponseEntity<?> saveAll(@RequestBody List<ExpenseRequest> requestList) {
        try {

            expensesService.saveAll(requestList);
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Expenses saved!"));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse("Could not save expenses. Error: " + e));
        }
    }

    @GetMapping("/monthly")
    public ResponseEntity<?> calculateMonthlyExpenses() {
        try {

            ArrayList<Expenses> expenses = expensesService.getAllUnpayedExpenses(false);
            return ResponseEntity.ok(expenses);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse("Could not get expense. Error: " + e));
        }
    }

    @GetMapping("/unpayed")
    public ResponseEntity<?> getAllUnpayedExpenses() {
        try {

            ArrayList<Expenses> expenses = expensesService.getAllUnpayedExpenses(false);
            return ResponseEntity.ok(expenses);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse("Could not get expense. Error: " + e));
        }
    }

    @GetMapping("/amount-due")
    public ResponseEntity<?> getTotalAmountDueResume() {
        try {

            TotalAmountDueResumeResponse response = expensesService.getTotalAmountDueResume();
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse("Could not get total amount due. Error: " + e));
        }
    }
}
