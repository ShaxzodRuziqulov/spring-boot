package com.example.demo.WebRest;

import com.example.demo.Model.Transaction;
import com.example.demo.Service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransactionResourse {
    private final TransactionService transactionService;

    public TransactionResourse(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public ResponseEntity getMsg(){
        return ResponseEntity.ok(transactionService.getAll());
    }

    @PostMapping("/transactions")
    public ResponseEntity create(@RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionService.saveExch(transaction));
    }

    @PutMapping("/transactions")
    public ResponseEntity update(@RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionService.update(transaction));
    }

}
