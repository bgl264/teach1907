package com.teach.data;

import java.io.Serializable;

/**
 * Created by 任小龙 on 2020/6/2.
 */
public class BaseInfo<D> implements Serializable {
    private static final long serialVersionUID = 7178751760258280801L;
    public int errNo;
    public int exeTime;
    public String msg;
    public D result;
    public String discount_info;

    public boolean isSuccess() {
        return errNo == 0;
    }
}
