package com.Controllers;

// 10.2.2: Importar las clases necesarias para el controlador, inyección de dependencias, manejo de fechas y de seguridad
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.Domain.Book;
import com.Domain.Loan;
import com.Domain.User;
import com.Service.BookService;
import com.Service.LoanService;
import com.Service.UserService;

@Controller
@RequestMapping("/user/loans") // 10.2.3: Se indica que este controlador gestionará rutas que comiencen con /user/loans
public class LoanController {

    // 10.2.4: Inyectamos el servicio de préstamos para gestionar la lógica de negocio de los préstamos
    @Autowired
    private LoanService loanService;

    // 10.2.5: Inyectamos el servicio de libros para acceder a la información del libro que se prestará
    @Autowired
    private BookService bookService;

    // 10.2.6: Inyectamos el servicio de usuarios para obtener el usuario autenticado
    @Autowired
    private UserService userService;

    // 10.2.7: Método para listar todos los préstamos del usuario autenticado
    // Se usa la interfaz Principal para obtener el nombre de usuario del usuario logueado
    @GetMapping("/")
    public String listLoans(Model model, Principal principal) {
        // 10.2.7.1: Obtener el nombre de usuario del usuario autenticado
        String username = principal.getName();
        // 10.2.7.2: Recuperar el objeto User desde la base de datos a través del servicio de usuarios
        User user = userService.getUserByUsername(username);
        // 10.2.7.3: Filtrar la lista de préstamos para obtener solo aquellos que pertenezcan al usuario actual.
        // Nota: Si en el servicio tienes un método específico para obtener préstamos por usuario, úsalo.
        List<Loan> loans = loanService.listAllLoans()
                .stream()
                .filter(loan -> loan.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());
        // 10.2.7.4: Agregar la lista de préstamos al objeto Model para enviarla a la vista
        model.addAttribute("loans", loans);
        // 10.2.7.5: Retornar el nombre de la vista, en este caso se buscará el archivo "loans.html"
        return "user/loans";
    }

    // 10.2.8: Método para registrar el préstamo (borrow) de un libro
    // Se espera recibir el ID del libro a prestar mediante la URL.
    @GetMapping("/borrow/{bookId}")
    public String borrowBook(@PathVariable("bookId") Long bookId, Principal principal) {
        // 10.2.8.1: Obtener el usuario autenticado
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        // 10.2.8.2: Buscar el libro que se desea prestar utilizando el ID recibido
        Book book = bookService.getBookById(bookId);
        if (book != null) {
            // 10.2.8.3: Crear un nuevo objeto Loan para registrar el préstamo
            Loan loan = new Loan();
            // 10.2.8.4: Asignar el libro al préstamo
            loan.setBook(book);
            // 10.2.8.5: Asignar el usuario que realiza el préstamo
            loan.setUser(user);
            // 10.2.8.6: Registrar la fecha actual como la fecha de préstamo
            loan.setLoanDate(LocalDate.now());
            // 10.2.8.7: Guardar el préstamo en la base de datos mediante el servicio de préstamos
            loanService.saveLoan(loan);
        }
        // 10.2.8.8: Redirigir a la lista de préstamos para que el usuario vea la actualización
        return "redirect:/user/loans/";
    }

    // 10.2.9: Método para registrar la devolución de un préstamo (return)
    // Se espera recibir el ID del préstamo que se desea devolver mediante la URL.
    @GetMapping("/return/{loanId}")
    public String returnBook(@PathVariable("loanId") Long loanId) {
        // 10.2.9.1: Obtener el objeto Loan correspondiente al ID proporcionado
        Loan loan = loanService.getLoanById(loanId);
        if (loan != null) {
            // 10.2.9.2: Registrar la fecha de devolución como la fecha actual
            loan.setReturnDate(LocalDate.now());
            // 10.2.9.3: Actualizar el préstamo en la base de datos mediante el servicio de préstamos
            loanService.updateLoan(loan);
        }
        // 10.2.9.4: Redirigir a la vista de la lista de préstamos para visualizar la actualización
        return "redirect:/user/loans/";
    }
}
