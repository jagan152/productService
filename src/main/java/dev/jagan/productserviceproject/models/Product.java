package dev.jagan.productserviceproject.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private float price;
    private String description;
    private String imageURL;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;


}
