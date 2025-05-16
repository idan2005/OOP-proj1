package com.example.OOP.exercise1.Controller;

import com.example.OOP.exercise1.Entity.Product;
import com.example.OOP.exercise1.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/products")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    // checking for http responses
    public ResponseEntity<String> httpCheck(){
        String message = productService.getAllProducts().toString();
        return ResponseEntity.ok(message);
    }

    @PostMapping
    private Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @GetMapping
    private List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    private Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    private void updateProduct(@PathVariable Long id, @RequestBody Product product){
        productService.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    private void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
    //
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchByName(name));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(@RequestParam Double maxPrice) {
        return ResponseEntity.ok(productService.filterByPrice(maxPrice));
    }
}
