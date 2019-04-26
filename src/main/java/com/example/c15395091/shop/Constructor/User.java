package com.example.c15395091.shop.Constructor;

public class User {


    private String email;
    private String username;
    private String shippingAddress;
    private String admin;

    public User(){

    }

    public User(String username, String email, String shippingAddress, String admin){
        this.username = username;
        this.email = email;
        this.shippingAddress = shippingAddress;
        this.admin = admin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}