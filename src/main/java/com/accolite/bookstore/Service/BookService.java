package com.accolite.bookstore.Service;

import com.accolite.bookstore.Model.Book;
import com.accolite.bookstore.Model.Inventory;
import com.accolite.bookstore.Repo.BookRepo;
import com.accolite.bookstore.Repo.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private InventoryService inventoryService;

    public List<Book> getBooks(){
        return this.bookRepo.findAll();
    };

    public Book getBookByIsbn(long bookIsbn){
        Optional<Book> bookObj = this.bookRepo.findByBookIsbnEquals(bookIsbn);

        if(bookObj.isPresent()){
            return bookObj.get();
        }else{
            throw new NullPointerException();
        }
    };

    public Book getBookById(int bookId){
        Optional<Book> bookObj = this.bookRepo.findByBookIdEquals(bookId);

        if(bookObj.isPresent()){
            return bookObj.get();
        }else{
            throw new NullPointerException();
        }
    };



    public Book addBook(Book book){
        this.inventoryService.updateBookCount(book,1);
        return bookRepo.save(book);
    }

    public Book updateBook (Book book){
        Book bookUpdate = getBookByIsbn(book.getBookIsbn());
        bookUpdate.setBookIsbn(book.getBookIsbn());
        bookUpdate.setBookId(book.getBookId());
        bookUpdate.setBookName(book.getBookName());
        bookUpdate.setBookAuthor(book.getBookAuthor());
        bookUpdate.setBookCategory(book.getBookCategory());
        bookUpdate.setBookPrice(book.getBookPrice());
        return this.bookRepo.save(bookUpdate);
    }



    public Book getAvailableBook(int bookId){
        return this.bookRepo.findAvailableBook(bookId);
    }

    public void setBookRented(long bookIsbn, int action){
        getBookByIsbn(bookIsbn).setIsRented(action);
    }
}
