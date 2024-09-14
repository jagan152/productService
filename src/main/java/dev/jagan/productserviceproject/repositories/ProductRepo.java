package dev.jagan.productserviceproject.repositories;

import dev.jagan.productserviceproject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product save(Product entity);

    Product findByIdIs(Long id);

    List<Product> findAll();

    void deleteById(Long id);

    List<Product> findByCategory_category(String title);
}
