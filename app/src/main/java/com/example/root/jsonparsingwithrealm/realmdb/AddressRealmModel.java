package com.example.root.jsonparsingwithrealm.realmdb;

import com.example.root.jsonparsingwithrealm.model.GeoPojo;

import io.realm.RealmObject;

/**
 * Created by root on 6/8/17.
 */

public class AddressRealmModel extends RealmObject {


    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoRealmModel geo;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public GeoRealmModel getGeo() {
        return geo;
    }

    public void setGeo(GeoRealmModel geo) {
        this.geo = geo;
    }
}
