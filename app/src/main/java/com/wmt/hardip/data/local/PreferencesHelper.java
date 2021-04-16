package com.wmt.hardip.data.local;

public interface PreferencesHelper {
    void clearUserData();

    int getUserId();

    void setUserId(int id);

    String getAuthToken();

    void setAuthToken(String authToken);

    String getRefreshToken();

    void setRefreshToken(String refreshToken);

    void setUserName(String userName);

    String getUserName();

    String getEmail();

    void setEmail(String email);

}
