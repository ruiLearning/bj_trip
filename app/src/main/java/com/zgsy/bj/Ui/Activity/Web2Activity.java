//package com.zgsy.bj.Ui.Activity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.KeyEvent;
//import android.webkit.JavascriptInterface;
//import android.widget.LinearLayout;
//
//
//import me.jingbin.web.ByWebView;
//
//public class Web2Activity extends BaseActivity<ActivityWebBinding> {
//
//    private String title = "";
//    private String url = "";
//    private ByWebView webView;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EventBus.getDefault().register(this);
//        title = getIntent().getStringExtra("title");
//        url = getIntent().getStringExtra("url");
//
//        mBinding.tbWeb.setTitle(title);
//        mBinding.tbWeb.setOnTitleBarListener(new OnTitleBarListener() {
//            @Override
//            public void onLeftClick(TitleBar titleBar) {
//                if (webView.getWebView().canGoBack()) {
//                    webView.getWebView().goBack();
//                }else {
//                    finish();
//                }
//            }
//        });
//
//        webView = new ByWebView.Builder(WebActivity.this)
//                .setWebParent(mBinding.llWeb, new LinearLayout.LayoutParams(-1,-1))
//                .useWebProgress(R.color.mColorPrimary)
//                .addJavascriptInterface("DuomeBridge",this)
//                .loadUrl(url);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        webView.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        webView.onResume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        webView.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void websocketMessage(MessageEvent event) {
//        if (event != null && event.message != null) {
//            CommonUtils.logs("事件  websocketMessage:" + event.message);
//            switch (event.message) {
//                case MessageType.VIP_BONUS_GOLD:
//
//                    break;
//            }
//        }
//    }
//
//    @JavascriptInterface
//    public void finishForJs() {
//        EventBus.getDefault().post(new MessageEvent(MessageType.messageRecharge));
//        EventBus.getDefault().post(new MainRefreshEvent(MessageType.messageRecharge));
//        ToastUtils.showShort(getString(R.string.balance_wait));
//        finish();
//    };
//
//    private void getPayStatus(String paymentId){
//        TreeMap<String, Object> params = new TreeMap<String, Object>();
//        params.put(HttpKey.paymentId, paymentId);
//        HttpRequest.getPayStatus(this, params, new HttpCallBackListener<Object>(this){
//            @Override
//            public void onHttpSuccess(String code, Object data) {
//
//            }
//        });
//
//    }
//
//    public static void goToThisActivity(Context context, String title, String url) {
//        Intent intent = new Intent();
//        intent.setClass(context, WebActivity.class);
//        intent.putExtra("title",title);
//        intent.putExtra("url",url);
//        context.startActivity(intent);
//    }
//
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.getWebView().canGoBack()) {
//            if (webView.getWebView().canGoBack()) {
//                webView.getWebView().goBack();
//            }else {
//                finish();
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//}
