package com.example.c15395091.shop.Builder;

import com.example.c15395091.shop.Constructor.Review;

public class ReviewBuilder {
    private String poster;
    private String review;
    private int stars;

    public ReviewBuilder setPoster(String poster) {
        this.poster = poster;
        return this;
    }

    public ReviewBuilder setReview(String review) {
        this.review = review;
        return this;
    }

    public ReviewBuilder setStars(int stars) {
        this.stars = stars;
        return this;
    }

    public Review createReview() {
        return new Review(poster, review, stars);
    }
}