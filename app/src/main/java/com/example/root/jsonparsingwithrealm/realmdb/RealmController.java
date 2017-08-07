package com.example.root.jsonparsingwithrealm.realmdb;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.root.jsonparsingwithrealm.model.CustomerDetailsPojo;

import java.util.List;

import io.realm.Realm;

/**
 * Created by root on 6/8/17.
 */

public class RealmController {
    private static RealmController instance;
    private final Realm realm;


    private RealmController(Context context) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController getInstance(Context context) {
        if (instance == null) {
            instance = new RealmController(context);
            return instance;
        }
        return instance;
    }


    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    //clear all objects from Book.class
    public void clearAll() {

        realm.beginTransaction();
        realm.commitTransaction();
    }

    public void insertCustomerDetailsInfo(List<CustomerDetailsPojo> customerDetailsPojo) {
        realm.beginTransaction();


        for (int i = 0; i < customerDetailsPojo.size(); i++) {
            CustomerDetailsRealmModel customerDetailsRealmModel = realm.where(CustomerDetailsRealmModel.class).equalTo("id", customerDetailsPojo.get(i).getId()).findFirst();

            if (customerDetailsRealmModel == null) {
                customerDetailsRealmModel = realm.createObject(CustomerDetailsRealmModel.class);
            }

            customerDetailsRealmModel.setName(customerDetailsPojo.get(i).getName());
            customerDetailsRealmModel.setUsername(customerDetailsPojo.get(i).getUsername());
            customerDetailsRealmModel.setEmail(customerDetailsPojo.get(i).getEmail());
            customerDetailsRealmModel.setPhone(customerDetailsPojo.get(i).getPhone());
        }


    }


}
