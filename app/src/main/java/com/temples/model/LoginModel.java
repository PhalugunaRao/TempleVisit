package com.temples.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginModel implements Parcelable {

    private String emailId;
    private String fullName;
    private String mobileNumber;
    private String tokenId;

    protected LoginModel(Parcel in) {
        emailId = in.readString();
        fullName = in.readString();
        mobileNumber = in.readString();
        tokenId = in.readString();
    }

    public static final Creator<LoginModel> CREATOR = new Creator<LoginModel>() {
        @Override
        public LoginModel createFromParcel(Parcel in) {
            return new LoginModel(in);
        }

        @Override
        public LoginModel[] newArray(int size) {
            return new LoginModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(emailId);
        dest.writeString(fullName);
        dest.writeString(mobileNumber);
        dest.writeString(tokenId);
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
