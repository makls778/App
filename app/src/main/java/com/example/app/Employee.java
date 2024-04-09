package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Employee extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee);


        listView = findViewById(R.id.list_viewE);


        String[] tariffs = new String[]{
                "Працівник 1: Hana Burns, Customer Service Representative",
                "Працівник 2: August Arroyo, Sales Representative",
                "Працівник 3: Kyra Merritt, Technical Support Specialist",
                "Працівник 4: Colten Cohen, Account Manager",
                "Працівник 5: Destiny Fox, Network Engineer",
                "Працівник 6: Antonio Arellano, Field Technician",
                "Працівник 7: Faye Richards, Marketing Manager",
                "Працівник 8: Holden Bradshaw, Product Manager",
                "Працівник 9: Berkley Nguyen, Data Analyst",
                "Працівник 10:Gabriel Vang Project, Manager",
                "Працівник 11:Madisyn Nielsen Chief, Executive Officer"
        };


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, tariffs);


        listView.setAdapter(adapter);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void backMain(View v){
        Intent intent = new Intent(this,MainPage.class);
        startActivity(intent);
    }
}