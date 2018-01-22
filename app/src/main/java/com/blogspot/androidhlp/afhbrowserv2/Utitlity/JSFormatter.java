package com.blogspot.androidhlp.afhbrowserv2.Utitlity;

/**
 * Created by rocket on 15-01-2018.
 */

/**
 * getNUmberOfFilesPerPage()
 * getNumberOfPages()
 */

public class JSFormatter {
    private static String prefix = "(function(){return window.document.getElementsByClassName('";

    private static String length = ".length})();";
    private static String innerHTML = ".innerHTML})();";
    private static String innerText = ".innerText})();";
    private static String href = ".href})();";

    private static String FilesPerPage = "list-group-item file clearfix')"; //JS for Number of files per page = prefix + FilesPerPage +length

    private static String TotalFilesNum = "navbar-brand')[1]"; //JS for Total number of files per device + prefix + TotalFilesNum + innerText

    private static String FileNameAndFIDPreIndex = "file-name')["; // JS for Name of file / FID = prefix + FFileNameAndFIDPreIndex + int array_index + FileNameAndFIDPostIndex + innerHTML(for file name) / href(for FID)
    private static String FileNameAndFIDPostIndex = "].getElementsByTagName('a')[0]";

    private static String DownloadsCountPreIndex = "file-attr col-sm-4 clearfix')["; // JS for Download count of a file = prefix + DownloadsCountPreIndex + int array_index +  DownloadsCountPostIndex + innerHTML
    private static String DownloadsCountPostIndex = "].getElementsByClassName('file-attr-value')[0]";

    private static String FileSizePreIndex = "file-attr col-sm-3 clearfix')["; // JS for Size of a file = prefix + FileSizePreIndex + int array_index + FileSizePostIndex + innerHTML
    private static String FileSizePostIndex = "].getElementsByClassName('file-attr-value')[0]";

    private static String UploadDatePreIndex = "file-attr col-sm-5 clearfix')["; // JS for Upload date of a file = prefix + UploadDatePreIndex + int array_index + UploadDatePostIndex + innerHTML
    private static String UploadDatePostIndex = "].getElementsByClassName('file-attr-value')[0]";

    public static String getFilesPerPage() {
        return prefix + FilesPerPage +length;
    }

    public static String getTotalFilesNum() {
        return prefix + TotalFilesNum + innerText;
    }

    public static String getFileName(int index) {
        return prefix + FileNameAndFIDPreIndex + (index - 1) + FileNameAndFIDPostIndex + innerHTML;
    }

    public static String getDownloadCount(int index) {
        return prefix + DownloadsCountPreIndex + (index - 1) +  DownloadsCountPostIndex + innerHTML;
    }

    public static String getFileSize(int index) {
        return prefix + FileSizePreIndex + (index - 1) + FileSizePostIndex + innerHTML;
    }

    public static String getUploadDate(int index) {
        return prefix + UploadDatePreIndex + (index - 1) + UploadDatePostIndex + innerHTML;
    }

    public static String getFID(int index) {
        return prefix + FileNameAndFIDPreIndex + (index - 1) + FileNameAndFIDPostIndex + href;
    }

    public static String getLargeJS(){
        return "function() {" +
                "var arr = document.getElementsByClassName('list-group-item file clearfix').length;" +
                "var filename = [];" +
                "var downloads = [];" +
                "var filesize = [];" +
                "var uploaddate =[];" +
                "var fileNameFinal = \"<fileName>\";" +
                "var downloadsFinal = \"<downloads>\";" +
                "var fileSizeFinal = \"<filesize>\";" +
                "var uploadDateFinal = \"<uploaddate>\";" +
                "var finalStr;" +
                "for(i = 0; i<arr; i++){" +
                "    filename[i] = document.getElementsByClassName('file-name')[i].getElementsByTagName('a')[0].innerHTML;" +
                "    downloads[i] = document.getElementsByClassName('file-attr col-sm-4 clearfix')[i].getElementsByClassName('file-attr-value')[0].innerHTML;" +
                "    filesize[i] = document.getElementsByClassName('file-attr col-sm-3 clearfix')[i].getElementsByClassName('file-attr-value')[0].innerHTML;" +
                "    uploaddate[i] = document.getElementsByClassName('file-attr col-sm-5 clearfix')[i].getElementsByClassName('file-attr-value')[0].innerHTML;" +
                "}\n" +
                "for(i = 0; i<arr; i++){" +
                "    fileNameFinal = fileNameFinal.concat(filename[i]).concat(\"\\n\");" +
                "    downloadsFinal = downloadsFinal.concat(downloads[i]).concat(\"\\n\");" +
                "    fileSizeFinal = fileSizeFinal.concat(filesize[i]).concat(\"\\n\");" +
                "    uploadDateFinal = uploadDateFinal.concat(uploaddate[i]).concat(\"\\n\");" +
                "}" +
                "finalStr = fileNameFinal.concat(downloadsFinal).concat(fileSizeFinal).concat(uploadDateFinal);" +
                "return finalStr;" +
                "} ();";
    }


}

