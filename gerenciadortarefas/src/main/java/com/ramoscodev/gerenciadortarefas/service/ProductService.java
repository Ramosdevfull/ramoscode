package com.ramoscodev.gerenciadortarefas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramoscodev.gerenciadortarefas.model.Product;
import com.ramoscodev.gerenciadortarefas.repository.IProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private IProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(Product p) {
        productRepository.delete(p);
    }
}
