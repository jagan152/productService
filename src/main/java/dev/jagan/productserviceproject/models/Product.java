package dev.jagan.productserviceproject.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private long id;
    private String title;
    private float price;
    private Category category;
    private String description;
    private String imageURL;

}
