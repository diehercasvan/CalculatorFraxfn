package com.edibca.fraxfn;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.edibca.fraxfn.R;

import class_fraxfn.General;

/**
 * Created by DIEGO CASALLAS on 11/09/2015.
 */
public class References extends Fragment implements View.OnClickListener {
    private View view;
    private WebView webView;
    private String sFile= General.FILE_URL;
    private Context context;
    public References(){

        context=General.CONTEXT;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.view_web,container,false);
        //Web view
        webView=(WebView)view.findViewById(R.id.Container_webView);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        sFile += "referencias.html";

        webView.clearCache(true);

        webView.loadUrl(sFile);

        return view;

    }

    @Override
    public void onClick(View v) {

    }
}
