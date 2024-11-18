package com.xuanthinh.service.impl;

import com.xuanthinh.entity.Product;
import com.xuanthinh.repository.ProductRepository;
import com.xuanthinh.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServicesImpl implements ProductServices {
    @Autowired
    private ProductRepository repo;

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Product get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Product save(Product product) {
        return repo.save(product);
    }

    @Override
    public List<Product> listAll() {
        return repo.findAll();
    }
}
