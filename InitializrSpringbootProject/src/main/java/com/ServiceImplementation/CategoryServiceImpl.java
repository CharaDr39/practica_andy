package com.ServiceImplementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.DAO.CategoryRepository;
import com.Domain.Category;
import com.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    // Inyectamos el repositorio de categorías
    @Autowired
    private CategoryRepository categoryRepository;
    
    // 1. Retorna todas las categorías
    @Override
    public List<Category> listAllCategories() {
        return categoryRepository.findAll();
    }
    
    // 2. Retorna una categoría por su ID o null si no se encuentra
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
    
    // 3. Guarda o actualiza una categoría en la base de datos
    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
    
    // 4. Elimina una categoría dada su ID
    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
