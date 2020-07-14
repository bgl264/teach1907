package com.teach.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 课程详细中的相关服务
 *
 * Author:Jason
 * <p>
 * Date:2016/11/3
 */
public class LessonService implements Parcelable {

    private String qq;
    private String qq_name;
    private String qq_url;
    private String text;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.qq);
        dest.writeString(this.qq_name);
        dest.writeString(this.qq_url);
        dest.writeString(this.text);
    }

    public LessonService() {
    }

    protected LessonService(Parcel in) {
        this.qq = in.readString();
        this.qq_name = in.readString();
        this.qq_url = in.readString();
        this.text = in.readString();
    }

    public static final Creator<LessonService> CREATOR = new Creator<LessonService>() {
        @Override
        public LessonService createFromParcel(Parcel source) {
            return new LessonService(source);
        }

        @Override
        public LessonService[] newArray(int size) {
            return new LessonService[size];
        }
    };

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getQq_name() {
        return qq_name;
    }

    public void setQq_name(String qq_name) {
        this.qq_name = qq_name;
    }

    public String getQq_url() {
        return qq_url;
    }

    public void setQq_url(String qq_url) {
        this.qq_url = qq_url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "LessonService{" +
                "qq='" + qq + '\'' +
                ", qq_name='" + qq_name + '\'' +
                ", qq_url='" + qq_url + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
