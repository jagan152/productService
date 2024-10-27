package dev.jagan.productserviceproject.controllers;

import dev.jagan.productserviceproject.dto.CategoryDTO;
import dev.jagan.productserviceproject.dto.CreateProductDTO;
import dev.jagan.productserviceproject.models.Product;
import dev.jagan.productserviceproject.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    ProductService productService;

    public ProductController(@Qualifier("DataBase") ProductService productService){
        this.productService = productService;
    }

    //Controller method for API-1 : getProductByID
    @GetMapping("/products/{id}")
    public Product getProductByID(@PathVariable("id") Long id ){
        return productService.getProductById(id);
    }

    //Controller method for API-2 : getAllProducts
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    //Controller method for API-3 : create Product
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductDTO createProductDTO){
        return productService.createNewProduct(createProductDTO.getTitle(),
                createProductDTO.getPrice(),
                createProductDTO.getCategory(),
                createProductDTO.getDescription(),
                createProductDTO.getImageURL());
    }

    //Controller method for API-4 : deleteProduct
    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }

    //Controller method for API-5 : GetAllCategory
    @GetMapping("/products/categories")
    public List<CategoryDTO> getAllCategory(){
        return productService.getAllCategory();
    }

    //Controller method for API-6 : Update product
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id,CreateProductDTO productRequestDTO){
        return productService.updateProduct(id,
                productRequestDTO.getTitle(),
                productRequestDTO.getPrice(),
                productRequestDTO.getCategory(),
                productRequestDTO.getDescription(),
                productRequestDTO.getImageURL());
    }

    //Controller method for API-7 : GetProductsByCategory
    @GetMapping("/products/category/{title}")
    public List<Product> getProductsByCategory(@PathVariable("title") String Category){
        return productService.getProductsByCategory(Category);
    }
}
