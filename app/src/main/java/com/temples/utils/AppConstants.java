package com.temples.utils;

import android.os.Environment;

public class AppConstants {public final static String REPORT_URL = "ReportUrl";
    public final static String REPORT_TITLE = "ReportTitle";
    public final static String IMAGE_PATH = Environment.getExternalStorageDirectory().getPath() + "/temples/Images";
    public final static String PDF_PATH = Environment.getExternalStorageDirectory().getPath() + "/temples/PDF";
    public final static String LOCALPATH = "local_path";
    public static final int PREGNENCY_TRACKER_MAX_DATE_VALUE = 280;
}