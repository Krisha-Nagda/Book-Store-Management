package com.accolite.bookstore.Controller;

import com.accolite.bookstore.Model.Transactions;
import com.accolite.bookstore.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionsController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    private ResponseEntity<List<Transactions>> getAllTransactions(){
        return ResponseEntity.ok().body(this.transactionService.getAllTransactions());
    }

    @GetMapping("/transactions/{userId}")
    private ResponseEntity<List<Transactions>> getAllTransactionsByUser(@PathVariable long userId){
        return ResponseEntity.ok().body(this.transactionService.getAllTransactionsOfUser(userId));
    }


}
