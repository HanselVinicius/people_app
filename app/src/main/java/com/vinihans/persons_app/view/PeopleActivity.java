package com.vinihans.persons_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.vinihans.persons_app.R;
import com.vinihans.persons_app.view.view_model.PeopleViewModel;

public class PeopleActivity extends AppCompatActivity {


    private static final String TAG = "PERSONSACTIVITY";

    private PeopleViewModel peopleViewModel;

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);
        this.peopleViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);
        Intent intent = getIntent();
        this.token = intent.getStringExtra("token");
        peopleViewModel.getAll(token);
        onTokenFailed();
    }





    private void onTokenFailed(){
        MutableLiveData<Integer> errorMessage = peopleViewModel.getErrorMessage();
        errorMessage.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                onBackPressed();
            }
        });
    }






}