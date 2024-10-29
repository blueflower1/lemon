package com.lhw.lemon.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.lhw.lemon.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //设置默认横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);


        webView = findViewById(R.id.webView);

        WindowInsetsControllerCompat insetsController = WindowCompat.getInsetsController(getWindow(),webView);
        insetsController.hide(WindowInsetsCompat.Type.systemBars());

        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        //设置允许缓存
        settings.setDomStorageEnabled(true);

        Intent intent = getIntent();
        String videoUrl = intent.getStringExtra("videoUrl");

        webView.loadUrl(videoUrl);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
