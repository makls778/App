package com.example.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Profile extends AppCompatActivity {
    private Button profileBack;
    private EditText profileName,  profileEmail, profileSurname;

    private ImageView profileAvatar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        String savedName = sharedPreferences.getString("name", "--");
        String savedSurname = sharedPreferences.getString("surname", "--");
        String savedEmail = sharedPreferences.getString("email", "--");
        String savedAvatar = sharedPreferences.getString("avatar", "--");

        profileAvatar = findViewById(R.id.profileAvatar);
        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profileSurname = findViewById(R.id.profileSurname);

        profileName.setText(savedName);
        profileEmail.setText(savedEmail);
        profileSurname.setText(savedSurname);
        try {
            if (!savedAvatar.equals("--")) {
                profileAvatar.setImageURI(Uri.parse(savedAvatar));
            }
        } catch (NullPointerException e) {
            Log.e("ProfileActivity", "Error setting profile image: " + e.getMessage());
            e.printStackTrace();
        }
        profileBack = findViewById(R.id.profileBack);

        profileBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (!profileName.getText().toString().isEmpty() && !profileEmail.getText().toString().isEmpty() && !profileSurname.getText().toString().isEmpty()){
                    editor.putString("name", profileName.getText().toString());
                    editor.putString("surname", profileSurname.getText().toString());
                    editor.putString("email", profileEmail.getText().toString());

                    editor.apply();
                    pushToMenu();
                } else {
                    showInstruction("Fill in all fields correctly!");
                }
            }
        });


        profileAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,3);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            profileAvatar.setImageURI(selectedImage);
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("avatar", selectedImage.toString());
            editor.apply();
        }
    }
    private void showInstruction(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    public void backMain(View v){
        Intent intent = new Intent(this,MainPage.class);
        startActivity(intent);
    }
    private void pushToMenu() {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }
}
