package com.vinihans.persons_app.view_model;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vinihans.persons_app.Application;
import com.vinihans.persons_app.infra.LoginCallback;
import com.vinihans.persons_app.model.user.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {

    private static final String TAG = "VIEW MODEL USER";

    public void login(User user, LoginCallback loginCallback){

        Call<ResponseBody> call = Application.apiService.login(user);
        call.enqueue(
                new Callback<>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {

                            if (response.isSuccessful()) {
                                String responseBody = response.body().string();
                                JsonObject jsonObject = new Gson().fromJson(responseBody, JsonObject.class);
                                String value = jsonObject.get("token").getAsString();
                                loginCallback.onLoginSuccess(value);
                            } else {
                                loginCallback.onLoginFailure("OCORREU UM ERRO NO LOGIN..");
                            }

                        } catch (Exception ex) {
                            Log.e(TAG, "onResponse: an error occured on retrive ", ex);
                            loginCallback.onLoginFailure("OCORREU UM ERRO NO LOGIN..");

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e(TAG, "onResponse: fail  " + t.getMessage());
                        loginCallback.onLoginFailure("OCORREU UM ERRO NO LOGIN..");
                    }
                }
        );
    }




}
