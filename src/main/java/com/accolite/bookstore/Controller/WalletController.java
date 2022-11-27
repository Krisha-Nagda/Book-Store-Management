package com.accolite.bookstore.Controller;

import com.accolite.bookstore.Model.Wallet;
import com.accolite.bookstore.Service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;

@RestController
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping("/wallet/{userId}")
    private double getUserBalance(@PathVariable long userId){
        return this.walletService.checkBalance(userId);
    }

    @PutMapping("/wallet/add/{userId}")
    private ResponseEntity<Wallet> addMoneyInWallet(@PathVariable long userId, @RequestBody Wallet wallet){
        if(wallet.getWalletAmount() < 500)
            throw new NullPointerException();
        else
            return ResponseEntity.ok().body(this.walletService.addMoneyInWalletByAPi(userId,wallet.getWalletAmount()));
    }

    @PutMapping("/wallet/deduct/{userId}")
    private ResponseEntity<Wallet> deductMoneyInWallet(@PathVariable long userId, @RequestBody Wallet wallet){
        return ResponseEntity.ok().body(this.walletService.deductMoneyInWallet(userId,wallet.getWalletAmount()));
    }

}
