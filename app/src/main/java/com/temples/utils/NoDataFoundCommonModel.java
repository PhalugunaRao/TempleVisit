package com.temples.utils;

import static android.R.attr.id;

public class NoDataFoundCommonModel {
    String title = "";
    String discription = "";
    int resourceId ;
    String buttonText = "";
    String sampleText = "";

    public NoDataFoundCommonModel(String title, String discription, int resourceId, String buttonText, String sampleText) {
        this.title = title;
        this.discription = discription;
        this.resourceId = resourceId;
        this.buttonText = buttonText;
        this.sampleText = sampleText;
    }

    public String getButtonText() {
        return buttonText;
    }

    public String getSampleText() {
        return sampleText;
    }

    public String getTitle() {
        return title;
    }

    public String getDiscription() {
        return discription;
    }

    public int getResourceId() {
        return resourceId;
    }

    public int getId() {
        return id;
    }


}
