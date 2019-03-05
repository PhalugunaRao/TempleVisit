package com.temples.details;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.temples.R;
import com.temples.dashboard.MainActivity;
import com.temples.model.PassModel;
import com.temples.model.TempleDetailsData;
import com.temples.network.NetworkHandlerController;
import com.temples.utils.CustomCircularProgress;
import com.temples.utils.CustomMultipartRequest;
import com.temples.utils.FileUtil;
import com.temples.utils.FileUtility;
import com.temples.utils.ImageUtility;
import com.temples.utils.PreferenceHelper;
import com.temples.utils.UrlData;
import com.temples.utils.VolleyRequestSingleton;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.Attributes;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static com.temples.utils.MediaUtils.getUploadEntity;
import static com.temples.utils.MediaUtils.savebitmap;
import static java.security.AccessController.getContext;
import android.util.Base64;

public class PackageDetailsActivity extends AppCompatActivity implements NetworkHandlerController.ResultListener{
    public static final int PERMISSION_REQUEST_CODE = 140;
    private static final int PICKFILE_RESULT_CODE = 105;
    static final int CAPTURE_IMAGE_REQUEST = 1;
    TextInputLayout textInputLayoutPassengerName;
    TextInputLayout textInputLayoutMobileNumber;
    TextInputLayout textInputLayoutsHouseNo;
    TextInputLayout textInputLayoutAddress;
    TextInputLayout textInputLayoutPincode;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPersons;


    EditText editTextPassengerName;
    EditText editTextMobileNumber;
    EditText editTextHouseNo;
    EditText editTextAddress;
    EditText editTextPincode;
    EditText editTextEmail;
    EditText editTextPersons;
    TextView datejourny;
    CheckBox homeDelivery;
    boolean myHomeDeliver = false;
    CardView homeCard;
    PassModel mPassModel;
    TextView place_name,placeAddress,placePrice,package_validate;
    String pName="",pMobile="",pDate="",pHouseNo="",pAddress="",pPinCode="",pEmail="",pCount="";
    File photoFile = null;

    private Calendar date;
    TextView proceed_pay;
    PreferenceHelper prefs;
    Bundle bundle;
    String templePassId;
    private Toolbar toolbar;
    TextView toolbarTextView;
    String  paybleAmount;
    ImageView uploadPic;
    private HttpEntity entity;
    private String mStringUri;
    private String path;
    String ba1;
    String imageFileName;


