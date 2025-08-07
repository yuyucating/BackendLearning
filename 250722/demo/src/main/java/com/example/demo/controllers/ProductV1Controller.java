package com.example.demo.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.requests.CreateProductRequest;
import com.example.demo.requests.UpdateProductRequest;
import com.example.demo.responses.ProductResponse;
import com.example.demo.responses.GetProductsResponse;


@RestController
@RequestMapping("v1/products")
@CrossOrigin("*")
public class ProductV1Controller {
    private final ProductRepository productRepository;

    @Autowired
    public ProductV1Controller(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    //GetAllProducts
    @GetMapping
    public ResponseEntity<List<GetProductsResponse>> getAllProducts (){
        List<Product> products = productRepository.findAll();

        return ResponseEntity.ok(products.stream().map(GetProductsResponse::new).toList());
    }
    //GetProductById
    @GetMapping("/{id}")
    public ResponseEntity<GetProductsResponse> getProductById(@PathVariable int id){
        Optional<Product> product = productRepository.findById(id);
        System.out.print("★");
        if(product.isPresent()){
            GetProductsResponse response = new GetProductsResponse(product.get());
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest request){

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setStatus(request.getStatus());
        product.setIs_deleted(request.getIs_deleted());
        product.setSupplierId(request.getSupplierId());
        
        Product productNew = productRepository.save(product);

        ProductResponse response = new ProductResponse(productNew.getName(), productNew.getPrice());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
        public ResponseEntity<ProductResponse> updateProductById(@PathVariable int id, @RequestBody UpdateProductRequest request){
        
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            Product updatedProduct = product.get(); 
            updatedProduct.setName(request.getName());
            Product savedProduct = productRepository.save(updatedProduct);
            ProductResponse response = new ProductResponse(savedProduct.getName(), savedProduct.getPrice()); 
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.notFound().build();
        }
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable int id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.delete(product.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
        
    }
}
