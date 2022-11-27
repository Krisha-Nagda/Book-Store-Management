package com.accolite.bookstore.Model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booksuser")
public class BooksUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dataId;
    private int bookId;
    private long userId;
    private String bookName;
    private long bookIsbn;
    private int isReturned;

    @CreationTimestamp
    private Date issuedOn;

    private Date returnedOn;

    public int getBookId() {
        return bookId;
    }

    public long getId() {
        return dataId;
    }

    public void setId(long id) {
        this.dataId = id;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public long getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(long bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public int getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(int isReturned) {
        this.isReturned = isReturned;
    }

    public Date getReturnedOn() {
        return returnedOn;
    }

    public void setReturnedOn(Date returnedOn) {
        this.returnedOn = returnedOn;
    }
}
