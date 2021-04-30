package com.beautify.qlog4j;

import android.app.Application;

import com.beautify.qlogger.QConfigurator;

import org.apache.log4j.Level;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        this.initLogger();
    }

    private void initLogger() {
        new QConfigurator().level(parseLevel())
                .filePath("sdcard/log")// /sdcard/Android/data/[package]/cache/demo.txt
                .maxBackupNumber(4)
                .maxFileSize(5 * 1024 *1024)
                .saveLogAsFile(BuildConfig.Q_LOG_FILE)
                .configure();
    }

    private Level parseLevel() {
        String level = BuildConfig.Q_LOG_LEVEL;
        if ("OFF".equals(level)) {
            return Level.OFF;
        } else if ("INFO".equals(level)) {
            return Level.INFO;
        } else if ("WARN".equals(level)) {
            return Level.WARN;
        } else if ("DEBUG".equals(level)) {
            return Level.DEBUG;
        } else if ("ERROR".equals(level)) {
            return Level.ERROR;
        } else if ("FATAL".equals(level)) {
            return Level.FATAL;
        } else if ("TRACE".equals(level)) {
            return Level.TRACE;
        } else {
            return Level.ALL;
        }
    }

}
