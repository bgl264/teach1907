package com.teach.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hejiaxing.cn on 2018/4/10.
 */

public class SkexamInfo implements Parcelable {
    private String is_praxis;       //1有评测
    private String skexam_three;

    public SkexamInfo() {
    }

    protected SkexamInfo(Parcel in) {
        is_praxis = in.readString();
        skexam_three = in.readString();
    }

    public static final Creator<SkexamInfo> CREATOR = new Creator<SkexamInfo>() {
        @Override
        public SkexamInfo createFromParcel(Parcel in) {
            return new SkexamInfo(in);
        }

        @Override
        public SkexamInfo[] newArray(int size) {
            return new SkexamInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(is_praxis);
        dest.writeString(skexam_three);
    }

    public String getIs_praxis() {
        return is_praxis;
    }

    public void setIs_praxis(String is_praxis) {
        this.is_praxis = is_praxis;
    }

    public String getSkexam_three() {
        return skexam_three;
    }

    public void setSkexam_three(String skexam_three) {
        this.skexam_three = skexam_three;
    }

    public static Creator<SkexamInfo> getCREATOR() {
        return CREATOR;
    }
}
