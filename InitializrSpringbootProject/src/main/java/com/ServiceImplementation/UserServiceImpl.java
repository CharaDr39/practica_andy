package com.ServiceImplementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.DAO.UserRepository;
import com.Domain.User;
import com.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

    // Inyectamos el repositorio de usuarios
    @Autowired
    private UserRepository userRepository;
    
    // 1. Retorna la lista de todos los usuarios registrados
    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }
    
    // 2. Retorna un usuario filtrado por su ID, o null si no existe
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    // 3. Retorna un usuario filtrado por su username
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    // 4. Guarda un nuevo usuario o actualiza uno existente en la base de datos
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    // 5. Elimina un usuario dado su ID
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
