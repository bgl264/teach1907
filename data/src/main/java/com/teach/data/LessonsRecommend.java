package com.teach.data;

/**
 * Created by xxl on 2017/8/2.
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 推荐课程实体
 */
public class LessonsRecommend implements Parcelable {

    /**
     * comment_level : 5.0
     * lesson_id : 3318
     * lesson_name : 建筑方案设计基础入门
     * price : 80.00
     * studentnum : 4472
     * thumb : http://attach.zhulong.com/edu/201701/20/45/112545whcutt14x5ehih9n_0_1_360_270.jpg
     * from_type
     */

    private String comment_level;
    private String lesson_id;
    private String lesson_name;
    private String price;
    private String studentnum;
    private String thumb;
    private String from_type;
    private String rate;

    public String getFrom_type() {
        return from_type;
    }

    public void setFrom_type(String from_type) {
        this.from_type = from_type;
    }

    public String getComment_level() {
        return comment_level;
    }

    public void setComment_level(String comment_level) {
        this.comment_level = comment_level;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStudentnum() {
        return studentnum;
    }

    public void setStudentnum(String studentnum) {
        this.studentnum = studentnum;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LessonsRecommend> CREATOR = new Creator<LessonsRecommend>() {
        @Override
        public LessonsRecommend createFromParcel(Parcel in) {
            return new LessonsRecommend(in);
        }

        @Override
        public LessonsRecommend[] newArray(int size) {
            return new LessonsRecommend[size];
        }
    };

    protected LessonsRecommend(Parcel in) {
        comment_level = in.readString();
        lesson_id = in.readString();
        lesson_name = in.readString();
        price = in.readString();
        studentnum = in.readString();
        thumb = in.readString();
        from_type = in.readString();
        rate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(comment_level);
        dest.writeString(lesson_id);
        dest.writeString(lesson_name);
        dest.writeString(price);
        dest.writeString(studentnum);
        dest.writeString(thumb);
        dest.writeString(from_type);
        dest.writeString(rate);
    }
}
