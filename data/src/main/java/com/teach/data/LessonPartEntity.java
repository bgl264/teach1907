package com.teach.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

/**
 * 课程目录章节
 *
 * Author:Jason
 * <p>
 * Date:2016/11/3
 */

public class LessonPartEntity implements Parcelable {

    public static final int LESSON_PART_VIDEO = 1;
    public static final int LESSON_PART_LIVE = 2;
    public static final int LESSON_PART_ZUOYE = 3;
    public static final int LESSON_PART_JIAOCAI = 4;
    public static final int LESSON_PART_ZILIAO = 5;
    public static final int LESSON_PART_CESHI = 6;

    // 1.视频 2.直播 3.作业 4.教材  5.资料 6.测试
    private int data_type;

    // data_type 1 小节
    private String id;
    private String part;
    private String part_name;
    private String is_dome;
    private String play_url;
    private String is_charge;
    private String mins;
    private String cc_id;
    private String wk_id;
    private String jiaocai;
    private String tiezi_url;
    private String ccvideo_size;    //清晰大小  79.70M
    private String high_m;          //高清大小  162.60M
    private long qingxi_byte;       //清晰字节数 83530572
    private long high_byte;         //高清字节数 170522487
    private int mins_hund;         //课程已学10%

    private String seq;
    private String is_exist_ccid;   //1视频可下载

    // data_type 2 直播
    private String live_name;
    private String class_id;
    private String live_id;
    private String playback;
    private String startTime;
    private String endTime;
    private String is_free;    //直播是否免费 1免费

    // data_type 3 作业
    @SerializedName("task_name")
    private String zuoyeName;
    @SerializedName("task_url")
    private String zuoyeUrl;
    @SerializedName("close_time")
    private String zuoyeCloseTime;

    // data_type 4 教材
    @SerializedName("teachbook_name")
    private String jiaocaiName;
    @SerializedName("teachbook_url")
    private String jiaocaiUrl;
    @SerializedName("teachbook_size")   //5.23M
    private String jiaocaiSize;
    @SerializedName("teachbook_filesize")   //5479717 字节
    private String jiaocaiFileSize;

    // data_type 5 资料
    @SerializedName("data_name")
    private String ziliaoName;
    @SerializedName("data_url")
    private String ziliaoUrl;
    @SerializedName("data_size")
    private String ziliaoSize;
    @SerializedName("data_filesize")
    private String ziliaoFileSize;

    // data_type 6 测试
    private String sub_part_name;
    private String true_rate;   //正确率0，该知识点共10分，可得0分
    private String da_id;   //评测过的答案id

//    private transient DownloaderWrapper downloaderWrapper;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.data_type);

        // data_type 1 小节
        dest.writeString(this.id);
        dest.writeString(this.part);
        dest.writeString(this.part_name);
        dest.writeString(this.is_dome);
        dest.writeString(this.play_url);
        dest.writeString(this.is_charge);
        dest.writeString(this.mins);
        dest.writeString(this.cc_id);
        dest.writeString(this.wk_id);
        dest.writeString(this.ccvideo_size);
        dest.writeString(this.high_m);
        dest.writeLong(this.qingxi_byte);
        dest.writeLong(this.high_byte);
        dest.writeInt(this.mins_hund);
        dest.writeString(this.jiaocai);
        dest.writeString(this.tiezi_url);
        dest.writeString(this.seq);
        dest.writeString(this.is_exist_ccid);

        // data_type 2 直播
        dest.writeString(this.live_name);
        dest.writeString(this.class_id);
        dest.writeString(this.live_id);
        dest.writeString(this.playback);
        dest.writeString(this.startTime);
        dest.writeString(this.endTime);
        dest.writeString(this.is_free);

        // data_type 3 作业
        dest.writeString(this.zuoyeName);
        dest.writeString(this.zuoyeUrl);
        dest.writeString(this.zuoyeCloseTime);


        // data_type 4 教材
        dest.writeString(this.jiaocaiName);
        dest.writeString(this.jiaocaiUrl);
        dest.writeString(this.jiaocaiSize);
        dest.writeString(this.jiaocaiFileSize);

        // data_type 5 资料
        dest.writeString(this.ziliaoName);
        dest.writeString(this.ziliaoUrl);
        dest.writeString(this.ziliaoSize);
        dest.writeString(this.ziliaoFileSize);

