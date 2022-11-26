package com.accolite.bookstore.Repo;

import com.accolite.bookstore.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
