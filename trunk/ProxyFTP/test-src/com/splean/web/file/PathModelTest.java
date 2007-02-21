package com.splean.web.file;

import junit.framework.TestCase;

/**
 * test for path model. Class that splits provided ftp url into parts 
 */
public class PathModelTest extends TestCase {

    public void testFullFilePath() throws Exception{
        String path = "ftp://admin:1111@localhost:21/root/folder1/readme.txt";
        final PathModel pathModel = new PathModel(path);
        assertEquals("localhost", pathModel.server);
        assertEquals(21, pathModel.port);
        assertEquals("admin", pathModel.username);
        assertEquals("1111", pathModel.password);
        assertEquals("/root/folder1/", pathModel.path);
        assertEquals("readme.txt", pathModel.name);

    }
    public void testDefaultFtpPort() throws Exception{
        String path = "ftp://admin:1111@localhost/root/folder1/readme.txt";
        final PathModel pathModel = new PathModel(path);
        assertEquals(21, pathModel.port);
    }

    public void testRootPath() throws Exception{
        String path = "ftp://admin:1111@localhost:21/";
        final PathModel pathModel = new PathModel(path);
        assertEquals("localhost", pathModel.server);
        assertEquals(21, pathModel.port);
        assertEquals("admin", pathModel.username);
        assertEquals("1111", pathModel.password);
        assertEquals("/", pathModel.path);
        assertEquals("", pathModel.name);

    }

    public void testRootFolderPath() throws Exception{
        String path = "ftp://admin:1111@localhost:21/root";
        final PathModel pathModel = new PathModel(path);
        assertEquals("localhost", pathModel.server);
        assertEquals(21, pathModel.port);
        assertEquals("admin", pathModel.username);
        assertEquals("1111", pathModel.password);
        assertEquals("/", pathModel.path);
        assertEquals("root", pathModel.name);

    }
}
