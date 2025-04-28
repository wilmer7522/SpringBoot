package com.example.hello.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.hello.domain.Producto;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class ApiController {

    @GetMapping("/") 
    public String home() {
        return "home Campers";
    }

    @GetMapping("/saludo") // /saludo?nombre=wilmer
    public String saludo(@RequestParam(name = "nombre", required = true) String name,
    @RequestParam(name = "apellido", required = true, defaultValue = "Apellido comun") String lastName
    ) {
        return "hello " + name + " " + lastName ;
    }


    @GetMapping("/search") // /search?name=BUC
    public Map<String, String> buscar(
        @RequestParam(name = "name", defaultValue = "") String name
    ) {
        Map<String, String> cities = new HashMap<>();
        cities.put( "BUC",  "Bucaramanda");
        cities.put( "CCS",  "Caracas");
        cities.put( "NYC",  "New York");
        cities.put( "BOG",  "Bogota");

        if(cities.containsKey(name)){
            return Map.of(name, cities.get(name));
        } else {
            return cities;
        }

        
    }

    @GetMapping("/tax")
    public Map<String, Object> calcular(
        @RequestParam(defaultValue = "0") double impuestos
    ) {
        List<Producto> productos = new ArrayList<>(
            List.of(new Producto(1, "Pan", 2000))
            );
        productos.add(new Producto(2, "Gaseosa", 3500));
        productos.add(new Producto(3, "Salchichichon", 15000));

        double total = 0;
        for (Producto p : productos) {
        total += p.getPrice();
        }

        double valorNeto = total + (total * (impuestos / 100));
        

        //return Map.of("productos", productos,"total", (total + (total * (impuestos / 100))), "valor_neto", total);
        return Map.of("productos", productos, "Total", total , "Valor Neto", valorNeto, "IVA", impuestos+"%");
    }

}


/*@GetMapping("/tax")
    public Map<String, Object>calcular(
        @RequestParam(defaultValue = "0") double impuestos
        ) {
            List<Producto> productos = new ArrayList<>(
                List.of(new Producto(1, "Pan", 2000))
                );
            productos.add(new Producto(2, "Gaseosa", 3500));
            productos.add(new Producto(3, "Salchichon Zenu", 1500));
            double precios = productos.stream().map(producto -> producto.getPrice()).reduce(0.0, (precioA, precioB) -> precioA + precioB);
            return Map.of("productos", productos,"total", (precios + (precios * (impuestos / 100))), "valor_neto", precios, "IVA",  impuestos+"%");
    } */

