package com.example.root.jsonparsingwithrealm.customeradapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.jsonparsingwithrealm.CustomerListIterface;
import com.example.root.jsonparsingwithrealm.GoogleMapActivity;
import com.example.root.jsonparsingwithrealm.R;
import com.example.root.jsonparsingwithrealm.model.CustomerDetailsPojo;

import java.util.List;

/**
 * Created by root on 6/8/17.
 */

public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListViewHolder> implements CustomerListIterface {

    private Activity activity;
    private List<CustomerDetailsPojo> customerDetailsArrayTypePojos;
    private CustomerListIterface customerListIterface;

    public ListRecyclerViewAdapter(Activity mainActivity, List<CustomerDetailsPojo> customerDetailsArrayTypePojo, CustomerListIterface customerListIterface) {
        this.activity = mainActivity;
        this.customerDetailsArrayTypePojos = customerDetailsArrayTypePojo;
        this.customerListIterface = customerListIterface;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view, parent, false);


        return new ListViewHolder(view, customerListIterface);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {

        dataViewHolder((ListViewHolder) holder, position);

    }

    private void dataViewHolder(ListViewHolder holder, int position) {
        holder.nameTextView.setText(customerDetailsArrayTypePojos.get(position).getName());
        holder.phoneNumberTextView.setText(customerDetailsArrayTypePojos.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return customerDetailsArrayTypePojos.size();
    }

    @Override
    public void onItemClick(View view, int position) {
        /*if (view.getId() == R.id.item_parent_view) {
            Intent intent = new Intent(activity, GoogleMapActivity.class);
            intent.putExtra("data", customerDetailsArrayTypePojos);
            activity.startActivity(intent);
        }*/

    }
}
