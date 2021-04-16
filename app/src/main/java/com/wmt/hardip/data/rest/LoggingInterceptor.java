package com.wmt.hardip.data.rest;

import android.content.Context;
import android.util.Log;

import com.wmt.hardip.data.local.PreferencesHelper;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class LoggingInterceptor implements Interceptor {


    Context context;

    PreferencesHelper mPreferencesHelper;
    ApiHeader apiHeader;

    public LoggingInterceptor(Context context, ApiHeader apiHeader, PreferencesHelper preferencesHelper) {
        this.context = context;
        mPreferencesHelper = preferencesHelper;
        this.apiHeader = apiHeader;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();
        Log.e("request", original.toString());
        HttpUrl originalHttpUrl = original.url();
        HttpUrl url;
        Request.Builder requestBuilder;

        url = originalHttpUrl.newBuilder().build();
        requestBuilder = original.newBuilder().url(url).headers(apiHeader.getHeaders());
        //requestBuilder = original.newBuilder().url(url).addHeader("Authorization",apiHeader.getHeaders());
        Request request = requestBuilder.build();
        Response response = chain.proceed(request);
        final String responseString = new String(response.body().bytes());
        return response.newBuilder().body(ResponseBody.create(response.body().contentType(), responseString)).build();
    }

}