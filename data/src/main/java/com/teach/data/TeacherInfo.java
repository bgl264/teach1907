package com.teach.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 任小龙 on 2020/6/19.
 */
public class TeacherInfo implements Parcelable {
    public String status;
    public List<TeachData> datas;

    protected TeacherInfo(Parcel in) {
        status = in.readString();
    }

    public static final Creator<TeacherInfo> CREATOR = new Creator<TeacherInfo>() {
        @Override
        public TeacherInfo createFromParcel(Parcel in) {
            return new TeacherInfo(in);
        }

        @Override
        public TeacherInfo[] newArray(int size) {
            return new TeacherInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
    }

    public class TeachData {
        public String thumbnail;
        public String title;
        public String author;
    }
}
