package com.beautify.qlog4j;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.beautify.qlogger.QLogger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QLogger.d("koo>>>debug----this is debug");
        QLogger.i("koo>>>info----this is info");
        QLogger.w("koo>>>warn----this is warning");
        QLogger.e("koo>>>error----this is error");
        QLogger.crash("koo>>>crash-----this is crash");
    }
}