package com.temples.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ParkModel  implements Parcelable {

    private List<TempleListData> objTempleEntityList;

    protected ParkModel(Parcel in) {
        objTempleEntityList = in.createTypedArrayList(TempleListData.CREATOR);
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
        dest.writeTypedList(objTempleEntityList);
    }

    public List<TempleListData> getObjTempleEntityList() {
        return objTempleEntityList;
    }

    public void setObjTempleEntityList(List<TempleListData> objTempleEntityList) {
        this.objTempleEntityList = objTempleEntityList;
    }

    public static class TempleListData  implements Parcelable{
        private String templeImage;
        private String templeId;
        private String templeName;
        private String aboutTemple;

        protected TempleListData(Parcel in) {
            templeImage = in.readString();
            templeId = in.readString();
            templeName = in.readString();
            aboutTemple = in.readString();
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
            dest.writeString(templeImage);
            dest.writeString(templeId);
            dest.writeString(templeName);
            dest.writeString(aboutTemple);
        }

        public String getTempleImage() {
            return templeImage;
        }

        public void setTempleImage(String templeImage) {
            this.templeImage = templeImage;
        }

        public String getTempleId() {
            return templeId;
        }

        public void setTempleId(String templeId) {
            this.templeId = templeId;
        }

        public String getTempleName() {
            return templeName;
        }

        public void setTempleName(String templeName) {
            this.templeName = templeName;
        }

        public String getAboutTemple() {
            return aboutTemple;
        }

        public void setAboutTemple(String aboutTemple) {
            this.aboutTemple = aboutTemple;
        }
    }
}
