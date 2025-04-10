package com.Service;

import java.util.List;
import com.Domain.Loan;

public interface LoanService {

    // 1. Método para obtener todos los préstamos registrados
    List<Loan> listAllLoans();
    
    // 2. Método para obtener un préstamo por su ID
    Loan getLoanById(Long id);
    
    // 3. Método para registrar un nuevo préstamo
    Loan saveLoan(Loan loan);
    
    // 4. Método para actualizar un préstamo (por ejemplo, al registrar una devolución)
    Loan updateLoan(Loan loan);
    
    // 5. Método para eliminar un préstamo por su ID
    void deleteLoan(Long id);
}
