package com.Service;

import java.util.List;
import com.Domain.Category;

public interface CategoryService {

    // 1. Método para obtener la lista completa de categorías
    List<Category> listAllCategories();
    
    // 2. Método para obtener una categoría por su ID
    Category getCategoryById(Long id);
    
    // 3. Método para guardar o actualizar una categoría
    Category saveCategory(Category category);
    
    // 4. Método para eliminar una categoría por su ID
    void deleteCategory(Long id);
}
