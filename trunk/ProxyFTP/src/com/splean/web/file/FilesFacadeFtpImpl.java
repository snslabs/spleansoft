package com.splean.web.file;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * FilesFacade to work with ftp server
 */
class FilesFacadeFtpImpl extends AbstractFilesImpl {
    public List<AbstractFileModel> dir(String path) throws FileBrowserException, IOException {
        PathModel pm = new PathModel(path);
        FTPClient ftpClient = getFtpClient(pm);
        ftpClient.changeWorkingDirectory(path);
        FTPFile[] ff = ftpClient.listFiles();
        List<AbstractFileModel> res = new ArrayList<AbstractFileModel>();
        for (FTPFile file : ff) {
            if(!file.getName().equals(".")){
                final FtpFileModel fileModel = new FtpFileModel(
                        file.getName(), pm.path,
                        ftpClient.getRemoteAddress().getHostAddress(), ftpClient.getRemotePort(),
                        pm.username, pm.password, file.isDirectory());
                res.add(
                        fileModel
                );
            }
        }
        return res;
    }

    public String createDirectory(String path) {
        return null;
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

    private FTPClient getFtpClient(PathModel pm) throws FileBrowserException {
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(InetAddress.getByName(pm.server), pm.port);
            ftpClient.login(pm.username, pm.password);
            ftpClient.changeWorkingDirectory(pm.path);
            return ftpClient;
        }
        catch (Exception e) {
            throw new FileBrowserException(e);
        }
    }

    private class PathModel{
        String server;
        String username;
        String password;
        int port;
        String path;
        String name;

        public PathModel(String fullPath) throws MalformedURLException {
            URL url = new URL(fullPath);
            server = url.getHost();
            port = url.getPort()==0?url.getDefaultPort():url.getPort();
            username = url.getUserInfo().substring(0,url.getUserInfo().indexOf(":"));
            password = url.getUserInfo().substring(url.getUserInfo().indexOf(":")+1);
            path = url.getPath();
        }
    }
}
