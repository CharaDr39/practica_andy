package com.Controllers;

// 8.1.2.2: Importar las anotaciones y clases necesarias para manejar las solicitudes HTTP
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 8.1.2.3: Anotar la clase con @Controller para que Spring la reconozca como un controlador
@Controller
public class HomeController {

    // 8.1.2.4: Método que mapea la ruta raíz ("/") para mostrar la página de inicio
    @GetMapping("/")
    public String home() {
        // 8.1.2.5: Se retorna el nombre de la vista; en este caso, se busca el archivo "index.html"
        return "index";
    }

    // 8.1.2.6: Método que mapea la ruta "/contact" para mostrar la página de contacto
    @GetMapping("/contact")
    public String contact() {
        // 8.1.2.7: Se retorna el nombre de la vista; en este caso, se busca el archivo "contact.html"
        return "contact";
    }
}
