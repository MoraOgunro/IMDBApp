package edu.mogunr2.project3app1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class WebPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page);
        //todo web view ask for internet permission
        WebView myWebView = findViewById(R.id.webView);
        myWebView.loadUrl(getIntent().getStringExtra("URL"));
        myWebView.setWebViewClient(new WebViewClient());

    }
}