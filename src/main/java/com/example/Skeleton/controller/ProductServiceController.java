package com.example.Skeleton.controller;

import com.example.Skeleton.Component.Products;
import com.example.Skeleton.Services.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductServiceController {

    @Autowired
    ProductRepository productRepository;

    /*private static Map<String, Products> productRepo = new HashMap<>();
    static {
        Products honey = new Products();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);

        Products almond = new Products();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }*/

    @RequestMapping(value="/products")
    public ResponseEntity<Object> getProducts() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);

    }

    /*@RequestMapping(value="/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Products item) {
        productRepo.put(item.getId(), item);
        return new ResponseEntity<>("Add success", HttpStatus.OK);
    }

    @RequestMapping(value="/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Products products) {
        productRepo.remove(id);
        products.setId(id);
        productRepo.put(id, products);
        return new ResponseEntity<>("Update success", HttpStatus.OK);
    }*/

    @RequestMapping(value="/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> removeProduct(@PathVariable("id") String id) {
        try {
            productRepository.deleteById(Integer.parseInt(id));
            return new ResponseEntity<>("Remove product success", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
