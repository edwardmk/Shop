package com.example.c15395091.shop.Iterator;

import com.example.c15395091.shop.Constructor.Review;

class ReviewIterator implements Iterator {
    Review[] reviewList;

    int pos = 0;

    public  ReviewIterator (Review[] reviewList) {
        this.reviewList = reviewList;
    }

    public Object next() {
        Review review =  reviewList[pos];
        pos += 1;
        return review;
    }

    public boolean hasNext() {
        if (pos >= reviewList.length ||
                reviewList[pos] == null)
            return false;
        else
            return true;
    }
}