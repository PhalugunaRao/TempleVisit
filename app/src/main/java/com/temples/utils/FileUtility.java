package com.temples.utils;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtility {
    protected static List<String> imageTypes = new ArrayList<String>(Arrays.asList(".jpg", ".png", ".jpeg", ".gif"));
        protected static String pdfExtension = ".pdf";

        public static File createDirIfNotExists(String path) {
            boolean ret = true;
            File file = null;
            if (Environment.getExternalStorageState() == null) {

                file = new File(Environment.getDataDirectory(), path);

            } else {
                file = new File(Environment.getExternalStorageDirectory(), path);

            }
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    ret = false;
                }
            }
            return file;

        }

        public static final String DOCUMENTS_DIR = "documents";

        public static File createDirs() {
            return createDirIfNotExists("/temples/download");
        }



        public static String getRealPathFromURI(Context context, Uri contentUri) {
            String result = null;
            Uri queryUri = MediaStore.Files.getContentUri("external");
            String columnData = MediaStore.Files.FileColumns.DATA;
            String columnSize = MediaStore.Files.FileColumns.SIZE;

            String[] projectionData = {MediaStore.Files.FileColumns.DATA};

            String name = null;
            String size = null;

            Cursor cursor = context.getContentResolver().query(contentUri, null, null, null, null);
            if ((cursor != null) && (cursor.getCount() > 0)) {
                int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
                cursor.moveToFirst();
                name = cursor.getString(nameIndex);
                size = cursor.getString(sizeIndex);
                cursor.close();
            }

            if ((name != null) && (size != null)) {
                String selectionNS = columnData + " LIKE '%" + name + "' AND " + columnSize + "='" + size + "'";

                Cursor cursorLike = context.getContentResolver().query(queryUri, projectionData, selectionNS, null, null);

                if ((cursorLike != null) && (cursorLike.getCount() > 0)) {
                    cursorLike.moveToFirst();
                    int indexData = cursorLike.getColumnIndex(columnData);
                    if (cursorLike.getString(indexData) != null) {
                        result = cursorLike.getString(indexData);
                    }
                    cursorLike.close();
                }
            }

            return result;

        }

private enum FileType {
    image, pdf, other
}

    public static FileType getFileType(String url) {
        String fileExtension = url.substring(url.lastIndexOf("."));
        if (isImageType(fileExtension)) {
            return FileType.image;
        } else if (isPdfType(fileExtension)) {
            return FileType.pdf;
        }
        return FileType.other;
    }

    public static boolean isImageType(String extension) {
        return imageTypes.contains(extension.toLowerCase());
    }

    public static boolean isPdfType(String extension) {
        return pdfExtension.equals(extension.toLowerCase());
    }


    @SuppressLint("NewApi")
    public static String getPath(Context context, Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);

                if (id != null && id.startsWith("raw:")) {
                    return id.substring(4);
                }

                String[] contentUriPrefixesToTry = new String[]{
                        "content://downloads/public_downloads",
                        "content://downloads/my_downloads"
                };

                for (String contentUriPrefix : contentUriPrefixesToTry) {
                    Uri contentUri = ContentUris.withAppendedId(Uri.parse(contentUriPrefix), Long.valueOf(id));
                    try {
                        String path = getDataColumn(context, contentUri, null, null);
                        if (path != null) {
                            return path;
                        }
                    } catch (Exception e) {}
                }

                // path could not be retrieved using ContentResolver, therefore copy file to accessible cache using streams
                String fileName = getFileName(context, uri);
                File cacheDir = getDocumentCacheDir(context);
                File file = generateFileName(fileName, cacheDir);
                String destinationPath = null;
                if (file != null) {
                    destinationPath = file.getAbsolutePath();
                    saveFileFromUri(context, uri, destinationPath);
                }

                return destinationPath;

            } else if (isGoogleDriveDocument(uri)) {

                try {
                    InputStream inputStream = context.getContentResolver().openInputStream(uri);
                    //File file = new File();

                } catch (FileNotFoundException e) {
                }

                String filePath = null;

                if (uri != null && "content".equals(uri.getScheme())) {
                    Cursor cursor = context.getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
                    cursor.moveToFirst();
                    filePath = cursor.getString(0);
                    filePath = "/storage/sdcard0/Android/data/com.google.android.apps.docs/" + filePath;
                    cursor.close();
                } else {
                    filePath = uri.getPath();
                }
                return filePath;
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isGoogleDriveDocument(Uri uri) {
        return uri.getAuthority().contains("com.google.android.apps.docs");
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static Bitmap getBitmapFileUpload(Context context, Uri selectedimg) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 3;
        AssetFileDescriptor fileDescriptor = null;
        fileDescriptor = context.getContentResolver().openAssetFileDescriptor(selectedimg, "r");
        return BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, options);
    }

    private File savebitmap(Bitmap bitmap, String name) {
        OutputStream outStream = null;
        String destinationFilename = Environment.getExternalStorageDirectory().getPath() + "/temples/Images"
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




    private static void saveFileFromUri(Context context, Uri uri, String destinationPath) {
        InputStream is = null;
        BufferedOutputStream bos = null;
        try {
            is = context.getContentResolver().openInputStream(uri);
            bos = new BufferedOutputStream(new FileOutputStream(destinationPath, false));
            byte[] buf = new byte[1024];
            is.read(buf);
            do {
                bos.write(buf);
            } while (is.read(buf) != -1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static File generateFileName( String name, File directory) {
        if (name == null) {
            return null;
        }

        File file = new File(directory, name);

        if (file.exists()) {
            String fileName = name;
            String extension = "";
            int dotIndex = name.lastIndexOf('.');
            if (dotIndex > 0) {
                fileName = name.substring(0, dotIndex);
                extension = name.substring(dotIndex);
            }

            int index = 0;

            while (file.exists()) {
                index++;
                name = fileName + '(' + index + ')' + extension;
                file = new File(directory, name);
            }
        }

        try {
            if (!file.createNewFile()) {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
        return file;
    }


    public static File getDocumentCacheDir(Context context) {
        File dir = new File(context.getCacheDir(), DOCUMENTS_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }



    public static String getFileName(Context context, Uri uri) {
        String mimeType = context.getContentResolver().getType(uri);
        String filename = null;

        if (mimeType == null && context != null) {
            String path = getPath(context, uri);
            if (path == null) {
                filename = getName(uri.toString());
            } else {
                File file = new File(path);
                filename = file.getName();
            }
        } else {
            Cursor returnCursor = context.getContentResolver().query(uri, null, null, null, null);
            if (returnCursor != null) {
                int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                returnCursor.moveToFirst();
                filename = returnCursor.getString(nameIndex);
                returnCursor.close();
            }
        }

        return filename;
    }

    public static String getName(String filename) {
        if (filename == null) {
            return null;
        }
        int index = filename.lastIndexOf('/');
        return filename.substring(index + 1);
    }
}