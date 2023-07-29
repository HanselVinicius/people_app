package com.vinihans.persons_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vinihans.persons_app.R;
import com.vinihans.persons_app.infra.LoginCallback;
import com.vinihans.persons_app.model.user.User;
import com.vinihans.persons_app.view.view_model.UserViewModel;

public class MainActivity extends AppCompatActivity implements LoginCallback {

    private UserViewModel userViewModel;

    private EditText login;
    private EditText senha;

    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        setContentView(R.layout.activity_main);
        prepareScreen();
        verifyLogin();


    }


    public void prepareScreen(){
        this.login = findViewById(R.id.login_field);
        this.senha = findViewById(R.id.password_field);
        this.button = findViewById(R.id.login_btn);
        this.button.setOnClickListener(view -> userViewModel.login(new User(this.login.getText().toString(),this.senha.getText().toString()),this));

    }

    private void saveTokenToSharedPreferences(String token) {
        SharedPreferences sharedPreferences = getSharedPreferences("tokenPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    private void verifyLogin(){
        String token = getSharedPrefs();
        if (token  != null){
            navigateToPersons(token);
        }
    }

    private String getSharedPrefs(){
        SharedPreferences sharedPreferences = getSharedPreferences("tokenPref", Context.MODE_PRIVATE);
        return sharedPreferences.getString("token",null);
    }


    private void navigateToPersons(String token){
        Intent i = new Intent(MainActivity.this, PeopleActivity.class);
        i.putExtra("token",token);
        startActivity(i);
    }


    @Override
    public void onLoginSuccess(String token) {
        saveTokenToSharedPreferences(token);
        navigateToPersons(token);
    }

    @Override
    public void onLoginFailure(String errorMessage) {
        Toast.makeText(this.getApplicationContext(),errorMessage, Toast.LENGTH_SHORT).show();
    }
}