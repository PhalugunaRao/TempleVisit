package com.temples.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class TempleDetailsData implements Parcelable{
    private List<TemplePass> objTemplePasses;
    private List<String> placeImage;
    private String aboutPlace;
    private String numberOfRatings;
    private String openingHours;
    private String placeName;
    private double rating;
    private String visitedCount;
    private String visitingPlaceId;
    private String viewMore;

    protected TempleDetailsData(Parcel in) {
        objTemplePasses = in.createTypedArrayList(TemplePass.CREATOR);
        placeImage = in.createStringArrayList();
        aboutPlace = in.readString();
        numberOfRatings = in.readString();
        openingHours = in.readString();
        placeName = in.readString();
        rating = in.readDouble();
        visitedCount = in.readString();
        visitingPlaceId = in.readString();
        viewMore=in.readString();
    }

    public static final Creator<TempleDetailsData> CREATOR = new Creator<TempleDetailsData>() {
        @Override
        public TempleDetailsData createFromParcel(Parcel in) {
            return new TempleDetailsData(in);
        }

        @Override
        public TempleDetailsData[] newArray(int size) {
            return new TempleDetailsData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(objTemplePasses);
        dest.writeStringList(placeImage);
        dest.writeString(aboutPlace);
        dest.writeString(numberOfRatings);
        dest.writeString(openingHours);
        dest.writeString(placeName);
        dest.writeDouble(rating);
        dest.writeString(visitedCount);
        dest.writeString(visitingPlaceId);
        dest.writeString(viewMore);
    }

    public List<TemplePass> getObjTemplePasses() {
        return objTemplePasses;
    }

    public void setObjTemplePasses(List<TemplePass> objTemplePasses) {
        this.objTemplePasses = objTemplePasses;
    }

    public List<String> getPlaceImage() {
        return placeImage;
    }

    public void setPlaceImage(List<String> placeImage) {
        this.placeImage = placeImage;
    }

    public String getAboutPlace() {
        return aboutPlace;
    }

    public void setAboutPlace(String aboutPlace) {
        this.aboutPlace = aboutPlace;
    }

    public String getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(String numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getVisitedCount() {
        return visitedCount;
    }

    public void setVisitedCount(String visitedCount) {
        this.visitedCount = visitedCount;
    }

    public String getVisitingPlaceId() {
        return visitingPlaceId;
    }

    public void setVisitingPlaceId(String visitingPlaceId) {
        this.visitingPlaceId = visitingPlaceId;
    }

    public String getViewMore() {
        return viewMore;
    }

    public void setViewMore(String viewMore) {
        this.viewMore = viewMore;
    }

    public static class TemplePass implements Parcelable {
        private String description;
        private String feeAmount;
        private String templePassId;
        private String typeOfPass;
        private String color;

        protected TemplePass(Parcel in) {
            description = in.readString();
            feeAmount = in.readString();
            templePassId = in.readString();
            typeOfPass = in.readString();
            color=in.readString();
        }

        public static final Creator<TemplePass> CREATOR = new Creator<TemplePass>() {
            @Override
            public TemplePass createFromParcel(Parcel in) {
                return new TemplePass(in);
            }

            @Override
            public TemplePass[] newArray(int size) {
                return new TemplePass[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(description);
            dest.writeString(feeAmount);
            dest.writeString(templePassId);
            dest.writeString(typeOfPass);
            dest.writeString(color);
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

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
