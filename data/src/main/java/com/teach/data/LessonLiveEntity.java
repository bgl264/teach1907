package com.teach.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by huangwy on 2017/10/11.
 * email: kisenhuang@163.com.
 */

public class LessonLiveEntity implements Parcelable {
    /**
     [live_id] => 322
     [live_name] => 测试多条直播2
     [startTime] => 1503307530
     [playback] => http://myedu.zhulong.com/weiketang/detail40896
     [sort] => 1
     [wkt_id] => 40896
     [is_liveing] => -1
     */
    private String live_name;
    private String live_id;
    private long startTime;
    private String playback;//视频微课地址
    private int sort;
    private String wkt_id;
    private int is_liveing;//直播状态：0 未开始  1正在直播 -1已结束

    public static final Creator<LessonLiveEntity> CREATOR = new Creator<LessonLiveEntity>() {
        @Override
        public LessonLiveEntity createFromParcel(Parcel source) {
            return new LessonLiveEntity(source);
        }

        @Override
        public LessonLiveEntity[] newArray(int size) {
            return new LessonLiveEntity[size];
        }
    };

    public LessonLiveEntity() {
    }

    protected LessonLiveEntity(Parcel in) {
        this.live_name = in.readString();
        this.live_id = in.readString();
        this.startTime = in.readLong();
        this.playback = in.readString();
        this.sort = in.readInt();
        this.wkt_id = in.readString();
        this.is_liveing = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.live_name);
        dest.writeString(this.live_id);
        dest.writeLong(this.startTime);
        dest.writeString(this.playback);
        dest.writeInt(this.sort);
        dest.writeString(this.wkt_id);
        dest.writeInt(this.is_liveing);
    }

    public String getLive_name() {
        return live_name;
    }

    public void setLive_name(String live_name) {
        this.live_name = live_name;
    }

    public String getLive_id() {
        return live_id;
    }

    public void setLive_id(String live_id) {
        this.live_id = live_id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getPlayback() {
        return playback;
    }

    public void setPlayback(String playback) {
        this.playback = playback;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getWkt_id() {
        return wkt_id;
    }

    public void setWkt_id(String wkt_id) {
        this.wkt_id = wkt_id;
    }

    public int getIs_liveing() {
        return is_liveing;
    }

    public void setIs_liveing(int is_liveing) {
        this.is_liveing = is_liveing;
    }
}
