package dev.jagan.productserviceproject.repositories;

import dev.jagan.productserviceproject.models.Category;
import dev.jagan.productserviceproject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    Category findByCategory(String title);

    Category save(Category category);

    List<Category> findAll();



}
