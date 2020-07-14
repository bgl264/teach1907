package com.teach.data;

import java.io.Serializable;

/**
 * 搜索得到的课程的实体
 * Created by huangwy on 2017/8/28.
 */

public class SearchItemEntity implements Serializable {
    private static final long serialVersionUID = 1687915148713922979L;
    /**
     * id : 17013
     * intro : 章节内容建设工程经济• 工程经济• 工程财务• 建设工程估价建设工程项目管理• 建设工程项目的组织与管理• 建设工程项目施工成本控制• 建设
     * lesson_id : 3706
     * lesson_name : （新版课件）2017一级建造师市政全科签约取证及实操班
     * price : 9800
     * rank : 5.0
     * specialty_id : 5,12,9,14,10,7,1
     * studentnum : 123
     * thumb : http://attach.zhulong.com/edu/201610/14/36/095336zm8itjhyxxvksyyn_0_1_360_270.jpg
     * type : 1
     */

    private String id;
    private String intro;
    private String lesson_id;
    private String lesson_name;
    private String price;
    private String rank;
    private String specialty_id;
    private String studentnum;
    private String thumb;
    private String type;

    private String rate;//好评率

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSpecialty_id() {
        return specialty_id;
    }

    public void setSpecialty_id(String specialty_id) {
        this.specialty_id = specialty_id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SearchItemEntity{" +
                "id='" + id + '\'' +
                ", intro='" + intro + '\'' +
                ", lesson_id='" + lesson_id + '\'' +
                ", lesson_name='" + lesson_name + '\'' +
                ", price='" + price + '\'' +
                ", rank='" + rank + '\'' +
                ", specialty_id='" + specialty_id + '\'' +
                ", studentnum='" + studentnum + '\'' +
                ", thumb='" + thumb + '\'' +
                ", type='" + type + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
