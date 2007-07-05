package com.luxoft.itci.i18n.plugin.scanner.filter;

import java.util.List;

/**
 * Chain filter. Used to apply chain of filters to provided result. If any of filter fails - the result will be rejected.
 */
public class ChainFilter implements Filter{

    private List<Filter> filters;

    /**
     * Constructs ChainFilter providing list of Filters to be applied
     * @param filters list of filters
     */
    public ChainFilter(List<Filter> filters) {
        this.filters = filters;
    }
    
    public boolean accept(String result) {
        for (Filter filter : filters) {
            if(!filter.accept(result)){
                return false;
            }
        }
        return true;
    }
}
