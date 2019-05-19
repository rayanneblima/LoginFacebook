package com.example.rayan.loginfacebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

public class Perfil extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        perfil();
    }

    private void perfil() {
        Intent intent = getIntent();
        String userID = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");

        TextView nameView = findViewById(R.id.txtNome);
        TextView emailView = findViewById(R.id.txtEmail);

        nameView.setText(" " + name);
        emailView.setText(email);

        ProfilePictureView profilePicture = findViewById(R.id.profileImage);
        profilePicture.setProfileId(userID);

    }

}
