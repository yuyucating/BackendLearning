package com.gtalent.commerce.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gtalent.commerce.service.models.Product;
import com.gtalent.commerce.service.requests.CreateProductRequest;
import com.gtalent.commerce.service.responses.GetProductsResponse;
import com.gtalent.commerce.service.responses.ProductDetailsResponse;
import com.gtalent.commerce.service.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("v1/products")
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @Operation(summary="Get all undeleted products", description="This API returns all undeleted products.")
    @GetMapping
    public ResponseEntity<List<GetProductsResponse>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        if(products!=null && !products.isEmpty()){
            return ResponseEntity.ok(products.stream().map(GetProductsResponse::new).toList());
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ProductDetailsResponse> createProduct(
        @Parameter(
            description="category",
            schema=@Schema(allowableValues={"Animal", "Beard", "Business", "Cars", "City", "Flowers", "Food", "Nature", "People", "Sports", "Tech", "Travel", "Water"})
        )
        @RequestParam(required=true) String category,
        @RequestBody CreateProductRequest request
    ){
        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★");
        System.out.println("★★★request="+request.getName());
        Product product = productService.createProduct(category, request);

        if(product!=null){
            ProductDetailsResponse response = new ProductDetailsResponse(product);
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
