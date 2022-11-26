package com.accolite.bookstore.Repo;

import com.accolite.bookstore.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepo extends JpaRepository<Inventory,Long> {
    Optional<Inventory> findByBookIdEquals(long bookId);
    Optional<Inventory> findByBookNameEquals(String bookName);
}
