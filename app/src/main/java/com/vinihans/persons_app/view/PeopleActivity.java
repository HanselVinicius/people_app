package com.vinihans.persons_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vinihans.persons_app.R;
import com.vinihans.persons_app.view.adapters.PeopleAdapter;
import com.vinihans.persons_app.view.view_model.PeopleViewModel;
import com.vinihans.persons_app.view.view_model.RegisterActivity;

public class PeopleActivity extends AppCompatActivity {


    private static final String TAG = "PERSONSACTIVITY";

    private PeopleViewModel peopleViewModel;
    private RecyclerView recyclerView;
    private PeopleAdapter peopleAdapter;

    private String token;

    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);

        prepareScreen();

        this.peopleViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);

        Intent intent = getIntent();
        this.token = intent.getStringExtra("token");
        peopleViewModel.getAll(token);

        onTokenFailed();
        observePeople();

    }

    public void prepareScreen(){
        recyclerView = findViewById(R.id.recyclerViewPeople);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        peopleAdapter = new PeopleAdapter(null,this.token);
        recyclerView.setAdapter(peopleAdapter);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(view -> {
                Intent i = new Intent(PeopleActivity.this, RegisterActivity.class);
                startActivity(i);
        });
    }


    private void observePeople(){

        peopleViewModel.getListOfPeople().observe(this, people -> {
            if (people != null){
                peopleAdapter = new PeopleAdapter(people,this.token);
                recyclerView.setAdapter(peopleAdapter);
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
            }
        });
    }

}