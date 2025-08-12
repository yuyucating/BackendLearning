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
import com.example.demo.models.Supplier;
import com.example.demo.repositories.SupplierRepository;
import com.example.demo.requests.CreateSupplierRequest;
import com.example.demo.requests.UpdateSupplierRequest;
import com.example.demo.responses.GetSuppliersResponse;
import com.example.demo.responses.ProductResponse;
import com.example.demo.responses.SupplierResponse;

@RestController
@RequestMapping("v1/suppliers")
@CrossOrigin("*")
public class SupplierV1Controller {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierV1Controller(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    //GetAllProducts
    @GetMapping
    public ResponseEntity<List<GetSuppliersResponse>> getAllSuppliers (){
        List<Supplier> suppliers = supplierRepository.findAll();

        // return ResponseEntity.ok(suppliers.stream().map(GetSuppliersResponse::new).toList());
        return ResponseEntity.ok(suppliers.stream().map(supplier -> {
            GetSuppliersResponse response = new GetSuppliersResponse(supplier);
            response.setProducts(supplier.getProducts().stream().map(ProductResponse::new).toList());
            return response;
        }).toList());
    }
    //GetProductById
    @GetMapping("/{id}")
    public ResponseEntity<GetSuppliersResponse> getSupplierById(@PathVariable int id){
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if(supplier.isPresent()){
            GetSuppliersResponse response = new GetSuppliersResponse(supplier.get());
            // 接 ManyToOne 的資料
            List<Product> productList = supplier.get().getProducts();

            response.setProducts(productList.stream().map(ProductResponse::new).toList());

            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SupplierResponse> createSupplier(@RequestBody CreateSupplierRequest request){

        Supplier supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setAddress(request.getAddress());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        supplier.setIs_deleted(request.getIs_deleted());
        
        Supplier supplierNew = supplierRepository.save(supplier);

        SupplierResponse response = new SupplierResponse(supplierNew.getName(), supplierNew.getAddress(), supplierNew.getPhone(), supplierNew.getEmail());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
        public ResponseEntity<SupplierResponse> updateSupplierById(@PathVariable int id, @RequestBody UpdateSupplierRequest request){
        
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if(supplier.isPresent()){
            Supplier updatedSupplier = supplier.get(); 
            updatedSupplier.setName(request.getName());
            updatedSupplier.setAddress(request.getAddress());
            updatedSupplier.setEmail(request.getEmail());
            updatedSupplier.setPhone(request.getPhone());
            Supplier savedSupplier = supplierRepository.save(updatedSupplier);
            SupplierResponse response = new SupplierResponse(savedSupplier.getName(), savedSupplier.getAddress(), savedSupplier.getPhone(), savedSupplier.getEmail()); 
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.notFound().build();
        }
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplierById(@PathVariable int id){
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if(supplier.isPresent()){
            supplierRepository.delete(supplier.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
        
    }
}
