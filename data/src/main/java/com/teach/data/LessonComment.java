package com.teach.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 课程评论实体类
 *
 * Author:Jason
 * <p>
 * Date:2016/11/2
 */
public class LessonComment implements Parcelable {

    private String id;
    private String lesson_id;
    private String userid;
    private String username;
    private String touserid;
    private String tousername;
    private String isrealname;
    private String content;
    private String rank;
    private String isreplay;
    private String istop;
    private String add_time;
    private String typeid;
    private List<String> replycontent;
    private String comment_type;
    private String comment_return_money;
    private String img;
    private List<String> attr;

    public LessonComment() {
    }

    protected LessonComment(Parcel in) {
        id = in.readString();
        lesson_id = in.readString();
        userid = in.readString();
        username = in.readString();
        touserid = in.readString();
        tousername = in.readString();
        isrealname = in.readString();
        content = in.readString();
        rank = in.readString();
        isreplay = in.readString();
        istop = in.readString();
        add_time = in.readString();
        typeid = in.readString();
        replycontent = in.createStringArrayList();
        comment_type = in.readString();
        comment_return_money = in.readString();
        img = in.readString();
        attr = in.createStringArrayList();
    }

    public static final Creator<LessonComment> CREATOR = new Creator<LessonComment>() {
        @Override
        public LessonComment createFromParcel(Parcel in) {
            return new LessonComment(in);
        }

        @Override
        public LessonComment[] newArray(int size) {
            return new LessonComment[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(lesson_id);
        dest.writeString(userid);
        dest.writeString(username);
        dest.writeString(touserid);
        dest.writeString(tousername);
        dest.writeString(isrealname);
        dest.writeString(content);
        dest.writeString(rank);
        dest.writeString(isreplay);
        dest.writeString(istop);
        dest.writeString(add_time);
        dest.writeString(typeid);
        dest.writeStringList(replycontent);
        dest.writeString(comment_type);
        dest.writeString(comment_return_money);
        dest.writeString(img);
        dest.writeStringList(attr);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTouserid() {
        return touserid;
    }

    public void setTouserid(String touserid) {
        this.touserid = touserid;
    }

    public String getTousername() {
        return tousername;
    }

    public void setTousername(String tousername) {
        this.tousername = tousername;
    }

    public String getIsrealname() {
        return isrealname;
    }

    public void setIsrealname(String isrealname) {
        this.isrealname = isrealname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getIsreplay() {
        return isreplay;
    }

    public void setIsreplay(String isreplay) {
        this.isreplay = isreplay;
    }

    public String getIstop() {
        return istop;
    }

    public void setIstop(String istop) {
        this.istop = istop;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public List<String> getReplycontent() {
        return replycontent;
    }

    public void setReplycontent(List<String> replycontent) {
        this.replycontent = replycontent;
    }

    public String getComment_type() {
        return comment_type;
    }

    public void setComment_type(String comment_type) {
        this.comment_type = comment_type;
    }

    public String getComment_return_money() {
        return comment_return_money;
    }

    public void setComment_return_money(String comment_return_money) {
        this.comment_return_money = comment_return_money;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "LessonComment{" +
                "id='" + id + '\'' +
                ", lesson_id='" + lesson_id + '\'' +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", touserid='" + touserid + '\'' +
                ", tousername='" + tousername + '\'' +
                ", isrealname='" + isrealname + '\'' +
                ", content='" + content + '\'' +
                ", rank='" + rank + '\'' +
                ", isreplay='" + isreplay + '\'' +
                ", istop='" + istop + '\'' +
                ", add_time='" + add_time + '\'' +
                ", typeid='" + typeid + '\'' +
                ", replycontent=" + replycontent +
                ", comment_type='" + comment_type + '\'' +
                ", comment_return_money='" + comment_return_money + '\'' +
                ", img='" + img + '\'' +
                ", tags=" + attr +
                '}';
    }

    public List<String> getTags() {
        return attr;
    }

    public void setTags(List<String> tags) {
        this.attr = tags;
    }
}
