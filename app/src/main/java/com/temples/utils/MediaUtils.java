package com.temples.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MediaUtils {public static float getWidthToHeightRatio(String url, Context context) {
    if (context == null)
        System.err.println("WARNING: You should call MediaMessage.setContexts(Context) before you pass the object to the fragment.");
    try {
        // TODO actually do this
        // final Bitmap image = Glide.with(context).load(url).get();
        // float width = image.getWidth();
        // float height = image.getHeight();
        // return width / height;
        return 1f;
    } catch (Exception e) {
        // System.err.println("WARNING: Something went wrong. Either the context is null or the image does not exist.");
        // e.printStackTrace();
        return 1f; // Try to make the program not crash anyway.
    }
}

    public static Bitmap getBitmap(Uri selectedimg, Context context) throws IOException {
        Uri imgUri = Uri.parse("file://"+selectedimg);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 3;
        AssetFileDescriptor fileDescriptor = null;
        fileDescriptor = context.getContentResolver().openAssetFileDescriptor(imgUri, "r");
        Bitmap original = BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, options);
        return original;
    }

    public static File savebitmap(Bitmap bitmap, String name) {
        OutputStream outStream = null;
        String destinationFilename = Environment.getExternalStorageDirectory().getPath() + "/eKincare/Images"
                + File.separatorChar + name;
        File file = new File(destinationFilename);
        try {
            outStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
        }
        return file;

    }

    public static HttpEntity getUploadEntity(String fileUrl, int profileId, String fileName) throws Exception {
        File file = new File(fileUrl);
        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        entity.addPart("file", new FileBody(file));
        //entity.addPart("name", new StringBody(file.getName()));
        //entity.addPart("id", new StringBody("" + profileId));
        return entity;
    }

    public static HttpEntity getUploadEntity2(String fileUrl, int profileId, String fileName) throws Exception {
        File file = new File(fileUrl);
        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        entity.addPart("files[]", new FileBody(file));
        entity.addPart("consultation_id", new StringBody(fileName));
        // entity.addPart("id", new StringBody("" + profileId));



        return entity;
    }
}
