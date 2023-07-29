package com.vinihans.persons_app.view.view_model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vinihans.persons_app.Application;
import com.vinihans.persons_app.model.people.People;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleViewModel extends ViewModel {

    private static final String TAG = "VIEW MODEL PEOPLE";
    private final MutableLiveData<Integer> errorMessage = new MutableLiveData<>();




    public void getAll(String token){
        Call<ResponseBody> call = Application.apiService.getAll(token);

        call.enqueue(
                new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            ResponseBody responseBody = response.body();

                            if (response.isSuccessful()) {

                                String json = responseBody.string();
                                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").create();
                                PeopleResponseModel responseModel = gson.fromJson(json, PeopleResponseModel.class);
                                List<People> peopleList = responseModel.getContent();

                                Log.e(TAG, "onResponse: people list " + peopleList.get(0).toString());

                            } else {
                                if (response.code() == 403) {
                                    errorMessage.postValue(403);
                                }
                            }
                        }catch (Exception ex){
                            Log.e(TAG, "onResponse: ERRO NO GETALL ",ex );
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e(TAG, "onResponse: fail  " + t.getMessage());
                    }
                }
        );
    }


    public MutableLiveData<Integer> getErrorMessage() {
        return errorMessage;
    }
}