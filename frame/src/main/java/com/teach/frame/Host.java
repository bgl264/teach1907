package com.teach.frame;

import android.content.Context;

/**
 * Created by 任小龙 on 2020/5/29.
 */
public class Host {

    private static Context mContext = FrameApplication.getFrameApplicationContext();

    public static final int API_TYPE = 3;//1,内测  2，外测  3，外正

    public static String AD_OPENAPI;
    public static String FOOLOW_LIST_OPENAPI;
    public static String BBS_URL;
    public static String BBS_OPENAPI;
    public static String WEBRULE_URL;
    public static String UPLOAD_PHOTO;
    public static String MESSAGE;
    public static String MESSAGE_API;
    public static String MESSAGE_OPENAPI;
    public static String PASSPORT;
    public static String PASSPORT_API;
    public static String PASSPORT_OPENAPI;
    public static String WEIBO;
    public static String WEIBO_API;
    public static String WEIBO_OPENAPI;
    public static String EDU_URL;
    public static String EDU_API;
    public static String EDU_OPENAPI;
    public static String PASSPORT_OPENAPI_USER;
    public static String BLOG_API;
    public static String S_URL;
    public static String TOPIC_OPENAPI;
    public static String ALL_OPENAPI;
    public static String INFO_OPENAPI;
    public static String PAYMENT_AGREEMENT_API;
    public static String E_PAYMENT_AGREEMENT_API;
    public static String VIP_LIST_OPENAPI;
    public static String FX_OPENAPI;
    public static String PHOTO_URL;
    public static String PHOTO_OPENAPI;
    public static String BBS_API;
    public static String WX_OAUTH;

    /**
     * 静态代码块，优先于对象的创建而执行，且只执行一次
     */
    static {
        if (API_TYPE == 1){
            AD_OPENAPI = "";
            PHOTO_OPENAPI=PHOTO_URL;
        }
        if (API_TYPE == 2){
            AD_OPENAPI = "";
            PHOTO_OPENAPI=AD_OPENAPI;
        }
        if (API_TYPE == 3){
            WX_OAUTH="https://api.weixin.qq.com/sns/oauth2/";
            BBS_API=mContext.getString(R.string.bbs_api);
            AD_OPENAPI = mContext.getString(R.string.ad_openapi);
            PHOTO_OPENAPI = mContext.getString(R.string.photo_openapi);
            PHOTO_URL = mContext.getString(R.string.photo_url);
            FX_OPENAPI = mContext.getString(R.string.fx_openapi);
            VIP_LIST_OPENAPI = mContext.getString(R.string.vip_list_openapi);
            E_PAYMENT_AGREEMENT_API = mContext.getString(R.string.e_payment_agreement_api);
            FOOLOW_LIST_OPENAPI = mContext.getString(R.string.foolow_list_openapi);
            PAYMENT_AGREEMENT_API = mContext.getString(R.string.payment_agreement_api);
            ALL_OPENAPI = mContext.getString(R.string.all_openapi);
            INFO_OPENAPI = mContext.getString(R.string.info_openapi);
            TOPIC_OPENAPI = mContext.getString(R.string.topic_openapi);
            S_URL = mContext.getString(R.string.s_url);
            BLOG_API = mContext.getString(R.string.blog_api);
            PASSPORT_OPENAPI_USER = mContext.getString(R.string.passport_openapi_user);
            BBS_URL=mContext.getString(R.string.bbs_url);
            BBS_OPENAPI=mContext.getString(R.string.bbs_openapi);
            WEBRULE_URL=mContext.getString(R.string.webrule_url);
            UPLOAD_PHOTO=mContext.getString(R.string.upload_photo);
            MESSAGE=mContext.getString(R.string.message);
            MESSAGE_API=mContext.getString(R.string.message_api);
            MESSAGE_OPENAPI=mContext.getString(R.string.message_openapi);
            PASSPORT=mContext.getString(R.string.passport);
            PASSPORT_API=mContext.getString(R.string.passport_api);
            PASSPORT_OPENAPI=mContext.getString(R.string.passport_openapi);
            WEIBO=mContext.getString(R.string.weibo);
            WEIBO_API=mContext.getString(R.string.weibo_api);
            WEIBO_OPENAPI=mContext.getString(R.string.weibo_openapi);
            EDU_URL=mContext.getString(R.string.edu_url);
            EDU_API=mContext.getString(R.string.edu_api);
            EDU_OPENAPI=mContext.getString(R.string.edu_openapi);
        }
    }
}
