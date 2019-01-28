package com.temples.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.temples.login.TestActivity;
import com.temples.utils.PreferenceHelper;
import com.temples.utils.VolleyRequestSingleton;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NetworkHandlerController {
    private final String TAG = "NetworkHandlerController";

    boolean showDialog = true;
    private ProgressDialog progressDialog;

    private static NetworkHandlerController controller = new NetworkHandlerController();

    public static NetworkHandlerController getInstance() {
        return controller;
    }


    /**
     * Call this method if you want to show/hide loader
     *
     * @param showDialog default value = true
     */
    public void showDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }


   /* public HashMap<String, String> getCustomHeaders(boolean checkFamily, Context context, PreferenceHelper prefs,
                                                    CustomerManager customerManager, String strFamilyMemberKey) {
        HashMap<String, String> customHeaders = new HashMap<String, String>();
        if (checkFamily) {
            if (strFamilyMemberKey == null || strFamilyMemberKey.equalsIgnoreCase("")) {
                strFamilyMemberKey = "";
            } else {
                if (customerManager.getCustomer().getIdentification_token().equalsIgnoreCase(strFamilyMemberKey)) {
                    strFamilyMemberKey = "";
                }
            }
            customHeaders.put("X-CUSTOMER-KEY", prefs.getCustomerKey());
            customHeaders.put("X-EKINCARE-KEY", prefs.getEkinKey());
            customHeaders.put("X-DEVICE-ID", CustomerManager.getDeviceID(context));
            if (!strFamilyMemberKey.equalsIgnoreCase(""))
                customHeaders.put("X-FAMILY-MEMBER-KEY", strFamilyMemberKey);
            customHeaders.put("Content-type", "application/json");
            customHeaders.put("Accept", "application/json");

        } else {
            customHeaders.put("X-CUSTOMER-KEY", prefs.getCustomerKey());
            customHeaders.put("X-EKINCARE-KEY", prefs.getEkinKey());
            customHeaders.put("X-DEVICE-ID", CustomerManager.getDeviceID(context));
            customHeaders.put("Content-type", "application/json");
            customHeaders.put("Accept", "application/json");
        }
        return customHeaders;
    }*/

    public void volleyGetRequest(Context context, String url,
                                 final HashMap<String, String> customHeader,
                                 final ResultListener resultListener, final String from) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, (JSONObject) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        resultListener.onResult(true, response, null, progressDialog, from);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        resultListener.onResult(false, null, error, progressDialog, from);
                        error.printStackTrace();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (customHeader != null) {
                    return customHeader;
                }

                return super.getHeaders();
            }
        };
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleyRequestSingleton.getInstance(context).addToRequestQueue(jsObjRequest);
    }

    public void volleyFirstPOSTRequest(Context context, String url, int method, JSONObject obj,
                                       final ResultListener resultListener, final String from, final PreferenceHelper prefs) {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(method, url, obj
                ,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        resultListener.onResult(true, response, null, progressDialog, from);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        resultListener.onResult(false, null, error, progressDialog, from);
                        error.printStackTrace();
                    }
                }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                return super.getHeaders();
            }
        };
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleyRequestSingleton.getInstance(context).addToRequestQueue(jsObjRequest);

    }

    public void volleyPOSTRequest(Context context, String url, int method, JSONObject
            obj, final ResultListener resultListener, final String from) {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(method, url, obj
                ,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        resultListener.onResult(true, response, null, progressDialog, from);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        resultListener.onResult(false, null, error, progressDialog, from);
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                Map<String, String> headers = response.headers;
                Set<String> keySet = headers.keySet();
                return super.parseNetworkResponse(response);
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                return super.getHeaders();
            }
        };
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleyRequestSingleton.getInstance(context).addToRequestQueue(jsObjRequest);

    }



    public void volleyFirstPOSTRequest(Context context, String url, int method, JSONObject obj, final HashMap<String, String> customHeader,
                                       final ResultListener resultListener, final String from, final PreferenceHelper prefs) {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(method, url, obj
                ,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        resultListener.onResult(true, response, null, progressDialog, from);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        resultListener.onResult(false, null, error, progressDialog, from);
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                Map<String, String> headers = response.headers;
                Set<String> keySet = headers.keySet();
                prefs.setEkinKey(headers.get("X-EKINCARE-KEY"));
                prefs.setCustomerKey(headers.get("X-CUSTOMER-KEY"));


                return super.parseNetworkResponse(response);
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (customHeader != null) {
                    return customHeader;
                }
                return super.getHeaders();
            }
        };
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleyRequestSingleton.getInstance(context).addToRequestQueue(jsObjRequest);

    }



    public interface ResultListener {
        void onResult(boolean isSuccess, JSONObject resultObject, VolleyError volleyError, ProgressDialog progressDialog, String from);
    }

    public static boolean isInternetOncheck(Context context) {
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (!(connec.getActiveNetworkInfo() != null && connec.getActiveNetworkInfo().isAvailable() && connec.getActiveNetworkInfo().isConnected())) {
            return false;
        } else {
            return true;
        }


    }

}

