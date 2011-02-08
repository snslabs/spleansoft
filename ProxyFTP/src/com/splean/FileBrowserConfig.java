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

    public String getDefaultTextColor(){
        return "black";
    }

    public String getDefaultBgColor(){
        return "white";
    }

    public String getPanelBgColor(){
        return "white";
    }

    public String getPanelTextColor(){
        return "black";
    }
}
