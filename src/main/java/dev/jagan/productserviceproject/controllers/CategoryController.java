package dev.jagan.productserviceproject.controllers;

import dev.jagan.productserviceproject.dto.CategoryDTO;
import dev.jagan.productserviceproject.services.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(@Qualifier("DataBaseCategoryService")CategoryService categoryService){
        this.categoryService = categoryService;
    }

    //Controller method for API-5 : GetAllCategory
    @GetMapping()
    public List<CategoryDTO> getAllCategory(){
        return categoryService.getAllCategory();
    }
}
