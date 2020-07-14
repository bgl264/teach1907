package com.teach.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 课程目录数据实例
 */
public class LessonCatalogueEntity implements Parcelable {

    @SerializedName("is_exist_catalogue")
    private String isExistCatalogue;      //是否存在章 1有章信息；0只有节没有章
    @SerializedName("catalogue_list")
    private List<LessonChapterEntity> catalogueList;  //章列表
    @SerializedName("total_part_size")
    private long totalPartSize;         //视频总大小

    private int part_count;
    private int live_count;


    public LessonCatalogueEntity() {
    }

    protected LessonCatalogueEntity(Parcel in) {
        this.isExistCatalogue = in.readString();
        this.catalogueList = in.createTypedArrayList(LessonChapterEntity.CREATOR);
        this.totalPartSize = in.readLong();
        this.part_count = in.readInt();
        this.live_count = in.readInt();
    }

    public static final Creator<LessonCatalogueEntity> CREATOR = new Creator<LessonCatalogueEntity>() {
        @Override
        public LessonCatalogueEntity createFromParcel(Parcel in) {
            return new LessonCatalogueEntity(in);
        }

        @Override
        public LessonCatalogueEntity[] newArray(int size) {
            return new LessonCatalogueEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.isExistCatalogue);
        dest.writeTypedList(this.catalogueList);
        dest.writeLong(this.totalPartSize);
        dest.writeInt(this.part_count);
        dest.writeInt(this.live_count);
    }

    public String getIsExistCatalogue() {
        return isExistCatalogue;
    }

    public void setIsExistCatalogue(String isExistCatalogue) {
        this.isExistCatalogue = isExistCatalogue;
    }

    public List<LessonChapterEntity> getCatalogueList() {
        return catalogueList;
    }

    public void setCatalogueList(List<LessonChapterEntity> catalogueList) {
        this.catalogueList = catalogueList;
    }

    public long getTotalPartSize() {
        return totalPartSize;
    }

    public void setTotalPartSize(long totalPartSize) {
        this.totalPartSize = totalPartSize;
    }

    public int getPart_count() {
        return part_count;
    }

    public void setPart_count(int part_count) {
        this.part_count = part_count;
    }

    public int getLive_count() {
        return live_count;
    }

    public void setLive_count(int live_count) {
        this.live_count = live_count;
    }

    @Override
    public String toString() {
        return "LessonCatalogueEntity{" +
                "isExistCatalogue='" + isExistCatalogue + '\'' +
                ", catalogueList=" + catalogueList +
                ", totalPartSize=" + totalPartSize +
                ", part_count=" + part_count +
                ", live_count=" + live_count +
                '}';
    }

}
