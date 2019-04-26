package com.example.c15395091.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.c15395091.shop.Constructor.StockItem;
import com.example.c15395091.shop.Template.AbstractCardValidation;
import com.example.c15395091.shop.Template.AmericanExpressValidation;
import com.example.c15395091.shop.Template.MasterCardValidation;
import com.example.c15395091.shop.Template.VisaValidation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class Payment extends AppCompatActivity {

    private List<StockItem> cart;

    FirebaseDatabase mFirebaseDatabase;
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference myRef;
    static final String TAG = "AddToDatabase";
    private FirebaseAuth mAuth;
    private Context context;
    String userID;

    EditText cardType, cardName, number, expiryMonth,
            expiryYear, editCvv;
    Button confirmPayment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        confirmPayment = findViewById(R.id.confirmPayment);
        cardType = findViewById(R.id.type);
        cardName = findViewById(R.id.cardName);
        number = findViewById(R.id.number);
        expiryMonth = findViewById(R.id.expiryMonth);
        expiryYear = findViewById(R.id.expiryYear);
        editCvv = findViewById(R.id.cvv);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        confirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = cardName.getText().toString();

                String cardNumber = number.getText().toString();

                String cardExpiryMonth = expiryMonth.getText().toString();
                int expiryDateMonthInt = Integer.parseInt(cardExpiryMonth);

                String cardExpiryYear = expiryYear.getText().toString();
                int expiryDateYearInt =Integer.parseInt(cardExpiryYear);

                String cvv = editCvv.getText().toString();

                String type = cardType.getText().toString();

                boolean result = false;
                AbstractCardValidation validator = null;

                if (type.trim().equalsIgnoreCase("Visa Card")) {
                    validator = new VisaValidation(name, cardNumber, expiryDateMonthInt,
                            expiryDateYearInt, cvv);

                } else if (type.trim().equalsIgnoreCase("MasterCard")) {
                    validator = new MasterCardValidation(name, cardNumber, expiryDateMonthInt,
                            expiryDateYearInt, cvv);

                } else if (type.trim().equalsIgnoreCase("American Express")) {
                    validator = new AmericanExpressValidation(name, cardNumber, expiryDateMonthInt,
                            expiryDateYearInt, cvv);

                }

                result = validator.validate();
                if (!result) {
                    Toast.makeText(getApplicationContext(), "Invalid Card Details", Toast.LENGTH_SHORT).show();

                }
                else {
                    final FirebaseUser user = mAuth.getCurrentUser();
                    userID = user.getUid();
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    final DatabaseReference cartDb = FirebaseDatabase.getInstance().getReference().child("users").child(userID).child("cart");
                    cartDb.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    StockItem item = ds.getValue(StockItem.class);
                                    myRef.child("users").child(userID).child("purchase_history").push().setValue(item);
                                    Intent i = new Intent(Payment.this, HomePage.class);
                                    startActivity(i);
                                    finish();
                                }
                            }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });
                }
            }
        });

    }
}

