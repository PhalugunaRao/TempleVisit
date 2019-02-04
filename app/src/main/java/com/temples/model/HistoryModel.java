package com.temples.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class HistoryModel implements Parcelable {


    private List<HistoryModelData> objBookingPassHistory;

    protected HistoryModel(Parcel in) {
        objBookingPassHistory = in.createTypedArrayList(HistoryModelData.CREATOR);
    }

    public static final Creator<HistoryModel> CREATOR = new Creator<HistoryModel>() {
        @Override
        public HistoryModel createFromParcel(Parcel in) {
            return new HistoryModel(in);
        }

        @Override
        public HistoryModel[] newArray(int size) {
            return new HistoryModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(objBookingPassHistory);
    }

    public List<HistoryModelData> getObjBookingPassHistory() {
        return objBookingPassHistory;
    }

    public void setObjBookingPassHistory(List<HistoryModelData> objBookingPassHistory) {
        this.objBookingPassHistory = objBookingPassHistory;
    }

    public static class HistoryModelData implements Parcelable{

        private String bookingId;
        private String bookingNumber;
        private String personName;
        private String placeName;
        private String typeOfPass;
        private String visitingDate;

        protected HistoryModelData(Parcel in) {
            bookingId = in.readString();
            bookingNumber = in.readString();
            personName = in.readString();
            placeName = in.readString();
            typeOfPass = in.readString();
            visitingDate = in.readString();
        }

        public static final Creator<HistoryModelData> CREATOR = new Creator<HistoryModelData>() {
            @Override
            public HistoryModelData createFromParcel(Parcel in) {
                return new HistoryModelData(in);
            }

            @Override
            public HistoryModelData[] newArray(int size) {
                return new HistoryModelData[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(bookingId);
            dest.writeString(bookingNumber);
            dest.writeString(personName);
            dest.writeString(placeName);
            dest.writeString(typeOfPass);
            dest.writeString(visitingDate);
        }

        public String getBookingId() {
            return bookingId;
        }

        public void setBookingId(String bookingId) {
            this.bookingId = bookingId;
        }

        public String getBookingNumber() {
            return bookingNumber;
        }

        public void setBookingNumber(String bookingNumber) {
            this.bookingNumber = bookingNumber;
        }

        public String getPersonName() {
            return personName;
        }

        public void setPersonName(String personName) {
            this.personName = personName;
        }

        public String getPlaceName() {
            return placeName;
        }

        public void setPlaceName(String placeName) {
            this.placeName = placeName;
        }

        public String getTypeOfPass() {
            return typeOfPass;
        }

        public void setTypeOfPass(String typeOfPass) {
            this.typeOfPass = typeOfPass;
        }

        public String getVisitingDate() {
            return visitingDate;
        }

        public void setVisitingDate(String visitingDate) {
            this.visitingDate = visitingDate;
        }
    }
}
