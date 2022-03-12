package com.adilmx.jee_servlet_jsp_app.model;

public class Product {
    private Long id;
    private String lib;
    private double price;

    public Product() {
    }

    public Product(Long id, String lib, double price) {
        this.id = id;
        this.lib = lib;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