        // data_type 6 测试
        dest.writeString(this.sub_part_name);
        dest.writeString(this.true_rate);
        dest.writeString(this.da_id);

    }

    public LessonPartEntity() {
    }

    protected LessonPartEntity(Parcel in) {
        this.data_type = in.readInt();

        // data_type 1 小节
        this.id = in.readString();
        this.part = in.readString();
        this.part_name = in.readString();
        this.is_dome = in.readString();
        this.play_url = in.readString();
        this.is_charge = in.readString();
        this.mins = in.readString();
        this.cc_id=in.readString();
        this.wk_id=in.readString();
        this.ccvideo_size=in.readString();
        this.high_m=in.readString();
        this.qingxi_byte=in.readLong();
        this.high_byte=in.readLong();
        this.mins_hund=in.readInt();
        this.jiaocai=in.readString();
        this.tiezi_url=in.readString();
        this.seq=in.readString();
        this.is_exist_ccid=in.readString();

        // data_type 2 直播
        this.live_name = in.readString();
        this.class_id = in.readString();
        this.live_id = in.readString();
        this.playback = in.readString();
        this.startTime = in.readString();
        this.endTime = in.readString();
        this.is_free = in.readString();


        // data_type 3 作业
        this.zuoyeName = in.readString();
        this.zuoyeUrl = in.readString();
        this.zuoyeCloseTime = in.readString();


        // data_type 4 教材
        this.jiaocaiName = in.readString();
        this.jiaocaiUrl = in.readString();
        this.jiaocaiSize = in.readString();
        this.jiaocaiFileSize = in.readString();

        // data_type 5 资料
        this.ziliaoName = in.readString();
        this.ziliaoUrl = in.readString();
        this.ziliaoSize = in.readString();
        this.ziliaoFileSize = in.readString();

        // data_type 5 资料
        this.sub_part_name = in.readString();
        this.true_rate = in.readString();
        this.da_id = in.readString();

    }

    public static final Creator<LessonPartEntity> CREATOR = new Creator<LessonPartEntity>() {
        @Override
        public LessonPartEntity createFromParcel(Parcel source) {
            return new LessonPartEntity(source);
        }

        @Override
        public LessonPartEntity[] newArray(int size) {
            return new LessonPartEntity[size];
        }
    };

