package com.ramoscodev.gerenciadortarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramoscodev.gerenciadortarefas.model.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {
    
}
