package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
public class ProductRepository {
    // Agrega 20 products con datos de ejemplo

    private final List<Product> products = new ArrayList<>();

{

    products.add(new Product(1L, "Laptop", "Tecnologia", 500.0, 50 ));
    products.add(new Product(2L, "Celular", "Tecnologia", 350.0, 100));
    products.add(new Product(3L, "Tablet", "Tecnologia", 400.0, 60));
    products.add(new Product(4L, "Silla Gamer", "Oficina", 150.0, 20));
    products.add(new Product(5L, "Escritorio", "Oficina", 300.0, 10));
    products.add(new Product(6L, "Impresora", "Tecnologia", 300.0, 25));
    products.add(new Product(7L, "Televisor", "Electrodomestico", 800.0, 10));
    products.add(new Product(8L, "Tablet", "Tecnologia", 250.0, 40));
    products.add(new Product(9L, "Monitor", "Tecnologia", 200.0, 35));
    products.add(new Product(10L, "Teclado Mecánico", "Tecnologia", 120.0, 60));
    products.add(new Product(11L, "Mouse Gamer", "Tecnologia", 75.0, 80));
    products.add(new Product(12L, "Auriculares Bluetooth", "Tecnologia", 90.0, 100));
    products.add(new Product(13L, "Cámara Digital", "Electronica", 600.0, 15));
    products.add(new Product(14L, "Consola de Videojuegos", "Electronica", 1200.0, 8));
    products.add(new Product(15L, "Router Wi-Fi", "Tecnologia", 110.0, 50));
    products.add(new Product(16L, "Proyector", "Tecnologia", 700.0, 12));
    products.add(new Product(17L, "Smartwatch", "Tecnologia", 180.0, 45));
    products.add(new Product(18L, "Licuadora", "Electrodomestico", 150.0, 30));
    products.add(new Product(19L, "Refrigerador", "Electrodomestico", 1500.0, 5));
    products.add(new Product(20L, "Parlante Inteligente", "Tecnologia", 130.0, 70));



}

    public List<Product> findAll() {
        return products;
    }
}
