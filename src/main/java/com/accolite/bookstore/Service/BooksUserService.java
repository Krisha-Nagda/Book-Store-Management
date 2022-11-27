package com.accolite.bookstore.Service;

import com.accolite.bookstore.Model.BooksUser;
import com.accolite.bookstore.Repo.BooksUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BooksUserService {

    @Autowired
    private BooksUserRepo booksUserRepo;

    public List<BooksUser> getHistory(){
        return this.booksUserRepo.findAll();
    }

    public List<BooksUser> getHistoryById(long userId){
        Optional<List<BooksUser>> booksUsersObj = this.booksUserRepo.findByUserIdEquals(userId);
        if(booksUsersObj.isPresent())
            return booksUsersObj.get();
        else
            throw new NullPointerException();
    }

    public BooksUser addBooksByDetails(int bookId,long userId,String bookName,long bookIsbn,int isReturned){
        BooksUser bu = new BooksUser();
        bu.setBookId(bookId);
        bu.setUserId(userId);
        bu.setBookIsbn(bookIsbn);
        bu.setBookName(bookName);
        bu.setIsReturned(isReturned);
        return this.booksUserRepo.save(bu);
    }

    public BooksUser getIssuedBook(long userId, int bookId){
        return this.booksUserRepo.getIssuedBook(userId,bookId);
    }

    public int alreadyIssued(long userId,int bookId){
        return this.booksUserRepo.getCountIfAlreadyIssued(userId,bookId);
    }

    public int numberOfTotalBooksIssued(long userId){
        return this.booksUserRepo.findCountOfTotalBooksIssuedByUser(userId);
    }

    public Date getLastReturnedDate(long userId, int bookId){
        return this.getLastReturnedDate(userId,bookId);
    }
    public BooksUser returnedBook(BooksUser bu){
        BooksUser buUpdate = getIssuedBook(bu.getUserId(),bu.getBookId());
        buUpdate.setIsReturned(1);
        buUpdate.setReturnedOn(new Date());
        return this.booksUserRepo.save(buUpdate);
    }
}
