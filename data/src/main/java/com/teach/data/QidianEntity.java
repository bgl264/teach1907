package com.teach.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 用其中aHref字段进行QQ跳转
 */
public class QidianEntity implements Parcelable {

    public QidianEntity() {
    }

    private String aHref;

    public String getaHref() {
        return aHref;
    }

    public void setaHref(String aHref) {
        this.aHref = aHref;
    }

    protected QidianEntity(Parcel in) {
        aHref = in.readString();
    }

    public static final Creator<QidianEntity> CREATOR = new Creator<QidianEntity>() {
        @Override
        public QidianEntity createFromParcel(Parcel in) {
            return new QidianEntity(in);
        }

        @Override
        public QidianEntity[] newArray(int size) {
            return new QidianEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(aHref);
    }

    @Override
    public String toString() {
        return "QidianEntity{" +
                "aHref='" + aHref + '\'' +
                '}';
    }
}
