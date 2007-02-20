package com.splean.web.file;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.List;
import java.util.ArrayList;

/**
 * FilesFacade to work with ftp server
 */
class FilesFacadeFtpImpl extends AbstractFilesImpl {
    private String server = "localhost";
    private String username = "admin";
    private String password = "1111";
    private int port = 21;

    public List<AbstractFileModel> dir(String path) throws FileBrowserException, IOException {

        FTPClient ftpClient = getFtpClient();
        ftpClient.changeWorkingDirectory(path);
        String[] files = ftpClient.listNames();
        String dirPath = ftpClient.printWorkingDirectory();
        List<AbstractFileModel> res = new ArrayList<AbstractFileModel>();
        for (String file : files) {
            res.add(
                    new FtpFileModel(
                            file, dirPath,
                            ftpClient.getRemoteAddress().getHostAddress(), ftpClient.getRemotePort(),
                            username, password)
            );
        }
        return res;
    }

    public void createDirectore(String path) {

    }

    public String deleteFile(String path) throws FileBrowserException {
        return null;
    }

    public String copyFile(String filePath, String clipboardId) {
        return null;
    }

    public void uploadFile(String path, byte[] fileData) throws IOException {

    }

    public String pasteFiles(String dstDirPath, String clipboardId) {
        return null;
    }

    public byte[] getFileDataAsByteArray(AbstractFileModel fileModel) throws IOException {
        return new byte[0];
    }

    public InputStream getFileDataAsStream(AbstractFileModel fileModel) throws IOException {
        return null;
    }

    public AbstractFileModel getFile(String path) {
        return null;
    }

    private FTPClient getFtpClient() throws FileBrowserException {
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(InetAddress.getByName(server), port);
            ftpClient.login(username, password);
            ftpClient.changeWorkingDirectory("root");
            return ftpClient;
        }
        catch (Exception e) {
            throw new FileBrowserException(e);
        }
    }
}
