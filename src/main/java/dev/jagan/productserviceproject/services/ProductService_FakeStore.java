package dev.jagan.productserviceproject.services;

import dev.jagan.productserviceproject.dto.CategoryDTO;
import dev.jagan.productserviceproject.dto.CreateProductDTO;
import dev.jagan.productserviceproject.dto.FakeStoreProductDTO;
import dev.jagan.productserviceproject.models.Product;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService_FakeStore implements ProductService{

    private final RestTemplate restTemplate;

    public ProductService_FakeStore(RestTemplate restTemplate){

        this.restTemplate = restTemplate;
    }



    //1. Implementation of first API - get single product by ID
    @Override
    public Product getProductById(Long Id) {
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate
                .getForEntity("http://fakestoreapi.com/products/"+Id,FakeStoreProductDTO.class);

        FakeStoreProductDTO responseBody = responseEntity.getBody();
        return responseBody.ToProduct();
    }

    //2. Implementation of second API - get All products
    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate
                .getForEntity("http://fakestoreapi.com/products",FakeStoreProductDTO[].class);
        FakeStoreProductDTO[] responseBody = responseEntity.getBody();

        List<Product> products = new ArrayList<Product>();
        for(FakeStoreProductDTO x : responseBody){
            products.add(x.ToProduct());
        }
        return products;
    }

    //3. Implementation of third API - Create Product
    @Override
    public Product createNewProduct(String title, float price, String category, String description, String imageURL) {
        //Converting the separate arguments into FakeStoreDto Object
        CreateProductDTO newProductDTO = new CreateProductDTO();
        newProductDTO.setTitle(title);
        newProductDTO.setPrice(price);
        newProductDTO.setCategory(category);
        newProductDTO.setDescription(description);
        newProductDTO.setImageURL(imageURL);

        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate
                .postForEntity("http://fakestoreapi.com/products",newProductDTO,FakeStoreProductDTO.class);

        FakeStoreProductDTO responseBody = responseEntity.getBody();

        return responseBody.ToProduct();

    }

    //4. Implementation of fourth API - Delete Product
    @Override
    public Product deleteProduct(Long id) {

        //We could not use restTemplate.DELETE() method of RestTemplate which doesn't return response-body as the delete api of FakeStore returns an object of Product type.
        //Hence, we are using the restTemplate.EXCHANGE() method
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.exchange("http://fakestoreapi.com/products/" + id,
                HttpMethod.DELETE, HttpEntity.EMPTY,FakeStoreProductDTO.class);

        FakeStoreProductDTO responseBody = responseEntity.getBody();

        return responseBody.ToProduct();
    }

    //5. Implementation of fifth API - Get all category
    @Override
    public List<CategoryDTO> getAllCategory() {
        ResponseEntity<String[]> responseEntity = restTemplate
                .getForEntity("http://fakestoreapi.com/products/categories",String[].class);

        String[] responseBody = responseEntity.getBody();
        List<CategoryDTO> CategoryList = new ArrayList<>();

        for(String CategoryName : responseBody){
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryName(CategoryName);
            CategoryList.add(categoryDTO);
        }

        return CategoryList;
    }


    //6. Implementation of sixth API - Update a Product
    @Override
    public Product updateProduct(Long id, String title, float price, String category, String description, String imageURL) {
        //Constructing the FakeStoreProductDTO Object
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(title);
        fakeStoreProductDTO.setPrice(price);
        fakeStoreProductDTO.setCategory(category);
        fakeStoreProductDTO.setDescription(description);
        fakeStoreProductDTO.setImageURL(imageURL);

        //Constructing the HttpHeader through which we are sending the details to be updated
        HttpHeaders Headers = new HttpHeaders();
        Headers.setContentType(MediaType.APPLICATION_JSON);

        //Constructing the Request HttpEntity
        HttpEntity<FakeStoreProductDTO> requestEntity = new HttpEntity<>(fakeStoreProductDTO,Headers);

        // Making the request
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate
                .exchange("https://fakestoreapi.com/products/"+id,HttpMethod.PUT,requestEntity,FakeStoreProductDTO.class);


        return responseEntity.getBody().ToProduct();
    }

    //7. Implementing Seventh API - Get list of Products by Category
    @Override
    public List<Product> getProductsByCategory(String title) {
        ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate
                .getForEntity("https://fakestoreapi.com/products/category/"+title,FakeStoreProductDTO[].class);
        FakeStoreProductDTO[] responseBody = responseEntity.getBody();

        List<Product> products = new ArrayList<Product>();
        for(FakeStoreProductDTO x : responseBody){
            products.add(x.ToProduct());
        }

        return products;
    }
}
