package com.wmt.hardip.di.component;

import android.app.Application;


import com.wmt.hardip.base.BaseApplication;
import com.wmt.hardip.di.module.ActivityBindingModule;
import com.wmt.hardip.di.module.ApplicationModule;
import com.wmt.hardip.di.module.ServiceBuilderModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, ApplicationModule.class, ActivityBindingModule.class, ServiceBuilderModule.class})
public interface ApplicationComponent {

    void inject(BaseApplication app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}