package com.vinihans.persons_app;

import androidx.lifecycle.ViewModelProvider;

import com.vinihans.persons_app.infra.ApiService;
import com.vinihans.persons_app.view_model.people.PeopleViewModelFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Application extends android.app.Application {

   public static ApiService apiService;

   private final String url = "http://10.0.2.2:8080";

   private ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        viewModelFactory = new PeopleViewModelFactory(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }


    public ViewModelProvider.Factory getViewModelFactory() {
        return viewModelFactory;
    }
}
