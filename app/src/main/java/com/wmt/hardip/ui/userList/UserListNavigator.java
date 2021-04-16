package com.wmt.hardip.ui.userList;

import java.util.ArrayList;

public interface UserListNavigator {
    void onFailCall(String s);
    void onSuccess( ArrayList<UserModel.dataObject.UsersArray> arrays,String success,int total);
}
