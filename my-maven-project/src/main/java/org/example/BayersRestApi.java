package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BayersRestApi {

    @GetMapping("/hello") // Maps to GET requests at /hello
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping("/greet") // Maps to GET requests at /greet
    public String greet() {
        return "Greetings from Spring Boot!";
    }


    @GetMapping("/add")
    public int add(@RequestParam int a, @RequestParam int b) {
        return a + b;
    }

    @GetMapping("/product")
    public Product getProduct() {
        return new Product("Laptop", 1200);
    }

    // Example of a simple data class (can be in a separate file)
    public static class Product {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }
}