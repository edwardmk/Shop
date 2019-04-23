package com.bowling.edward.shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItem extends AppCompatActivity {
    EditText title, manufacturer, price, category, stock;

    Button submitItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        submitItem = findViewById(R.id.submitButton);

        submitItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitNewItem();
            }
        });
    }

    public void submitNewItem(){

        String iTitle, iManufacturer, iCategory, iPriceString, iStockString;
        double iPrice;
        int iStock;

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

        StockItem item = new StockItem(iTitle, iManufacturer, iPrice, iCategory, iStock);

    }

}
