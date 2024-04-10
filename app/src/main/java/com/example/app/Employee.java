package com.example.app;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Employee extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewEmployee);
        Button backE, addbutE;
        backE = findViewById(R.id.backE);
        addbutE = findViewById(R.id.addbutE);

        List<Item> items = new ArrayList<Item>();
        items.add(new Item("River West", "Sales manager", 40000f));
        items.add(new Item("Wilson Chase", " support specialist", 20000f));
        items.add(new Item("Miley Watson", "Telecommunications engineer", 25000f));
        items.add(new Item("Alec Norton", "Technical support specialist", 45000f));
        items.add(new Item("Trey Harper", "Marketer", 60000f));
        items.add(new Item("Kelly Pitts", "Advertising specialist", 22000f));
        items.add(new Item("Bexley Meyer", "PR specialist", 15000f));
        items.add(new Item("Nola Barrera", "Lawyer", 20000f));
        items.add(new Item("Clare Zavala", "QA", 35000f));
        items.add(new Item("Imani Medina", "Chief financial", 40000f));
        items.add(new Item("Giovanna Phan", "HR specialist", 40000f));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(getApplicationContext(), items));

        backE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushToMenu();
            }
        });
        addbutE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, items);
            }
        });
    }

    private void pushToMenu() {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }

    private void showPopupWindow(View view, List<Item> items) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_layout_add, null);
        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        EditText editName = popupView.findViewById(R.id.add_name);
        EditText editPosition = popupView.findViewById(R.id.add_position);
        EditText editSalary = popupView.findViewById(R.id.add_salary);
        Button saveButton = popupView.findViewById(R.id.add_save_button);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewEmployee);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String position = editPosition.getText().toString();
                float salary = Float.parseFloat(editSalary.getText().toString());
                items.add(new Item(name, position, salary));
                recyclerView.getAdapter().notifyItemInserted(items.size() - 1);

                popupWindow.dismiss();
            }
        });

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }



    public void backMain(View v){
        Intent intent = new Intent(this,MainPage.class);
        startActivity(intent);
    }
}