package com.example.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class dialog_recharge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_recharge);

        EditText rechargeAmountEditText = findViewById(R.id.recharge_amount);
        Button rechargeButton = findViewById(R.id.recharge_button);


        rechargeButton.setOnClickListener(view -> {

            String amountStr = rechargeAmountEditText.getText().toString();
            if (!amountStr.isEmpty()) {
                int amount = Integer.parseInt(amountStr);


                setResult(amount);
            }


            finish();
        });
    }
}