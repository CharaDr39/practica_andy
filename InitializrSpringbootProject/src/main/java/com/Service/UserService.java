package com.Service;

import java.util.List;
import com.Domain.User;

public interface UserService {

    // 1. Método para obtener la lista completa de usuarios
    List<User> listAllUsers();
    
    // 2. Método para obtener un usuario por su ID
    User getUserById(Long id);
    
    // 3. Método para obtener un usuario por su username (para login o validación)
    User getUserByUsername(String username);
    
    // 4. Método para guardar o actualizar un usuario
    User saveUser(User user);
    
    // 5. Método para eliminar un usuario por su ID
    void deleteUser(Long id);
}
