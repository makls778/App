package com.example.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



    public class Tarifs extends AppCompatActivity {

        private ListView listView;
        private Button addbut, delbut;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tarifs);

            listView = findViewById(R.id.list_view);
            addbut = findViewById(R.id.addbut);
            delbut = findViewById(R.id.delbut);

            String[] tariffs = new String[]{
                    "Тариф 1: Безліміт дзвінки, 10 GB інтернету",
                    "Тариф 2: 200 хвилин, 5 GB інтернету",
                    "Тариф 3: Безліміт інтернет",
                    "Тариф 4: Безліміт дзвінки",
                    "Тариф 5: 1000 хв 15GB",
                    "Тариф 6: Роумінг 100хв",
                    "Тариф 7: 20 GB 1500 хв",
                    "Тариф 8: 7 GB 400 хв",
                    "Тариф 9: 5 Gb 200 хв",
                    "Тариф 10: 2000 хв 25 GB",
                    "Тариф 11: Безліміт на місяць"

            };

            final ArrayAdapter<String>[] adapter = new ArrayAdapter[]{new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, tariffs)};
            listView.setAdapter(adapter[0]);

            addbut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String newTariff = "Новий тариф";
                    String[] newTarifs = new String[tariffs.length + 1];
                    System.arraycopy(tariffs, 0, newTarifs, 0, tariffs.length);
                    newTarifs[newTarifs.length - 1] = newTariff;


                    adapter[0] = new ArrayAdapter<>(Tarifs.this,
                            android.R.layout.simple_list_item_1, newTarifs);
                    listView.setAdapter(adapter[0]);
                }
            });

            delbut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = listView.getCheckedItemPosition();
                    if (position != ListView.INVALID_POSITION) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(Tarifs.this);
                        builder.setTitle("Підтвердження видалення")
                                .setMessage("Ви дійсно хочете видалити цей елемент?")
                                .setPositiveButton("Так", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        String[] newTarifs = new String[tariffs.length - 1];
                                        System.arraycopy(tariffs, 0, newTarifs, 0, position);
                                        System.arraycopy(tariffs, position + 1, newTarifs, position, tariffs.length - position - 1);
                                        adapter[0] = new ArrayAdapter<>(Tarifs.this,
                                                android.R.layout.simple_list_item_1, newTarifs);
                                        listView.setAdapter(adapter[0]);
                                        listView.clearChoices(); // Очистити вибір
                                    }
                                })
                                .setNegativeButton("Ні", null)
                                .show();
                    } else {
                        Toast.makeText(Tarifs.this, "Виберіть елемент для видалення", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        public void backMain(View v){
            Intent intent = new Intent(this,MainPage.class);
            startActivity(intent);
        }


    }




