package com.Domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "books")
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    private String author;
    
    // Relación con Category (muchos libros pueden pertenecer a una misma categoría)
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Otros campos que necesites (ej: estado, si está prestado, etc.)
}
