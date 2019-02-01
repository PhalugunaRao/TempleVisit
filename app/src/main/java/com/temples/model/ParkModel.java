package com.temples.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ParkModel  implements Parcelable {

    private List<TempleListData> objVisitingPlacesInfoList;

    protected ParkModel(Parcel in) {
        objVisitingPlacesInfoList = in.createTypedArrayList(TempleListData.CREATOR);
    }

    public static final Creator<ParkModel> CREATOR = new Creator<ParkModel>() {
        @Override
        public ParkModel createFromParcel(Parcel in) {
            return new ParkModel(in);
        }

        @Override
        public ParkModel[] newArray(int size) {
            return new ParkModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(objVisitingPlacesInfoList);
    }

    public List<TempleListData> getObjVisitingPlacesInfoList() {
        return objVisitingPlacesInfoList;
    }

    public void setObjVisitingPlacesInfoList(List<TempleListData> objVisitingPlacesInfoList) {
        this.objVisitingPlacesInfoList = objVisitingPlacesInfoList;
    }

    public static class TempleListData  implements Parcelable{



        private String placeImage;
        private String placeName;
        private String visitingPlaceId;
        private double rating;
        private String visitedCount;
        private  String numberOfRatings;


        protected TempleListData(Parcel in) {
            placeImage = in.readString();
            placeName = in.readString();
            visitingPlaceId = in.readString();
            rating = in.readDouble();
            visitedCount = in.readString();
            numberOfRatings = in.readString();
        }

        public static final Creator<TempleListData> CREATOR = new Creator<TempleListData>() {
            @Override
            public TempleListData createFromParcel(Parcel in) {
                return new TempleListData(in);
            }

            @Override
            public TempleListData[] newArray(int size) {
                return new TempleListData[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(placeImage);
            dest.writeString(placeName);
            dest.writeString(visitingPlaceId);
            dest.writeDouble(rating);
            dest.writeString(visitedCount);
            dest.writeString(numberOfRatings);
        }

        public String getPlaceImage() {
            return placeImage;
        }

        public void setPlaceImage(String placeImage) {
            this.placeImage = placeImage;
        }

        public String getPlaceName() {
            return placeName;
        }

        public void setPlaceName(String placeName) {
            this.placeName = placeName;
        }

        public String getVisitingPlaceId() {
            return visitingPlaceId;
        }

        public void setVisitingPlaceId(String visitingPlaceId) {
            this.visitingPlaceId = visitingPlaceId;
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

        public String getNumberOfRatings() {
            return numberOfRatings;
        }

        public void setNumberOfRatings(String numberOfRatings) {
            this.numberOfRatings = numberOfRatings;
        }
    }
}
