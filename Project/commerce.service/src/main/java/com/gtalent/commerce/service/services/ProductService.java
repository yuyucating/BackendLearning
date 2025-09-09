package com.gtalent.commerce.service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtalent.commerce.service.models.Category;
import com.gtalent.commerce.service.models.Product;
import com.gtalent.commerce.service.repositories.CategoryRepository;
import com.gtalent.commerce.service.repositories.ProductRepository;
import com.gtalent.commerce.service.requests.CreateProductRequest;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAllProducts(){
        List<Product> products = productRepository.findByIsDeleted(false);
        if(products!=null && !products.isEmpty()){
            return products;
        }else{
            return null;
        }
    }

    public Product createProduct(String category_name, CreateProductRequest request){
        Product product = new Product();
        Optional<Category> category = categoryRepository.findByName(category_name);
        if(category.isPresent()){
            product.setCategory(category.get());
        }else{
            product.setCategory(null);
        }    
        product.setDescription(request.getDescription());
        product.setHeight(request.getHeight());
        product.setWidth(request.getWidth());
        product.setImageUrl(request.getImageUrl());
        product.setImageThumbnail(request.getImageThumbnail());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★");
        System.out.println(product.toString());
        Product newProduct = productRepository.save(product);
        
        return newProduct;
    }

}
