package com.teach.frame;

/**
 * Created by 任小龙 on 2020/5/29.
 */
public class ServerAddressConfig {
    public static final int API_TYPE = 3;//1,内测  2，外测  3，外正
    public static String BASE_URL = "";

    /**
     * 静态代码块，优先于对象的创建而执行，且只执行一次
     */
    static {
        if (API_TYPE == 1){
            BASE_URL = "";
        }
        if (API_TYPE == 2){
            BASE_URL = "";
        }
        if (API_TYPE == 3){
            BASE_URL = "http://static.owspace.com/";
        }
    }
}
