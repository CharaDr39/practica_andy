package com.ServiceImplementation;

// Paso 7.2.2.2: Importar las clases necesarias para implementar la interfaz UserDetailsService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.DAO.UserRepository;
import com.Domain.User;
import com.Domain.Role;
import java.util.stream.Collectors;
import java.util.List;

// Paso 7.2.2.3: Anotar la clase con @Service para que Spring la gestione como un componente
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // Paso 7.2.2.4: Inyectar el repositorio de usuarios para acceder a la BD
    @Autowired
    private UserRepository userRepository;

    // Paso 7.2.2.5: Implementar el método loadUserByUsername para buscar un usuario por su nombre de usuario
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar el usuario en la base de datos mediante el método definido en el repositorio
        User user = userRepository.findByUsername(username);
        
        // Paso 7.2.2.6: Si el usuario no es encontrado, lanzar una excepción para indicar que no existe
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        
        // Paso 7.2.2.7: Convertir la lista de roles del usuario a una lista de objetos SimpleGrantedAuthority
        List<SimpleGrantedAuthority> authorities = user.getRoles()
                .stream()
                // Por cada rol, obtener el nombre (por ejemplo "ROLE_ADMIN" o "ROLE_USER")
                .map(Role::getName)
                // Crear un objeto SimpleGrantedAuthority con el nombre del rol
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
                
        // Paso 7.2.2.8: Retornar un objeto de tipo UserDetails con la información del usuario, su contraseña y sus autorizaciones
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),  // Nombre de usuario
                user.getPassword(),  // Contraseña (debe estar encriptada)
                authorities          // Lista de roles convertidos a GrantedAuthority
        );
    }
}
