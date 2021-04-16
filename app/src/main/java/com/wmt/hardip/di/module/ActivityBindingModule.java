package com.wmt.hardip.di.module;


import com.wmt.hardip.ui.signup.SignUPActivity;
import com.wmt.hardip.ui.userList.UserListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract SignUPActivity bindSignUpActivity();

    @ContributesAndroidInjector
    abstract UserListActivity bindUserListActivity();

}
