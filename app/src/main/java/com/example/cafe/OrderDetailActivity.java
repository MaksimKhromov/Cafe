package com.example.cafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "userName";
    private static final String DRINK_TYPE = "drinkType";
    private static final String ADDITIVES = "additives";
    private static final String DRINK = "drink";
    private TextView textViewName;
    private TextView textViewDrink;
    private TextView textViewDrinkType;
    private TextView textViewAdditives;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        dataDefinition();
        initialization();
    }


    public static Intent newIntent(Context context, String additives, String drinkType, String  userName, String drink){
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        intent.putExtra(ADDITIVES, additives);
        intent.putExtra(DRINK_TYPE, drinkType);
        intent.putExtra(DRINK, drink);
        return intent;
    }

    private void initialization(){
        textViewName.setText(getIntent().getStringExtra(EXTRA_USER_NAME));
        textViewDrink.setText(getIntent().getStringExtra(DRINK));
        textViewDrinkType.setText(getIntent().getStringExtra(DRINK_TYPE));
        textViewAdditives.setText(getIntent().getStringExtra(ADDITIVES));
    }

    private void dataDefinition(){
        textViewName = findViewById(R.id.textViewName);
        textViewDrink = findViewById(R.id.textViewDrink);
        textViewDrinkType = findViewById(R.id.textViewDrinkType);
        textViewAdditives = findViewById(R.id.textViewAdditives);
    }
}