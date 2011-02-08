package com.splean.comm.common;

import java.util.Map;

public interface Subscriber extends Comparable{
    /**
     * @return Nick name of user
     */
    String getName();

    /**
     * @return correct address according to subscriber type (email, sn)
     */
    String getAddress();

    /**
     * @return textual descriprion of user's status
     */
    String getStatus();

    /**
     * @return all available parameters about subscriber in common key/value format (for information only)
     */
    Map getInfo();

    /**
     * @return subscriber type
     */
    SubscriberType getType();
}
