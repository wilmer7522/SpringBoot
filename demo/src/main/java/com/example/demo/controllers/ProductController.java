package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
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
        @RequestParam(required = false) String category,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
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


    int skip = page * size;

    return listadoFiltrado.stream()
        .skip(skip)
        .limit(size)
        .collect(Collectors.toList());
   }

    @GetMapping("/products/stats")
   public Map<String, Double> getStats(
       @RequestParam(required = false) String category
   ) {
     
     //Obtenga el listado de productos filtrados por categoría
    
    List<Product> products = productRepository.findAll();

    //Filtrar por categoría
    if (category != null && !category.isEmpty()) {
        products = products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // 3. Obtener estadísticas de precios
    DoubleSummaryStatistics statistics = products.stream()
            .mapToDouble(Product::getPrice)
            .summaryStatistics();
   
       return Map.of(
           "count", (double) statistics.getCount(),
           "avgPrice", statistics.getAverage(),
           "minPrice", statistics.getMin(),
           "maxPrice", statistics.getMax(),
           "totalValue", statistics.getSum()
       );
   }


    
   }
