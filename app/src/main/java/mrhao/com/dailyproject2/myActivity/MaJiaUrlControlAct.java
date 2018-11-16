package mrhao.com.dailyproject2.myActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import mrhao.com.dailyproject2.R;
import mrhao.com.dailyproject2.bean.MaJiaUrlBean;

public class MaJiaUrlControlAct extends AppCompatActivity {

    @BindView(R.id.majia_web)
    WebView webView;

    String majiaurl = "https://www.baidu.com/";
    @BindView(R.id.btn_changeurl)
    Button btnChangeurl;
    MaJiaUrlBean urlBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ma_jia_url_control);
        ButterKnife.bind(this);
        getUrl();

    }

    private void getUrl() {

        Map<String, String> map = new HashMap<>();
        map.put("", "");
        OkHttpUtils.get().url("http://jk.kingtrunk.com/index.php/Home/show/nlist").params(map).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {

                        Gson gs = new Gson();
                        urlBean = gs.fromJson(response, MaJiaUrlBean.class);
                        if (urlBean.getData().get(0).getType().equals("0")) {
                            webBaseSet();
                        } else {
                            majiaurl = urlBean.getData().get(0).getUrl();
                            webBaseSet();
                        }

                    }
                });
    }


    private void webBaseSet() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) return false;
                try {
                    if (url.startsWith("alipay://") || url.startsWith("alipays://") ||
                            url.startsWith("mqqapi://") || url.startsWith("mqqapis://") ||
                            url.startsWith("weixin://") || url.startsWith("weixins://") ||
                            url.startsWith("mqq://") || url.startsWith("mqqs://")
                            ) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                } catch (Exception e) {
                    return false;
                }

                view.loadUrl(url);
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        WebSettings webSettings = webView.getSettings();
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setGeolocationEnabled(true);
        // webSettings.setDatabaseEnabled(true);
        // 使用localStorage则必须打开
        //设置 缓存模式
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 开启 DOM storage API 功能
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl(majiaurl);
    }


}
