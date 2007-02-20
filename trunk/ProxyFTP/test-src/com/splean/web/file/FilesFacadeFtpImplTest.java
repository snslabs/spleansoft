package com.splean.web.file;

import junit.framework.TestCase;

public class FilesFacadeFtpImplTest extends TestCase {
    public void testDir() throws Exception{
        final AbstractFilesImpl ff = new FilesFacadeFtpImpl();
        ff.dir("root");
    }
}
