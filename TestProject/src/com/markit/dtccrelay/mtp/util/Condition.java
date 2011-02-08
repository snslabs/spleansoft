package com.markit.dtccrelay.mtp.util;

import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Condition class. defines rule.
 */
public class Condition {
    public static final Condition DEFAULT = new Condition();

    private static Set<String> ALL_PRODUCT_TYPES = new HashSet<String>();
    private static Set<String> ALL_TRANSACTION_TYPES = new HashSet<String>();

    List<String> productTypes;
    List<String> transactionTypes;


    public Condition() {
        productTypes = new ArrayList<String>();
        transactionTypes = new ArrayList<String>();
    }

    public Condition(String productTypes, String transactionTypes) {
        this();
        if (StringUtils.hasText(productTypes)) {
            final List<String> list = Arrays.asList(StringUtils.tokenizeToStringArray(productTypes, ","));
            this.productTypes.addAll(list);
            ALL_PRODUCT_TYPES.addAll(list);
        }
        if (StringUtils.hasText(transactionTypes)) {
            final List<String> list = Arrays.asList(StringUtils.tokenizeToStringArray(transactionTypes, ","));
            this.transactionTypes.addAll(list);
            ALL_TRANSACTION_TYPES.addAll(list);
        }
    }

    public List<String> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<String> productTypes) {
        this.productTypes = productTypes;
    }

    public List<String> getTransactionTypes() {
        return transactionTypes;
    }

    public void setTransactionTypes(List<String> transactionTypes) {
        this.transactionTypes = transactionTypes;
    }

    /**
     * Is the current condition complies specified one ( does the current condition contains provided)
     *
     * @param condition condition to compare
     * @return true if current condition statisfies desired conditions (more generial or equals)
     */
    public boolean comply(Condition condition) {
        boolean result = true;
        if (!this.productTypes.isEmpty() || !condition.productTypes.isEmpty()) {
            result = productTypes.containsAll(condition.productTypes) || productTypes.isEmpty();
        }

        if (transactionTypes.isEmpty() || condition.transactionTypes.isEmpty()) {
            result = result && (transactionTypes.containsAll(condition.transactionTypes) || transactionTypes.isEmpty());
        }
        return result;
    }

    public int hashCode() {
        return (productTypes == null ? 0 : 31 * productTypes.hashCode()) +
                (transactionTypes == null ? 0 : 31 * transactionTypes.hashCode());
    }


    public String toString() {
        return StringUtils.arrayToCommaDelimitedString(productTypes.toArray())
                + ":"
                + StringUtils.arrayToCommaDelimitedString(transactionTypes.toArray());
    }

    public static Set<String> getAllProductTypes() {
        return Collections.unmodifiableSet(ALL_PRODUCT_TYPES);
    }

    public static Set<String> getAllTransactionTypes() {
        return Collections.unmodifiableSet(ALL_TRANSACTION_TYPES);
    }

    public String getProductTypesAsString() {
        return StringUtils.collectionToCommaDelimitedString(getAllProductTypes());
    }

    public String getTransactionTypesAsString() {
        return StringUtils.collectionToCommaDelimitedString(getAllTransactionTypes());
    }
}
