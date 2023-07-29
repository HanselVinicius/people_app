package com.vinihans.persons_app.infra;

public interface LoginCallback {

    void onLoginSuccess(String token);
    void onLoginFailure(String errorMessage);

}
