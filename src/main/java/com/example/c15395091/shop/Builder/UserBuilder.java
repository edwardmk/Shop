package com.example.c15395091.shop.Builder;


import com.example.c15395091.shop.Constructor.User;

public class UserBuilder {
    private String username;
    private String email;
    private String shippingAddress;
    private String admin;

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public UserBuilder setAdmin(String admin) {
        this.admin = admin;
        return this;
    }

    public User createUser() {
        return new User(username, email, shippingAddress, admin);
    }
}