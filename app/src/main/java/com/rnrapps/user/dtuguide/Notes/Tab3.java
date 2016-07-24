//package com.rnrapps.user.dtuguide.Notes;
//
//import android.app.DownloadManager;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.support.annotation.Nullable;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.DownloadListener;
//import android.webkit.WebResourceError;
//import android.webkit.WebResourceRequest;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.rnrapps.user.dtuguide.R;
//
//
///**
// * Created by rohanpc on 10/19/2015.
// */
//public class Tab3 extends Fragment {
//    private WebView webView;
//    private ProgressBar progressBar;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v =inflater.inflate(R.layout.tab_3,container,false);
//
//        webView = (WebView) v.findViewById(R.id.webView);
//        webView.setWebViewClient(new MyWebViewClient());
//        progressBar=(ProgressBar)v.findViewById(R.id.progressBar);
//        //ImageButton imageButton=(ImageButton)v.findViewById(R.id.imageButton);
//        //imageButton.setOnClickListener(new View.OnClickListener() {
//          //  @Override
//            //public void onClick(View v) {
//              //  webView.goBack();
//            //}
//        //});
//        FloatingActionButton fab=(FloatingActionButton)v.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                webView.goBack();
//            }
//        });
//
//        String url = "https://drive.google.com/folderview?id=0B5WAUv2qkI6zZHBuVWNBNWNMYlE&usp=sharing";
//        webView.getSettings().setJavaScriptEnabled(true);
//
//        webView.loadUrl(url);
//        webView.setDownloadListener(new DownloadListener() {
//            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
//                //for downloading directly through download manager
//                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
//                request.allowScanningByMediaScanner();
//                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "download");
//                DownloadManager dm = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
//                dm.enqueue(request);
//            }
//        });
//
//        return v;
//    }
//
//
//    private class MyWebViewClient extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//
//                    view.loadUrl(url);
//
//            return true;
//
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            progressBar.setVisibility(View.INVISIBLE);
//            super.onPageFinished(view, url);
//        }
//
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            progressBar.setVisibility(View.VISIBLE);
//            super.onPageStarted(view, url, favicon);
//        }
//
//        @Override
//        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//            progressBar.setVisibility(View.INVISIBLE);
//            TextView tv=(TextView)view.findViewById(R.id.texxt);
//            tv.setVisibility(View.VISIBLE);
//            super.onReceivedError(view, request, error);
//        }
//    }
//    public String getFileName(String url) {
//        String filenameWithoutExtension = "";
//        filenameWithoutExtension = String.valueOf(System.currentTimeMillis()
//                + ".mp4");
//        return filenameWithoutExtension;
//    }
//}