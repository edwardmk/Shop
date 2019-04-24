package com.example.c15395091.shop;

public class StockItemBuilder {
    private String title;
    private String manufacturer;
    private double price;
    private String category;
    private int stock;

    public StockItemBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public StockItemBuilder setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public StockItemBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public StockItemBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public StockItemBuilder setStock(int stock) {
        this.stock = stock;
        return this;
    }

    public StockItem createStockItem() {
        return new StockItem(title, manufacturer, price, category, stock);
    }
}