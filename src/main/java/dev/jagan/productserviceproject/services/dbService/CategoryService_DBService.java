package dev.jagan.productserviceproject.services.dbService;

import dev.jagan.productserviceproject.dto.CategoryDTO;
import dev.jagan.productserviceproject.exceptions.NotFoundException;
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
    public String[] getAllCategory() throws NotFoundException {
        List<Category> categoryList = categoryRepo.findAll();
        if(!categoryList.isEmpty()){
            String[] categories = new String[categoryList.size()];
            int i =0;
            for (Category category : categoryList){
                categories[i++] = category.getCategory();
            }
            return categories;
        }
        throw new NotFoundException("There are no categories");
    }
}
