package com.wmt.hardip.data.rest;


import com.wmt.hardip.data.local.PreferencesHelper;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Headers;

@Singleton
public class ApiHeader {

    private Headers headers;
    Map<String, String> stringStringMap;

    PreferencesHelper preferencesHelper;

    @Inject
    public ApiHeader(PreferencesHelper preferencesHelper) {
        this.preferencesHelper = preferencesHelper;
    }

    public Headers getHeaders() {
        stringStringMap = new HashMap<>();
        stringStringMap.put("Authorization", "Bearer "+preferencesHelper.getAuthToken());
        headers = Headers.of(stringStringMap);
        return headers;
    }
}