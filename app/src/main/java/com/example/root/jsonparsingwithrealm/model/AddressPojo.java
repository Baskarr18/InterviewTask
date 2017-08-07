package com.example.root.jsonparsingwithrealm.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by root on 6/8/17.
 */

public class AddressPojo implements Parcelable {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoPojo geo;

    protected AddressPojo(Parcel in) {
        street = in.readString();
        suite = in.readString();
        city = in.readString();
        zipcode = in.readString();
        geo = in.readParcelable(GeoPojo.class.getClassLoader());
    }

    public static final Creator<AddressPojo> CREATOR = new Creator<AddressPojo>() {
        @Override
        public AddressPojo createFromParcel(Parcel in) {
            return new AddressPojo(in);
        }

        @Override
        public AddressPojo[] newArray(int size) {
            return new AddressPojo[size];
        }
    };

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

    public GeoPojo getGeo() {
        return geo;
    }

    public void setGeo(GeoPojo geo) {
        this.geo = geo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(street);
        dest.writeString(suite);
        dest.writeString(city);
        dest.writeString(zipcode);
        dest.writeParcelable(geo, flags);
    }
}
