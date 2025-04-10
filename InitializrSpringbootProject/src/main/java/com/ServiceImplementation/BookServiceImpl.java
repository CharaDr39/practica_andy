package com.ServiceImplementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.DAO.BookRepository;
import com.Domain.Book;
import com.Service.BookService;

@Service
public class BookServiceImpl implements BookService {

    // Inyectamos el repositorio de libros
    @Autowired
    private BookRepository bookRepository;
    
    // 1. Retorna todos los libros disponibles en la base de datos
    @Override
    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }
    
    // 2. Retorna un libro filtrado por su ID, o null si no existe
    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    
    // 3. Guarda un nuevo libro o actualiza uno existente
    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    
    // 4. Elimina un libro según su ID
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
