package com.beautify.qlogger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Log工具，类似android.util.Log。
 * tag自动产生
 */
public class QLogger {
    private static Logger logger = LogManager.getRootLogger();

    /**
     * 在输出到Logcat时,过滤匿名内部类只需要显示外部类
     * <p>
     * WingApp$2{.java} -> WingApp{.java}
     */
    private static String filtrateInnerClass(String className) {
        if (className.contains("$")) {
            return className.substring(0, className.indexOf('$'));
        }
        return className;
    }

    private static String generateTag(StackTraceElement caller) {
        String tag = "(%s.java:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, filtrateInnerClass(callerClazzName), caller.getLineNumber());
        return tag;
    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

    public static void fatal(String content) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.fatal(tag + "->" + content);
    }

    public static void fatal(Throwable throwable) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.fatal(tag, throwable);
    }

    public static void d(String content) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.debug(tag + "->" + content);
    }

    public static void d(Throwable tr) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.debug(tag, tr);
    }

    public static void e(String content) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.error(tag + "->" + content);
    }

    public static void e(Throwable tr) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.error(tag, tr);
    }

    public static void i(String content) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.info(tag + "->" + content);
    }

    public static void i(Throwable tr) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.info(tag, tr);
    }

    public static void w(String content) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.warn(tag + "->" + content);
    }

    public static void w(Throwable tr) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.warn(tag, tr);
    }

    public static void crash(String content) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        LogManager.getLogger("crash").error(tag + "->" + content);
    }

    public static void crash(Throwable throwable) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        LogManager.getLogger("crash").error(tag, throwable);
    }
}