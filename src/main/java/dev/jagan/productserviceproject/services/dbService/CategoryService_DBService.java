package dev.jagan.productserviceproject.services.dbService;

import dev.jagan.productserviceproject.dto.CategoryDTO;
import dev.jagan.productserviceproject.models.Category;
import dev.jagan.productserviceproject.repositories.CategoryRepo;
import dev.jagan.productserviceproject.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("DataBaseCategoryService")
public class CategoryService_DBService implements CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryService_DBService(CategoryRepo categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categoryList = categoryRepo.findAll();
        List<CategoryDTO> finalCategoryList = new ArrayList<>();

        for(Category category : categoryList){
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryName(category.getCategory());
            finalCategoryList.add(categoryDTO);
        }

        return finalCategoryList;
    }
}
