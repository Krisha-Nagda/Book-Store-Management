package com.accolite.bookstore.Repo;

import com.accolite.bookstore.Model.BooksUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BooksUserRepo extends JpaRepository<BooksUser,Long> {
Optional<List<BooksUser>> findByUserIdEquals(long userId);

@Query(value = "Select * from booksuser where user_id = ?1 and book_id = ?2",nativeQuery = true)
BooksUser getIssuedBook(long userId,int bookId);

@Query(value = "Select count(data_id) from booksuser where user_id = ?1 and book_id = ?2 and is_returned=0" , nativeQuery = true)
int getCountIfAlreadyIssued(long userId, int bookId);

@Query(value = "Select count(data_id) from booksuser where user_id = ?1 and is_returned=0",nativeQuery = true)
int findCountOfTotalBooksIssuedByUser(long userId);

@Query(value = "Select returned_on from booksuser where user_id= ?1 and book_id= ?2 and is_returned=1",nativeQuery = true)
    Date findLastReturnedDateOfABook(long userId, int bookId);
}


