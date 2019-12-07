package com.appium.utility;

import com.appium.threadLocal.ThreadLocalLogger;
import org.apache.logging.log4j.LogManager;
import rp.com.google.common.io.BaseEncoding;

import java.io.File;

public class ReportPortalLoggingUtils {

    public ReportPortalLoggingUtils() {
        ThreadLocalLogger.setLogger(LogManager.getLogger(ReportPortalLoggingUtils.class.getName()));
    }

    public void log(File file, String message) {
        ThreadLocalLogger.getLogger().info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), message);
    }

    public void log(byte[] bytes, String message) {
        ThreadLocalLogger.getLogger().info("RP_MESSAGE#BASE64#{}#{}", BaseEncoding.base64().encode(bytes), message);
    }

    public void logBase64(String base64, String message) {
        ThreadLocalLogger.getLogger().info("RP_MESSAGE#BASE64#{}#{}", base64, message);
    }


}
