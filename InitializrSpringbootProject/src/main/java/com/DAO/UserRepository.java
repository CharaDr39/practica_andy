package com.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
