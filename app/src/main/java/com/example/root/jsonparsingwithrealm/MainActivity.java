package com.example.root.jsonparsingwithrealm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.root.jsonparsingwithrealm.customeradapter.ListRecyclerViewAdapter;
import com.example.root.jsonparsingwithrealm.model.CustomerDetailsPojo;
import com.example.root.jsonparsingwithrealm.realmdb.RealmController;
import com.example.root.jsonparsingwithrealm.utilities.SystemProgressDialog;
import com.example.root.jsonparsingwithrealm.webservices.WebService;
import com.example.root.jsonparsingwithrealm.webservices.WebServiceAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * This MainActivity class displays
 * the customer details in a list
 */


public class MainActivity extends Activity implements CustomerListIterface {

    RecyclerView listRecyclerView;
    SystemProgressDialog systemProgressDialog;
    ListRecyclerViewAdapter listRecyclerViewAdapter;
    private ArrayList<CustomerDetailsPojo> customerDetailsPojos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UIInitialisation();


    }

    /**
     * UI has been initialised
     */

    private void UIInitialisation() {
        listRecyclerView = (RecyclerView) findViewById(R.id.listRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listRecyclerView.setLayoutManager(linearLayoutManager);
        listRecyclerViewAdapter = new ListRecyclerViewAdapter(this, customerDetailsPojos, this);
        listRecyclerView.setAdapter(listRecyclerViewAdapter);
        systemProgressDialog = new SystemProgressDialog();

        webServiceDataFetch();
    }

    /**
     * This method is for retrieving the customer details from webservice
     */

    private void webServiceDataFetch() {
        systemProgressDialog.showDialog(this);
        WebServiceAPI webServiceAPI = WebService.getRetrofitInstance().create(WebServiceAPI.class);
        Call<List<CustomerDetailsPojo>> call = webServiceAPI.customerSupportDetailsFetch();
        call.enqueue(new Callback<List<CustomerDetailsPojo>>() {
            @Override
            public void onResponse(Call<List<CustomerDetailsPojo>> call, Response<List<CustomerDetailsPojo>> response) {
                systemProgressDialog.dismissDialog();

                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        try {
                            customerDetailsPojos.addAll(response.body());
                            RealmController.getInstance(MainActivity.this).insertCustomerDetailsInfo(customerDetailsPojos);
                            listRecyclerViewAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CustomerDetailsPojo>> call, Throwable t) {

            }
        });
    }

    /**
     * @param view     Selected item in the recycler view
     * @param position Selected position in recycler view
     */

    @Override
    public void onItemClick(View view, int position) {
        if (view.getId() == R.id.item_parent_view) {
            String itemClickDetails = customerDetailsPojos.get(position).getEmail() + "\n" + customerDetailsPojos.get(position).getName() + "\n" + customerDetailsPojos.get(position).getPhone() +
                    "\n" + customerDetailsPojos.get(position).getUsername() + "\n" + customerDetailsPojos.get(position).getWebsite() + "\n" + customerDetailsPojos.get(position).getAddress().getCity();
            Intent intent = new Intent(this, GoogleMapActivity.class);
            intent.putExtra("array_list", itemClickDetails);
            intent.putExtra("lat", "" + customerDetailsPojos.get(position).getAddress().getGeo().getLat());
            intent.putExtra("lan", customerDetailsPojos.get(position).getAddress().getGeo().getLng());
            Log.e("main_activity", "" + itemClickDetails);
            startActivity(intent);
        }
    }
}
