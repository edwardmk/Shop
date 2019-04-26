package com.example.c15395091.shop.Constructor;

public class StockItem {
    private String title;
    private String manufacturer;
    private double price;
    private String category;
    private int stock;

    public StockItem(){

    }

    public StockItem(String title, String manufacturer, double price, String category, int stock){
        this.title = title;
        this.manufacturer = manufacturer;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String priceToString(){
        String priceString = String.valueOf(price);
        return priceString;
    }

    public String stockToString(){
        String stockString = String.valueOf(stock);
        return stockString;
    }
}