//    public DownloaderWrapper getDownloaderWrapper() {
//        return downloaderWrapper;
//    }
//
//    public void setDownloaderWrapper(DownloaderWrapper downloaderWrapper) {
//        this.downloaderWrapper = downloaderWrapper;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getPart_name() {
        return part_name;
    }

    public void setPart_name(String part_name) {
        this.part_name = part_name;
    }


    public boolean liveIsFree(){
        return !TextUtils.isEmpty(this.is_free) && TextUtils.equals("1",this.is_free);
    }

    public boolean IsDome() {
        return !TextUtils.isEmpty(this.is_dome) && TextUtils.equals("1",this.is_dome);
    }

    public boolean isExistCCId() {
        return !TextUtils.isEmpty(this.is_exist_ccid) && TextUtils.equals("1",this.is_exist_ccid);
    }

    public void setIs_dome(String is_dome) {
        this.is_dome = is_dome;
    }

    public String getPlay_url() {
        return play_url;
    }

    public void setPlay_url(String play_url) {
        this.play_url = play_url;
    }

    public String getIs_charge() {
        return is_charge;
    }

    public void setIs_charge(String is_charge) {
        this.is_charge = is_charge;
    }

    public String getMins() {
        return mins;
    }

    public void setMins(String mins) {
        this.mins = mins;
    }


    public String getCc_id() {
        return cc_id;
    }

    public void setCc_id(String cc_id) {
        this.cc_id = cc_id;
    }

    public int getData_type() {
        return data_type;
    }

    public void setData_type(int data_type) {
        this.data_type = data_type;
    }

    public String getLive_name() {
        return live_name;
    }

    public void setLive_name(String live_name) {
        this.live_name = live_name;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getLive_id() {
        return live_id;
    }

    public void setLive_id(String live_id) {
        this.live_id = live_id;
    }

    public String getPlayback() {
        return playback;
    }

    public void setPlayback(String playback) {
        this.playback = playback;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getZuoyeName() {
        return zuoyeName;
    }

    public void setZuoyeName(String zuoyeName) {
        this.zuoyeName = zuoyeName;
    }

    public String getZuoyeUrl() {
        return zuoyeUrl;
    }

    public void setZuoyeUrl(String zuoyeUrl) {
        this.zuoyeUrl = zuoyeUrl;
    }

    public String getZuoyeCloseTime() {
        return zuoyeCloseTime;
    }

    public void setZuoyeCloseTime(String zuoyeCloseTime) {
        this.zuoyeCloseTime = zuoyeCloseTime;
    }

    public String getJiaocaiName() {
        return jiaocaiName;
    }

    public void setJiaocaiName(String jiaocaiName) {
        this.jiaocaiName = jiaocaiName;
    }

    public String getJiaocaiUrl() {
        return jiaocaiUrl;
    }

    public void setJiaocaiUrl(String jiaocaiUrl) {
        this.jiaocaiUrl = jiaocaiUrl;
    }

    public String getJiaocaiSize() {
        return jiaocaiSize;
    }

    public void setJiaocaiSize(String jiaocaiSize) {
        this.jiaocaiSize = jiaocaiSize;
    }

    public String getJiaocaiFileSize() {
        return jiaocaiFileSize;
    }

    public void setJiaocaiFileSize(String jiaocaiFileSize) {
        this.jiaocaiFileSize = jiaocaiFileSize;
    }

    public String getZiliaoName() {
        return ziliaoName;
    }

    public void setZiliaoName(String ziliaoName) {
        this.ziliaoName = ziliaoName;
    }

    public String getZiliaoUrl() {
        return ziliaoUrl;
    }

    public void setZiliaoUrl(String ziliaoUrl) {
        this.ziliaoUrl = ziliaoUrl;
    }

    public String getZiliaoSize() {
        return ziliaoSize;
    }

    public void setZiliaoSize(String ziliaoSize) {
        this.ziliaoSize = ziliaoSize;
    }

    public String getZiliaoFileSize() {
        return ziliaoFileSize;
    }

    public void setZiliaoFileSize(String ziliaoFileSize) {
        this.ziliaoFileSize = ziliaoFileSize;
    }

    public String getWk_id() {
        return wk_id;
    }

    public void setWk_id(String wk_id) {
        this.wk_id = wk_id;
    }

    public String getCcvideo_size() {
        return ccvideo_size;
    }

    public void setCcvideo_size(String ccvideo_size) {
        this.ccvideo_size = ccvideo_size;
    }

    public String getHigh_m() {
        return high_m;
    }

    public void setHigh_m(String high_m) {
        this.high_m = high_m;
    }

    public long getQingxi_byte() {
        return qingxi_byte;
    }

    public void setQingxi_byte(long qingxi_byte) {
        this.qingxi_byte = qingxi_byte;
    }

    public int getMins_hund() {
        return mins_hund;
    }

    public void setMins_hund(int mins_hund) {
        this.mins_hund = mins_hund;
    }

    public long getHigh_byte() {
        return high_byte;
    }

    public void setHigh_byte(long high_byte) {
        this.high_byte = high_byte;
    }

    public String getJiaocai() {
        return jiaocai;
    }

    public void setJiaocai(String jiaocai) {
        this.jiaocai = jiaocai;
    }

    public String getTiezi_url() {
        return tiezi_url;
    }

    public void setTiezi_url(String tiezi_url) {
        this.tiezi_url = tiezi_url;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public void setIs_exist_ccid(String is_exist_ccid) {
        this.is_exist_ccid = is_exist_ccid;
    }

    public void setIs_free(String is_free) {
        this.is_free = is_free;
    }

    public String getSub_part_name() {
        return sub_part_name;
    }

    public void setSub_part_name(String sub_part_name) {
        this.sub_part_name = sub_part_name;
    }

    public String getTrue_rate() {
        return true_rate;
    }

    public void setTrue_rate(String true_rate) {
        this.true_rate = true_rate;
    }

    public String getDa_id() {
        return da_id;
    }

    public void setDa_id(String da_id) {
        this.da_id = da_id;
    }

    @Override
    public String toString() {
        return "LessonPartEntity{" +
                "data_type=" + data_type +
                ", id='" + id + '\'' +
                ", part='" + part + '\'' +
                ", part_name='" + part_name + '\'' +
                ", is_dome='" + is_dome + '\'' +
                ", play_url='" + play_url + '\'' +
                ", is_charge='" + is_charge + '\'' +
                ", mins='" + mins + '\'' +
                ", cc_id='" + cc_id + '\'' +
                ", wk_id='" + wk_id + '\'' +
                ", jiaocai='" + jiaocai + '\'' +
                ", tiezi_url='" + tiezi_url + '\'' +
                ", ccvideo_size='" + ccvideo_size + '\'' +
                ", high_m='" + high_m + '\'' +
                ", qingxi_byte=" + qingxi_byte +
                ", high_byte=" + high_byte +
                ", seq='" + seq + '\'' +
                ", is_exist_ccid='" + is_exist_ccid + '\'' +
                ", live_name='" + live_name + '\'' +
                ", class_id='" + class_id + '\'' +
                ", live_id='" + live_id + '\'' +
                ", playback='" + playback + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", is_free='" + is_free + '\'' +
                ", zuoyeName='" + zuoyeName + '\'' +
                ", zuoyeUrl='" + zuoyeUrl + '\'' +
                ", zuoyeCloseTime='" + zuoyeCloseTime + '\'' +
                ", jiaocaiName='" + jiaocaiName + '\'' +
                ", jiaocaiUrl='" + jiaocaiUrl + '\'' +
                ", jiaocaiSize='" + jiaocaiSize + '\'' +
                ", jiaocaiFileSize='" + jiaocaiFileSize + '\'' +
                ", ziliaoName='" + ziliaoName + '\'' +
                ", ziliaoUrl='" + ziliaoUrl + '\'' +
                ", ziliaoSize='" + ziliaoSize + '\'' +
                ", ziliaoFileSize='" + ziliaoFileSize + '\'' +
                ", sub_part_name='" + sub_part_name + '\'' +
                ", true_rate='" + true_rate + '\'' +
//                ", downloaderWrapper=" + downloaderWrapper +
                '}';
    }
}
