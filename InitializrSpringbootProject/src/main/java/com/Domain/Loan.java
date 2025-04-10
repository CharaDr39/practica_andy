package com.Domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con el libro que se está prestando
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    // Relación con el usuario que realiza el préstamo
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate loanDate;
    private LocalDate returnDate;

    // Podrías añadir un estado de "devuelto" o similar
}
