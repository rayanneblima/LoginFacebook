package com.example.rayanne.loginfacebook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.login.LoginManager;

public class PagMenu extends AppCompatActivity {

    boolean session;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagmenu);
        SESSION();
    }

    public void SESSION() {
        //Setando FALSE porque é o primeiro login
        session = Boolean.parseBoolean(SharedPref.read(getApplicationContext(), "session", "false"));

        if(!session) {
            //Primeiro login ou logout
            Intent intent = new Intent(getApplicationContext(), PagLogin.class);
            startActivity(intent);
            finish();
        } else {
            //Quando o usuario ja logou, assume valor TRUE
            Toast.makeText(this, "Login já realizado!", Toast.LENGTH_SHORT).show();
        }

    }

    //Menu de "3 pontinhos"
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.user_profile){
            Intent intent = new Intent(this, PagPerfil.class);
            startActivity(intent);
            finish();
        }
        else{
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        LoginManager.getInstance().logOut();
        Intent login = new Intent(PagMenu.this, PagLogin.class);
        startActivity(login);
        finish();
        SharedPref.save(getApplicationContext(), "session", "false");
    }

    public void onBackPressed(){
        popup_Cancel();
    }

    public void popup_Cancel() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define a mensagem
        builder.setMessage("Deseja fechar o aplicativo?");
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        //cria o AlertDialog
        //atributo da classe.
        AlertDialog alerta = builder.create();
        //Exibe
        alerta.show();
    }

}