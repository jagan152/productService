package dev.jagan.productserviceproject.services;

import dev.jagan.productserviceproject.dto.CategoryDTO;
import dev.jagan.productserviceproject.exceptions.NotFoundException;

import java.util.List;

public interface CategoryService {
    //5. Get all Category
    public String[] getAllCategory() throws NotFoundException;
}
