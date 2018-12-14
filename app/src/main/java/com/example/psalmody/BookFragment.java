package com.example.psalmody;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.psalmody.R;

public class BookFragment extends Fragment {
    private WebView webView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_books, container, false);
        webView = (WebView) v.findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://drive.google.com/file/d/0B15o--Kqj0K-RXQ0N1dDVDF0VFU/view?usp=sharing");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        return v;
    }
//    @Override
//    public void onBackPressed(){
//
//        int count = getFragmentManager().getBackStackEntryCount();
//        if (count ==0){
//           super.onBackPressed();
//        }
//        else{
//            getFragmentManager().popBackStack();
//        }
//    }


}
