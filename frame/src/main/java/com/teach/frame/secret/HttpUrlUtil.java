package com.teach.frame.secret;

import android.content.res.Resources;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.annotation.StringDef;
import androidx.annotation.StringRes;

import com.teach.frame.FrameApplication;
import com.teach.frame.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务器地址替换和Post请求中的secret替换
 * 实现线上服务器和线下服务器选择
 * <p>
 * 实现原理：1.在get和post的参数类中拦截请求地址，判断环境，
 * 如果是线下环境就把请求地址中的线上host替换为线下host。
 * 2.post请求会使用secretKey校验，在Http请求中组装secretKey的时候对其进行操作。
 * </p>
 * Created by huangwy on 2017/10/18.
 * email: kisenhuang@163.com.
 */

public class HttpUrlUtil {

    public static final String BBS_URL = "bbs_url";
    public static final String PHOTO_URL = "photo_url";
    public static final String MESSAGE = "message";
    public static final String PASSPORT = "passport";
    public static final String WEIBO = "weibo";
    public static final String EDU_URL = "edu_url";
    public static final String BLOG_API = "blog_api";
    public static final String SEARCH_API = "s_api";
    public static final String WEBSOCKET_API = "websocket_connect_api";

    @StringDef({BBS_URL, MESSAGE, PASSPORT, WEIBO, EDU_URL, BLOG_API, WEBSOCKET_API, PHOTO_URL, SEARCH_API})
    @Retention(RetentionPolicy.SOURCE)
    public @interface HostType {
    }

    private static HttpUrlUtil mInstance;
    private boolean mOffline = false;
    /**
     * key 是 xml id，value是tag
     */
    private Map<String, String> mOnlineServer = new HashMap<>();
    /**
     * key 是 tag， value是xml id
     */
    private Map<String, String> mOfflineServer = new HashMap<>();
    /**
     * key 是 tag， value是xml id
     */
    private Map<String, String> mOnlineServerMap = new HashMap<>();
    /**
     * key 是 xml id，value是tag
     */
    private Map<String, String> mOnlineKey = new HashMap<>();
    /**
     * key 是 tag， value是xml id
     */
    private Map<String, String> mOfflineKey = new HashMap<>();
    private boolean openSelectServer = false;//全局开关

    private String[][] onlineHosts = {
            {R.string.bbs_url + "", BBS_URL},
            {R.string.photo_url + "", PHOTO_URL},
            {R.string.message + "", MESSAGE},
            {R.string.passport + "", PASSPORT},
            {R.string.weibo + "", WEIBO},
            {R.string.edu_url + "", EDU_URL},
            {R.string.blog_api + "", BLOG_API},
            {R.string.s_url + "", SEARCH_API},
            {R.string.websocket_connect_api + "", WEBSOCKET_API}
    };

    private String[][] offlineHosts = {
            {R.string.off_bbs_url + "", BBS_URL},
            {R.string.off_photo_url + "", PHOTO_URL},
            {R.string.off_message + "", MESSAGE},
            {R.string.off_passport + "", PASSPORT},
            {R.string.off_weibo + "", WEIBO},
            {R.string.off_edu_url + "", EDU_URL},
            {R.string.off_blog_api + "", BLOG_API},
            {R.string.off_s_api + "", SEARCH_API},
            {R.string.off_websocket_connect_api + "", WEBSOCKET_API}
    };

    private String[][] onlineKeys = {
            {R.string.secrectKey_passport + "", "secrectKey_passport"},
            {R.string.secrectKey_posting + "", "secrectKey_posting"},
            {R.string.secrectKey_weibo4a + "", "secrectKey_weibo4a"},
            {R.string.secrectKey_message + "", "secrectKey_message"},
    };

    private String[][] offlineKeys = {
            {R.string.off_secrectKey_passport + "", "secrectKey_passport"},
            {R.string.off_secrectKey_posting + "", "secrectKey_posting"},
            {R.string.off_secrectKey_weibo4a + "", "secrectKey_weibo4a"},
            {R.string.off_secrectKey_message + "", "secrectKey_message"},
    };

    public static HttpUrlUtil getInstance() {
        if (mInstance == null)
            mInstance = new HttpUrlUtil();
        return mInstance;
    }

    private HttpUrlUtil() {
    }

    /**
     * 是否开启线上线下切换模式
     * 只能在Application被调用，且只能被调用一次
     */
    public void openSelectServer(boolean open) {
        openSelectServer = open;
    }

    public boolean isOpenSelectServer() {
        return openSelectServer;
    }

    /**
     * 设置是否使用线下服务器
     */
    public void setOfflineServer(boolean isOffline) {
        mOffline = isOffline;
    }

    /**
     * 处理Url
     *
     * @param url 请求地址
     * @return 处理后的请求地址
     */
    public String adapt(String url) {
        //测试版并且用户选择了线下服务器才会处理Url
        if (needAdapt()) {
            generateHostMap();
            String host = mapHost(url);
            if (!TextUtils.isEmpty(host)) {
                return makeOfflineUrl(url, host);
            }
        }
        return url;
    }

