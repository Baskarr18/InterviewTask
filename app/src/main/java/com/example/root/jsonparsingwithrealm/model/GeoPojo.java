package com.example.root.jsonparsingwithrealm.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by root on 6/8/17.
 */

public class GeoPojo implements Parcelable {
    private String lat;
    private String lng;

    public GeoPojo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    private GeoPojo(Parcel in) {
        lat = in.readString();
        lng = in.readString();
    }

    public static final Creator<GeoPojo> CREATOR = new Creator<GeoPojo>() {
        @Override
        public GeoPojo createFromParcel(Parcel in) {
            return new GeoPojo(in);
        }

        @Override
        public GeoPojo[] newArray(int size) {
            return new GeoPojo[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lat);
        dest.writeString(lng);
    }
}
