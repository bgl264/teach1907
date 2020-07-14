package com.teach.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by whb on 2018/1/12.
 */

public class LessonCrowdfundingEntity implements Parcelable {

    /**
     * start_time : 1515340800
     * end_time : 1518019200
     * jump_url : http://edu.zhulong.com/service/qidian?lessonId=4826&type=1
     * discount : 6
     * discount_price : 149
     * price : 249
     */

    private String start_time;
    private String end_time;
    private String jump_url;
    private String discount;
    private String discount_price;
    private String price;

    public LessonCrowdfundingEntity() {
    }

    public LessonCrowdfundingEntity(Parcel in) {
        start_time = in.readString();
        end_time = in.readString();
        jump_url = in.readString();
        discount = in.readString();
        discount_price = in.readString();
        price = in.readString();
    }

    public static final Creator<LessonCrowdfundingEntity> CREATOR = new Creator<LessonCrowdfundingEntity>() {
        @Override
        public LessonCrowdfundingEntity createFromParcel(Parcel in) {
            return new LessonCrowdfundingEntity(in);
        }

        @Override
        public LessonCrowdfundingEntity[] newArray(int size) {
            return new LessonCrowdfundingEntity[size];
        }
    };

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

    public String getJump_url() {
        return jump_url;
    }

    public void setJump_url(String jump_url) {
        this.jump_url = jump_url;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(start_time);
        dest.writeString(end_time);
        dest.writeString(jump_url);
        dest.writeString(discount);
        dest.writeString(discount_price);
        dest.writeString(price);
    }
}
