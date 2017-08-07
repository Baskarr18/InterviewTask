package com.example.root.jsonparsingwithrealm.realmdb;

import com.example.root.jsonparsingwithrealm.model.AddressPojo;
import com.example.root.jsonparsingwithrealm.model.CompanyPojo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by root on 6/8/17.
 */

public class CustomerDetailsRealmModel extends RealmObject {

    // @PrimaryKey
    private String id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private AddressRealmModel address;
    private CompanyRealmModel company;

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

    public AddressRealmModel getAddress() {
        return address;
    }

    public void setAddress(AddressRealmModel address) {
        this.address = address;
    }

    public CompanyRealmModel getCompany() {
        return company;
    }

    public void setCompany(CompanyRealmModel company) {
        this.company = company;
    }
}
