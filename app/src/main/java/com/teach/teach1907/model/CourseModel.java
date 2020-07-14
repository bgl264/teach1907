package com.teach.teach1907.model;

import com.teach.frame.ApiConfig;
import com.teach.frame.FrameApplication;
import com.teach.frame.Host;
import com.teach.frame.ICommonModel;
import com.teach.frame.ICommonPresenter;
import com.teach.frame.NetManger;
import com.teach.frame.constants.Constants;
import com.teach.frame.utils.ParamHashMap;
import com.teach.teach1907.constants.Method;

import java.util.Map;

/**
 * Created by 任小龙 on 2020/6/9.
 */
public class CourseModel implements ICommonModel {
    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
        switch (whichApi){
            case ApiConfig.COURSE_CHILD:
                ParamHashMap add = new ParamHashMap().add("specialty_id", FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_id()).add("page", params[2]).add("limit", Constants.LIMIT_NUM).add("course_type", params[1]);
                NetManger.getInstance().netWork(NetManger.mService.getCourseChildData(Host.EDU_OPENAPI+ Method.GETLESSONLISTFORAPI,add),pPresenter,whichApi,params[0]);
                break;
            case ApiConfig.COURSE_DETAIL_INFO:
                NetManger.getInstance().netWork(NetManger.mService.getCourseDetail(Host.EDU_API+Method.GETNEWLESSONDETAIL, (Map<String, Object>) params[0]),pPresenter,whichApi);
                break;
            case ApiConfig.COURSE_COMMENT:
                ParamHashMap add1 = new ParamHashMap().add("lesson_id", params[1]).add("type", params[2]).add("page", params[3]).add("limit", Constants.LIMIT_NUM);
                NetManger.getInstance().netWork(NetManger.mService.getCommentList(Host.EDU_OPENAPI+Method.GETCOMMENTLIST,add1),pPresenter,whichApi,params[0]);
                break;
        }
    }
}
