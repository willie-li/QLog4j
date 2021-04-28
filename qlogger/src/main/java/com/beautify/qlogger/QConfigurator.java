package com.beautify.qlogger;

import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.helpers.LogLog;

import java.io.File;
import java.io.IOException;

import de.mindpipe.android.logging.log4j.LogCatAppender;

public class QConfigurator {
    private Level level = Level.ALL; // 根等级，初始比较等级
    private boolean internalDebugging = true; // 内部调试
    private boolean resetConfiguration = true; // 重新设置配置清单

    private String logCatPattern = "%m%n"; // 日志模式
    private boolean useLogcatAppender = true; // 使用Logcat输出器

    private int maxBackupNumber = 5; // 最大备份数量
    private String filePath = ""; // 文件路径
    private String fileName = "output.log"; // 文件名字
    private boolean isSaveLogAsFile = true; // 是否保存log日志文件
    private boolean immediateFlush = true;  // 马上刷新
    private long maxFileSize = 5 * 1024 * 1024; // 最大文件大小
    private String filePattern = "%d{yyyy/MM/dd HH:mm:ss,SSS}:%m%n";  // 文件输出模式

    public QConfigurator() {

    }

    /**
     * 日志等级
     * @param level
     * @return
     */
    public QConfigurator level(Level level) {
        this.level = level;
        return this;
    }

    /**
     * 是否为内部调试
     * @param internalDebugging
     * @return
     */
    public QConfigurator internalDebugging(boolean internalDebugging) {
        this.internalDebugging = internalDebugging;
        return this;
    }

    /**
     * 是否重新设置
     * @param resetConfiguration
     * @return
     */
    public QConfigurator resetConfiguration(boolean resetConfiguration) {
        this.resetConfiguration = resetConfiguration;
        return this;
    }

    /**
     * 日志模式
     * @param logCatPattern
     * @return
     */
    public QConfigurator logCatPattern(String logCatPattern) {
        this.logCatPattern = logCatPattern;
        return this;
    }

    /**
     * 是否使用Logcat输出器
     * @param useLogcatAppender
     * @return
     */
    public QConfigurator useLogcatAppender(boolean useLogcatAppender) {
        this.useLogcatAppender = useLogcatAppender;
        return this;
    }

    /**
     * 输出文件路径
     * @param filePath
     * @return
     */
    public QConfigurator filePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    /**
     * 文件格式
     * @param filePattern
     * @return
     */
    public QConfigurator filePattern(String filePattern) {
        this.filePattern = filePattern;
        return this;
    }

    /**
     * 最大文件大小
     * @param maxFileSize
     * @return
     */
    public QConfigurator maxFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
        return this;
    }

    /**
     * 备份文件数量
     * @param maxBackupSize
     * @return
     */
    public QConfigurator maxBackupNumber(int maxBackupSize) {
        this.maxBackupNumber = maxBackupSize;
        return this;
    }

    /**
     * 是否立即刷新
     * @param immediateFlush
     * @return
     */
    public QConfigurator immediateFlush(boolean immediateFlush) {
        this.immediateFlush = immediateFlush;
        return this;
    }

    public QConfigurator saveLogAsFile(boolean useFileAppender) {
        this.isSaveLogAsFile = useFileAppender;
        return this;
    }

    public void configure() {
        Logger root = LogManager.getRootLogger(); // 获取跟日志级别
        Logger crash = LogManager.getLogger("crash");

        if (this.resetConfiguration) {   // 如果重设，则执行重设命令
            LogManager.getLoggerRepository().resetConfiguration();
        }
        root.setLevel(this.level);
        crash.setLevel(this.level);
        crash.setAdditivity(false);
        LogLog.setInternalDebugging(this.internalDebugging);

        if (this.isSaveLogAsFile) {
            root.addAppender(this.configureFileAppender());
            crash.addAppender(this.configureCrashFileAppender());
        }

        if (this.useLogcatAppender) {
            root.addAppender(this.configureLogCatAppender());
            crash.addAppender(this.configureLogCatAppender());
        }
    }

    /**
     * 配置文件输出器
     */
    private RollingFileAppender configureFileAppender() {
        try {
            Layout fileLayout = new PatternLayout(this.filePattern);
            RollingFileAppender rollingFileAppender = new RollingFileAppender(fileLayout, this.filePath + File.separator + "log" + File.separator + this.fileName);  // 规定文件输出模式和文件名字输出模式
            rollingFileAppender.setMaxBackupIndex(this.maxBackupNumber);  // 设置最大备份索引
            rollingFileAppender.setMaximumFileSize(this.maxFileSize); // 设置最大文件大小
            rollingFileAppender.setImmediateFlush(this.immediateFlush); // 设置是否立即刷新
            return rollingFileAppender;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private RollingFileAppender configureCrashFileAppender() {
        try {
            Layout fileLayout = new PatternLayout(this.filePattern);
            RollingFileAppender rollingFileAppender = new RollingFileAppender(fileLayout, this.filePath + File.separator + "crash" + File.separator + "crash-" + this.fileName);  // 规定文件输出模式和文件名字输出模式
            rollingFileAppender.setMaxBackupIndex(this.maxBackupNumber);  // 设置最大备份索引
            rollingFileAppender.setMaximumFileSize(this.maxFileSize); // 设置最大文件大小
            rollingFileAppender.setImmediateFlush(this.immediateFlush); // 设置是否立即刷新
            return rollingFileAppender;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 配置类似LogCat的日志输出
     */
    private LogCatAppender configureLogCatAppender() {
        try {
            Layout logCatLayout = new PatternLayout(this.logCatPattern);
            return new LogCatAppender(logCatLayout);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
