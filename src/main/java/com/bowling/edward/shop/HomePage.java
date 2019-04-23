package com.bowling.edward.shop;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.PointValue;

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
    }

    public void checkIfAdmin() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference statsRef = ref.child("users").child(userID);
        statsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.exists()) {
                        String checkAdmin = ds.getValue().toString();
                        if (checkAdmin.equals("true")) {
                            Intent i = new Intent(HomePage.this, AdminHopePage.class);
                            startActivity(i);
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
}
