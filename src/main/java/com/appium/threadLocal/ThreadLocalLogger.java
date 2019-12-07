package com.appium.threadLocal;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class ThreadLocalLogger {
    private static ThreadLocal<Logger> loggerThreadLocal = new ThreadLocal<>();

    public static void setLogger(Logger logger) {
        loggerThreadLocal.set(logger);
    }

    public static Logger getLogger() {
        return loggerThreadLocal.get();
    }

}
