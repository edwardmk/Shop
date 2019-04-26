package com.example.c15395091.shop.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.c15395091.shop.R;
import com.example.c15395091.shop.Constructor.StockItem;
import com.example.c15395091.shop.Builder.StockItemBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class CartListAdapter extends RecyclerView.Adapter<ItemListViewHolder> {

    private List<StockItem> stockList;
    private List<StockItem> cart;
    FirebaseDatabase mFirebaseDatabase;
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference myRef;
    static final String TAG = "AddToDatabase";
    private FirebaseAuth mAuth;
    private Context context;
    String userID;

    public CartListAdapter(){

    }
    public CartListAdapter(List<StockItem> stockList, Context context, List<StockItem> cart) {
        this.stockList = stockList;
        this.context = context;
        this.cart = cart;
    }


    @Override
    public ItemListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, null);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        return new ItemListViewHolder(layoutView);
    }


    @Override
    public void onBindViewHolder(final ItemListViewHolder holder, int position) {
        holder.mTitle.setText(stockList.get(position).getTitle());
        holder.mManufacturing.setText(stockList.get(position).getManufacturer());
        holder.mCategory.setText(stockList.get(position).getCategory());
        holder.mPrice.setText(stockList.get(position).priceToString());
        holder.mStock.setText(stockList.get(position).stockToString());
        holder.removeFromCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFromCart(holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.stockList.size();
    }

    public void addToCart(final ItemListViewHolder holder) {
        String title = holder.mTitle.getText().toString();
        String manufacturing = holder.mManufacturing.getText().toString();
        String category = holder.mCategory.getText().toString();

        String price = holder.mPrice.getText().toString();
        double priceDouble = Double.parseDouble(price);
        String stock = holder.mStock.getText().toString();
        int stockInt = Integer.parseInt(stock);

        StockItem item = new StockItemBuilder().setTitle(title).setManufacturer(manufacturing).setPrice(priceDouble).setCategory(category).setStock(stockInt).createStockItem();

        holder.addToCart.setVisibility(View.INVISIBLE);
        holder.removeFromCart.setVisibility(View.VISIBLE);
        cart.add(item);
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        myRef.child("users").child(userID).child("cart").push().setValue(item);
    }

    public void removeFromCart(final ItemListViewHolder holder){

    }

}