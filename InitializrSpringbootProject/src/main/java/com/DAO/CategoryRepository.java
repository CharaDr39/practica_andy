package com.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
