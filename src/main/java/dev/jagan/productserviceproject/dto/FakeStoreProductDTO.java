package dev.jagan.productserviceproject.dto;

import dev.jagan.productserviceproject.models.Category;
import dev.jagan.productserviceproject.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private long id;
    private String title;
    private float price;
    private String category;
    private String description;
    private String imageURL;

    public Product ToProduct(){
        Product product = new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setPrice(getPrice());

        Category category = new Category();
        category.setCategory(getCategory());

        product.setCategory(category);

        product.setDescription(getDescription());
        product.setImageURL(getImageURL());

        return product;

    }
}
