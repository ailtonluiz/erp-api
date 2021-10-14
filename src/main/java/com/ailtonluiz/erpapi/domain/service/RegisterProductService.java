package com.ailtonluiz.erpapi.domain.service;

import com.ailtonluiz.erpapi.domain.exception.ProductNotFoundException;
import com.ailtonluiz.erpapi.domain.model.Product;
import com.ailtonluiz.erpapi.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product searchOrFail(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }
}