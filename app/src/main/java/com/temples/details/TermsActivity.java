package com.temples.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.temples.R;

public class TermsActivity extends AppCompatActivity {
    WebView viewData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_conditions);

        viewData=findViewById(R.id.web);

        viewData.loadData(getString(R.string.hello), "text/html; charset=utf-8", "utf-8");

    }
}
