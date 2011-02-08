package ru.snslabs.plugin.search.scanner;

import ru.snslabs.plugin.search.scanner.impl.RegexpScanner;
import ru.snslabs.plugin.search.scanner.impl.RegexpScannerWithSubst;

public class ScannerFactory {

    public static Scanner buildScanner(ScannerType scannerType, String desc){
        if(scannerType == ScannerType.REGEXP){
            return new RegexpScanner(desc);
        }
        else if(scannerType == ScannerType.REGEXP_W_SUBST){
            return new RegexpScannerWithSubst(desc);
        }
        else{
            throw new IllegalArgumentException("Scanner type "+ scannerType + " not implemented yet :-(");
        }
    }


}
