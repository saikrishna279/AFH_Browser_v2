package com.blogspot.androidhlp.afhbrowserv2;

/**
 * Created by rocket on 15-01-2018.
 */

public class FileInfoSummary {
    private String fileName, uploadDate, downloads, fileSize;

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

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public FileInfoSummary(String fileName, String uploadDate, String downloads, String fileSize) {

        this.fileName = fileName;
        this.uploadDate = uploadDate;
        this.downloads = downloads;
        this.fileSize = fileSize;
    }
}
