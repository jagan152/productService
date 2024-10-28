package dev.jagan.productserviceproject.services;

import dev.jagan.productserviceproject.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    //5. Get all Category
    public List<CategoryDTO> getAllCategory();
}
