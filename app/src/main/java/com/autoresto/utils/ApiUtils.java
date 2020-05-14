package com.autoresto.utils;

import com.autoresto.network.ApiClient;
import com.autoresto.network.ApiInterface;

public class ApiUtils {
    public static final String BASE_URL_API = "http://35.240.141.139:8083/api/v1/";

    // Mendeklarasikan Interface ApiInterface
    public static ApiInterface getAPIService(){
        return ApiClient.getClient(BASE_URL_API).create(ApiInterface.class);
    }
}
