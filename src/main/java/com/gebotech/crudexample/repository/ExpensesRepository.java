package com.gebotech.crudexample.repository;

import com.gebotech.crudexample.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

    ArrayList<Expenses> getExpensesByPayed(Boolean payed);
}
