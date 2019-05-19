package com.example.rayanne.loginfacebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.login.LoginManager;
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
        String surname = intent.getStringExtra("lastname");
        String email = intent.getStringExtra("email");

        TextView id = findViewById(R.id.txtId);
        TextView nameView = findViewById(R.id.txtNome);
        TextView emailView = findViewById(R.id.txtEmail);

        id.setText(userID);
        nameView.setText(" " + name + " " + surname);
        emailView.setText(email);

        ProfilePictureView profilePicture = findViewById(R.id.profileImage);
        profilePicture.setProfileId(userID);

        Button logout = findViewById(R.id.login_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }

    private void logout() {
        LoginManager.getInstance().logOut();
        Intent login = new Intent(Perfil.this, MainActivity.class);
        startActivity(login);
        finish();
    }


}
