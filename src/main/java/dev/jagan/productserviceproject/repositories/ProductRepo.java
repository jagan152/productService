package dev.jagan.productserviceproject.repositories;

import dev.jagan.productserviceproject.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findByIdIs(Long id);

    Product save(Product entity);

    List<Product> findAll();

    void deleteById(Long id);

    List<Product> findByCategory_category(String title);

    Page<Product> findByTitleContaining(String title, Pageable pageable);
}
