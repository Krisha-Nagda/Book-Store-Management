package com.accolite.bookstore.Controller;

import com.accolite.bookstore.Model.BooksUser;
import com.accolite.bookstore.Service.BooksUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BooksUserController {

    @Autowired
    private BooksUserService booksUserService;

    @GetMapping("history/{userId}")
    private ResponseEntity<List<BooksUser>> getHistoryOfUser(@PathVariable long userId){
        return ResponseEntity.ok().body(this.booksUserService.getHistoryById(userId));
    }

    @GetMapping("/history")
    private ResponseEntity<List<BooksUser>> getHistory(){
        return ResponseEntity.ok().body(this.booksUserService.getHistory());
    }
}
