package com.temples.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;

import org.apache.http.HttpEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class CustomMultipartRequest  extends Request<String> {
    private Response.Listener <String>mListener = null;
    private Response.ErrorListener mEListener;
    OnTaskCompleted listnerupload;
    private long cacheTimeToLive = 0;
    String method;
    private Map<String, String> header;
    HttpEntity entity;

    public interface OnTaskCompleted {
        void onTaskCompleted(String method, Object result);
    }

    public CustomMultipartRequest(OnTaskCompleted listenerUpload, String url, Response.ErrorListener eListener, Response.Listener<String> rListener, HttpEntity entity) {
        super(Method.POST, url, eListener);
        listnerupload=listenerUpload;
        mListener = rListener;
        this.method=url;
        this.mEListener = eListener;
        this.entity = entity;
    }
    @Override
    public String getBodyContentType() {
        return entity.getContentType().getValue();
    }
    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            entity.writeTo(bos);
        } catch (IOException e) {
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }
    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
        if (listnerupload!= null)
            listnerupload.onTaskCompleted(method, response);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        // TODO Auto-generated method stub
        try {
            if (response.data.length == 0) {
                byte[] responseData = "{}".getBytes("UTF8");
                response = new NetworkResponse(response.statusCode,
                        responseData, response.headers, response.notModified);
            }

            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(jsonString,
                    parseIgnoreCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    public Cache.Entry parseIgnoreCacheHeaders(NetworkResponse response) {
        long now = System.currentTimeMillis();

        Map<String, String> headers = response.headers;
        long serverDate = 0;
        String serverEtag = null;
        String headerValue;

        headerValue = headers.get("Date");
        if (headerValue != null) {
            serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
        }
        serverEtag = headers.get("ETag");

        final long cacheHitButRefreshed =15 * 60 * 1000; //Fifteen Minutes
        final long cacheExpired = cacheTimeToLive;
        final long softExpire = now + cacheHitButRefreshed;
        final long ttl = now + cacheExpired;

        Cache.Entry entry = new Cache.Entry();
        entry.data = response.data;
        entry.etag = serverEtag;
        entry.softTtl = softExpire;
        entry.ttl = ttl;
        entry.serverDate = serverDate;
        entry.responseHeaders = headers;

        return entry;
    }

    public Map<String, String> getHeader() {
        return header;
    }


    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
    }
}