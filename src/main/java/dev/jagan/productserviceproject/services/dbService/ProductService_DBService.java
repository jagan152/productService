package dev.jagan.productserviceproject.services.dbService;

import dev.jagan.productserviceproject.dto.CategoryDTO;
import dev.jagan.productserviceproject.exceptions.NotFoundException;
import dev.jagan.productserviceproject.models.Category;
import dev.jagan.productserviceproject.models.Product;
import dev.jagan.productserviceproject.repositories.CategoryRepo;
import dev.jagan.productserviceproject.repositories.ProductRepo;
import dev.jagan.productserviceproject.services.ProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Product getProductById(Long Id) throws NotFoundException {
        Optional<Product> response = productRepo.findByIdIs(Id);
        if(response.isPresent()){
            return response.get();
        }
        throw new NotFoundException("There is no product for the ID :"+Id);
    }

    //2. Implementation of second API - get All products
    @Override
    public List<Product> getAllProducts() throws NotFoundException {
        List<Product> response =  productRepo.findAll();
        if(response.isEmpty()){
            throw new NotFoundException("There are no products available");
        }
        return response;
    }

    //3. Implementation of third API - Create Product
    @Override
    public Product createNewProduct(String title, Float price, String categoryTitle, String description, String imageURL) {
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
    public Product deleteProduct(Long id) throws NotFoundException{
        Optional<Product> productFromDB = productRepo.findByIdIs(id);
        if(productFromDB.isPresent()){
            productRepo.deleteById(id);
            return productFromDB.get();
        }
        throw new NotFoundException("There is no product available to delete for the ID :"+id);


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
    public Product updateProduct(Long id, String title, Float price, String category, String description, String imageURL) throws NotFoundException{
        Optional<Product> response = productRepo.findByIdIs(id);

        if (response.isPresent()){
            Product product = response.get();
            if(title != null) {
                product.setTitle(title);}
            if(price != null){
                product.setPrice(price);
            }
            if(description != null){
                product.setDescription(description);
            }
            if(imageURL != null){
                product.setImageURL(imageURL);
            }

            Category categoryFromDB = categoryRepo.findByCategory(category);
            if (categoryFromDB == null){
                Category newCategory = new Category();
                newCategory.setCategory(category);
                categoryFromDB = categoryRepo.save(newCategory);
            }
            product.setCategory(categoryFromDB);
            return productRepo.save(product);

        }
        throw new NotFoundException("There is no product available to update for the ID :"+id);

    }

    //7. Implementing Seventh API - Get list of Products by Category
    @Override
    public List<Product> getProductsByCategory(String title) throws NotFoundException{
        return productRepo.findByCategory_category(title);
    }
}
