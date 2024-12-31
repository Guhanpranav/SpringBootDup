package com.example.banking.controller;


import com.example.banking.entity.Transaction;
import com.example.banking.entity.User;
import com.example.banking.repo.TransactionRepo;
import com.example.banking.repo.UserRepo;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    TransactionRepo tRep;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserController uc;

    @PostMapping("/transfer")
    @Transactional
    public ResponseEntity<String> makeTransaction(@ModelAttribute Transaction transaction, HttpSession ss){
        // System.out.println(ss.getAttribute("currentUserId"));
        int senderId = (int) ss.getAttribute("currentUserId");
        Optional<User> sender = userRepo.findById(senderId);
        if(sender.isEmpty()){
            return ResponseEntity.badRequest().body("Sender is not valid or not found");
        }

        Optional<User> receiver = userRepo.findByAccNo(transaction.getReceiverAccNumber());

        long senderBalance = sender.get().getBalance();
        long receiverBalance = receiver.get().getBalance();

        long transferAmount = transaction.getAmount();

        if((senderBalance - transferAmount) < 0){
            return ResponseEntity.badRequest().body("Insufficient Balance");
        }
        sender.get().setBalance(senderBalance - transferAmount);
        receiver.get().setBalance(receiverBalance + transferAmount);

        Transaction Sendertrans = new Transaction();
        Sendertrans.setAmount(-transferAmount);
        Sendertrans.setReceiverAccNumber(transaction.getReceiverAccNumber());
        Sendertrans.setUser(sender.get());
        List<Transaction> l1 = sender.get().getTransactions();
        l1.add(Sendertrans);
        sender.get().setTransactions(l1);

        Transaction receivertrans = new Transaction();
        receivertrans.setAmount(transferAmount);
        receivertrans.setReceiverAccNumber(sender.get().getAccNo());
        receivertrans.setUser(receiver.get());
        List<Transaction> l2 = receiver.get().getTransactions();
        l2.add(receivertrans);
        receiver.get().setTransactions(l2);

        tRep.save(Sendertrans);
        tRep.save(receivertrans);

        userRepo.save(sender.get());
        userRepo.save(receiver.get());


        return ResponseEntity.ok("Transaction successful !!!!");

    }
}
