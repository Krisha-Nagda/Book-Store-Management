package com.accolite.bookstore.Repo;

import com.accolite.bookstore.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepo extends JpaRepository<Book,Long> {
    Optional<Book> findByBookIsbnEquals(long isbn);

    @Query(value = "Select * from book where book_id = ?1 Limit 1",nativeQuery = true)
    Optional<Book> findByBookIdEquals(int id);

    @Query(value = "SELECT * FROM book b WHERE b.book_id = ?1 and b.is_rented = 0 LIMIT 1", nativeQuery = true)
    Book findAvailableBook(int bookId);
}
