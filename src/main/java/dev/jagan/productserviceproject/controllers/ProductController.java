package dev.jagan.productserviceproject.controllers;

import dev.jagan.productserviceproject.dto.CategoryDTO;
import dev.jagan.productserviceproject.dto.CreateProductDTO;
import dev.jagan.productserviceproject.exceptions.NotFoundException;
import dev.jagan.productserviceproject.models.Product;
import dev.jagan.productserviceproject.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(@Qualifier("DataBaseProductService") ProductService productService){
        this.productService = productService;
    }

    //Controller method for API-1 : getProductByID
    @GetMapping("/{id}")
    public ResponseEntity<Product>  getProductByID(@PathVariable("id") Long id ) throws NotFoundException {
        //return productService.getProductById(id);
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);

    }

    //Controller method for API-2 : getAllProducts
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() throws NotFoundException{
        //return productService.getAllProducts();
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }

    //Controller method for API-3 : create Product
    @PostMapping()
    public Product createProduct(@RequestBody CreateProductDTO createProductDTO){
        return productService.createNewProduct(createProductDTO.getTitle(),
                createProductDTO.getPrice(),
                createProductDTO.getCategory(),
                createProductDTO.getDescription(),
                createProductDTO.getImageURL());
    }

    //Controller method for API-4 : deleteProduct
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) throws NotFoundException{
        //return productService.deleteProduct(id);
        return new ResponseEntity<Product>(productService.deleteProduct(id),HttpStatus.NO_CONTENT);
    }

//    //Controller method for API-5 : GetAllCategory
//    @GetMapping("/products/categories")
//    public List<CategoryDTO> getAllCategory(){
//        return productService.getAllCategory();
//    }

    //Controller method for API-6 : Update product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,CreateProductDTO productRequestDTO) throws NotFoundException{
        return  new ResponseEntity<>(
                productService.updateProduct(id,
                productRequestDTO.getTitle(),
                productRequestDTO.getPrice(),
                productRequestDTO.getCategory(),
                productRequestDTO.getDescription(),
                productRequestDTO.getImageURL()),HttpStatus.OK
        );
    }

    //Controller method for API-7 : GetProductsByCategory
    @GetMapping("/products/category/{title}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("title") String Category) throws NotFoundException{
        //return productService.getProductsByCategory(Category);
        return new ResponseEntity<>(productService.getProductsByCategory(Category),HttpStatus.OK);
    }
}
