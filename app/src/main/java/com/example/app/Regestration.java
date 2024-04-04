package com.example.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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

public class Regestration extends AppCompatActivity {

    private EditText editNameR ;
    private EditText editNameRC;
    private EditText editTextTextEmailAddress ;
    private EditText editTextNumberSigned;
    private EditText editTextPasswordR;
    private EditText editTextPasswordRC;
    private Button buttonReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration);

        editNameR = findViewById(R.id.editNameR);
        editNameRC = findViewById(R.id.editNameRC);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextNumberSigned = findViewById(R.id.editTextNumberSigned);
        editTextPasswordR = findViewById(R.id.editTextPasswordR);
        editTextPasswordRC = findViewById(R.id.editTextPasswordRC);
        buttonReg = findViewById(R.id.buttonReg);

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextTextEmailAddress.getText().toString();
                String pass = editTextPasswordR.getText().toString();
                String passRepeat =  editTextPasswordRC.getText().toString();
                String login =  editTextNumberSigned.getText().toString();
                String name =  editNameR.getText().toString();
                String surname =  editNameRC.getText().toString();
                if (isValidEmail(email) && isValidPassword(pass) && pass.equals(passRepeat) && isValidLogin(login) && isValidName(name) && isValidName(surname)) {
                    saveData(name, pass, login, email, surname);
                    pushToMenu();
                } else {
                    showInstruction("Fill in all fields correctly!");
                }

            }
        });
    }

    public boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean isValidName(String name) {
        String namePattern = "^[a-zA-Z]{6,24}$";
        return Pattern.matches(namePattern, name);
    }

    public boolean isValidPassword(String s) {
        Pattern PASSWORD_PATTERN
                = Pattern.compile(
                "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}");

        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s).matches();
    }

    public boolean isValidLogin(String login) {
        // Перевірка довжини
        if (login.length() != 10) {
            return false;
        }
        // Перевірка на цифри
        for (char c : login.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        // Логін дійсний
        return true;
    }
    private void pushToMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void showInstruction(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
    private void saveData(String name, String pass, String login, String email, String surname) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", pass);
        editor.putString("phone", login);
        editor.putString("name", name);
        editor.putString("surname", surname);
        editor.apply();
    }
    public void backMain(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}