package com.teach.frame;

import android.text.TextUtils;

import com.teach.frame.constants.ConstantKey;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 任小龙 on 2020/3/23.
 */
public class CommonHeadersInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!TextUtils.isEmpty(FrameApplication.getFrameApplication().getCookie())){
            //headers(Headers.of(NetHeaders.getHeadMap()))//可通过map集合一次性添加多个请求头
            request = request.newBuilder().header("Cookie", FrameApplication.getFrameApplication().getCookie()).build();
        }
        Response proceed = chain.proceed(request);
        String url = request.url().toString();
        if (url.contains("user/userLoginNewAuth") || url.contains("loginByMobileCode") || url.contains("thirdlogin")) {
            saveSession(proceed);
        }
        return proceed;
    }

    private void saveSession(Response response) {
        Headers headers = response.headers();
        //如果只有一个cooKie信息用get即可
//        String session = headers.get("Set-Cookie");
        String cookies = "";
        List<String> values = headers.values("Set-Cookie");
        for (int i = 0; i < values.size(); i++) {
            String preStr = values.get(i);
            if (preStr.contains("deleted"))continue;
            String[] split = preStr.split(";");
            cookies += split[0] + ";";
        }
        if (!TextUtils.isEmpty(cookies)) {
            FrameApplication.getFrameApplication().setCookie(cookies);
            SharedPrefrenceUtils.saveString(FrameApplication.getFrameApplicationContext(), ConstantKey.COOKIES, cookies);
        }
    }
}
