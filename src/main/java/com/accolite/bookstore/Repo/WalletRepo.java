package com.accolite.bookstore.Repo;

import com.accolite.bookstore.Model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepo extends JpaRepository<Wallet,Long> {
    Optional<Wallet> findByUserIdEquals(long userId);
}
