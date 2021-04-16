package com.wmt.hardip.di.util;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.wmt.hardip.data.local.PreferencesHelper;
import com.wmt.hardip.data.rest.RepoService;
import com.wmt.hardip.ui.signup.SignUpViewModel;
import com.wmt.hardip.ui.userList.UserListViewModel;
import com.wmt.hardip.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final PreferencesHelper mPreferencesHelper;
    private final SchedulerProvider schedulerProvider;
    private final RepoService mRepoService;

    @Inject
    public ViewModelFactory(PreferencesHelper mPreferencesHelper, SchedulerProvider schedulerProvider, RepoService mRepoService) {
        this.mPreferencesHelper = mPreferencesHelper;
        this.schedulerProvider = schedulerProvider;
        this.mRepoService = mRepoService;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SignUpViewModel.class)) {
            return (T) new SignUpViewModel(mPreferencesHelper, mRepoService, schedulerProvider);
        }else if (modelClass.isAssignableFrom(UserListViewModel.class)) {
            return (T) new UserListViewModel(mPreferencesHelper, mRepoService, schedulerProvider);
        }
        throw new IllegalArgumentException("unknown model class " + modelClass);
    }
}