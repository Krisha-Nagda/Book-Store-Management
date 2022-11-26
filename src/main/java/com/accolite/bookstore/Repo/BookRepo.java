package com.accolite.bookstore.Repo;

import com.accolite.bookstore.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book,Long> {
}
