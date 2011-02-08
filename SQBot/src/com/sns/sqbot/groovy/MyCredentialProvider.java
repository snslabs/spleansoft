package com.sns.sqbot.groovy;

import org.apache.commons.httpclient.auth.CredentialsProvider;
import org.apache.commons.httpclient.auth.CredentialsNotAvailableException;
import org.apache.commons.httpclient.auth.AuthScheme;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.Credentials;

public class MyCredentialProvider implements CredentialsProvider {
            public Credentials getCredentials(AuthScheme authScheme, String string, int i, boolean b) throws CredentialsNotAvailableException {
                return new NTCredentials("slubimov","mcu164219","slubimov","luxoft.com");
            }
        }