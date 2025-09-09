package com.gtalent.commerce.service.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtalent.commerce.service.models.Category;
import com.gtalent.commerce.service.repositories.CategoryRepository;
import com.gtalent.commerce.service.requests.UpdateCategoryRequest;
import com.gtalent.commerce.service.responses.GetCategoryProductsResponse;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(){
        try {
            List<Category> categories = categoryRepository.findByIsDeleted(false);
            return categories;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    public GetCategoryProductsResponse updateCategory(int id, UpdateCategoryRequest request){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            category.get().setName(request.getName());
            category.get().setProducts(request.getProducts());
            category.get().setDeleted(request.isDelete());
            categoryRepository.save(category.get());
            return new GetCategoryProductsResponse(category.get());
        }else{
            return null;
        }

    }

    public boolean deleteCategory(int id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            category.get().setDeleted(true);
            return true;
        }else{
            return false;
        }
    }

    private GetCategoryProductsResponse GetCategoryProductsResponse(Category get) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
