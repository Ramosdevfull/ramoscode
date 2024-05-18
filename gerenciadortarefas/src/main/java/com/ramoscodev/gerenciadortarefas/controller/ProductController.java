package com.ramoscodev.gerenciadortarefas.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ramoscodev.gerenciadortarefas.dto.ProductRecordDto;
import com.ramoscodev.gerenciadortarefas.model.Product;
import com.ramoscodev.gerenciadortarefas.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductRecordDto entityProduct) {
        var productModel = new Product();
        BeanUtils.copyProperties(entityProduct, productModel); 
        
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));
    }
    
    
}
