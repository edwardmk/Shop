package com.bowling.edward.shop;

public class Model {
    private String title;
    private String manufacturing;
    private double price;
    private String category;
    private int stock;

    public Model() {

    }

    public Model(String title, String manufacturing, double price, String category, int stock) {
        this.title = title;
        this.manufacturing = manufacturing;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }


    public String getManufacturing() {
        return manufacturing;
    }

    public void setManufacturing(String manufacturing) {
        this.manufacturing = manufacturing;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}