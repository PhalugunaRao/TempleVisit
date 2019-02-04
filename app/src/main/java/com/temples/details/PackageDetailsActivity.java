package com.temples.details;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.temples.R;
import com.temples.model.PassModel;
import com.temples.model.TempleDetailsData;
import com.temples.network.NetworkHandlerController;
import com.temples.utils.CustomCircularProgress;
import com.temples.utils.PreferenceHelper;
import com.temples.utils.UrlData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.jar.Attributes;

public class PackageDetailsActivity extends AppCompatActivity implements NetworkHandlerController.ResultListener{
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


    private Calendar date;
    TextView proceed_pay;
    PreferenceHelper prefs;
    Bundle bundle;
    String templePassId;
    private Toolbar toolbar;
    TextView toolbarTextView;
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
        editTextPhouseno();
        editTextPaddress();
        editTextPincode();
        CustomCircularProgress.getInstance().show(this);
        Thread thread = new Thread(new GetPackageDetailsThread());
        thread.start();

        homeDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myHomeDeliver=true;
                    homeCard.setVisibility(View.VISIBLE);
                }else{
                    myHomeDeliver=false;
                    homeCard.setVisibility(View.GONE);
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
        if (pHouseNo.isEmpty()) {
            Toast.makeText(PackageDetailsActivity.this, "House Number or Apartment required", Toast.LENGTH_SHORT).show();

        }
        if (pAddress.isEmpty()) {
            Toast.makeText(PackageDetailsActivity.this, "Address required", Toast.LENGTH_SHORT).show();

        }
        if (pPinCode.isEmpty()) {
            Toast.makeText(PackageDetailsActivity.this, "PinCode required", Toast.LENGTH_SHORT).show();

        }
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
                object.put("deliveryAddress", pHouseNo+","+pAddress+","+pPinCode);
                //object.put("paymentGatewayCharges", 3);
                object.put("tokenId", prefs.getAppToken());
                object.put("deliveryCharges", 10);
            }else{
                object.put("visitingDate", pDate);
                object.put("personName", pName);
                object.put("personMobileNumber", pMobile);
                object.put("personEmailId", pEmail);
                object.put("numberOfPersons", pCount);
                object.put("visitingPassId", mPassModel.getTemplePassId());
                object.put("homeDelivery", myHomeDeliver);
                //object.put("paymentGatewayCharges", 3);
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

                    }
                    break;
                    default:
                        break;
            }
        }else{

        }
    }
}
