package com.example.rayanne.loginfacebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;

public class PagPerfil extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        iniciarMenu();
        perfil();
    }

    private void iniciarMenu(){
        Button button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PagPerfil.this, PagMenu.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void perfil() {
        String userID = SharedPref.readUserId(getApplicationContext(), "user id", " ");
        String name = SharedPref.readUserName(getApplicationContext(), "user name", " ");
        String email = SharedPref.readUserEmail(getApplicationContext(), "user email", " ");

        TextView id = findViewById(R.id.txtId);
        TextView nameView = findViewById(R.id.txtNome);
        TextView emailView = findViewById(R.id.txtEmail);

        id.setText(userID);
        nameView.setText(" " + name);
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
        Intent login = new Intent(PagPerfil.this, PagLogin.class);
        startActivity(login);
        finish();
        SharedPref.save(getApplicationContext(), "session", "false");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent login = new Intent(PagPerfil.this, PagMenu.class);
        startActivity(login);
    }

}
