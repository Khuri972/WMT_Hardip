package com.wmt.hardip.di.module;

import android.app.Application;
import android.content.Context;

import com.wmt.hardip.data.local.AppPreferencesHelper;
import com.wmt.hardip.data.local.PreferencesHelper;
import com.wmt.hardip.data.rest.ApiHeader;
import com.wmt.hardip.data.rest.EndPoint;
import com.wmt.hardip.data.rest.LoggingInterceptor;
import com.wmt.hardip.data.rest.RepoService;
import com.wmt.hardip.di.PreferenceInfo;
import com.wmt.hardip.utils.Config;
import com.wmt.hardip.utils.rx.AppSchedulerProvider;
import com.wmt.hardip.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class ApplicationModule {

    @Provides
    @PreferenceInfo
    String ProvidePreferenceName() {
        return Config.PrefName;
    }


    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit(OkHttpClient client) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder().baseUrl(EndPoint.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    static RepoService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(RepoService.class);
    }

    @Singleton
    @Provides
    OkHttpClient providesOkHttpClient(LoggingInterceptor interceptor) {
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    @Singleton
    LoggingInterceptor ProvidersLoggingInterceptor(Context context, ApiHeader apiHeader, PreferencesHelper preferencesHelper) {
        return new LoggingInterceptor(context,apiHeader,preferencesHelper);
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    ApiHeader provideProtectedApiHeader(PreferencesHelper preferencesHelper) {
        return new ApiHeader(preferencesHelper);
    }
}