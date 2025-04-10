package com.Controllers;

// Importar las clases necesarias para gestionar las solicitudes HTTP y redirecciones
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {

    // Método para mostrar la página de contacto (se reutiliza la vista contact.html)
    @GetMapping("/contact")
    public String showContactForm() {
        // Retorna la vista contact.html para que el usuario pueda completar el formulario
        return "contact";
    }

    // Método para procesar el envío del formulario de contacto
    @PostMapping("/contact")
    public String processContactForm(
            // Recibir los datos enviados desde el formulario mediante @RequestParam
            @RequestParam("nombre") String nombre,
            @RequestParam("correo") String correo,
            @RequestParam("mensaje") String mensaje,
            // Permite agregar atributos flash (mensajes de confirmación) tras la redirección
            RedirectAttributes redirectAttributes) {

        // Procesamiento de los datos:
        // Aquí puedes agregar la lógica para enviar un correo, almacenar la información en la BD,
        // o simplemente registrar la información en el log.
        System.out.println("Mensaje de contacto recibido:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo: " + correo);
        System.out.println("Mensaje: " + mensaje);

        // Agregar un mensaje de éxito al atributo flash para mostrar en la vista
        redirectAttributes.addFlashAttribute("successMessage", "Gracias por contactarnos, nos pondremos en contacto contigo pronto.");

        // Redirigir nuevamente a la página de contacto para mostrar el mensaje de éxito
        return "redirect:/contact";
    }
}
