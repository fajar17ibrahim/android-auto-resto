package com.autoresto.network;


import com.autoresto.model.ChangePassword;
import com.autoresto.model.Menu;
import com.autoresto.model.Order;
import com.autoresto.model.OrderDetail;
import com.autoresto.model.OrderSend;
import com.autoresto.model.Register;
import com.autoresto.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    @GET("order/signed-user/history_ordered")
    Call<List<Order>> getHistories(@Header("Authorization") String bearer);

    @GET("order-detail/get-details/{order_id}?join=menu")
    Call<List<OrderDetail>> getOrderDetails(@Header("Authorization") String bearer,@Path("order_id") int order_id);

    //POST METHOD
    @POST("auth/register")
    Call<Register> sendRegister(@Body Register register);

    @POST("order")
    Call<OrderSend> sendOrder(@Header("Authorization") String bearer, @Body OrderSend orderSend);

    @POST("auth/change-password")
    Call<ChangePassword> sendPassword(@Header("Authorization") String bearer, @Body ChangePassword changePassword);


}
