package com.gebotech.crudexample.controller;

import com.gebotech.crudexample.model.NotaFiscalMessagem;
import com.gebotech.crudexample.security.services.ExpensesService;
import com.gebotech.crudexample.security.services.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    MessageBuilder messageBuilder;

    @Autowired
    ExpensesService expensesService;

    @GetMapping("/all")
    public String allAccess() {

        return "Public Content. " + LocalDateTime.now();
    }

    @GetMapping("/nf/description")
    public String nfDescription(@RequestBody NotaFiscalMessagem menssagem) {

        String fullDescription = messageBuilder.buildMessage(menssagem);

        return fullDescription;
    }

//    @GetMapping("/expenses/{salary}")
//    public Expenses calculateMonthlyExpenses(@RequestParam Float salary, @RequestBody Expenses expenses) {
//
//        return expensesService.calculateMonthlyExpenses(salary, expenses);
//
//    }

//    @GetMapping("/expenses/brunashalf")
//    public Expenses calculateBrunasHalf(@RequestBody Expenses expenses) {
//
//        return expensesService.brunasHalf(expenses);
//
//    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAcess() {
        return "User content";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
