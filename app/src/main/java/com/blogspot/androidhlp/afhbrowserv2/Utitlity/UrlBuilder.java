package com.blogspot.androidhlp.afhbrowserv2.Utitlity;

import android.net.Uri;

/**
 * Created by rocket on 16-01-2018.
 */

public class UrlBuilder {
    String finalUrl, search_term;
    int page_index;

    public  String UrlBuilder(String finalUrl, String search_term, int page_index) {
        this.finalUrl = finalUrl;
        this.search_term = search_term;
        this.page_index = page_index;
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("androidfilehost.com")
                .appendQueryParameter("w", "search")
                .appendQueryParameter("s", search_term)
                .appendQueryParameter("type", "files")
                .appendQueryParameter("page", String.valueOf(page_index))
                .build();
        return builder.toString();
    }
}
