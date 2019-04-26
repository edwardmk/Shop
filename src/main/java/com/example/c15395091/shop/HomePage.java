package com.example.c15395091.shop;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

public class HomePage extends AppCompatActivity {


    public Button toAdminHomePage, toBasket, toItems;
    DatabaseReference reference;
    String userID;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private static final String TAG = "AddToDatabase";
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        toAdminHomePage = findViewById(R.id.toAdminPage);
        toBasket = findViewById(R.id.shoppingCartButton);
        toItems = findViewById(R.id.browseItems);

        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        toAdminHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfAdmin();
            }
        });

        toItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToItems();
            }
        });

        toBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCart();
            }
        });
    }

    public void checkIfAdmin() {
        DatabaseReference adminRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference adRef = adminRef.child("users").child(userID);
        adRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot2) {
                for (DataSnapshot ds2 : dataSnapshot2.getChildren()) {
                    if(ds2.exists()) {
                        String checkAdmin = ds2.getValue().toString();
                        if (checkAdmin.equals("true")) {
                            Intent it = new Intent(HomePage.this, AdminHomePage.class);
                            startActivity(it);
                        } else {
                            Toast.makeText(HomePage.this, "Admin only mode.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    public void goToItems(){
        Intent i = new Intent(HomePage.this, DisplayItems.class);
        startActivity(i);
    }

    public void goToCart(){
        Intent i = new Intent(HomePage.this, Cart.class);
        startActivity(i);
    }
}