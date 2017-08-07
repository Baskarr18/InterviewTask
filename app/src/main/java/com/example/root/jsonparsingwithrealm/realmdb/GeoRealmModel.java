package com.example.root.jsonparsingwithrealm.realmdb;

import io.realm.RealmObject;

/**
 * Created by root on 6/8/17.
 */

public class GeoRealmModel extends RealmObject {

    private String lat;
    private String lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
