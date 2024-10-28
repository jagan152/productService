package dev.jagan.productserviceproject.services.fakeStore;

import dev.jagan.productserviceproject.dto.CategoryDTO;
import dev.jagan.productserviceproject.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreCategoryService")
public class CategoryService_FakeStore implements CategoryService {
    private final RestTemplate restTemplate;

    public CategoryService_FakeStore(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    @Override
    public List<CategoryDTO> getAllCategory() {
        ResponseEntity<String[]> responseEntity = restTemplate
                .getForEntity("http://fakestoreapi.com/products/categories",String[].class);

        String[] responseBody = responseEntity.getBody();
        List<CategoryDTO> CategoryList = new ArrayList<>();

        for(String CategoryName : responseBody){
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryName(CategoryName);
            CategoryList.add(categoryDTO);
        }

        return CategoryList;
    }
}
