package com.wmt.hardip.data.rest;

import com.wmt.hardip.data.model.RegisterResponse;
import com.wmt.hardip.ui.userList.UserModel;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RepoService {

    @FormUrlEncoded
    @POST(EndPoint.API_REGISTER)
    Single<RegisterResponse> register(@Field("username") String username,
                                      @Field("email") String email,
                                      @Field("password") String password);

    @GET(EndPoint.API_USERS_LIST)
    Single<UserModel> getData(@Query("page") int page);

}
