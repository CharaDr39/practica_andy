package com.Controllers;

// 8.2.2.2: Importar las clases necesarias para el controlador, inyección de dependencias y manejo de vistas
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.Domain.Book;
import com.Service.BookService;
import java.util.List;

// 8.2.2.3: Anotar la clase con @Controller y establecer la ruta base para este controlador
@Controller
@RequestMapping("/admin/books")
public class BookController {

    // 8.2.2.4: Inyectar el servicio de libros para acceder a la lógica de negocio
    @Autowired
    private BookService bookService;

    // 8.2.2.5: Método para listar todos los libros
    // Se mapea la ruta "/admin/books/" que mostrará la lista de libros
    @GetMapping("/")
    public String listBooks(Model model) {
        // 8.2.2.6: Invocar el servicio para obtener la lista completa de libros
        List<Book> books = bookService.listAllBooks();
        // 8.2.2.7: Agregar la lista obtenida al objeto Model para pasarla a la vista
        model.addAttribute("books", books);
        // 8.2.2.8: Retornar el nombre de la vista; se buscará el archivo "books.html" dentro de la carpeta "templates/admin/"
        return "admin/books";
    }

    // 8.2.2.9: Método para mostrar el formulario de creación de un nuevo libro
    // Se mapea la ruta "/admin/books/new" para desplegar el formulario
    @GetMapping("/new")
    public String newBookForm(Model model) {
        // 8.2.2.10: Crear un objeto Book vacío y agregarlo al modelo para rellenar el formulario
        model.addAttribute("book", new Book());
        // 8.2.2.11: Retornar el nombre de la vista con el formulario, buscando "book_form.html" en "templates/admin/"
        return "admin/book_form";
    }

    // 8.2.2.12: Método para procesar y guardar el libro enviado desde el formulario
    // Se mapea la ruta "/admin/books/save" y se utiliza el método POST para enviar los datos
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        // 8.2.2.13: Invocar el servicio para guardar o actualizar el libro en la base de datos
        bookService.saveBook(book);
        // 8.2.2.14: Después de guardar, redirigir a la página de listado de libros
        return "redirect:/admin/books/";
    }

    // 8.2.2.15: Método para eliminar un libro por medio de su ID
    // Se mapea la ruta "/admin/books/delete/{id}" para recibir el ID del libro que se desea eliminar
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        // 8.2.2.16: Utilizar el servicio para eliminar el libro con el ID recibido
        bookService.deleteBook(id);
        // 8.2.2.17: Redirigir nuevamente a la lista de libros para actualizar la vista
        return "redirect:/admin/books/";
    }
}
