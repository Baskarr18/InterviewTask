package com.example.root.jsonparsingwithrealm.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by root on 6/8/17.
 */

public class CompanyPojo implements Parcelable {
    private String name;
    private String catchPhrase;
    private String bs;

    public CompanyPojo(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    private CompanyPojo(Parcel in) {
        name = in.readString();
        catchPhrase = in.readString();
        bs = in.readString();
    }

    public static final Creator<CompanyPojo> CREATOR = new Creator<CompanyPojo>() {
        @Override
        public CompanyPojo createFromParcel(Parcel in) {
            return new CompanyPojo(in);
        }

        @Override
        public CompanyPojo[] newArray(int size) {
            return new CompanyPojo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(catchPhrase);
        dest.writeString(bs);
    }
}
