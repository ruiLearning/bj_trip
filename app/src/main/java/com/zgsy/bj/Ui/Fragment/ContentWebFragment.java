package com.zgsy.bj.Ui.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zgsy.bj.Constants;
import com.zgsy.bj.Data.ImageData;
import com.zgsy.bj.R;
import com.zgsy.bj.Tools.AsynImageLoader;
import com.zgsy.bj.Ui.Activity.WebActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import me.jingbin.web.ByWebView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContentWebFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentWebFragment extends Fragment {

    private ByWebView textView;
    //    private ImageView imageView;

    public ContentWebFragment() {
        // Required empty public constructor
    }

    public static ContentWebFragment newInstance(Bundle bundle) {
        ContentWebFragment fragment = new ContentWebFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_web, container, false);

        textView = new ByWebView.Builder(getActivity())
                .setWebParent(contentView.findViewById(R.id.ll_web), new LinearLayout.LayoutParams(-1,-1))
                .addJavascriptInterface("DuomeBridge",this)
                .loadUrl(Constants.BASE_URL_WEB);

        return contentView;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
