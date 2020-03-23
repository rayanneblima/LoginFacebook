package com.example.rayanne.loginfacebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

public class PagLogin extends AppCompatActivity {

    LoginButton login_button;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        initializeControls();
        loginWithFB();
    }

    private void initializeControls(){
        callbackManager = CallbackManager.Factory.create();
        login_button = findViewById(R.id.login_button);
        login_button.setReadPermissions("email", "public_profile");
    }


    private void loginWithFB() {
        login_button.registerCallback(callbackManager, new
                FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        if (Profile.getCurrentProfile() != null) {
                            AccessToken accessToken = loginResult.getAccessToken();
                            Toast.makeText(getApplicationContext(), "Aguarde.", Toast.LENGTH_SHORT).show();
                            get_profile(accessToken);
                            //Login OK, session = true
                            SharedPref.save(getApplicationContext(), "session", "true");
                            Intent intent = new Intent(PagLogin.this, PagMenu.class);
                            startActivity(intent);
                            finish();
                        } else {
                            ProfileTracker profileTracker = new ProfileTracker() {
                                @Override
                                protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                                    stopTracking();
                                    Profile.setCurrentProfile(currentProfile);
                                    String firstName = currentProfile.getName();
                                    Toast.makeText(getApplicationContext(), "Bem-vindo(a), " + firstName + "!", Toast.LENGTH_LONG).show();
                                }
                            };
                            profileTracker.startTracking();
                        }
                    }

                    @Override
                    public void onCancel() {
                    }

                    @Override
                    public void onError(FacebookException error) {
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void get_profile(AccessToken accessToken){
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        //Intent profileIntent = new Intent(MainActivity.this, Perfil.class);
                        try {
                            String userID = object.getString("id");
                            SharedPref.saveUserId(getApplicationContext(), "user id", userID);
                            String firstname;
                            String lastname;
                            String email;

                            if (object.has("first_name") && object.has("last_name")) {
                                firstname = object.getString("first_name");
                                lastname = object.getString("last_name");
                                SharedPref.saveUserName(getApplicationContext(), "user name", firstname + " " + lastname);
                            }
                            if (object.has("email")){
                                email = object.getString("email");
                                SharedPref.saveUserEmail(getApplicationContext(), "user email", email);
                            }
                            //startActivity(profileIntent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}


