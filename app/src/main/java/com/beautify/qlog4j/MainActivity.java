package com.beautify.qlog4j;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.beautify.qlogger.QLogger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QLogger.d("koo---ddddddddddddddddddd");
        QLogger.i("koo---cccccccccccccccccccccccc");
        QLogger.e("koo---gggggggggggggggggggggggggggggggg");
        QLogger.w("koo---llllllllllllllllllllllllllllllllllllllll");
    }
}