package com.example.c15395091.shop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddItem extends AppCompatActivity {
    EditText title, manufacturer, price, category, stock;
    FirebaseDatabase mFirebaseDatabase;
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference myRef;
    static final String TAG = "AddToDatabase";
    private FirebaseAuth mAuth;

    Button submitItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        submitItem = findViewById(R.id.submitButton);

        submitItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitNewItem();
            }
        });
    }

    public void submitNewItem(){

        final String iTitle, iManufacturer, iCategory, iPriceString, iStockString;
        final double iPrice;
        final int iStock;

        title = findViewById(R.id.sTitle);
        manufacturer = findViewById(R.id.sManufacturer);
        price = findViewById(R.id.sPrice);
        category = findViewById(R.id.sCategory);
        stock = findViewById(R.id.sStock);

        iTitle = title.getText().toString();
        iManufacturer = manufacturer.getText().toString();
        iCategory = category.getText().toString();
        iPriceString = price.getText().toString();
        iStockString = stock.getText().toString();

        iPrice = Double.parseDouble(iPriceString);
        iStock = Integer.parseInt(iStockString);
        final DatabaseReference itemsDb = FirebaseDatabase.getInstance().getReference().child("items");
        itemsDb.addListenerForSingleValueEvent(new ValueEventListener() {
            String mGroupId = itemsDb.push().getKey();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        StockItem item = ds.getValue(StockItem.class);
                        if (item.getTitle().equals(iTitle)){
                            Toast.makeText(AddItem.this, "Item already in database.", android.widget.Toast.LENGTH_SHORT).show();

                        }
                        else{
                            item = new StockItemBuilder().setTitle(iTitle).setManufacturer(iManufacturer).setPrice(iPrice).setCategory(iCategory).setStock(iStock).createStockItem();
                            myRef.child("items").push().setValue(item);
                            Intent i = new Intent(AddItem.this, HomePage.class);
                            startActivity(i);
                            finish();
                        }
                    }
                }
                else{
                    StockItem item = new StockItemBuilder().setTitle(iTitle).setManufacturer(iManufacturer).setPrice(iPrice).setCategory(iCategory).setStock(iStock).createStockItem();
                    myRef.child("items").push().setValue(item);
                    Intent i = new Intent(AddItem.this, HomePage.class);
                    startActivity(i);
                    finish();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }
//    DatabaseReference itemsDb = FirebaseDatabase.getInstance().getReference().child("items");
//        itemsDb.addValueEventListener(new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            if(dataSnapshot.getChildren().equals(null)){
//                StockItem item = ds.getValue(StockItem.class);
//
//                item = new StockItemBuilder().setTitle(iTitle).setManufacturer(iManufacturer).setPrice(iPrice).setCategory(iCategory).setStock(iStock).createStockItem();
//                myRef.child("items").push().setValue(item);
//                Intent i = new Intent(AddItem.this, HomePage.class);
//                startActivity(i);
//                finish();
//            }
//            for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                if (dataSnapshot.exists()) {
//                    StockItem item = ds.getValue(StockItem.class);
//
//                    if (item.getTitle().equals(iTitle)) {
//                        Toast.makeText(AddItem.this, "Item already in database.", android.widget.Toast.LENGTH_SHORT).show();
//                    } else {
//                        item = new StockItemBuilder().setTitle(iTitle).setManufacturer(iManufacturer).setPrice(iPrice).setCategory(iCategory).setStock(iStock).createStockItem();
//                        myRef.child("items").push().setValue(item);
//                        Intent i = new Intent(AddItem.this, HomePage.class);
//                        startActivity(i);
//                        finish();
//                    }
//                }
//                else {
//
//                }
//            }
//
//        }
}