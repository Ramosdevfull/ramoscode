package com.ramoscodev.gerenciadortarefas.service;

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
}
