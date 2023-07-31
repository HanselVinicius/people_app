package com.vinihans.persons_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.vinihans.persons_app.R;
import com.vinihans.persons_app.model.people.People;
import com.vinihans.persons_app.view.adapters.PeopleAdapter;
import com.vinihans.persons_app.view.view_model.PeopleViewModel;

import java.util.List;

public class PeopleActivity extends AppCompatActivity {


    private static final String TAG = "PERSONSACTIVITY";

    private PeopleViewModel peopleViewModel;
    private RecyclerView recyclerView;
    private PeopleAdapter peopleAdapter;

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);

        recyclerView = findViewById(R.id.recyclerViewPeople);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        peopleAdapter = new PeopleAdapter(null);
        recyclerView.setAdapter(peopleAdapter);

        this.peopleViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);

        Intent intent = getIntent();
        this.token = intent.getStringExtra("token");
        peopleViewModel.getAll(token);

        onTokenFailed();
        observePeople();

    }



    private void observePeople(){
        peopleViewModel.getPeople().observe(this, new Observer<List<People>>() {
            @Override
            public void onChanged(List<People> people) {
                if (people != null){
                    peopleAdapter = new PeopleAdapter(people);
                    recyclerView.setAdapter(peopleAdapter);
                }
            }
        });
    }


    private void onTokenFailed(){
        MutableLiveData<Integer> errorMessage = peopleViewModel.getErrorMessage();
        errorMessage.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
//                Intent i = new Intent(PeopleActivity.this, MainActivity.class);
//                startActivity(i);
                finish();
                Log.e(TAG, "onChanged: testeeeeee" );
            }
        });
    }






}