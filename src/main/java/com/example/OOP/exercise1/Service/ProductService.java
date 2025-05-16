package com.example.OOP.exercise1.Service;

import com.example.OOP.exercise1.Entity.Product;
import com.example.OOP.exercise1.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    //creating a new product
    public Product createProduct(Product product){
        return productRepository.save(product);
    }
    // retrieving all products
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    //finding an existing product using its id
    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Product not found"));
    }
    //updating an existing product
    public void updateProduct(Long id, Product productDetails){
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());

        productRepository.save(product);
    }
    //deleting an existing product
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    //filter methods
    public List<Product> searchByName(String name) {
        return productRepository.findByNameContaining(name);
    }
    public List<Product> filterByPrice(Double maxPrice) {
        return productRepository.findByPriceLessThan(maxPrice);
    }
}
