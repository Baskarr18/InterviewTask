package com.example.root.jsonparsingwithrealm.webservices;

import com.example.root.jsonparsingwithrealm.model.CustomerDetailsPojo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by root on 6/8/17.
 */

public interface WebServiceAPI {

    @GET("/users")
    Call<List<CustomerDetailsPojo>> customerSupportDetailsFetch();
}
