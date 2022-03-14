package com.adilmx.jee_servlet_jsp_app.service.impl;

import com.adilmx.jee_servlet_jsp_app.model.Product;
import com.adilmx.jee_servlet_jsp_app.service.ProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private static Long PRODUCT_INDEX = 0L;
    private HashMap<Long,Product> products = new HashMap<>();
    @Override
    public Product save(Product product) {
        if(product.getId() == null) {
            product.setId(++PRODUCT_INDEX);
        }
        return products.put(product.getId(),product);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> findByName(String keyWord) {
        /*List<Product> productsByKeyWord = new ArrayList<>();
        products.values().stream().forEach(product -> {
            if(product.getLib().indexOf(keyWord) != -1){
                productsByKeyWord.add(product);
            }
        });
        return productsByKeyWord;
        */
        return products.values().stream().filter(product -> product.getLib().contains(keyWord)).collect(Collectors.toList());
    }

    @Override
    public Product update(Product product) {
        /*System.out.println("");
        if (product.getLib() != null) {
            products.get(product.getId()).setLib(product.getLib());
        }
        if (product.getPrice() > 0) {
            products.get(product.getId()).setPrice(product.getPrice());
        }
        return products.get(product.getId());*/
        return products.put(product.getId(),product);
    }

    @Override
    public int delete(Long id) {
        if(products.get(id) != null){
            products.remove(id);
            return 1;
        }
        return -1;
    }
    

    @Override
    public void initCatalog() {
        save(new Product(null,"PC",6000));
        save(new Product(null,"PHONE",5000));
        save(new Product(null,"TELEVISION",7000));
    }

    @Override
	public Product findById(Long id) {
    	return products.get(id);
	}
	

	
}
