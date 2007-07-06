package ru.snslabs.plugin.search.scanner.impl;

import ru.snslabs.plugin.search.scanner.Filter;

import java.util.regex.Pattern;

/**
 * rejects (return false) for entries that matches provided regexp
 */
public class RegexpFilter implements Filter {
    private Pattern pattern;
    public boolean accept(String result) {

        return !pattern.matcher(result).matches();
    }

    public void setPattern(String regexp) {
        this.pattern = Pattern.compile(regexp);
    }
}
