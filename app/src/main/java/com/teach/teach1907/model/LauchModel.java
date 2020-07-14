package com.teach.teach1907.model;

import android.content.Context;
import android.text.TextUtils;

import com.teach.frame.ApiConfig;
import com.teach.frame.FrameApplication;
import com.teach.frame.ICommonModel;
import com.teach.frame.ICommonPresenter;
import com.teach.frame.NetManger;
import com.teach.frame.Host;
import com.teach.frame.secret.RsaUtil;
import com.teach.frame.utils.ParamHashMap;
import com.teach.teach1907.base.Application1907;
import com.teach.teach1907.constants.Method;

/**
 * Created by 任小龙 on 2020/6/2.
 */
public class LauchModel implements ICommonModel {
    private NetManger mManger = NetManger.getInstance();
    private Context mContext = Application1907.get07ApplicationContext();

    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
        switch (whichApi) {
            case ApiConfig.ADVERT:
                ParamHashMap map = new ParamHashMap().add("w", params[1]).add("h", params[2]).add("positions_id", "APP_QD_01").add("is_show", 0);
                if (!TextUtils.isEmpty((String) params[0])) map.add("specialty_id", params[0]);
                mManger.netWork(NetManger.mService.getAdvert(Host.AD_OPENAPI+ Method.ADVERT_PATH,map), pPresenter, whichApi);
                break;
            case ApiConfig.SUBJECT:
                mManger.netWork(NetManger.mService.getSubjectList(Host.EDU_OPENAPI+ Method.GETALLSPECIALTY), pPresenter, whichApi);
                break;
        }
    }
}
