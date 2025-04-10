package com.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Domain.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    // ...
}
