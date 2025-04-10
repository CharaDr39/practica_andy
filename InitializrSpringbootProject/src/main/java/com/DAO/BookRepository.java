package com.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
