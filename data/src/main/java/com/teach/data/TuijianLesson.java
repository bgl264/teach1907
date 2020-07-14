package com.teach.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hejiaxing.cn on 2018/3/30.
 */

public class TuijianLesson implements Parcelable {
    private String lession_id;
    private String lesson_name;
    private String sort;
    private String thumb;
    private String url;
    private String length;
    private String open_time;
    private String comment_num;
    private String img;
    private String price;
    private String comment_rate;
    private String studentnum;

    public TuijianLesson() {
    }

    protected TuijianLesson(Parcel in) {
        lession_id = in.readString();
        lesson_name = in.readString();
        sort = in.readString();
        thumb = in.readString();
        url = in.readString();
        length = in.readString();
        open_time = in.readString();
        comment_num = in.readString();
        img = in.readString();
        price = in.readString();
        comment_rate = in.readString();
        studentnum = in.readString();
    }

    public static final Creator<TuijianLesson> CREATOR = new Creator<TuijianLesson>() {
        @Override
        public TuijianLesson createFromParcel(Parcel in) {
            return new TuijianLesson(in);
        }

        @Override
        public TuijianLesson[] newArray(int size) {
            return new TuijianLesson[size];
        }
    };

    public String getLession_id() {
        return lession_id;
    }

    public void setLession_id(String lession_id) {
        this.lession_id = lession_id;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getComment_num() {
        return comment_num;
    }

    public void setComment_num(String comment_num) {
        this.comment_num = comment_num;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getComment_rate() {
        return comment_rate;
    }

    public void setComment_rate(String comment_rate) {
        this.comment_rate = comment_rate;
    }

    public String getStudentnum() {
        return studentnum;
    }

    public void setStudentnum(String studentnum) {
        this.studentnum = studentnum;
    }

    public static Creator<TuijianLesson> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lession_id);
        dest.writeString(lesson_name);
        dest.writeString(sort);
        dest.writeString(thumb);
        dest.writeString(url);
        dest.writeString(length);
        dest.writeString(open_time);
        dest.writeString(comment_num);
        dest.writeString(img);
        dest.writeString(price);
        dest.writeString(comment_rate);
        dest.writeString(studentnum);
    }
}
