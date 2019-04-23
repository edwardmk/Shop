package com.bowling.edward.shop;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ItemListViewHolder extends RecyclerView.ViewHolder {
    public TextView mTitle, mManufacturing, mPrice, mStock, mCategory;
    public Button addToCart, review;

    public ItemListViewHolder(View itemView){
        super(itemView);

        mTitle = itemView.findViewById(R.id.title);
        mManufacturing = itemView.findViewById(R.id.manufacturer);
        mPrice = itemView.findViewById(R.id.price);
        mStock = itemView.findViewById(R.id.stock);
        mCategory = itemView.findViewById(R.id.category);

        addToCart = itemView.findViewById(R.id.cartButton);
        review = itemView.findViewById(R.id.reviewButton);

    }

}