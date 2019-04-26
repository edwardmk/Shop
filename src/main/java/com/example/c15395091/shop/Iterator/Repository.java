package com.example.c15395091.shop.Iterator;

import com.example.c15395091.shop.Constructor.Review;

import java.util.ArrayList;

public class Repository implements Container {

    public ArrayList<Review> reviews = new ArrayList<>();

    @Override
    public ReviewIterator getIterator() {
        return new ReviewIterator();
    }

    private class ReviewIterator implements Iterator {

        int index;

        @Override
        public boolean hasNext() {

            if(index < reviews.size()){
                return true;
            }
            return false;
        }

        @Override
        public Review next() {

            if(this.hasNext()){
                return reviews.get(index++);
            }
            return null;
        }
    }
}