package com.temples.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.temples.R;
import com.temples.dashboard.DashboardActivity;
import com.temples.dashboard.MainActivity;
import com.temples.network.NetworkHandlerController;
import com.temples.utils.UrlData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity  implements NetworkHandlerController.ResultListener{

    TextView signupLable,siginLable;
    TextInputLayout textInputLayoutName;
    TextInputLayout textInputLayoutPasswordNumber;

    EditText editTextRegisterName;
    EditText editTextRegisterPasssword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        editTextRegisterUserNameLogic();
        editTextRegisterPasswordLogic();

        signupLable.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        siginLable.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               loginValidation();
            }
        });
    }

    private void loginValidation() {
            if (editTextRegisterName.getText().toString().isEmpty()) {
                textInputLayoutName.setError("Username can't be empty");
            }
            if (editTextRegisterPasssword.getText().toString().isEmpty()) {
                textInputLayoutPasswordNumber.setError("Password can't be empty");
            }
            else {
                loginMethodCall();
            }
    }

    private void loginMethodCall() {
        JSONObject object = new JSONObject();
        try {
            object.put("userName", editTextRegisterName.getText());
            object.put("password", editTextRegisterPasssword.getText());
        } catch (JSONException e1) {
        }
        if (NetworkHandlerController.getInstance().isInternetOncheck(LoginActivity.this)) {
            loginVolleyRequest(object);
        } else {
            //No internet message
        }
    }

    private void loginVolleyRequest(JSONObject object) {
        String URL = UrlData.LOGIN_URL;
       /* HashMap<String, String> customHeaders = NetworkHandlerController.getInstance().getCustomHeaders(false,
                this, prefs,  "");*/
        NetworkHandlerController.getInstance().volleyPOSTRequest(this, URL, Request.Method.POST, object,
                this,
                "login_request");
    }

    private void initView() {
        textInputLayoutName =  findViewById(R.id.floating_text_register_name);
        textInputLayoutPasswordNumber =  findViewById(R.id.floating_user_password);
        editTextRegisterName = findViewById(R.id.edit_text_register_user_name);
        editTextRegisterPasssword =  findViewById(R.id.edit_text_user_password);
        signupLable=findViewById(R.id.instruction_button);
        siginLable=findViewById(R.id.button_next_register);
    }

    private void editTextRegisterUserNameLogic() {
        editTextRegisterName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (editTextRegisterName.getText().toString().isEmpty()) {
                    textInputLayoutName.setError("Invalid name");
                }else {
                    textInputLayoutName.setErrorEnabled(false);
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


    @Override
    public void onResult(boolean isSuccess, JSONObject resultObject, VolleyError volleyError, ProgressDialog progressDialog, String from) {
        if(isSuccess){
            System.out.println("LoginActivity.onResult==="+resultObject.toString());
            if(resultObject!=null){
                try {
                    String statusCode=resultObject.getString("statusCode");
                    String statusMessage=resultObject.getString("statusMessage");
                    System.out.println("RegisterActivity.onResult==="+statusCode);
                    switch (statusCode){
                        case "4":
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            break;
                        case  "1":
                        case  "2":
                        case  "0":
                        case  "3":
                            Toast.makeText(LoginActivity.this, statusMessage, Toast.LENGTH_SHORT).show();
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

