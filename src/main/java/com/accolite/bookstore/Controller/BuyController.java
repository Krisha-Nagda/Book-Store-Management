package com.accolite.bookstore.Controller;

import com.accolite.bookstore.Model.Book;
import com.accolite.bookstore.Service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyController {

    @Autowired
    private BuyService buyService;

    @PutMapping("/buy/{userId}/{bookId}")
    private String buyBooks(@PathVariable long userId, @PathVariable int bookId){
        int response = this.buyService.buyBook(userId,bookId);
        if(response == 0){
            return "Already Issued this book";
        }else if(response == 1){
            return "Cannot issue more than 3 books";
        }else if(response == 2){
            return "Not enough money in wallet, please recharge";
        }else if(response == 3){
            return "Book Out of stock, please try later";
        }else if(response == 100){
            return "User is suspended";
        }
        else{
            return "Book issued";
        }
    }

}
