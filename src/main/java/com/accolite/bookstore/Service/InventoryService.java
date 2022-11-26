package com.accolite.bookstore.Service;

import com.accolite.bookstore.Model.Inventory;
import com.accolite.bookstore.Repo.BookRepo;
import com.accolite.bookstore.Repo.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class InventoryService {
    @Autowired
    private InventoryRepo inventoryRepo;

    public Inventory getBookById(long bookId){
        Optional<Inventory> inventory = this.inventoryRepo.findByBookIdEquals(bookId);
        if(inventory.isPresent()){
            return inventory.get();
        }else {
            throw new NullPointerException();
        }
    }

    public Inventory getBookByName(String bookName){
        Optional<Inventory> inventory = this.inventoryRepo.findByBookNameEquals(bookName);
        if(inventory.isPresent()){
            return inventory.get();
        }else {
            throw new NullPointerException();
        }
    }

    public Inventory addBookInInventory(Inventory inv){
        return inventoryRepo.save(inv);
    }
}
