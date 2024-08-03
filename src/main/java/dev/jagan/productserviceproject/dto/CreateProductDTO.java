package dev.jagan.productserviceproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDTO {
    //This DTO class is for CreateProduct API and similar to FakeStoreProductDTO except this one
    //does not have "Id" as it will be generated in the server Side upon creation of a Product.

    private String title;
    private float price;
    private String category;
    private String description;
    private String imageURL;
}
