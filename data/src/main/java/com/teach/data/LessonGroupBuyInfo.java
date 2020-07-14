package com.teach.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by WangZhen on 2019/3/27.
 * 团购信息
 */
public class LessonGroupBuyInfo implements Parcelable {

    //团购状态：0 未开始；1 已结束成功；2 已结束失败；3 未结束已满员；4 未结束可团购
    public static final int BUY_NOT_START = 0;
    public static final int BUY_END_SUCCESS = 1;
    public static final int BUY_END_FAILED = 2;
    public static final int BUY_NOTEND_FULL = 3;
    public static final int BUY_NOTEND_CAN_JOIN = 4;

    /**
     * id : 52
     * uid : 7170569
     * lesson_id : 7591
     * start_time : 1553656205
     * end_time : 1553673600
     * number : 9
     * type : 0
     * create_time : 1553593366
     * tg_status : 4
     * user_status : 0
     * synum : 9
     */

    private String id;
    private String uid;
    private String lesson_id;
    private String start_time;  //开始时间
    private String end_time;    //结束时间
    private String number;      //团购总人数
    private String type;
    private String create_time;
    private int tg_status;      //团购状态：0 未开始；1 已结束成功；2 已结束失败；3 未结束已满员；4 未结束可团购
    private int user_status;    //0 未购买；1 已购买
    private int synum;          //剩余人数

    public LessonGroupBuyInfo() {
    }

    protected LessonGroupBuyInfo(Parcel in) {
        id = in.readString();
        uid = in.readString();
        lesson_id = in.readString();
        start_time = in.readString();
        end_time = in.readString();
        number = in.readString();
        type = in.readString();
        create_time = in.readString();
        tg_status = in.readInt();
        user_status = in.readInt();
        synum = in.readInt();
    }

    public static final Creator<LessonGroupBuyInfo> CREATOR = new Creator<LessonGroupBuyInfo>() {
        @Override
        public LessonGroupBuyInfo createFromParcel(Parcel in) {
            return new LessonGroupBuyInfo(in);
        }

        @Override
        public LessonGroupBuyInfo[] newArray(int size) {
            return new LessonGroupBuyInfo[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getTg_status() {
        return tg_status;
    }

    public void setTg_status(int tg_status) {
        this.tg_status = tg_status;
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }

    public int getSynum() {
        return synum;
    }

    public void setSynum(int synum) {
        this.synum = synum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(uid);
        dest.writeString(lesson_id);
        dest.writeString(start_time);
        dest.writeString(end_time);
        dest.writeString(number);
        dest.writeString(type);
        dest.writeString(create_time);
        dest.writeInt(tg_status);
        dest.writeInt(user_status);
        dest.writeInt(synum);
    }
}
