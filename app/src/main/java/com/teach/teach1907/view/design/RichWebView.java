package com.teach.teach1907.view.design;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.http.SslError;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.teach.data.BuildCirclePic;
import com.yiyatech.utils.newAdd.DensityUtil;

import java.io.IOException;
import java.util.ArrayList;

/**
 * QQ内核WebView实现加载富文本
 * 添加图片样式控制和点击事件，url拦截事件。
 * Created by huangwy on 2017/10/13.
 * email: kisenhuang@163.com.
 */
public class RichWebView extends WebView {

    private float oldScale;     //缩放初始比例
    private float newScale;     //缩放最新比例

    private CustomScrollChanged scrollChangedListener;

    private Context context = null;
    private OnRichImageClickListener mOnImageClickListener;
    private OnRichUrlClickListener mOnRichUrlClickListener;
    private OnRichFinishListener mOnRichFinishListener;
    private int displayWidth;
    private ArrayList<BuildCirclePic> mImages = new ArrayList<>();

    public RichWebView(Context context) {
        this(context, null);
    }

    public RichWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    @SuppressLint("SetJavaScriptEnabled")
    public RichWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setWebChromeClient(new WebChromeClient());
        setWebViewClient(new ZLWebViewClient());
        getSettings().setJavaScriptEnabled(true);
        getSettings().setSupportZoom(true);
        getSettings().setBuiltInZoomControls(true);
        getSettings().setDisplayZoomControls(false);

        displayWidth = DensityUtil.px2dip(context, DensityUtil.getDisplaySize(context).x);

