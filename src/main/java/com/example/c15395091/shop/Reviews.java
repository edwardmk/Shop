package com.example.c15395091.shop;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.c15395091.shop.Adapter.ItemListAdapter;
import com.example.c15395091.shop.Constructor.Review;
import com.example.c15395091.shop.Constructor.StockItem;
import com.example.c15395091.shop.Iterator.Iterator;
import com.example.c15395091.shop.Iterator.Repository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Reviews extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<StockItem> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        listenForData();
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplication());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ItemListAdapter(results, Reviews.this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void listenForData() {
        DatabaseReference itemsDb = FirebaseDatabase.getInstance().getReference().child("items");
        itemsDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    StockItem item = ds.getValue(StockItem.class);
                    Iterator itn = new Iterator() {
                        @Override
                        public boolean hasNext() {
                            return false;
                        }

                        @Override
                        public Object next() {
                            return null;
                        }
                    };
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