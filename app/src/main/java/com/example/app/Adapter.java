package com.example.app;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Holder> {

    Context context;
    List<Item> items;

    public Adapter(Context context, List<Item> items){
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.name.setText(items.get(position).getName());
        holder.position.setText(items.get(position).getPosition());
        float salary = (items.get(position).getSalary());
        holder.salary.setText(String.valueOf(salary));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindowMore(holder.itemView, position);
            }
        });

        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, items.size());
            }
        });

        holder.edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(holder.itemView, position);
            }
        });
    }

    private void showPopupWindow(View view, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_layout, null);
        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        EditText editName = popupView.findViewById(R.id.edit_name);
        EditText editPosition = popupView.findViewById(R.id.edit_position);
        EditText editSalary = popupView.findViewById(R.id.edit_salary);

        editName.setText(items.get(position).getName());
        editPosition.setText(items.get(position).getPosition());
        editSalary.setText(String.valueOf(items.get(position).getSalary()));

        Button saveButton = popupView.findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = items.get(position);
                item.setName(editName.getText().toString());
                item.setPosition(editPosition.getText().toString());
                item.setSalary(Float.parseFloat(editSalary.getText().toString()));

                notifyDataSetChanged();

                popupWindow.dismiss();
            }
        });

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }
    private void showPopupWindowMore(View view, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_layout_more, null);
        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        TextView editName = popupView.findViewById(R.id.more_name);
        TextView editPosition = popupView.findViewById(R.id.more_position);
        TextView editSalary = popupView.findViewById(R.id.more_salary);

        editName.setText(items.get(position).getName());
        editPosition.setText(items.get(position).getPosition());
        editSalary.setText(String.valueOf(items.get(position).getSalary()));
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ItemClickListener implements View.OnClickListener {
        private int position;

        public ItemClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            showPopupWindowMore(v, position);
        }
    }
}