        setupJs();
    }



    /**
     * 设置屏幕宽度
     *
     * @param displayWidth 屏幕宽度
     */
    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    /**
     * 给WebView设置文本
     *
     * @param text 文本
     */
    public void setText(String text) {
        load("<p style=\"font-size: 12px; color: rgb(153, 153, 153);\">" + text + "</p>");
    }

    /**
     * 加载html
     *
     * @param trigger html数据
     */
    private void load(String trigger) {
        loadDataWithBaseURL("about:blank", trigger, "text/html", "UTF-8", "about:blank");
    }

    private void setupJs() {
        addJavascriptInterface(new JsCallImageListener() {
            @JavascriptInterface
            @Override
            public void onImageClicked(String url) {
                if (mOnImageClickListener != null) {
                    int pos = -1;
                    for (int i = 0; i < mImages.size(); i++) {
                        if (mImages.get(i).getOriginal().equals(url)) {
                            pos = i;
                            break;
                        }
                    }
                    if (pos != -1)
                        mOnImageClickListener.onImageClick(mImages, pos);
                }
            }

            @JavascriptInterface
            @Override
            public boolean onImageLoaded(boolean hasJumpUrl, String url, String width, String maxWidth) {
                if (TextUtils.isEmpty(url))
                    return false;
                if (!hasJumpUrl) {
                    BuildCirclePic pic = new BuildCirclePic();
                    pic.setOriginal(url);
                    mImages.add(pic);
                }
                boolean hasWidth = !TextUtils.isEmpty(width);
                //图片有固定宽度
                if (hasWidth && width.contains("px")) {
                    try {
                        String substring = width.substring(0, width.length() - 2);
                        int widthPx = Integer.parseInt(substring);
                        //图片宽度超过屏幕宽度时，需要处理，返回true
                        if (widthPx >= displayWidth) {
                            return true;
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                //图片没有宽度并且没有最大宽度时，需要处理，返回true
                return !hasWidth && TextUtils.isEmpty(maxWidth);
            }

        }, "jsCallImageListener");
    }

    public void setOnRichImageClickListener(OnRichImageClickListener listener) {
        mOnImageClickListener = listener;
    }

    public void setOnRichUrlClickListener(OnRichUrlClickListener listener) {
        mOnRichUrlClickListener = listener;
    }

    public void setOnRichFinishListener(OnRichFinishListener listener) {
        mOnRichFinishListener = listener;
    }

    /**
     * 自定义WebViewClient类
     * 注入js处理图片样式和点击事件
     * 添加url跳转控制
     */
    private class ZLWebViewClient extends WebViewClient {
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);

        }

        @Override
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
        }

        @Override
        public void onScaleChanged(WebView webView, float v, float v1) {
            super.onScaleChanged(webView, v, v1);
            if (oldScale == 0){
                oldScale = v;
            }
            newScale = v1;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (mOnRichFinishListener != null) {
                mOnRichFinishListener.onFinish(view, url);
            }

            //js注入设置图片点击事件和图片大小处理代码
            String jsCode = "javascript:(function(){\n" +
                    "    var imgs = document.getElementsByTagName('img');\n" +
                    "    for(var i = 0; i < imgs.length; i++){\n" +
                    "        var img = imgs[i];\n" +
                    "        var parent = img;\n" +
                    "        var _isA = false;\n" +
                    "        var _isP = false;\n" +
                    "        var _isFinal = false;\n" +
                    "        do {\n" +
                    "            parent = parent.parentNode;\n" +
                    "            if (parent == null) {\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            var parentName = parent.nodeName;\n" +
                    "            _isA = parentName == 'a' || parentName=='A';\n" +
                    "            _isP = parentName == 'p' || parentName=='P';\n" +
                    "            _isFinal = parentName == 'body' || parentName == 'BODY';\n" +
                    "        } while(!(_isA || _isP || _isFinal))\n" +
                    "        var adapt = window.jsCallImageListener.onImageLoaded(_isA, img.src, img.style.width, img.style.maxWidth);\n" +
                    "        if (adapt) {\n" +
                    "            img.style.maxWidth = '100%';\n" +
                    "            img.style.height = 'auto';\n" +
                    "        }\n" +
                    "        img.onclick=function(){\n" +
                    "            window.jsCallImageListener.onImageClicked(this.src);\n" +
                    "        }\n" +
                    "    }\n" +
                    "})()";
            view.loadUrl(jsCode);
            //加载第三方字体
            view.loadUrl("javascript:!function(){" +
                    "s=document.createElement('style');s.innerHTML="
                    + "\"@font-face{font-family:myfont;src:url('**injection**/fonts/font.ttf');}*{font-family:myfont !important;}\";"
                    + "document.getElementsByTagName('head')[0].appendChild(s);" +
                    "document.getElementsByTagName('body')[0].style.fontFamily = \"myfont\";}()");
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //设置URL点击事件
            boolean intercept = false;
            if (mOnRichUrlClickListener != null) {
                intercept = mOnRichUrlClickListener.onUrlClick(url);
            }
            return intercept || super.shouldOverrideUrlLoading(view, url);
        }



        @Override
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {

            WebResourceResponse response = super.shouldInterceptRequest(webView,webResourceRequest);

            String fonturl = webResourceRequest.getUrl().toString();
            System.out.println("fonturl"+fonturl);
            if (fonturl != null && fonturl.contains("**injection**/")) {
                String assertPath = fonturl.substring(fonturl.indexOf("**injection**/") + "**injection**/".length(), fonturl.length());
                try {
                    response = new WebResourceResponse("application/x-font-ttf",
                            "UTF8", getResources().getAssets().open(assertPath)
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return response;
        }


    }

    /**
     * JS交互接口
     */
    private interface JsCallImageListener {

        /**
         * 图片点击事件
         *
         * @param url 图片url
         */
        void onImageClicked(String url);

        /**
         * 图片加载完成回调
         *
         * @param hasJumpUrl 图片的父标签中是否有A标签
         * @param url        图片地址
         * @param width      图片原始宽度
         * @return 是否需要处理
         */
        boolean onImageLoaded(boolean hasJumpUrl, String url, String width, String maxWidth);
    }

    /**
     * 图片点击事件接口
     */
    public interface OnRichImageClickListener {
        void onImageClick(ArrayList<BuildCirclePic> images, int pos);
    }

    /**
     * 网页Url跳转监听接口
     */
    public interface OnRichUrlClickListener {
        boolean onUrlClick(String url);
    }

    /**
     * 网页加载完成监听接口
     */
    public interface OnRichFinishListener {
        boolean onFinish(WebView view, String url);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollChangedListener != null) {
            scrollChangedListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    public void setCustomScrollChangedListener(CustomScrollChanged scrollChangedListener) {
        this.scrollChangedListener = scrollChangedListener;
    }

    public interface CustomScrollChanged {
        void onScrollChanged(int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }
}
