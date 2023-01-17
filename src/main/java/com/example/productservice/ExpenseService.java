package com.example.productservice;


import com.example.productservice.model.Expense;
import com.example.productservice.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    public void  addExpense(Expense expense){expenseRepository.insert(expense);}
    public Expense  updateExpense(Expense expense){
        Expense expenseFromDb = expenseRepository.findById(expense.getId())
                .orElseThrow(() -> new RuntimeException(String.format("Cannot find Expense by ID %s", expense.getId())));
        expenseFromDb.setExpenseName(expense.getExpenseName());
        expenseFromDb.setExpenseAmount(expense.getExpenseAmount());
        expenseFromDb.setExpenseCategory(expense.getExpenseCategory());
        return expenseRepository.save(expenseFromDb);
    }
    public List<Expense> getAllExpense(){return expenseRepository.findAll();}
    public Expense  getExpenseBuName(String name){
        return expenseRepository.findByName(name).orElseThrow(() -> new RuntimeException(String.format("Expense not found with NAME %s", name)));
    }
    public void  deleteExpense(String id){
        expenseRepository.deleteById(id);
    }
}
