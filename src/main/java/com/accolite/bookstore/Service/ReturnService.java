package com.accolite.bookstore.Service;

import com.accolite.bookstore.Model.Book;
import com.accolite.bookstore.Model.BooksUser;
import com.accolite.bookstore.Model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReturnService {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private BooksUserService booksUserService;

    @Autowired
    private TransactionService transactionService;

    public int returnBook(long userId, int bookId){
        BooksUser bu = this.booksUserService.getIssuedBook(userId,bookId);
        this.booksUserService.returnedBook(bu);

        Book book = this.bookService.getBookById(bookId);
        this.bookService.setBookRented(bu.getBookIsbn(),0);
        this.inventoryService.updateBookCount(book,1);
        Wallet w = this.walletService.addMoneyInWallet(userId,0.1*book.getBookPrice());
        this.transactionService.addTransactionByDetail(userId,w.getWalletId(),bookId,0.1*book.getBookPrice(),"Added");
        return 0;
    }
}
