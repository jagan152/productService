package dev.jagan.productserviceproject.services;

import dev.jagan.productserviceproject.dto.CreateProductDTO;
import dev.jagan.productserviceproject.dto.FakeStoreProductDTO;
import dev.jagan.productserviceproject.models.Category;
import dev.jagan.productserviceproject.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    @Override
    public Product deleteProduct(Long id) {

        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.exchange("http://fakestoreapi.com/products/" + id,
                HttpMethod.DELETE, HttpEntity.EMPTY,FakeStoreProductDTO.class);

        FakeStoreProductDTO responseBody = responseEntity.getBody();

        return responseBody.ToProduct();
    }

    @Override
    public List<Category> getAllCategory() {
        return List.of();
    }

    @Override
    public Product updateProduct(String title, float price, String category, String description, String imageURL) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(String title) {
        return List.of();
    }
}
