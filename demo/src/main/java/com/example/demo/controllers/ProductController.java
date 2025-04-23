package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class ProductController {

   private final ProductRepository productRepository;

   public ProductController(ProductRepository productRepository){
    this.productRepository = productRepository;
   }

   @GetMapping("/products")
   public List<Product> getProducts(
        @RequestParam(required = false) Double minPrice,
        @RequestParam(required = false) Double maxPrice,
        @RequestParam(required = false) String category
   ){
        
        List<Product> listadoFiltrado = productRepository.findAll();

    if (minPrice != null){
        listadoFiltrado = listadoFiltrado.stream()
            .filter(product -> product.getPrice() <= minPrice)
            .collect(Collectors.toList());
    }

    if (maxPrice != null){
        listadoFiltrado = listadoFiltrado.stream()
        .filter(product -> product.getPrice() >= maxPrice)
        .collect(Collectors.toList());
    }

    if (category != null){
        listadoFiltrado = listadoFiltrado.stream()
        .filter(product -> product.getCategory().equalsIgnoreCase(category))
        .collect(Collectors.toList());
    }


    return listadoFiltrado;
   }
    
   }
