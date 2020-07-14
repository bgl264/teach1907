package com.teach.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * 课程章信息
 */
public class LessonChapterEntity implements Parcelable {

    @SerializedName("catalogue_name")
    private String catalogueName;  //章名称

    @SerializedName("part_total")
    private int partTotal;  //章节数

    @SerializedName("part_list")
    private ArrayList<LessonPartEntity> partList;    //小节列表

    public LessonChapterEntity() {
    }

    protected LessonChapterEntity(Parcel in) {
        this.catalogueName = in.readString();
        this.partTotal = in.readInt();
        this.partList = in.createTypedArrayList(LessonPartEntity.CREATOR);
    }

    public static final Creator<LessonChapterEntity> CREATOR = new Creator<LessonChapterEntity>() {
        @Override
        public LessonChapterEntity createFromParcel(Parcel in) {
            return new LessonChapterEntity(in);
        }

        @Override
        public LessonChapterEntity[] newArray(int size) {
            return new LessonChapterEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(catalogueName);
        dest.writeInt(partTotal);
        dest.writeTypedList(this.partList);
    }

    public int getPartTotal() {
        return partTotal;
    }

    public void setPartTotal(int partTotal) {
        this.partTotal = partTotal;
    }

    public String getCatalogueName() {
        return catalogueName;
    }

    public void setCatalogueName(String catalogueName) {
        this.catalogueName = catalogueName;
    }

    public ArrayList<LessonPartEntity> getPartList() {
        return partList;
    }

    public void setPartList(ArrayList<LessonPartEntity> partList) {
        this.partList = partList;
    }

    @Override
    public String toString() {
        return "LessonChapterEntity{" +
                "catalogueName='" + catalogueName + '\'' +
                ", partTotal='" + partTotal + '\'' +
                ", partList=" + partList +
                '}';
    }
}
