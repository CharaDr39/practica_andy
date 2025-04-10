package com.ServiceImplementation;  

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.DAO.LoanRepository;
import com.Domain.Loan;
import com.Service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

    // Inyectamos el repositorio de préstamos
    @Autowired
    private LoanRepository loanRepository;
    
    // 1. Retorna la lista de todos los préstamos registrados
    @Override
    public List<Loan> listAllLoans() {
        return loanRepository.findAll();
    }
    
    // 2. Retorna un préstamo filtrado por su ID, o null si no existe
    @Override
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElse(null);
    }
    
    // 3. Guarda un nuevo préstamo en la base de datos
    @Override
    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }
    
    // 4. Actualiza un préstamo existente (por ejemplo, al registrar la fecha de devolución)
    @Override
    public Loan updateLoan(Loan loan) {
        // La actualización se realiza mediante el método save() ya que JPA gestiona las entidades actualizadas
        return loanRepository.save(loan);
    }
    
    // 5. Elimina un préstamo dado su ID
    @Override
    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }
}
