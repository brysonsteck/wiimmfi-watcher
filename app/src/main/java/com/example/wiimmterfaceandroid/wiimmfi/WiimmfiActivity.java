package com.example.wiimmterfaceandroid.wiimmfi;

import android.content.Intent;
import android.os.Bundle;
import org.jsoup.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wiimmterfaceandroid.R;

public class WiimmfiActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String friendCode;
        setContentView(R.layout.activity_wiimmfi);
    }
}
