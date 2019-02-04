package com.temples.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.temples.R;
import com.temples.dashboard.MainActivity;
import com.temples.network.NetworkHandlerController;
import com.temples.utils.CustomCircularProgress;
import com.temples.utils.PreferenceHelper;
import com.temples.utils.UrlData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements NetworkHandlerController.ResultListener {

    TextInputLayout textInputLayoutFirstName;
    TextInputLayout textInputLayoutPasswordNumber;
    TextInputLayout textInputLayoutsMobile;
    TextInputLayout textInputLayoutEmailID;


    EditText editTextRegisterName;
    EditText editTextRegisterPasssword;
    EditText editTextMobileNumber;
    EditText editTextEmailID;
    private TextView textViewRegister, button_register,backlable;
    PreferenceHelper prefs;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_register);
        prefs = new PreferenceHelper(this);
        initViews();
        editTextRegisterUserNameLogic();
        editTextRegisterPasswordLogic();
        editTextRegisterSponserLogic();
        editTextRegisterEmailLogic();
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                regesterValidation();
            }
        });
        backlable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void regesterValidation() {
        if (editTextRegisterName.getText() != null) {
            Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$");
            Matcher matcher = pattern.matcher(editTextRegisterName.getText().toString());
            if (!matcher.matches()) {
                textInputLayoutFirstName.setError("Special characters are not allowed in username");
            } else {
                textInputLayoutFirstName.setErrorEnabled(false);
            }
        }

        if (editTextRegisterName.getText().toString().isEmpty()) {
            textInputLayoutFirstName.setError("Username can't be empty");
        }

        if (editTextRegisterPasssword.getText().toString().isEmpty()) {
            textInputLayoutPasswordNumber.setError("Password can't be empty");
        }

        if (editTextMobileNumber.getText().toString().isEmpty()) {
            textInputLayoutsMobile.setError("Invalid Mobile Number");
        }

        if (!editTextEmailID.getText().toString().matches(UrlData.emailPATTERN) || editTextEmailID.getText().toString().isEmpty()) {
            textInputLayoutEmailID.setError("Invalid Email id");
        }

        else {
            regesterMethodCall();
        }
    }


    private void initViews() {
        textInputLayoutFirstName =  findViewById(R.id.floating_text_register_name);
        textInputLayoutPasswordNumber =  findViewById(R.id.floating_user_password);
        textInputLayoutsMobile =  findViewById(R.id.floating_mobile_number);
        textInputLayoutEmailID=findViewById(R.id.floating_email_id);
        button_register =  findViewById(R.id.button_next_register);
        editTextRegisterName = findViewById(R.id.edit_text_register_user_name);
        editTextRegisterPasssword =  findViewById(R.id.edit_text_user_password);
        editTextMobileNumber =  findViewById(R.id.edit_text_mobile_number);
        editTextEmailID=findViewById(R.id.edit_text_email_id);
        backlable=findViewById(R.id.back_lable);

    }

    private void editTextRegisterUserNameLogic() {
        editTextRegisterName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (editTextRegisterName.getText().toString().isEmpty()) {
                    textInputLayoutFirstName.setError("Invalid name");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void editTextRegisterEmailLogic(){

        editTextEmailID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextEmailID.getText().toString().isEmpty()) {
                    textInputLayoutsMobile.setError("Invalid Email id");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    private void editTextRegisterSponserLogic() {
        editTextMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextMobileNumber.getText().toString().isEmpty()) {
                    textInputLayoutsMobile.setError("Invalid Sponsor Code");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void editTextRegisterPasswordLogic() {
        editTextRegisterPasssword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextRegisterPasssword.getText().toString().isEmpty()) {
                    textInputLayoutPasswordNumber.setError("Invalid Password");
                } else {
                    textInputLayoutPasswordNumber.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void regesterMethodCall() {

         JSONObject object = new JSONObject();
        try {

            object.put("fullName", editTextRegisterName.getText());
            object.put("emailId", editTextEmailID.getText());
            object.put("password", editTextRegisterPasssword.getText());
            object.put("mobileNumber", editTextMobileNumber.getText());

        } catch (JSONException e1) {
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        if (NetworkHandlerController.getInstance().isInternetOncheck(RegisterActivity.this)) {
            // mParcelabelClass= new Login();
            registerVolleyRequest(object);
        } else {
            //No internet message
        }
    }

    private void registerVolleyRequest(JSONObject object2) {
        System.out.println("RegisterActivity.registerVolleyRequest==="+object2.toString());
        String URL = UrlData.SIGNUP;
       /* HashMap<String, String> customHeaders = NetworkHandlerController.getInstance().getCustomHeaders(false,
                this, prefs,  "");*/
        NetworkHandlerController.getInstance().volleyPOSTRequest(this, URL, Request.Method.POST, object2,
                 this,
                "register_request");

    }
    public void dismissDialog() {
        if (!isFinishing() && CustomCircularProgress.getInstance() != null)
            CustomCircularProgress.getInstance().dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onResult(boolean isSuccess, JSONObject resultObject, VolleyError volleyError, ProgressDialog progressDialog, String from) {
        if(isSuccess){
            System.out.println("RegisterActivity.onResult==="+resultObject.toString());
            if(resultObject!=null){
                try {
                    String statusCode=resultObject.getString("statusCode");
                    String statusMessage=resultObject.getString("statusMessage");
                    System.out.println("RegisterActivity.onResult==="+statusCode);
                    switch (statusCode){
                        case "3":
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            break;
                        case  "1":
                        case  "2":
                        case  "0":
                            Toast.makeText(RegisterActivity.this, statusMessage, Toast.LENGTH_SHORT).show();
                            break;
                        default:
                           break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }else{

        }
    }
}
