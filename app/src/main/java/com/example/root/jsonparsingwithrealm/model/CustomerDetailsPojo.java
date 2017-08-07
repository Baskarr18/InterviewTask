package com.example.root.jsonparsingwithrealm.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by root on 6/8/17.
 */

public class CustomerDetailsPojo implements Parcelable {

    private String id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private AddressPojo address;
    private CompanyPojo company;


    private CustomerDetailsPojo(Parcel in) {
        id = in.readString();
        name = in.readString();
        username = in.readString();
        email = in.readString();
        phone = in.readString();
        website = in.readString();
        address = in.readParcelable(AddressPojo.class.getClassLoader());
        company = in.readParcelable(CompanyPojo.class.getClassLoader());
    }

    public static final Creator<CustomerDetailsPojo> CREATOR = new Creator<CustomerDetailsPojo>() {
        @Override
        public CustomerDetailsPojo createFromParcel(Parcel in) {
            return new CustomerDetailsPojo(in);
        }

        @Override
        public CustomerDetailsPojo[] newArray(int size) {
            return new CustomerDetailsPojo[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public AddressPojo getAddress() {
        return address;
    }

    public void setAddress(AddressPojo address) {
        this.address = address;
    }

    public CompanyPojo getCompany() {
        return company;
    }

    public void setCompany(CompanyPojo company) {
        this.company = company;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(website);
        dest.writeParcelable(address, flags);
        dest.writeParcelable(company, flags);
    }
}
