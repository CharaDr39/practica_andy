package com.Service;

import java.util.List;
import com.Domain.Book;

public interface BookService {

    // 1. Método para obtener la lista completa de libros
    List<Book> listAllBooks();
    
    // 2. Método para obtener un libro por su ID
    Book getBookById(Long id);
    
    // 3. Método para guardar o actualizar un libro
    Book saveBook(Book book);
    
    // 4. Método para eliminar un libro por su ID
    void deleteBook(Long id);
}
