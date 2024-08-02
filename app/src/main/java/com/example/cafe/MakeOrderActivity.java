package com.example.cafe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.Format;
import java.util.ArrayList;

public class MakeOrderActivity extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "userName";
    private TextView textViewGreetings;
    private RadioGroup radioGroupDrinks;
    private RadioButton radioButtonTea;
    private RadioButton radioButtonCoffe;
    private TextView textViewAdditives;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxLimon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffe;
    private Button buttonMakeOrder;
    private String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
        dataDefinition();
        initViews();
        radioGroupDrinks.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int id) {
                if (id == findViewById(R.id.radioButtonTea).getId()) {
                    textViewAdditives.setText(textViewsAdditives(id));
                    checkBoxLimon.setVisibility(View.VISIBLE);
                    findViewById(R.id.spinnerTea).setVisibility(View.VISIBLE);
                    findViewById(R.id.spinnerCoffee).setVisibility(View.GONE);
                } else if (id == findViewById(R.id.radioButtonCoffee).getId()) {
                    textViewAdditives.setText(textViewsAdditives(id));
                    checkBoxLimon.setVisibility(View.GONE);
                    findViewById(R.id.spinnerTea).setVisibility(View.INVISIBLE);
                    findViewById(R.id.spinnerCoffee).setVisibility(View.VISIBLE);
                }
            }
        });
        radioButtonTea.setChecked(true);

        buttonMakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserMadeOrder();
            }
        });
    }

    private void onUserMadeOrder() {
        ArrayList<String> additives = new ArrayList<>();
        if (checkBoxSugar.isChecked()) {
            additives.add(checkBoxSugar.getText().toString());
        }
        if (checkBoxMilk.isChecked()) {
            additives.add(checkBoxMilk.getText().toString());
        }
        if (radioButtonTea.isChecked() && checkBoxLimon.isChecked()) {
            additives.add(checkBoxLimon.getText().toString());
        }
        String drinkType;
        String drink;
        if (radioButtonTea.isChecked()) {
            drinkType = spinnerTea.getSelectedItem().toString();
            drink = radioButtonTea.getText().toString();
        } else if (radioButtonCoffe.isChecked()) {
            drinkType = spinnerCoffe.getSelectedItem().toString();
            drink = radioButtonCoffe.getText().toString();
        } else {
            drinkType = "";
            drink = "";
        }

        Intent intent = OrderDetailActivity.newIntent(MakeOrderActivity.this, additives.toString(), drinkType, userName, drink);
        startActivity(intent);
    }

    private String textViewsAdditives(int id) {
        RadioButton radioButtonId = findViewById(id);
        String radioButtonText = radioButtonId.getText().toString();
        return getString(R.string.additives, radioButtonText);
    }

    private void initViews() {
        userName = getIntent().getStringExtra(EXTRA_USER_NAME);
        String textGreetings = getString(R.string.greetings, userName);
        textViewGreetings.setText(textGreetings);
    }

    public static Intent newIntent(Context context, String userName) {
        Intent intent = new Intent(context, MakeOrderActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        return intent;
    }

    private void dataDefinition() {
        textViewGreetings = findViewById(R.id.textViewGreetings);
        textViewAdditives = findViewById(R.id.textViewAdditives);
        buttonMakeOrder = findViewById(R.id.buttonMakeOrder);
        radioGroupDrinks = findViewById(R.id.radioGroupDrinks);
        checkBoxSugar = findViewById(R.id.checkBoxSugar);
        checkBoxMilk = findViewById(R.id.checkBoxMilk);
        checkBoxLimon = findViewById(R.id.checkBoxlimon);
        radioButtonTea = findViewById(R.id.radioButtonTea);
        radioButtonCoffe = findViewById(R.id.radioButtonCoffee);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffe = findViewById(R.id.spinnerCoffee);
    }
}