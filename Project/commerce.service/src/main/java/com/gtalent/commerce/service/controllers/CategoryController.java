package com.gtalent.commerce.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtalent.commerce.service.models.Category;
import com.gtalent.commerce.service.requests.UpdateCategoryRequest;
import com.gtalent.commerce.service.responses.GetCategoryProductsResponse;
import com.gtalent.commerce.service.responses.GetCategoryResponse;
import com.gtalent.commerce.service.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("v1/categories")
@CrossOrigin("*")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    //getAllCategories
    @Operation(summary="Get all categories", description="This API returns all categories.")
    @GetMapping
    public ResponseEntity<List<GetCategoryResponse>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories(); //調整為篩出 is_deleted=false

        // System.out.print(users.stream().map(GetUserListResponse::new).toList());
        return ResponseEntity.ok(categories.stream().map(GetCategoryResponse::new).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetCategoryProductsResponse> updateCategory(@PathVariable int id, @RequestBody UpdateCategoryRequest request){
        // 為了先檢查資料是否存在!
        GetCategoryProductsResponse categoryProducts = categoryService.updateCategory(id, request);

        if(categoryProducts!=null){
            return ResponseEntity.ok(categoryProducts);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id){
        boolean check = categoryService.deleteCategory(id);
        if(check){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    
}
