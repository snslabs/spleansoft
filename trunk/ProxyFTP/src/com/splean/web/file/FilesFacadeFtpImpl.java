package com.splean.web.file;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * FilesFacade to work with ftp server
 */
class FilesFacadeFtpImpl extends AbstractFilesImpl {
    private FTPClient ftpClient;
    private String server;
    private int port;
    private String username;
    private String password;
    private String path;

    public void init(String path) throws FileBrowserException {
        try {
            PathModel pathModel = new PathModel(path);
            server = pathModel.server;
            port = pathModel.port;
            username = pathModel.username;
            password = pathModel.password;
            this.path = pathModel.path;
        }
        catch (Exception e) {
            throw new FileBrowserException(e);

        }
    }

    public List<AbstractFileModel> dir(String path) throws FileBrowserException, IOException {
        PathModel pm = new PathModel(path);
        FTPFile[] ff = ftpClient.listFiles();
        List<AbstractFileModel> res = new ArrayList<AbstractFileModel>();
        for (FTPFile file : ff) {
            if (!file.getName().equals(".")) {
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

    public String createDirectory(String path) throws IOException, FileBrowserException {
        PathModel pm = new PathModel(path);
        boolean res = ftpClient.makeDirectory(pm.name);
        if (!res) {
            return "Cannot create directory\n" + ftpClient.getReplyString();
        }
        return null;
    }

    public String deleteFile(String path) throws FileBrowserException, IOException {
        PathModel pm = new PathModel(path);
        StringBuffer result = new StringBuffer();
        String name = pm.name;
        FTPFile[] ftpf = ftpClient.listFiles();
        if (ftpf != null && ftpf.length > 0) {
            for (FTPFile ftpFile : ftpf) {
                System.out.println(
                        MessageFormat.format("comparing name=>{0}< to filename=>{1}<", name, ftpFile.getName())
                );
                if (!ftpFile.getName().equals(name)) {
                    continue;
                }
                boolean res;
                if (ftpFile.isDirectory()) {
                    System.out.println("deleting dir " + ftpFile.getName());
                    res = ftpClient.removeDirectory(ftpFile.getName());
                    System.out.println(ftpClient.getReplyString());
                }
                else {
                    System.out.println("deleting file " + ftpFile.getName());
                    res = ftpClient.deleteFile(ftpFile.getName());
                    System.out.println(ftpClient.getReplyString());
                }
                if (!res) {
                    result.append("Cannot delete ").append(pm.name).append("\n").append(ftpClient.getReplyString());
                }
            }
        }
        else {
            System.out.println("Nothing found to delete for mask" + name);
        }

        return result.length() == 0 ? null : result.toString();
    }

    public String copyFile(String filePath, String clipboardId) {
        return null;
    }

    public void uploadFile(String path, byte[] fileData) throws IOException, FileBrowserException {
        PathModel pm = new PathModel(path);
        InputStream is = new ByteArrayInputStream(fileData);
        boolean res = ftpClient.storeFile(pm.name, is);
        String result;
        if (!res) {
            result = "Cannot upload file " + pm.name + "\n" + ftpClient.getReplyString();
            throw new FileBrowserException(result);
        }

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

    public void initFtpClient() throws FileBrowserException {
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(InetAddress.getByName(server), port);
            ftpClient.login(username, password);
            ftpClient.changeWorkingDirectory(path);
            System.out.println(ftpClient.getReplyString());
            System.out.println(ftpClient.printWorkingDirectory());
        }
        catch (Exception e) {
            throw new FileBrowserException(e);
        }
    }

    public void tearDownFtpClient() throws FileBrowserException {
        try {
            ftpClient.logout();
            ftpClient.disconnect();
        }
        catch (Exception e) {
            throw new FileBrowserException(e);
        }
    }
}
