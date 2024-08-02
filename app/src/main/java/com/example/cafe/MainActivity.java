package com.example.cafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewPassword;
    private Button buttonSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataDefinition();
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textViewName.getText().toString().trim().isEmpty() || textViewPassword.getText().toString().trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, getString(R.string.error_fields_empty), Toast.LENGTH_SHORT).show();
                } else {
                    launchNextScreen();
                }
            }
        });
    }

    private void launchNextScreen(){
        Intent intent = MakeOrderActivity.newIntent(MainActivity.this, textViewName.getText().toString());
        startActivity(intent);
    }

    private void dataDefinition() {
        textViewName = findViewById(R.id.editTextName);
        textViewPassword = findViewById(R.id.editTextPassword);
        buttonSignIn = findViewById(R.id.buttonSignIn);
    }

}