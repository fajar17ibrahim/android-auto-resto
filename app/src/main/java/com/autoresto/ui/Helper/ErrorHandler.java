package com.autoresto.ui.Helper;

import android.text.Annotation;

import com.autoresto.model.ErrorResponse;
import com.autoresto.network.ApiClient;
import com.autoresto.utils.ApiUtils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorHandler {
//    public static ErrorResponse parseError(Response<?> response) {
//        Converter<ResponseBody, ErrorResponse> converter =
//                ApiClient.getClient(ApiUtils.BASE_URL_API).create(A)
//                        .responseBodyConverter(ErrorResponse.class, new Annotation[0]);
//
//        ErrorResponse error;
//
//        try {
//            error = converter.convert(response.errorBody());
//        } catch (IOException e) {
//            return new ErrorResponse();
//        }
//
//        return error;
//    }
}
