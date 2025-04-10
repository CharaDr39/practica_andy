package com.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import com.Domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // ...
}
