package com.example.c15395091.shop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.c15395091.shop.Adapter.CartListAdapter;
import com.example.c15395091.shop.Constructor.StockItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    Button proceed;

    DatabaseReference reference;
    String userID;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private static final String TAG = "AddToDatabase";
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<StockItem> followingIds = new ArrayList<>();
    private ArrayList<StockItem> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        proceed = findViewById(R.id.proceedButton);
        listenForData();
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplication());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CartListAdapter(results, Cart.this, followingIds);
        mRecyclerView.setAdapter(mAdapter);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedToPayment();
            }
        });
    }

    public void proceedToPayment(){
        DatabaseReference payRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference adRef = payRef.child("users").child(userID).child("cart");
        adRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot2) {
                for (DataSnapshot ds2 : dataSnapshot2.getChildren()) {
//                    if(ds2.child("cart").exists()) {
                        StockItem item = ds2.getValue(StockItem.class);
                        results.add(item);
                        mAdapter.notifyDataSetChanged();

//                        } else {
//                            Toast.makeText(Cart.this, "Cart is empty.", Toast.LENGTH_SHORT).show();
//                        }
                    }
                Intent it = new Intent(Cart.this, Payment.class);
                startActivity(it);
                }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });

    }

    private void listenForData() {
        DatabaseReference itemsDb = FirebaseDatabase.getInstance().getReference().child("items");
        itemsDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    StockItem item = ds.getValue(StockItem.class);

                    results.add(item);
                    mAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

}

