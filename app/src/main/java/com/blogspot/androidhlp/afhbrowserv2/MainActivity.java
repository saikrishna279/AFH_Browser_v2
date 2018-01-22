package com.blogspot.androidhlp.afhbrowserv2;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.androidhlp.afhbrowserv2.Utitlity.JSFormatter;
import com.blogspot.androidhlp.afhbrowserv2.Utitlity.UrlBuilder;

import org.apache.commons.lang.StringEscapeUtils;

import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    WebView afh;
    EditText searchBar;
    RelativeLayout relativeLayout;
    FileSummaryRVAdapter adapter;
    List<FileInfoSummary> currentList;
    RecyclerView recyclerView;

    UrlBuilder urlBuilder;

    int file_index, mCurrentPage, i;
    String rotatorOutput, rotatorOutputPrevious;
    boolean noMoreFiles, isPageEmpty;

    String name = "";
    String CurrentURLstring = "";
    String newUA = "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        afh = (WebView) findViewById(R.id.afhView);

        afh.getSettings().setJavaScriptEnabled(true);//setup WebView
        afh.getSettings().setLoadWithOverviewMode(true);
        afh.getSettings().setUseWideViewPort(true);
        afh.getSettings().setSupportZoom(true);
        afh.getSettings().setBuiltInZoomControls(true);
        afh.getSettings().setDisplayZoomControls(false);
        afh.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        afh.setScrollbarFadingEnabled(false);
        afh.getSettings().setUserAgentString(newUA);

        if (android.os.Build.VERSION.SDK_INT >= 21) { //setup Cookie Storage
            CookieManager.getInstance().setAcceptThirdPartyCookies(afh, true);
        } else {
            CookieManager.getInstance().setAcceptCookie(true);
        }

        searchBar = (EditText) findViewById(R.id.search);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        currentList = new ArrayList<>();
        adapter = new FileSummaryRVAdapter(this, currentList);
        recyclerView.setAdapter(adapter);

        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                name = searchBar.getText().toString();
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (!name.isEmpty()) {
                        if (name.isEmpty()) {
                            Toast.makeText(MainActivity.this, R.string.no_search_field, Toast.LENGTH_SHORT).show();
                        } else {
                            isPageEmpty = false;
                            noMoreFiles = false;
                            CurrentURLstring = UrlStringBuilder(name, mCurrentPage);
                            afh.loadUrl(CurrentURLstring);
                            afh.setWebViewClient(new WebViewClient() {
                                @Override
                                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                    return false;
                                }

                                @Override
                                public void onPageFinished(WebView view, String url) {
                                    Afh();
                                }
                            });


                        }
                    } else {
                        Toast.makeText(MainActivity.this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });

    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void Afh() {
        afh.evaluateJavascript(JSFormatter.getFilesPerPage(), new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                file_index = Integer.valueOf(value, 10);
                if (file_index != 0) {
                    i =0;
                    ListPopulator();
                } else {
                    isPageEmpty = true;
                }
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void ListPopulator() {
        afh.evaluateJavascript(JSFormatter.getFileName(i), new ValueCallback<String>() {
            @Override
            public void onReceiveValue(final String filename) {
                afh.evaluateJavascript(JSFormatter.getUploadDate(i), new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(final String uploadDate) {
                        afh.evaluateJavascript(JSFormatter.getDownloadCount(i), new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(final String downloadCount) {
                                afh.evaluateJavascript(JSFormatter.getFileSize(i), new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String fileSize) {
                                        //currentList.add(
                                        //        new FileInfoSummary(QuoteFormatter(filename), BRTagFormatter(uploadDate), BRTagFormatter(downloadCount), BRTagFormatter(fileSize))
                                        //);
                                        Toast.makeText(MainActivity.this, filename, Toast.LENGTH_SHORT).show();
                                        if ((i + 1) == file_index) {
                                            //adapter.notifyDataSetChanged();
                                            mCurrentPage++;
                                            afh.loadUrl(UrlStringBuilder(name, mCurrentPage));
                                        }
                                        i++;
                                        if(i < file_index){
                                            ListPopulator();
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }



    private String BRTagFormatter(String Raw) {
        String tempStr = "";
        try {
            tempStr = StringEscapeUtils.unescapeJava(Raw).substring(1, tempStr.indexOf("<br>"));
            //return StringEscapeUtils.unescapeJava(Raw).substring(1, tempStr.indexOf("<br>"));
        }catch (StringIndexOutOfBoundsException e){
        }
        return tempStr;
    }


    private String QuoteFormatter(String Raw) {
        String TempStr = "";
        try {
            TempStr = Raw.substring(Raw.indexOf('"') + 1, Raw.lastIndexOf('"'));
        } catch (StringIndexOutOfBoundsException e) {

        }
        try {
            return TempStr.substring(0, TempStr.indexOf("files") - 1);
        } catch (StringIndexOutOfBoundsException e) {
            return TempStr;
        }
    }


    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    public String UrlStringBuilder(String searchTerm, int page_index) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("androidfilehost.com")
                .appendQueryParameter("w", "search")
                .appendQueryParameter("s", searchTerm)
                .appendQueryParameter("type", "files")
                .appendQueryParameter("page", String.valueOf(page_index))
                .build();
        return builder.toString();
    }
}
