package org.tanvir.product.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.tanvir.product.model.Product;
import org.tanvir.product.repository.ProductRepository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;


    public Product createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        kafkaTemplate.send("product-events",
                "Product created: " + savedProduct.getId());
        return savedProduct;
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

}