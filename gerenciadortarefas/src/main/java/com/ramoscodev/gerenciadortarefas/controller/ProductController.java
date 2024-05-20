package com.ramoscodev.gerenciadortarefas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.ramoscodev.gerenciadortarefas.dto.ProductRecordDto;
import com.ramoscodev.gerenciadortarefas.model.Product;
import com.ramoscodev.gerenciadortarefas.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;




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

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        if (!productList.isEmpty()) {
            for(Product p : productList) {
                Long id = p.getId();
                p.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") Long id) {
        Optional<Product> pOptional = productService.getProduct(id);
        if (pOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!!");
        }
        pOptional.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("Products_List"));
        return ResponseEntity.status(HttpStatus.OK).body(pOptional.get());
    }

    @PutMapping("product/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") Long id,
                                                @RequestBody @Valid ProductRecordDto prodDto) {
        Optional<Product> pOptional = productService.getProduct(id);
        if (pOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!!");
        }
        var entityModel = pOptional.get();
        BeanUtils.copyProperties(prodDto, entityModel);
        return ResponseEntity.status(HttpStatus.OK).body(productService.save(entityModel));
    }
    
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Long id) {
        Optional<Product> pOptional = productService.getProduct(id);
        if (pOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!!");
        }
        productService.deleteProduct(pOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully!");
    }
    // https://www.google.com/search?q=postman+vscode&sca_esv=b76e5b87cda5eeaa&sca_upv=1&sxsrf=ADLYWILAkqIG1ogStLMmA-9UEExexgLcAw%3A1715993383206&source=hp&ei=J_tHZuqsCozU1sQP_LGwsAY&iflsig=AL9hbdgAAAAAZkgJN3oKMCFQ6JJl-SR2E2JKzUd48brI&udm=&oq=postman+v&gs_lp=Egdnd3Mtd2l6Iglwb3N0bWFuIHYqAggCMgUQABiABDIFEAAYgAQyBRAAGIAEMgUQABiABDIFEAAYgAQyBRAAGIAEMgUQABiABDIFEAAYgAQyBRAAGIAEMgUQABiABEjcd1CAHVjoQnABeACQAQCYAXigAdgHqgEDMS44uAEByAEA-AEBmAIKoALwCKgCCsICBxAjGCcY6gLCAg0QLhjRAxjHARgnGOoCwgIEECMYJ8ICChAjGIAEGCcYigXCAgsQABiABBixAxiDAcICCBAuGIAEGLEDwgIFEC4YgATCAggQABiABBixA8ICDhAAGIAEGLEDGIMBGIoFwgIOEC4YgAQYsQMY0QMYxwHCAhEQLhiABBixAxjRAxiDARjHAcICCxAAGIAEGJIDGIoFwgIIEAAYgAQYyQPCAgsQLhiABBixAxiDAcICBxAuGIAEGAqYAxqSBwMxLjmgB7FU&sclient=gws-wiz#fpstate=ive&vld=cid:dd24d200,vid:lsPYIYr4zRA,st:0
}
