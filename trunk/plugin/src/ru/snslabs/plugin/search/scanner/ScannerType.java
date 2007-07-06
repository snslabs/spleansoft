package ru.snslabs.plugin.search.scanner;

import java.util.*;

public abstract class ScannerType {
    private static List<ScannerType> scannerTypes = new ArrayList<ScannerType>();
    private static Map<String,ScannerType> scannerTypesMap = new HashMap<String, ScannerType>();

    private String name;
    private String desc;

    public static ScannerType REGEXP =
        new ScannerType("Regexp","Regular expression scanner.\nScans provided stream using regexp." +
                "in addition - group can be specified that will determine found entry if it is not entire match for regexp."){};

    public static ScannerType REGEXP_W_SUBST =
        new ScannerType("Regexp w/subst","Multiple regular expression scanner with substitution.\n" +
                "Scans provided stream using multiple regexps each generated using some formula and values provider.\n" +
                "Can be used to find, let's say, all values from specified list (like application properties)."){};

    private ScannerType(String name, String desc) {
        this.name = name;
        this.desc = desc;
        scannerTypes.add(this);
        scannerTypesMap.put(name,this);
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public static ScannerType getScannerTypeByName(String name){
        return scannerTypesMap.get(name);
    }
    
    public static List<ScannerType> getAllScannerTypes(){
        return Collections.unmodifiableList(scannerTypes);
    }
}
