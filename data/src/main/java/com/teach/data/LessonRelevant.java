package com.teach.data;

import android.os.Parcel;
import android.os.Parcelable;

public class LessonRelevant implements Parcelable {

    private String lesson_id;
    private String lesson_name;
    private String url;
    private String from_type;

    public LessonRelevant() {
    }

    protected LessonRelevant(Parcel in) {
        lesson_id = in.readString();
        lesson_name = in.readString();
        url = in.readString();
        from_type = in.readString();
    }

    public static final Creator<LessonRelevant> CREATOR = new Creator<LessonRelevant>() {
        @Override
        public LessonRelevant createFromParcel(Parcel in) {
            return new LessonRelevant(in);
        }

        @Override
        public LessonRelevant[] newArray(int size) {
            return new LessonRelevant[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lesson_id);
        dest.writeString(lesson_name);
        dest.writeString(url);
        dest.writeString(from_type);
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFrom_type() {
        return from_type;
    }

    public void setFrom_type(String from_type) {
        this.from_type = from_type;
    }

    @Override
    public String toString() {
        return "LessonRelevant{" +
                "lesson_id='" + lesson_id + '\'' +
                ", lesson_name='" + lesson_name + '\'' +
                ", url='" + url + '\'' +
                ", from_type='" + from_type + '\'' +
                '}';
    }
}
