package com.luxoft.itci.i18n.plugin.scanner.filter;

/**
 * After search filter. The only accept method determines does founded result matches provided filter criteria 
 */
public interface Filter  {
    /**
     * Returns filter result.
     * @param result
     * @return
     */
    boolean accept(String result);
}
