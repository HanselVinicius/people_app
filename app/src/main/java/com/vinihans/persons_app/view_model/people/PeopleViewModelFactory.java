package com.vinihans.persons_app.view_model.people;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PeopleViewModelFactory implements ViewModelProvider.Factory {

    private Application application;

    public PeopleViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PeopleViewModel.class)){
            return (T) new PeopleViewModel();
        }
        throw  new IllegalArgumentException("viewModel desconhecido");
    }
}
