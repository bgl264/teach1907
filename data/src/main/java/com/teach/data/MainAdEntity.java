package com.teach.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by whb on 2017/9/20.
 * Email 18720982457@163.com
 * 首页活动实体
 */

public class MainAdEntity implements Parcelable {

    /**
     * ad_id : 6860
     * place_name : APP_800
     * height : 213
     * type : 2
     * info_url : http://static.zhulong.com/poster/201709/20/105047wmkigv02c97qgd3h.png
     * jump_url : http://www.zhulong.com
     * title : APP弹框
     * start_time : 1505836800
     * end_time : 1513785600
     * width : 800
     */

    private String ad_id;
    private String place_name;
    private String height;
    private String type;
    private String info_url;
    private String jump_url;
    private String title;
    private String start_time;
    private String end_time;
    private String width;

    protected MainAdEntity(Parcel in) {
        ad_id = in.readString();
        place_name = in.readString();
        height = in.readString();
        type = in.readString();
        info_url = in.readString();
        jump_url = in.readString();
        title = in.readString();
        start_time = in.readString();
        end_time = in.readString();
        width = in.readString();
    }

    public static final Creator<MainAdEntity> CREATOR = new Creator<MainAdEntity>() {
        @Override
        public MainAdEntity createFromParcel(Parcel in) {
            return new MainAdEntity(in);
        }

        @Override
        public MainAdEntity[] newArray(int size) {
            return new MainAdEntity[size];
        }
    };

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo_url() {
        return info_url;
    }

    public void setInfo_url(String info_url) {
        this.info_url = info_url;
    }

    public String getJump_url() {
        return jump_url;
    }

    public void setJump_url(String jump_url) {
        this.jump_url = jump_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ad_id);
        dest.writeString(place_name);
        dest.writeString(height);
        dest.writeString(type);
        dest.writeString(info_url);
        dest.writeString(jump_url);
        dest.writeString(title);
        dest.writeString(start_time);
        dest.writeString(end_time);
        dest.writeString(width);
    }
}
