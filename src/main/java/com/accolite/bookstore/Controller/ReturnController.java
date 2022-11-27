package com.accolite.bookstore.Controller;

import com.accolite.bookstore.Service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReturnController {
    @Autowired
    private ReturnService returnService;

    @PutMapping("return/{userId}/{bookId}")
    private String returnBook(@PathVariable long userId, @PathVariable int bookId){
        int response = this.returnService.returnBook(userId,bookId);
        return "Returned";
    }
}
