package com.temples.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PassModel implements Parcelable {
    private String  color;
    private String description;
    private String feeAmount;
    private String placeAddress;
    private String placeName;
    private String templePassId;
    private String typeOfPass;

    protected PassModel(Parcel in) {
        color = in.readString();
        description = in.readString();
        feeAmount = in.readString();
        placeAddress = in.readString();
        placeName = in.readString();
        templePassId = in.readString();
        typeOfPass = in.readString();
    }

    public static final Creator<PassModel> CREATOR = new Creator<PassModel>() {
        @Override
        public PassModel createFromParcel(Parcel in) {
            return new PassModel(in);
        }

        @Override
        public PassModel[] newArray(int size) {
            return new PassModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(color);
        dest.writeString(description);
        dest.writeString(feeAmount);
        dest.writeString(placeAddress);
        dest.writeString(placeName);
        dest.writeString(templePassId);
        dest.writeString(typeOfPass);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(String feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getTemplePassId() {
        return templePassId;
    }

    public void setTemplePassId(String templePassId) {
        this.templePassId = templePassId;
    }

    public String getTypeOfPass() {
        return typeOfPass;
    }

    public void setTypeOfPass(String typeOfPass) {
        this.typeOfPass = typeOfPass;
    }
}
