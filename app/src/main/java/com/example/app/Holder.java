package com.example.app;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder{
    TextView name, position, salary;
    Button delete_btn, edit_btn;
    public Holder (@NonNull @org.jetbrains.annotations.NotNull View itemView){
        super(itemView);
        name = itemView.findViewById(R.id.name);
        position = itemView.findViewById(R.id.position);
        salary = itemView.findViewById(R.id.salary);
        delete_btn = itemView.findViewById(R.id.delete_btn);
        edit_btn = itemView.findViewById(R.id.edit_btn);
    }
}
