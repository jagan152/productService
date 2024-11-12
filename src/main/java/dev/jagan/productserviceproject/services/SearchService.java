package dev.jagan.productserviceproject.services;

import dev.jagan.productserviceproject.models.Product;
import dev.jagan.productserviceproject.repositories.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class SearchService {
    private final ProductRepo productRepo;

    public SearchService(ProductRepo productRepo){
        this.productRepo = productRepo;
    }

    public Page<Product> search(String query, int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return productRepo.findByTitleContaining(query,pageable);
    }
}
