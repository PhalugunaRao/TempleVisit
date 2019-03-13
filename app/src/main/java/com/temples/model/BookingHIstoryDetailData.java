package com.temples.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BookingHIstoryDetailData implements Parcelable {

    private String bookingId;
    private String  bookingNumber;
    private String  deliveryAddress;
    private String  deliveryCharges;
    private String  feeAmount;
    private boolean  homeDelivery;
    private String  numberOfPersons;
    private String personEmailId;
    private String personMobileNumber;
    private String  personName;
    private String  placeAddress;
    private String  placeName;
    private String typeOfPass;
    private String visitingDate;
    private String imageFileName;
    private List<PersonData> objVisitingPassMembers;

    protected BookingHIstoryDetailData(Parcel in) {
        bookingId = in.readString();
        bookingNumber = in.readString();
        deliveryAddress = in.readString();
        deliveryCharges = in.readString();
        feeAmount = in.readString();
        homeDelivery = in.readByte() != 0;
        numberOfPersons = in.readString();
        personEmailId = in.readString();
        personMobileNumber = in.readString();
        personName = in.readString();
        placeAddress = in.readString();
        placeName = in.readString();
        typeOfPass = in.readString();
        visitingDate = in.readString();
        imageFileName = in.readString();
        objVisitingPassMembers = in.createTypedArrayList(PersonData.CREATOR);
    }

    public static final Creator<BookingHIstoryDetailData> CREATOR = new Creator<BookingHIstoryDetailData>() {
        @Override
        public BookingHIstoryDetailData createFromParcel(Parcel in) {
            return new BookingHIstoryDetailData(in);
        }

        @Override
        public BookingHIstoryDetailData[] newArray(int size) {
            return new BookingHIstoryDetailData[size];
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
        dest.writeString(deliveryAddress);
        dest.writeString(deliveryCharges);
        dest.writeString(feeAmount);
        dest.writeByte((byte) (homeDelivery ? 1 : 0));
        dest.writeString(numberOfPersons);
        dest.writeString(personEmailId);
        dest.writeString(personMobileNumber);
        dest.writeString(personName);
        dest.writeString(placeAddress);
        dest.writeString(placeName);
        dest.writeString(typeOfPass);
        dest.writeString(visitingDate);
        dest.writeString(imageFileName);
        dest.writeTypedList(objVisitingPassMembers);
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

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(String deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public String getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(String feeAmount) {
        this.feeAmount = feeAmount;
    }

    public boolean isHomeDelivery() {
        return homeDelivery;
    }

    public void setHomeDelivery(boolean homeDelivery) {
        this.homeDelivery = homeDelivery;
    }

    public String getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(String numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public String getPersonEmailId() {
        return personEmailId;
    }

    public void setPersonEmailId(String personEmailId) {
        this.personEmailId = personEmailId;
    }

    public String getPersonMobileNumber() {
        return personMobileNumber;
    }

    public void setPersonMobileNumber(String personMobileNumber) {
        this.personMobileNumber = personMobileNumber;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public List<PersonData> getObjVisitingPassMembers() {
        return objVisitingPassMembers;
    }

    public void setObjVisitingPassMembers(List<PersonData> objVisitingPassMembers) {
        this.objVisitingPassMembers = objVisitingPassMembers;
    }

    public static class PersonData implements Parcelable{
        private String firstName;
        private String lastName;
        private String imageFileName;

        protected PersonData(Parcel in) {
            firstName = in.readString();
            lastName = in.readString();
            imageFileName = in.readString();
        }

        public static final Creator<PersonData> CREATOR = new Creator<PersonData>() {
            @Override
            public PersonData createFromParcel(Parcel in) {
                return new PersonData(in);
            }

            @Override
            public PersonData[] newArray(int size) {
                return new PersonData[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(firstName);
            dest.writeString(lastName);
            dest.writeString(imageFileName);
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getImageFileName() {
            return imageFileName;
        }

        public void setImageFileName(String imageFileName) {
            this.imageFileName = imageFileName;
        }
    }
}
