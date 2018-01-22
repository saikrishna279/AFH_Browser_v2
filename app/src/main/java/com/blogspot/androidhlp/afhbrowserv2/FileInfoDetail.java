package com.blogspot.androidhlp.afhbrowserv2;

/**
 * Created by rocket on 15-01-2018.
 */

public class FileInfoDetail {
    String fileName, uploadDate, downloads, fileSize, MD5, deviceName, devName, devLink;

    public FileInfoDetail(String fileName, String uploadDate, String downloads, String fileSize, String MD5, String deviceName, String devName, String devLink) {
        this.fileName = fileName;
        this.uploadDate = uploadDate;
        this.downloads = downloads;
        this.fileSize = fileSize;
        this.MD5 = MD5;
        this.deviceName = deviceName;
        this.devName = devName;
        this.devLink = devLink;
    }

    public String getFileName() {
        return fileName;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public String getDownloads() {
        return downloads;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getMD5() {
        return MD5;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDevName() {
        return devName;
    }

    public String getDevLink() {
        return devLink;
    }
}