    /**
     * 检测Url是否需要操作
     *
     * @param resId string id
     * @return 结果
     */
    public String checkUrl(@StringRes int resId) {
        return adapt(FrameApplication.getFrameApplicationContext().getString(resId));
    }

    /**
     * 通过tag拼接请求地址
     *
     * @param url 文件地址
     * @param tag 标识Host
     * @return 完整的请求地址
     */
    public String makeUrl(String url, @HostType String tag) {
        if (TextUtils.isEmpty(tag))
            return url;
        generateHostMap();
        if (needAdapt() && mOfflineServer.containsKey(tag)) {
            return mOfflineServer.get(tag) + url;
        }
        if (mOnlineServerMap.containsKey(tag)) {
            return mOnlineServerMap.get(tag) + url;
        }
        return url;
    }

    /**
     * post 请求 添加secretKey线下替换
     */
    public String secretKey(String key) {
        //测试版并且用户选择了线下服务器才会处理Url
        if (needAdapt()) {
            generateSecretMap();
            String onlineKey = mapSecret(key);
            if (!TextUtils.isEmpty(onlineKey)) {
                return makeOfflineKey(onlineKey);
            }
        }
        return key;
    }

    private String makeOfflineKey(String onlineKey) {
        String keyTag = mOnlineKey.get(onlineKey);
        if (TextUtils.isEmpty(keyTag))
            return onlineKey;
        String offlineKey = mOfflineKey.get(keyTag);
        if (TextUtils.isEmpty(offlineKey))
            return onlineKey;
        return offlineKey;
    }

    /**
     * 生成线下请求地址
     *
     * @param url  请求地址
     * @param host url对应服务器地址
     * @return 线下请求地址
     */
    private String makeOfflineUrl(String url, String host) {
        String hostName = mOnlineServer.get(host);
        if (TextUtils.isEmpty(hostName))
            return url;
        String offHost = mOfflineServer.get(hostName);
        if (TextUtils.isEmpty(offHost))
            return url;
        return url.replace(host, offHost);
    }

    /**
     * 在线上Map中寻找是否有对应服务器地址
     *
     * @param url 请求地址
     * @return 对应Host地址，如果没有返回null
     */
    @Nullable
    private String mapHost(String url) {
        for (Map.Entry<String, String> onlineEntity : mOnlineServer.entrySet()) {
            String key = onlineEntity.getKey();
            if (url.contains(key))
                return key;
        }
        return null;
    }

    /**
     * 在线上Map中寻找是否有对应秘钥
     *
     * @param secretKey Post请求中的秘钥
     * @return 对应秘钥，如果没有返回null
     */
    @Nullable
    private String mapSecret(String secretKey) {
        for (Map.Entry<String, String> onlineEntity : mOnlineKey.entrySet()) {
            String key = onlineEntity.getKey();
            if (TextUtils.equals(secretKey, key) || secretKey.contains(key))
                return key;
        }
        return null;
    }

    /**
     * 生成Post请求秘钥Map
     * 只执行一次，动态初始化
     */
    private void generateSecretMap() {
        if (!mOnlineKey.isEmpty() && !mOfflineKey.isEmpty())
            return;
        Resources resources = FrameApplication.getFrameApplicationContext().getResources();
        //加载线上接口秘钥
        for (String[] onlineKey : onlineKeys) {
            int stringId = Integer.parseInt(onlineKey[0]);
            String name = onlineKey[1];
            mOnlineKey.put(resources.getString(stringId), name);
        }
        //加载线上接口秘钥
        for (String[] offlineKey : offlineKeys) {
            int stringId = Integer.parseInt(offlineKey[0]);
            String name = offlineKey[1];
            mOfflineKey.put(name, resources.getString(stringId));
        }
    }

    /**
     * 生成服务器地址Map
     * 只执行一次，动态初始化
     */
    private void generateHostMap() {
        if (!mOnlineServer.isEmpty() && !mOfflineServer.isEmpty())
            return;
        Resources resources = FrameApplication.getFrameApplicationContext().getResources();
        //加载线上地址
        for (String[] onlineHost : onlineHosts) {
            int stringId = Integer.parseInt(onlineHost[0]);
            String name = onlineHost[1];
            String result = resources.getString(stringId);
            mOnlineServer.put(result, name);
            mOnlineServerMap.put(name, result);
        }
        //加载线上地址
        for (String[] offlineHost : offlineHosts) {
            int stringId = Integer.parseInt(offlineHost[0]);
            String name = offlineHost[1];
            mOfflineServer.put(name, resources.getString(stringId));
        }
    }

    /**
     * 是否需要处理
     */
    private boolean needAdapt() {
        return openSelectServer  && mOffline;
    }

}
