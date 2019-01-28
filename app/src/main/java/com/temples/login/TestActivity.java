package com.temples.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.Settings;
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
import com.temples.network.NetworkHandlerController;
import com.temples.utils.PreferenceHelper;
import com.temples.utils.UrlData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestActivity extends AppCompatActivity implements NetworkHandlerController.ResultListener {

    TextInputLayout textInputLayoutFirstName;
    TextInputLayout textInputLayoutPasswordNumber;
    TextInputLayout textInputLayoutsponserCode;
    EditText editTextRegisterName;
    EditText editTextRegisterPasssword;
    EditText editTextSponseCode;
    private TextView textViewRegister, button_register;
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
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                regesterValidation();
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
        } else if (editTextRegisterPasssword.getText().toString().length() < 8) {
            textInputLayoutPasswordNumber.setError("Password length must be 8");
        }
        if (editTextSponseCode.getText().toString().isEmpty()) {
            textInputLayoutsponserCode.setError("Sponsor code should not be empty");
        } else {
            regesterMethodCall();
        }
    }


    private void initViews() {
        textInputLayoutFirstName =  findViewById(R.id.floating_text_register_name);
        textInputLayoutPasswordNumber =  findViewById(R.id.floating_user_password);
        textInputLayoutsponserCode =  findViewById(R.id.floating_sponser_code);
        button_register =  findViewById(R.id.button_next_register);
        editTextRegisterName = findViewById(R.id.edit_text_register_user_name);
        editTextRegisterPasssword =  findViewById(R.id.edit_text_user_password);
        editTextSponseCode =  findViewById(R.id.edit_text_sponser_code);
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
                }/* else {
                    for (int i = start; i < before; i++) {
                        if (!Character.isLetter(s.charAt(i)) &&
                                !Character.toString(s.charAt(i)).equals("_") &&
                                !Character.toString(s.charAt(i)).equals("-") && !Character.toString(s.charAt(i)).equals("*") && !Character.toString(s.charAt(i)).equals("!")) {
                            textInputLayoutFirstName.setError("Special Characters and numbers are not allowed.");
                        }else {
                            textInputLayoutFirstName.setErrorEnabled(false);
                        }
                    }
                }*/
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void editTextRegisterSponserLogic() {
        editTextSponseCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextSponseCode.getText().toString().isEmpty()) {
                    textInputLayoutsponserCode.setError("Invalid Sponsor Code");
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
        JSONObject object2 = new JSONObject();
        JSONObject object = new JSONObject();
        try {

            object.put("first_name", editTextRegisterName.getText());
            object.put("last_name", "");
            //object.put("email", textRegisterEmail.getText());
            object.put("password", editTextRegisterPasssword.getText());
            object2.put("sponsor_code", editTextSponseCode.getText());

            object2.put("online_customer", object);
        } catch (JSONException e1) {
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        if (NetworkHandlerController.getInstance().isInternetOncheck(TestActivity.this)) {
            // mParcelabelClass= new Login();
            registerVolleyRequest(object2);
        } else {
            //No internet message
        }
    }

    private void registerVolleyRequest(JSONObject object2) {
        String URL = UrlData.SIGNUP;
       /* HashMap<String, String> customHeaders = NetworkHandlerController.getInstance().getCustomHeaders(false,
                this, prefs,  "");*/
        NetworkHandlerController.getInstance().volleyPOSTRequest(this, URL, Request.Method.POST, object2,
                 this,
                "register_request");

    }

    @Override
    public void onResult(boolean isSuccess, JSONObject resultObject, VolleyError volleyError, ProgressDialog progressDialog, String from) {

    }
}
