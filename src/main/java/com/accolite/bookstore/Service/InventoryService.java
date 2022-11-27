package com.accolite.bookstore.Service;

import com.accolite.bookstore.Model.Book;
import com.accolite.bookstore.Model.Inventory;
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

    public Inventory updateBookInInventory (Inventory inv){
        Inventory invObj = getBookById(inv.getBookId());
        invObj.setInventoryId(inv.getInventoryId());
        invObj.setBookId(inv.getBookId());
        invObj.setBookName(inv.getBookName());
        invObj.setQuantity(inv.getQuantity());
        invObj.setPrice(inv.getPrice());
        return this.inventoryRepo.save(invObj);
    }

    public void updateBookCount(Book book, int action){
        long bookId = book.getBookId();
        Optional<Inventory> inventory = this.inventoryRepo.findByBookIdEquals(bookId);

        if(inventory.isPresent()){
            Inventory invUpdate = inventory.get();
            invUpdate.setQuantity(invUpdate.getQuantity()+action);
            updateBookInInventory(invUpdate);
        }else{
            Inventory inv = new Inventory(); //tight coupling
            inv.setBookId(book.getBookId());
            inv.setBookName(book.getBookName());
            inv.setQuantity(1);
            inv.setPrice(book.getBookPrice());
            addBookInInventory(inv);
        }
    }

    public int getBookStock(long bookId){
        return getBookById(bookId).getQuantity();
    }

    public int getBookPrice(int bookId){
        return getBookById(bookId).getPrice();
    }
}
