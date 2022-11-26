package com.accolite.bookstore.Controller;

import com.accolite.bookstore.Model.Inventory;
import com.accolite.bookstore.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/books/id/{bookId}")
    private Inventory getBookById(@PathVariable long bookId){
        return this.inventoryService.getBookById(bookId);
    }

    @GetMapping("/books/name/{bookName}")
    private Inventory getBookByName(@PathVariable String bookName){
        return this.inventoryService.getBookByName(bookName);
    }

    @PostMapping("/books")
    private ResponseEntity<Inventory> addBook(@RequestBody Inventory inv){
        return ResponseEntity.ok().body(this.inventoryService.addBookInInventory(inv));
    }

}
