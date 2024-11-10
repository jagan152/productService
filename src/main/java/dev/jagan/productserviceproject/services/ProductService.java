package dev.jagan.productserviceproject.services;

import dev.jagan.productserviceproject.dto.CategoryDTO;
import dev.jagan.productserviceproject.models.Category;
import dev.jagan.productserviceproject.models.Product;

import java.util.List;

public interface ProductService {
    //1. Get Product Detail by ID
    public Product getProductById(Long Id);

    //2. Get all Products
    public List<Product> getAllProducts();

    //3. Create a new Product
    public Product createNewProduct(String title, Float price, String category, String description, String imageURL);

    //4. Delete a product by ID
    public Product deleteProduct(Long id);

    //5. Get all Category
    //public List<CategoryDTO> getAllCategory();

    //6. Update a Product
    public Product updateProduct(Long id, String title, Float price, String category, String description, String imageURL);

    //7. Get Products by Category
    public List<Product> getProductsByCategory(String title);

}
