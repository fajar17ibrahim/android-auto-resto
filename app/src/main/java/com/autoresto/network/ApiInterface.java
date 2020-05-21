package com.autoresto.network;


import com.autoresto.model.Menu;
import com.autoresto.model.Order;
import com.autoresto.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("password") String password,
                                    @Field("role_id") int roleId);


    @GET("auth/profile")
    Call<User> getProfile(@Header("Authorization") String bearer);


    @GET("menu/by-category/1")
    Call<List<Menu>> getFoods(@Header("Authorization") String bearer);

    @GET("menu/by-category/2")
    Call<List<Menu>> getDrinks(@Header("Authorization") String bearer);

    @GET("order/signed-user/list-ordered")
    Call<List<Order>> getOrders(@Header("Authorization") String bearer);


}
