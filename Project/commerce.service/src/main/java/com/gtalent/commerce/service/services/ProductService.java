package com.gtalent.commerce.service.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gtalent.commerce.service.models.Category;
import com.gtalent.commerce.service.models.Product;
import com.gtalent.commerce.service.repositories.CategoryRepository;
import com.gtalent.commerce.service.repositories.ProductRepository;
import com.gtalent.commerce.service.requests.CreateProductRequest;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

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
        product.setStock(request.getStock());
        product.setSales(request.getSales());
        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★");
        System.out.println(product.toString());
        Product newProduct = productRepository.save(product);
        
        return newProduct;
    }

    public Page<Product> getAllProduct2(String query, Integer stockFrom, Integer stockTo, String category, PageRequest pageRequest){
        Specification<Product> spec = productSpecification(query, stockFrom, stockTo, category, false);
        return productRepository.findAll(spec, pageRequest);

    }

    private Specification<Product> productSpecification(String queryName, Integer stockFrom, Integer stockTo, String category, Boolean isDeleted){
        return ((root, query, criteriaBuilder)->{
            List<Predicate> predicates = new ArrayList<>();
            if(queryName!=null && !queryName.isEmpty()){
                predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+queryName.toLowerCase()+"%")
                    ));
            }
            if(category!=null){
                Join<Product, Category> categoryJoin = root.join("category");
                predicates.add(criteriaBuilder.equal(categoryJoin.get("name"), category));
            }
            if(stockFrom != null && stockTo != null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("stock"), stockFrom));
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("stock"), stockTo));
            }
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), isDeleted));

            Predicate[] predicateArray = predicates.toArray(new Predicate[0]);
            return criteriaBuilder.and(predicateArray);
        });
    }

    public boolean deleteProduct(int id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            product.get().setDeleted(true);
            productRepository.save(product.get());
            return true;
        }else{
            return false;
        }
    }
}
