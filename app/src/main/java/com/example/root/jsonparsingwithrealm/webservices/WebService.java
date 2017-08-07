package com.example.root.jsonparsingwithrealm.webservices;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

/**
 * Created by root on 6/8/17.
 */


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService implements WebServiceConstants {

    private static WebService mnrWebRequest;

    // For printing log
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static WebService getInstance() {
        if (mnrWebRequest == null) {
            mnrWebRequest = new WebService();
        }
        return mnrWebRequest;
    }

    public static Retrofit getRetrofitInstance() {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        //Basic Authontication
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        // Timeout handling
        OkHttpClient client = httpClient.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();


        return new Retrofit.Builder().baseUrl(DEV_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(new MNRErrorHandlingCallAdapter.ErrorHandlingCallAdapterFactory())
                .client(client).build();
    }


}
