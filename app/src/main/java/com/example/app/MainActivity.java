package com.example.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private Button buttonRL;
    private Button button_login;
    private EditText phoneL;
    private EditText passwordL;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        buttonRL = findViewById(R.id.buttonRL);
        button_login = findViewById(R.id.button_login);
        phoneL = findViewById(R.id.phoneL);
        passwordL = findViewById(R.id.passwordL);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneL.getText().toString();
                String password = passwordL.getText().toString();

                if (isValidLogin(phone) && isValidPassword(password)) {
                    String savedPhone = sharedPreferences.getString("phone", "");
                    String savedPassword = sharedPreferences.getString("password", "");

                    if (phone.equals(savedPhone) && password.equals(savedPassword)) {

                        Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MainPage.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "Invalid phone number or password!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Invalid phone number or password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isValidLogin(String login) {

        if (login.length() != 10) {
            return false;
        }

        for (char c : login.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public boolean isValidPassword(String password) {

        Pattern PASSWORD_PATTERN
                = Pattern.compile(
                "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}");

        return !TextUtils.isEmpty(password) && PASSWORD_PATTERN.matcher(password).matches();
    }

    public void RegestrationMove(View v) {
        Intent intent = new Intent(this, Regestration.class);
        startActivity(intent);
    }



    private void checkForSavedLogin() {

    }
}