package com.splean;

/**
 * FileBrowser application cofiguration helper
 */
public class FileBrowserConfig {
    public static final String VERSION = "0.0.1";

    public static FileBrowserConfig configure(){
        return new FileBrowserConfig();
    }

    public String getVersion(){
        return VERSION;
    }
}
