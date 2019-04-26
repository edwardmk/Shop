package com.example.c15395091.shop.Iterator;

import com.example.c15395091.shop.Constructor.Review;

public class ReviewCollection implements Collection
{
    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    Review[] reviewList;

    public ReviewCollection()
    {
        reviewList = new Review[MAX_ITEMS];

    }

    public void addItem(String name, String post, int review)
    {
        Review review1 = new Review(name, post, review);
        if (numberOfItems >= MAX_ITEMS)
            System.err.println("Full");
        else
        {
            reviewList[numberOfItems] = review1;
            numberOfItems = numberOfItems + 1;
        }
    }

    public Iterator createIterator()
    {
        return new ReviewIterator(reviewList);
    }
}