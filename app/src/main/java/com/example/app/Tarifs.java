package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Tarifs extends AppCompatActivity {

    Button tarifback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifs);

        tarifback = findViewById(R.id.tarifback);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewProjects);

        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Tarif1", "100 min 1gb", 400f));
        items.add(new Item("Tarif2", "300 min 13gb", 200f));
        items.add(new Item("Tarif3", "100 min 2gb", 250f));
        items.add(new Item("Tarif4", "200 min 2gb", 500f));
        items.add(new Item("Tarif5", "300 min 3gb", 600f));
        items.add(new Item("Tarif6", "400 min 4gb", 220f));
        items.add(new Item("Tarif7", "500 min 5gb", 150f));
        items.add(new Item("Tarif8", "700 min 15gb", 200f));
        items.add(new Item("Tarif9", "800 min 12gb", 100f));
        items.add(new Item("Tarif10", "900 min 20gb", 900f));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(getApplicationContext(), items));
        tarifback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushToMenu();
            }
        });
    }

    private void pushToMenu() {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }


    public void backMain(View v) {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }


}




