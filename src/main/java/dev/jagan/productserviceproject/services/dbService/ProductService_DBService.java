package dev.jagan.productserviceproject.services.dbService;

import dev.jagan.productserviceproject.dto.CategoryDTO;
import dev.jagan.productserviceproject.models.Category;
import dev.jagan.productserviceproject.models.Product;
import dev.jagan.productserviceproject.repositories.CategoryRepo;
import dev.jagan.productserviceproject.repositories.ProductRepo;
import dev.jagan.productserviceproject.services.ProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("DataBaseProductService")
public class ProductService_DBService implements ProductService {

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public ProductService_DBService(ProductRepo productRepo, CategoryRepo categoryRepo){
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    //1. Implementation of first API - get single product by ID
    @Override
    public Product getProductById(Long Id) {
        return productRepo.findByIdIs(Id);
    }

    //2. Implementation of second API - get All products
    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    //3. Implementation of third API - Create Product
    @Override
    public Product createNewProduct(String title, float price, String categoryTitle, String description, String imageURL) {
        Product newProduct = new Product();
        newProduct.setTitle(title);
        newProduct.setPrice(price);
        newProduct.setDescription(description);
        newProduct.setImageURL(imageURL);

        Category categoryFromDataBase = categoryRepo.findByCategory(categoryTitle);
        if (categoryFromDataBase == null){
            Category newCategory = new Category();
            newCategory.setCategory(categoryTitle);
            categoryFromDataBase = categoryRepo.save(newCategory);
        }

        newProduct.setCategory(categoryFromDataBase);

        return productRepo.save(newProduct);
    }

    //4. Implementation of fourth API - Delete Product
    @Override
    public Product deleteProduct(Long id) {
        Product productFromDB = productRepo.findByIdIs(id);
        productRepo.deleteById(id);
        return productFromDB;
    }

    //5. Implementation of fifth API - Get all category
//    @Override
//    public List<CategoryDTO> getAllCategory() {
//        List<Category> categoryList = categoryRepo.findAll();
//        List<CategoryDTO> finalCategoryList = new ArrayList<>();
//
//        for(Category category : categoryList){
//            CategoryDTO categoryDTO = new CategoryDTO();
//            categoryDTO.setCategoryName(category.getCategory());
//            finalCategoryList.add(categoryDTO);
//        }
//
//        return finalCategoryList;
//    }

    //6. Implementation of sixth API - Update a Product
    @Override
    public Product updateProduct(Long id, String title, float price, String category, String description, String imageURL) {
        Product product = productRepo.findByIdIs(id);
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageURL(imageURL);

        Category categoryFromDB = categoryRepo.findByCategory(category);
        if (categoryFromDB == null){
            Category newCategory = new Category();
            newCategory.setCategory(category);
            categoryFromDB = categoryRepo.save(newCategory);
        }
        product.setCategory(categoryFromDB);

        return productRepo.save(product);
    }

    //7. Implementing Seventh API - Get list of Products by Category
    @Override
    public List<Product> getProductsByCategory(String title) {
        return productRepo.findByCategory_category(title);
    }
}
