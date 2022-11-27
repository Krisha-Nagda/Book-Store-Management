package com.accolite.bookstore.Service;

import com.accolite.bookstore.Model.Book;
import com.accolite.bookstore.Model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class BuyService {
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

    public int buyBook(long userId, int bookId){
        if(this.userService.isUserSuspended(userId) == 0){
//            Check if same book is issued by same user already
            if(this.booksUserService.alreadyIssued(userId,bookId) > 0)
                return 0;

//             Check count of books issued
            if(this.booksUserService.numberOfTotalBooksIssued(userId) >= 3)
                return 1;

//            Check if returned today
//            Date date = this.booksUserService.getLastReturnedDate(userId,bookId).
//            if(.compareTo())

//            Check if enough money in wallet
            int bookPrice = this.inventoryService.getBookPrice(bookId);
            double walletAmount = this.walletService.checkBalance(userId);
            if((0.2*bookPrice) > walletAmount)
                return 2;

//              Check if Book is in stock
            int stock = this.inventoryService.getBookStock(bookId);
            if(stock <= 0)
                return 3;

            Book b = this.bookService.getAvailableBook(bookId);
            this.bookService.setBookRented(b.getBookIsbn(),1);
            this.booksUserService.addBooksByDetails(bookId,userId,b.getBookName(),b.getBookIsbn(),0);
            this.inventoryService.updateBookCount(b,-1);
            Wallet w = this.walletService.deductMoneyInWallet(userId,0.2*b.getBookPrice());
            this.transactionService.addTransactionByDetail(userId,w.getWalletId(),bookId,0.2*b.getBookPrice(),"deducted");
            return 4;
        }else{
            return 100;
        }
    }
}