    LinearLayout package_price_view,home_collection_view,payment_gateway_charges_view;
    TextView total_amount_price,package_price;
    public static List<String> fileTypes = new ArrayList<String>(
            Arrays.asList(".doc", ".docx", ".pdf", ".zip", ".rar"));
    private String extension;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.package_details_activity);
        prefs=new PreferenceHelper(this);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            templePassId = bundle.getString("templePassId", "");
        }
        initView();
        setUpToolBar();
        editTextPname();
        editTextPmobile();
        editTextPemail();
        editTextPnumber();
        //editTextPhouseno();
        editTextPaddress();
       // editTextPincode();
        CustomCircularProgress.getInstance().show(this);
        Thread thread = new Thread(new GetPackageDetailsThread());
        thread.start();

        homeDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myHomeDeliver=true;
                    homeCard.setVisibility(View.VISIBLE);
                    paybleAmount=String.valueOf(Integer.parseInt(mPassModel.getFeeAmount())+3+1);
                    home_collection_view.setVisibility(View.VISIBLE);
                    total_amount_price.setText("$"+paybleAmount);
                }else{
                    myHomeDeliver=false;
                    homeCard.setVisibility(View.GONE);
                    paybleAmount=String.valueOf(Integer.parseInt(mPassModel.getFeeAmount())+3);
                    home_collection_view.setVisibility(View.GONE);
                    total_amount_price.setText("$"+paybleAmount);
                }
            }
        });

        datejourny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker();
            }
        });
        proceed_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validattionPay();
            }
        });


        uploadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    private void selectImage() {
            final CharSequence[] options = {"Take Photo", "Choose from Gallery",};
            AlertDialog.Builder builder = new AlertDialog.Builder(PackageDetailsActivity.this);
            builder.setTitle("Attach file or images");
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (options[item].equals("Take Photo")) {
                            prefs.setPREF_RUNTIME_PERMISSION("Camera");
                        setFloatingActionButtonCameraFunctionality();

                    } else if (options[item].equals("Choose from Gallery")) {
                        prefs.setPREF_RUNTIME_PERMISSION("File");
                        setFloatingActionButtonGalleryFunctionality();
                    }
                }
            });
            builder.show();

    }

    private void setFloatingActionButtonGalleryFunctionality() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!checkPermission()) {
                requestPermission();
            } else {
                readfileMemory();
            }
        } else {
            readfileMemory();
        }
    }

    public void readfileMemory() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!checkPermission()) {
                requestPermission();
            } else {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("*/*");
                startActivityForResult(intent, PICKFILE_RESULT_CODE);
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setType("*/*");
            startActivityForResult(intent, PICKFILE_RESULT_CODE);
        }

    }

    private void setFloatingActionButtonCameraFunctionality() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            aboveLollipopCaptureImage();
        } else {
            belowLollipopCaptureImage();
        }

    }
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, CAMERA}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean readAccept = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeAccept = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (readAccept && writeAccept) {
                        switch (prefs.getPREF_RUNTIME_PERMISSION()) {
                            case "Camera":
                                aboveLollipopCaptureImage();
                                break;
                            case "File":
                                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                intent.addCategory(Intent.CATEGORY_OPENABLE);
                                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                                intent.setType("*/*");
                                startActivityForResult(intent, PICKFILE_RESULT_CODE);
                                break;
                            default:
                                break;
                        }
                    }
                } else {
                    Toast.makeText(this, "Permission Denied, You cannot access  camera.", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }


    @SuppressLint("NewApi")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAPTURE_IMAGE_REQUEST:
                    firstTimeCamera();
                    break;
                case PICKFILE_RESULT_CODE:
                    try {
                        ClipData clipData = data.getClipData();
                        if (clipData != null) {
                            System.out.println("PackageDetailsActivity.onActivityResult==="+"noooo");
                            for (int i = 0; i < clipData.getItemCount(); i++) {
                                ClipData.Item item = clipData.getItemAt(i);
                                Uri uri = item.getUri();
                                mStringUri = uri.toString();
                                Bitmap myBitmap = BitmapFactory.decodeFile(mStringUri);
                                uploadPic.setImageBitmap(myBitmap);
                                getEncoded64ImageStringFromBitmap(myBitmap);
                                JSONObject object1 = new JSONObject();
                                object1.put("imgData",getEncoded64ImageStringFromBitmap(myBitmap));

                                uploadMYIMage(object1);
                                if (mStringUri.contains("content:")) {
                                    //checkPathExtensionAndUpload(uri, null);
                                } else if (mStringUri.contains("file:")) {
                                    entity = getUploadEntity(mStringUri, 0, "");
                                    uploadMultipleReport(entity);
                                }
                            }
                        } else {
                            System.out.println("PackageDetailsActivity.onActivityResult==="+"yeess");
                            if (data.getDataString() != null) {
                                Bitmap bm=null;
                                if (data != null) {
                                    try {
                                        bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                uploadPic.setImageBitmap(bm);
                                getEncoded64ImageStringFromBitmap(bm);
                                JSONObject object1 = new JSONObject();
                                object1.put("imgData",getEncoded64ImageStringFromBitmap(bm));

                                uploadMYIMage(object1);
                                String dataString = data.getDataString();
                                if (dataString.contains("content:")) {
                                    //checkPathExtensionAndUpload(null, data);
                                }
                            }
                        }
                    } catch (Exception e) {
                    }
                    break;
                default:
                    break;
            }
        }
    }


    private void checkPathExtensionAndUpload(Uri uri, Intent data) {
        if (uri == null)
            uri = data.getData();
        if (uri != null) {
            if (!FileUtility.isGoogleDriveDocument(uri)) {
                path = FileUtility.getPath(this, uri);
                if (path != null) {
                    extension = path.substring(path.lastIndexOf("."));
                }
                if (FileUtility.isImageType(extension)) {
                    uploadBitmap(path, uri);

                } else {
                    if (fileTypes.contains(extension)) {
                        try {
                            entity = getUploadEntity(path, 0, "");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        uploadMultipleReport(entity);
                    } else {
                        // throw unsupported format
                        Toast.makeText(this, "Not support format", Toast.LENGTH_SHORT).show();

                    }
                }
            } else {
                Toast.makeText(this, "Not support format", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void uploadBitmap(String path, Uri uri) {
        try {
            String fileName = path.substring(path.lastIndexOf("/"));
            File file = null;
            file = savebitmap(getBitmapFileUpload(uri), fileName);
            String url = FileUtil.decodeFile(file.getAbsolutePath(),
                    getResources().getInteger(R.integer.compressedHeightandWidth),
                    getResources().getInteger(R.integer.compressedHeightandWidth),
                    fileName);
            entity = getUploadEntity(path, 0, url);

            uploadMultipleReport(entity);
        } catch (IOException e) {
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private Bitmap getBitmapFileUpload(Uri selectedimg) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 3;
        AssetFileDescriptor fileDescriptor = null;
        fileDescriptor = getContentResolver().openAssetFileDescriptor(selectedimg, "r");
        assert fileDescriptor != null;
        return BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, options);
    }
    public void uploadMultipleReport(HttpEntity entity) {

            getUploadMultipleReportSatus("http://ticketingapp.nhealth.in/TicketBooking.svc/UploadBookingPersonPhoto", entity);

    }
    private CustomMultipartRequest.OnTaskCompleted listenerUpload = new CustomMultipartRequest.OnTaskCompleted() {

        @Override
        public void onTaskCompleted(String method, Object result) {
            if (result != null) {

            } else {
                // showAlert(getResources().getString(R.string.no_documents_available), MainActivity.this);
            }
        }
    };

    public void getUploadMultipleReportSatus(String methodName, HttpEntity entity) {
        try {

            CustomMultipartRequest jsObjRequest = new CustomMultipartRequest(listenerUpload, methodName,
                    new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {


                        }
                    }, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response != null) {
                        System.out.println("PackageDetailsActivity.onResponse==="+response.toString());



                    }

                }
            }, entity) {



            };

            jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleyRequestSingleton.getInstance(this).addToRequestQueue(jsObjRequest);


        } catch (NullPointerException e) {
        }

    }

    public void firstTimeCamera() {
        new Thread(new Runnable() {
            public void run() {
                final String path = photoFile.getAbsolutePath();



                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            Bitmap myBitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());

                            uploadPic.setImageBitmap(myBitmap);
                            //entity = getUploadEntity(path, 0, "");
                            System.out.println("PackageDetailsActivity.run==="+getEncoded64ImageStringFromBitmap(myBitmap));
                            getEncoded64ImageStringFromBitmap(myBitmap);
                            JSONObject object1 = new JSONObject();
                            object1.put("imgData",getEncoded64ImageStringFromBitmap(myBitmap));

                            uploadMYIMage(object1);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        //uploadMultipleReport(entity);
                    }
                });
            }
        }).start();


    }

    private void uploadMYIMage(JSONObject object1) {
        System.out.println("PackageDetailsActivity.postData==="+object1.toString());
        String URL = UrlData.UPLOAD_IMAGE;
        NetworkHandlerController.getInstance().volleyPOSTRequest(this, URL, Request.Method.POST, object1,
                this,
                "upload_image_data");
    }

    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = android.util.Base64.encodeToString(byteFormat, android.util.Base64.NO_WRAP);

        return imgString;
    }

    private void belowLollipopCaptureImage() {

        try {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            photoFile = ImageUtility.createImageFile(this, false);
            if (photoFile != null) {
                Uri photoURI = Uri.fromFile(photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, CAPTURE_IMAGE_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void aboveLollipopCaptureImage() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        } else {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                try {

                    photoFile = ImageUtility.createImageFile(this, true);
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(this,
                                getResources().getString(R.string.provider_name),
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, CAPTURE_IMAGE_REQUEST);
                    }
                } catch (Exception ex) {
                    // Error occurred while creating the File
                }
            }
        }
    }

    private void setUpToolBar() {
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void editTextPincode() {
        editTextPincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (editTextPincode.getText().toString().isEmpty()) {
                    Toast.makeText(PackageDetailsActivity.this, "Invalid Pincode", Toast.LENGTH_SHORT).show();
                }else{
                    pPinCode=editTextPincode.getText().toString();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void editTextPaddress() {
        editTextAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (editTextAddress.getText().toString().isEmpty()) {
                    Toast.makeText(PackageDetailsActivity.this, "Invalid Address", Toast.LENGTH_SHORT).show();
                }else{
                    pAddress=editTextAddress.getText().toString();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void editTextPhouseno() {
        editTextHouseNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (editTextHouseNo.getText().toString().isEmpty()) {
                    Toast.makeText(PackageDetailsActivity.this, "Invalid House Number", Toast.LENGTH_SHORT).show();
                }else{
                    pHouseNo=editTextHouseNo.getText().toString();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void editTextPnumber() {
        editTextPersons.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (editTextPersons.getText().toString().isEmpty()) {
                    Toast.makeText(PackageDetailsActivity.this, "Invalid Count", Toast.LENGTH_SHORT).show();
                }else{
                    pCount=editTextPersons.getText().toString();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void editTextPemail() {
        editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (editTextEmail.getText().toString().isEmpty()) {
                    Toast.makeText(PackageDetailsActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }else{
                    pEmail=editTextEmail.getText().toString();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void editTextPname() {
        editTextPassengerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (editTextPassengerName.getText().toString().isEmpty()) {
                    Toast.makeText(PackageDetailsActivity.this, "Invalid Passenger Name", Toast.LENGTH_SHORT).show();
                }else{
                    pName=editTextPassengerName.getText().toString();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void editTextPmobile() {
        editTextMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (editTextMobileNumber.getText().toString().isEmpty()) {
                    Toast.makeText(PackageDetailsActivity.this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();

                }else{
                    pMobile=editTextMobileNumber.getText().toString();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void validattionPay() {
                if(myHomeDeliver){
                withHomedelivery();
                }else{
                    withoutHomedelivery();
                }

        
    }

    private void withoutHomedelivery() {
        if(imageFileName.isEmpty()){
            Toast.makeText(PackageDetailsActivity.this, "Passenger Photo required", Toast.LENGTH_SHORT).show();
        }
        if (pName.isEmpty()) {
            Toast.makeText(PackageDetailsActivity.this, "Passenger Name required", Toast.LENGTH_SHORT).show();
        }
        if (pDate.isEmpty()) {
            Toast.makeText(PackageDetailsActivity.this, "journey date is required", Toast.LENGTH_SHORT).show();

        }
        if (pMobile.isEmpty()) {
            Toast.makeText(PackageDetailsActivity.this, "Mobile number is required", Toast.LENGTH_SHORT).show();

        }
        if (pEmail.isEmpty()) {
            Toast.makeText(PackageDetailsActivity.this, "Email is required", Toast.LENGTH_SHORT).show();

        }
        if (pCount.isEmpty()) {
            Toast.makeText(PackageDetailsActivity.this, "Number of persons required", Toast.LENGTH_SHORT).show();

        }
        else {
            proceedtoPayRequest();
        }

    }

    private void withHomedelivery() {
        if (pName.isEmpty()) {
            Toast.makeText(PackageDetailsActivity.this, "Passenger Name required", Toast.LENGTH_SHORT).show();
        }
        if (pDate.isEmpty()) {
            Toast.makeText(PackageDetailsActivity.this, "journey date is required", Toast.LENGTH_SHORT).show();

        }
        if (pMobile.isEmpty()) {
            Toast.makeText(PackageDetailsActivity.this, "Mobile number is required", Toast.LENGTH_SHORT).show();

        }
        if (pEmail.isEmpty()) {
            Toast.makeText(PackageDetailsActivity.this, "Email is required", Toast.LENGTH_SHORT).show();

        }
        if (pCount.isEmpty()) {
            Toast.makeText(PackageDetailsActivity.this, "Number of persons required", Toast.LENGTH_SHORT).show();

        }
       /* if (pHouseNo.isEmpty()) {
            Toast.makeText(PackageDetailsActivity.this, "House Number or Apartment required", Toast.LENGTH_SHORT).show();

        }*/
        if (pAddress.isEmpty()) {
            Toast.makeText(PackageDetailsActivity.this, "Address required", Toast.LENGTH_SHORT).show();

        }
        /*if (pPinCode.isEmpty()) {
            Toast.makeText(PackageDetailsActivity.this, "PinCode required", Toast.LENGTH_SHORT).show();

        }*/
        else {
            proceedtoPayRequest();
        }
    }

    private void proceedtoPayRequest() {

        JSONObject object = new JSONObject();
        try {

            if(myHomeDeliver){
                object.put("visitingDate", pDate);
                object.put("personName", pName);
                object.put("personMobileNumber", pMobile);
                object.put("personEmailId", pEmail);
                object.put("numberOfPersons", pCount);
                object.put("visitingPassId", mPassModel.getTemplePassId());
                object.put("homeDelivery", myHomeDeliver);
                object.put("deliveryAddress", pAddress);
                object.put("paymentGatewayCharges", 3);
                object.put("tokenId", prefs.getAppToken());
                object.put("deliveryCharges", 1);
                object.put("imageFileName",imageFileName);
            }else{
                object.put("visitingDate", pDate);
                object.put("personName", pName);
                object.put("personMobileNumber", pMobile);
                object.put("personEmailId", pEmail);
                object.put("numberOfPersons", pCount);
                object.put("visitingPassId", mPassModel.getTemplePassId());
                object.put("homeDelivery", myHomeDeliver);
                object.put("paymentGatewayCharges", 3);
                object.put("imageFileName",imageFileName);
                object.put("tokenId", prefs.getAppToken());
            }




        } catch (JSONException e1) {
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        postData(object);
    }

    private void postData(JSONObject object) {
        System.out.println("PackageDetailsActivity.postData==="+object.toString());
        String URL = UrlData.PROCEED_PAYMENT;
        NetworkHandlerController.getInstance().volleyPOSTRequest(this, URL, Request.Method.POST, object,
                this,
                "post_data_package");
    }

    public void showDateTimePicker(){
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                String mydate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                datejourny.setText(mydate);
                pDate=datejourny.getText().toString();
                System.out.println("PackageDetailsActivity.onDateSet==="+pDate);
            }
        };
        DatePickerDialog datePickerDialog = new  DatePickerDialog(PackageDetailsActivity.this, dateSetListener, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH),   currentDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void initView() {
        uploadPic=findViewById(R.id.upload_pic);
        toolbar = findViewById(R.id.common_toolbar);
        toolbarTextView=findViewById(R.id.common_toolbarText);
         textInputLayoutPassengerName=findViewById(R.id.package_passenger_name_float);
         textInputLayoutMobileNumber=findViewById(R.id.floating_mobile);
         textInputLayoutsHouseNo=findViewById(R.id.floating_house);
         textInputLayoutAddress=findViewById(R.id.floating_address);
         textInputLayoutPincode=findViewById(R.id.floating_pin_cod);

         editTextPassengerName=findViewById(R.id.edit_package_passenger_name);
         editTextMobileNumber=findViewById(R.id.edit_mobile);
         editTextHouseNo=findViewById(R.id.edit_house);
         editTextAddress=findViewById(R.id.edit_address);
         editTextPincode=findViewById(R.id.edit_pin_code);
         datejourny=findViewById(R.id.edit_date);
         homeDelivery=findViewById(R.id.home_collection_check_box);
         homeCard=findViewById(R.id.home_card);

        place_name=findViewById(R.id.place_name);
        placeAddress=findViewById(R.id.package_details_address);
        placePrice=findViewById(R.id.package_details_price);
        package_validate=findViewById(R.id.package_validate);
        textInputLayoutEmail=findViewById(R.id.floating_emial);
        textInputLayoutPersons=findViewById(R.id.floating_person);
        editTextEmail=findViewById(R.id.edit_email);
        editTextPersons=findViewById(R.id.edit_perosn);
        proceed_pay=findViewById(R.id.proceed_pay);

        package_price_view=findViewById(R.id.package_price_view);
        home_collection_view=findViewById(R.id.home_collection_view);
        payment_gateway_charges_view=findViewById(R.id.payment_gateway_charges_view);
        total_amount_price=findViewById(R.id.total_amount_price);
        package_price=findViewById(R.id.package_price);
    }
    private class GetPackageDetailsThread implements Runnable {
        @Override
        public void run() {
            getPackageDetails();
        }
    }

    private void getPackageDetails() {
        String url = UrlData.PACKAGE_DETAIL+templePassId;
        System.out.println("PackageDetailsActivity.getPackageDetails==="+url);
        NetworkHandlerController.getInstance().volleyGetRequestT(this, url,
                this, "package_details");

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissDialog();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dismissDialog();
    }
    public void dismissDialog() {
        if (!isFinishing() && CustomCircularProgress.getInstance() != null)
            CustomCircularProgress.getInstance().dismiss();
    }
    @Override
    public void onResult(boolean isSuccess, JSONObject resultObject, VolleyError volleyError, ProgressDialog progressDialog, String from) {
        dismissDialog();
        if(isSuccess){
            switch (from){
                case "upload_image_data":
                    System.out.println("PackageDetailsActivity.onResult==="+resultObject.toString());
                    if(isSuccess){
                        try {
                            imageFileName=resultObject.getString("imageFileName");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "post_data_package":
                    System.out.println("PackageDetailsActivity.onResult==="+resultObject.toString());
                    if(resultObject!=null){
                        try {
                            String statusCode=resultObject.getString("statusCode");
                            String bookingId =resultObject.getString("bookingId");
                            if(statusCode.equalsIgnoreCase("2")){
                                Intent intent = new Intent(PackageDetailsActivity.this, BookingSuccessActvity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("bookingId",bookingId);
                                startActivity(intent);

                            }else{
                                Toast.makeText(PackageDetailsActivity.this,"Something went wrong...",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }

                    break;

                case "package_details":
                    mPassModel = new Gson().fromJson(resultObject.toString(), PassModel.class);
                    if(mPassModel!=null){
                        toolbarTextView.setText(mPassModel.getTypeOfPass());
                        place_name.setText(mPassModel.getPlaceName());
                        placeAddress.setText(mPassModel.getPlaceAddress());
                        placePrice.setText("Price of Package $"+mPassModel.getFeeAmount());
                        package_validate.setText(mPassModel.getDescription());
                        package_price.setText("$"+mPassModel.getFeeAmount());
                        paybleAmount=String.valueOf(Integer.parseInt(mPassModel.getFeeAmount())+3);
                        total_amount_price.setText("$"+String.valueOf(Integer.parseInt(mPassModel.getFeeAmount())+3));

                    }
                    break;
                    default:
                        break;
            }
        }else{

        }
    }
}
