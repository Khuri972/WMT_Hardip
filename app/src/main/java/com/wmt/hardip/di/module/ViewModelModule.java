package com.wmt.hardip.di.module;


import androidx.lifecycle.ViewModelProvider;


import com.wmt.hardip.di.util.ViewModelFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
