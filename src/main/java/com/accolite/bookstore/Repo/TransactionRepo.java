package com.accolite.bookstore.Repo;

import com.accolite.bookstore.Model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends JpaRepository<Transactions,Long> {
    Optional<List<Transactions>> findByUserIdEquals(long userId);

    List<Transactions> findAllByUserIdEquals(long userId);
}
