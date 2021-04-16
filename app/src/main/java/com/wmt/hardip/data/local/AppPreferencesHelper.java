package com.wmt.hardip.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.wmt.hardip.di.PreferenceInfo;

import javax.inject.Inject;

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_ID = "PREF_KEY_USER_ID";
    private static final String PREF_KEY_AUTH_TOKEN = "PREF_KEY_AUTH_TOKEN";
    private static final String PREF_KEY_REFRESHTOKEN = "PREF_KEY_REFRESHTOKEN";
    private static final String PREF_KEY_USER_NAME = "PREF_KEY_USER_NAME";
    private static final String PREF_KEY_EMAIL = "PREF_KEY_EMAIL";
    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public void clearUserData() {
        SharedPreferences.Editor prefsPrivateEditor = mPrefs.edit();
        prefsPrivateEditor.clear();
        prefsPrivateEditor.apply();
    }

    @Override
    public int getUserId() {
        return mPrefs.getInt(PREF_KEY_USER_ID, 0);
    }

    @Override
    public void setUserId(int id) {
        mPrefs.edit().putInt(PREF_KEY_USER_ID, id).apply();
    }

    @Override
    public String getAuthToken() {
        return mPrefs.getString(PREF_KEY_AUTH_TOKEN, "");
    }

    @Override
    public void setAuthToken(String authToken) {
        mPrefs.edit().putString(PREF_KEY_AUTH_TOKEN, authToken).apply();
    }

    @Override
    public String getRefreshToken() {
        return mPrefs.getString(PREF_KEY_REFRESHTOKEN, "");
    }

    @Override
    public void setRefreshToken(String refreshToken) {
        mPrefs.edit().putString(PREF_KEY_REFRESHTOKEN, refreshToken).apply();
    }

    @Override
    public void setUserName(String userName) {
        mPrefs.edit().putString(PREF_KEY_USER_NAME, userName).apply();
    }

    @Override
    public String getUserName() {
        return mPrefs.getString(PREF_KEY_USER_NAME, "");
    }

    @Override
    public String getEmail() {
        return mPrefs.getString(PREF_KEY_EMAIL, "");
    }

    @Override
    public void setEmail(String email) {
        mPrefs.edit().putString(PREF_KEY_EMAIL, email).apply();
    }

}