package com.adilmx.jee_servlet_jsp_app.service;

import com.adilmx.jee_servlet_jsp_app.model.Product;

import java.util.List;

public interface ProductService {
    public Product save(Product product);
    public List<Product> findAll();
    public List<Product> findByName(String keyWord);
    public Product update(Product product);
    public Product findById(Long id);
    public int delete(Long id);
    public void initCatalog();

}
