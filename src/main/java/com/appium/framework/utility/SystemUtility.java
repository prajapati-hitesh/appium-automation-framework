package com.appium.framework.utility;

public class SystemUtility {

    /**
     *
     * @return User working directory
     */
    public String getUserDirectory() {
        return System.getProperty("user.dir");
    }

    /**
     *
     * @return Character that separates components of a file path. This is "/" on UNIX and "\" on Windows.
     */
    public String getFileSeparator() {
        return System.getProperty("file.separator");
    }

    /**
     *
     * @return Operating system name
     */
    public String getOperatingSystemName() {
        return System.getProperty("os.name");
    }

    /**
     * Operating system architecture
     * @return
     */
    public String getOperatingSystemArchitecture() {
        return System.getProperty("os.arch");
    }

    /**
     *
     * @return Operating system version
     */
    public String getOperatingSystemVersion() {
        return System.getProperty("os.version");
    }

    /**
     *
     * @return Path separator character used in java.class.path
     */
    public String getPathSeparator() {
        return System.getProperty("path.separator");
    }

    /**
     *
     * @return User home directory
     */
    public String getUserHomeDirectory() {
        return System.getProperty("user.home");
    }

    /**
     *
     * @return User account name
     */
    public String getUserAccountName() {
        return System.getProperty("user.name");
    }

    /**
     *
     * @return Sequence used by operating system to separate lines in text files
     */
    public String getLineSeparator() {
        return System.getProperty("line.separator");
    }

    /**
     *
     * @return Default temp file path
     */
    public String getDefaultTempFilePath() {
        return System.getProperty("java.io.tmpdir");
    }
}
