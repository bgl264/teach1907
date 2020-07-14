package com.teach.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.util.List;

/**
 * 课程详细
 * <p>
 * Author:WangZhen
 * <p>
 * Date:2018/11/9
 */
public class CourseDetailInfo implements Parcelable {

    private String lesson_id;
    private String from_type;   //LessonType，5是微课，Constants.TYPE_MICRO_LESSON
    private String course_type; //课程类型 1:精品课; 2:公开课; 3:训练营
    private String lesson_name;
    private String price;
    private String info;
    private String zt_url;
    private String thumb;
    private String only_vip_buy;
    private String only_vip_pay;
    private String status;

    private String studentnum;
    private String is_vip_free;
    private String vip_price;
    private String comment_level;
    private String comment_num;
    private String price_unit;
    private String type;        //1就是筑龙课程 2 是 网校的课程
    private String is_package;
    private List<String> tags;
    private LessonService related_service;
    private LessonCrowdfundingEntity crowdfunding;
    private String is_collect;
    private String voucher;
    private String wk_tequan;
    private String is_pay;
    private String freedom;
    private LessonCatalogueEntity catalogue;  //课程目录
    private LessonCrowdfundingEntity prefunding;//课程预售信息
    private String is_down;//是否可以下载
    private String expiration_time;//课程过期时间
    private String amiba_id;
    private String teacher_id;
    private TeacherInfo teacher_info;
    private String crowds;
    private String sale_status;     //1众筹；2预售；3正常
    private String is_p_show;       //1隐藏讲师信息
    private String is_vip;          //1是VIP
//
    private boolean belongToUser;   //表示已购买状态。已购买课程,或用户是VIP且课程是VIP课程，为true
    private boolean hideLessonList; //是否隐藏课表

    private QidianEntity qidian;

    private List<LessonRelevant> relevant;    //搭配课程

    private List<LessonComment> comment_list;       //精选评论

    private String discount_info;       //打折信息：VIP9折,E会员98折

    private String last_part_id;//最后一次播放

    private LessonsRecommend recommend_lesson;

    private List<LessonLiveEntity> live;//直播信息

    private String rate;//好评率

    private TuijianLesson tuijian_lesson;
    private SkexamInfo skexam_info ;
    private String show_vip_tag;

//    private LessonGroupBuyInfo groupbuy_info; //团购信息

//    public LessonGroupBuyInfo getGroupBuyInfo() {
//        return groupbuy_info;
//    }

    public void setIs_p_show(String is_p_show) {
        this.is_p_show = is_p_show;
    }

    public void setIs_vip(String is_vip) {
        this.is_vip = is_vip;
    }

//    public void setGroupbuy_info(LessonGroupBuyInfo groupbuy_info) {
//        this.groupbuy_info = groupbuy_info;
//    }

    public String getDiscount_info() {
        return discount_info;
    }

    public void setDiscount_info(String discount_info) {
        this.discount_info = discount_info;
    }

    public List<LessonRelevant> getRelevant() {
        return relevant;
    }

    public void setRelevant(List<LessonRelevant> relevant) {
        this.relevant = relevant;
    }

    public List<LessonComment> getComment_list() {
        return comment_list;
    }

    public void setComment_list(List<LessonComment> comment_list) {
        this.comment_list = comment_list;
    }

    public QidianEntity getQidian() {
        return qidian;
    }

    public void setQidian(QidianEntity qidian) {
        this.qidian = qidian;
    }

    public boolean isBelongToUser() {
        return belongToUser;
    }

    public void setBelongToUser(boolean belongToUser) {
        this.belongToUser = belongToUser;
    }

    public boolean isHideLessonList() {
        return hideLessonList;
    }

    public void setHideLessonList(boolean hideLessonList) {
        this.hideLessonList = hideLessonList;
    }

    /**
     * 是否隐藏讲师信息
     * @return true 隐藏
     */
    public boolean hideTeacherInfo(){
        return !TextUtils.isEmpty(this.is_p_show) && TextUtils.equals("1",this.is_p_show);
    }

    /**
     * 是否是会员身份
     * @return true 是会员
     */
    public boolean isVip(){
        return !TextUtils.isEmpty(this.is_vip) && TextUtils.equals("1",this.is_vip);
    }

    public String getSale_status() {
        return sale_status;
    }

    public void setSale_status(String sale_status) {
        this.sale_status = sale_status;
    }

    public String getCourse_type() {
        return course_type;
    }

    public void setCourse_type(String course_type) {
        this.course_type = course_type;
    }

    public SkexamInfo getSkexam_info() {
        return skexam_info;
    }

    public void setSkexam_info(SkexamInfo skexam_info) {
        this.skexam_info = skexam_info;
    }

    public String getCrowds() {
        return crowds;
    }

    public void setCrowds(String crowds) {
        this.crowds = crowds;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }


    public TeacherInfo getTeacher_info() {
        return teacher_info;
    }

    public void setTeacher_info(TeacherInfo teacher_info) {
        this.teacher_info = teacher_info;
    }

    public String getAmiba_id() {
        return amiba_id;
    }

    public void setAmiba_id(String amiba_id) {
        this.amiba_id = amiba_id;
    }

    private String initial_size; //微客视频大小  单位字节
    private String isdownload;   //微客是否可离线下载

    public TuijianLesson getTuijian_lesson() {
        return tuijian_lesson;
    }

    public void setTuijian_lesson(TuijianLesson tuijian_lesson) {
        this.tuijian_lesson = tuijian_lesson;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.course_type);
        dest.writeString(this.crowds);

        dest.writeString(this.teacher_id);
        dest.writeParcelable(this.teacher_info,flags);
        dest.writeString(this.amiba_id);
        dest.writeString(this.lesson_id);
        dest.writeString(this.from_type);
        dest.writeString(this.lesson_name);
        dest.writeString(this.price);
        dest.writeString(this.info);
        dest.writeString(this.zt_url);
        dest.writeString(this.thumb);
        dest.writeString(this.only_vip_buy);
        dest.writeString(this.only_vip_pay);
        dest.writeString(this.status);
        dest.writeString(this.studentnum);
        dest.writeString(this.is_vip_free);
        dest.writeString(this.vip_price);
        dest.writeString(this.comment_level);
        dest.writeString(this.comment_num);
        dest.writeString(this.price_unit);
        dest.writeString(this.type);
        dest.writeString(this.is_package);
        dest.writeStringList(this.tags);

        dest.writeParcelable(this.related_service, flags);
        dest.writeParcelable(this.crowdfunding, flags);
        dest.writeParcelable(this.prefunding, flags);
        dest.writeTypedList(this.live);
        dest.writeString(this.is_collect);
        dest.writeString(this.voucher);
        dest.writeString(this.wk_tequan);
        dest.writeString(this.is_pay);
        dest.writeString(this.freedom);
        dest.writeParcelable(this.catalogue,flags);
        dest.writeString(this.isdownload);
        dest.writeString(this.initial_size);
        dest.writeString(this.is_down);
        dest.writeString(this.expiration_time);
        dest.writeString(this.last_part_id);

        dest.writeParcelable(this.recommend_lesson, flags);
        dest.writeString(this.rate);
        dest.writeParcelable(this.tuijian_lesson,flags);
        dest.writeParcelable(this.skexam_info,flags);
        dest.writeString(this.show_vip_tag);
        dest.writeString(this.sale_status);
        dest.writeString(this.is_p_show);
        dest.writeString(this.is_vip);
        dest.writeInt(this.belongToUser ? 1 : 0);
        dest.writeInt(this.hideLessonList ? 1 : 0);
        dest.writeParcelable(this.qidian,flags);
        dest.createTypedArrayList(LessonRelevant.CREATOR);
        dest.createTypedArrayList(LessonComment.CREATOR);
        dest.writeString(this.discount_info);
//        dest.writeParcelable(this.groupbuy_info,flags);

    }

    public CourseDetailInfo() {
    }

    protected CourseDetailInfo(Parcel in) {
        this.course_type = in.readString();
        this.crowds = in.readString();
        this.teacher_id = in.readString();
        this.teacher_info = in.readParcelable(TeacherInfo.class.getClassLoader());
        this.amiba_id =in.readString();
        this.lesson_id = in.readString();
        this.from_type = in.readString();
        this.lesson_name = in.readString();
        this.price = in.readString();
        this.info = in.readString();
        this.zt_url = in.readString();
        this.thumb = in.readString();
        this.only_vip_buy = in.readString();
        this.only_vip_pay = in.readString();
        this.status = in.readString();
        this.studentnum = in.readString();
        this.is_vip_free = in.readString();
        this.vip_price = in.readString();
        this.comment_level = in.readString();
        this.comment_num = in.readString();
        this.price_unit = in.readString();
        this.type = in.readString();
        this.is_package = in.readString();
        this.tags = in.createStringArrayList();
        this.related_service = in.readParcelable(LessonService.class.getClassLoader());
        this.crowdfunding = in.readParcelable(LessonCrowdfundingEntity.class.getClassLoader());
        this.prefunding = in.readParcelable(LessonCrowdfundingEntity.class.getClassLoader());

        this.live = in.createTypedArrayList(LessonLiveEntity.CREATOR);
        this.is_collect = in.readString();
        this.voucher = in.readString();
        this.wk_tequan = in.readString();
        this.is_pay = in.readString();
        this.freedom = in.readString();
        this.catalogue = in.readParcelable(LessonCatalogueEntity.class.getClassLoader());
        this.isdownload = in.readString();
        this.initial_size = in.readString();
        this.is_down = in.readString();
        this.expiration_time = in.readString();
        this.last_part_id = in.readString();

        this.recommend_lesson = in.readParcelable(LessonsRecommend.class.getClassLoader());
        this.rate = in.readString();
        this.tuijian_lesson = in.readParcelable(TuijianLesson.class.getClassLoader());
        this.skexam_info = in.readParcelable(SkexamInfo.class.getClassLoader());
        this.show_vip_tag = in.readString();
        this.sale_status = in.readString();
        this.is_p_show = in.readString();
        this.is_vip = in.readString();
        this.belongToUser = in.readInt() == 1;
        this.hideLessonList = in.readInt() == 1;
        this.qidian = in.readParcelable(QidianEntity.class.getClassLoader());

        this.relevant = in.createTypedArrayList(LessonRelevant.CREATOR);
        this.comment_list = in.createTypedArrayList(LessonComment.CREATOR);
        this.discount_info = in.readString();
//        this.groupbuy_info = in.readParcelable(LessonGroupBuyInfo.class.getClassLoader());
    }

    public static final Creator<CourseDetailInfo> CREATOR = new Creator<CourseDetailInfo>() {
        @Override
        public CourseDetailInfo createFromParcel(Parcel source) {
            return new CourseDetailInfo(source);
        }

        @Override
        public CourseDetailInfo[] newArray(int size) {
            return new CourseDetailInfo[size];
        }
    };

    public LessonCrowdfundingEntity getCrowdfunding() {
        return crowdfunding;
    }

    public LessonCrowdfundingEntity getPrefunding() {
        return prefunding;
    }

    public void setPrefunding(LessonCrowdfundingEntity prefunding) {
        this.prefunding = prefunding;
    }

    public void setCrowdfunding(LessonCrowdfundingEntity crowdfunding) {
        this.crowdfunding = crowdfunding;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public List<LessonLiveEntity> getLive() {
        return live;
    }

    public void setLive(List<LessonLiveEntity> live) {
        this.live = live;
    }

    public LessonsRecommend getRecommend_lesson() {
        return recommend_lesson;
    }

    public void setRecommend_lesson(LessonsRecommend recommend_lesson) {
        this.recommend_lesson = recommend_lesson;
    }

    public boolean isVipCourse() {  // amiba_id为112，是Vip课程
        return !TextUtils.isEmpty(amiba_id) && TextUtils.equals("112", amiba_id);
    }

    public boolean isBought() {
        return !TextUtils.isEmpty(is_pay) && TextUtils.equals("1", is_pay);

    }

    public boolean isPayByRMB() {
        return TextUtils.isEmpty(price_unit) || TextUtils.equals("2", price_unit);

    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getFrom_type() {
        return from_type;
    }

    public void setFrom_type(String from_type) {
        this.from_type = from_type;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getOnly_vip_buy() {
        return only_vip_buy;
    }

    public void setOnly_vip_buy(String only_vip_buy) {
        this.only_vip_buy = only_vip_buy;
    }

    public String getOnly_vip_pay() {
        return only_vip_pay;
    }

    public void setOnly_vip_pay(String only_vip_pay) {
        this.only_vip_pay = only_vip_pay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentnum() {
        return studentnum;
    }

    public void setStudentnum(String studentnum) {
        this.studentnum = studentnum;
    }

    public String getIs_vip_free() {
        return is_vip_free;
    }

    public void setIs_vip_free(String is_vip_free) {
        this.is_vip_free = is_vip_free;
    }

    public String getVip_price() {
        return vip_price;
    }

    public void setVip_price(String vip_price) {
        this.vip_price = vip_price;
    }

    public String getComment_level() {
        return comment_level;
    }

    public void setComment_level(String comment_level) {
        this.comment_level = comment_level;
    }

    public String getComment_num() {
        return comment_num;
    }

    public void setComment_num(String comment_num) {
        this.comment_num = comment_num;
    }

    public String getPrice_unit() {
        return price_unit;
    }

    public void setPrice_unit(String price_unit) {
        this.price_unit = price_unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIs_package() {
        return is_package;
    }

    public void setIs_package(String is_package) {
        this.is_package = is_package;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public LessonService getRelated_service() {
        return related_service;
    }

    public void setRelated_service(LessonService related_service) {
        this.related_service = related_service;
    }

    public String getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(String is_collect) {
        this.is_collect = is_collect;
    }

    public String getWk_tequan() {
        return wk_tequan;
    }

    public void setWk_tequan(String wk_tequan) {
        this.wk_tequan = wk_tequan;
    }

    public String getIs_pay() {
        return is_pay;
    }

    public void setIs_pay(String is_pay) {
        this.is_pay = is_pay;
    }

    public String getFreedom() {
        return freedom;
    }

    public void setFreedom(String freedom) {
        this.freedom = freedom;
    }

    public LessonCatalogueEntity getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(LessonCatalogueEntity catalogue) {
        this.catalogue = catalogue;
    }

    public boolean isLessonCollected() {
        return !TextUtils.isEmpty(is_collect) && TextUtils.equals(is_collect, "1");
    }

    public boolean isLessonValid() {
        return !TextUtils.isEmpty(status) && TextUtils.equals(status, "1");
    }

    public boolean isLessonFree() {
        return !TextUtils.isEmpty(price) && Float.parseFloat(price) == 0;
    }

    public boolean isWkTQEnough() {
        return !TextUtils.isEmpty(wk_tequan) && !TextUtils.isEmpty(voucher) && Float.parseFloat(wk_tequan) + Float.parseFloat(voucher) > 0;
    }

    public boolean isVipFreeLesson() {
        return !TextUtils.isEmpty(is_vip_free) && TextUtils.equals("1", is_vip_free);
    }

    public boolean isVipPriceZero() {
        return !TextUtils.isEmpty(vip_price) && Float.parseFloat(vip_price) == 0;
    }

    public String getInitial_size() {
        return initial_size;
    }

    public void setInitial_size(String initial_size) {
        this.initial_size = initial_size;
    }

    public String getIsdownload() {
        return isdownload;
    }

    public void setIsdownload(String isdownload) {
        this.isdownload = isdownload;
    }

    public String getIs_down() {
        return is_down;
    }

    public void setIs_down(String is_down) {
        this.is_down = is_down;
    }

    public String getExpiration_time() {
        return expiration_time;
    }

    public void setExpiration_time(String expiration_time) {
        this.expiration_time = expiration_time;
    }

    public String getLast_part_id() {
        return last_part_id;
    }

    public void setLast_part_id(String last_part_id) {
        this.last_part_id = last_part_id;
    }

    public String getShow_vip_tag() {
        return show_vip_tag;
    }

    public void setShow_vip_tag(String show_vip_tag) {
        this.show_vip_tag = show_vip_tag;
    }

    @Override
    public String toString() {
        return "LessonDetail{" +
                "lesson_id='" + lesson_id + '\'' +
                ", from_type='" + from_type + '\'' +
                ", course_type='" + course_type + '\'' +
                ", lesson_name='" + lesson_name + '\'' +
                ", price='" + price + '\'' +
                ", info='" + info + '\'' +
                ", zt_url='" + zt_url + '\'' +
                ", thumb='" + thumb + '\'' +
                ", only_vip_buy='" + only_vip_buy + '\'' +
                ", only_vip_pay='" + only_vip_pay + '\'' +
                ", status='" + status + '\'' +
                ", studentnum='" + studentnum + '\'' +
                ", is_vip_free='" + is_vip_free + '\'' +
                ", vip_price='" + vip_price + '\'' +
                ", comment_level='" + comment_level + '\'' +
                ", comment_num='" + comment_num + '\'' +
                ", price_unit='" + price_unit + '\'' +
                ", type='" + type + '\'' +
                ", is_package='" + is_package + '\'' +
                ", tags=" + tags +
                ", related_service=" + related_service +
                ", crowdfunding=" + crowdfunding +
                ", is_collect='" + is_collect + '\'' +
                ", voucher='" + voucher + '\'' +
                ", wk_tequan='" + wk_tequan + '\'' +
                ", is_pay='" + is_pay + '\'' +
                ", freedom='" + freedom + '\'' +
                ", catalogue=" + catalogue +
                ", prefunding=" + prefunding +
                ", is_down='" + is_down + '\'' +
                ", expiration_time='" + expiration_time + '\'' +
                ", amiba_id='" + amiba_id + '\'' +
                ", teacher_id='" + teacher_id + '\'' +
                ", teacher_info=" + teacher_info +
                ", crowds='" + crowds + '\'' +
                ", sale_status='" + sale_status + '\'' +
                ", is_p_show='" + is_p_show + '\'' +
                ", is_vip='" + is_vip + '\'' +
                ", belongToUser=" + belongToUser +
                ", hideLessonList=" + hideLessonList +
                ", qidian=" + qidian +
                ", relevant=" + relevant +
                ", comment_list=" + comment_list +
                ", discount_info='" + discount_info + '\'' +
                ", last_part_id='" + last_part_id + '\'' +
                ", recommend_lesson=" + recommend_lesson +
                ", live=" + live +
                ", rate='" + rate + '\'' +
                ", tuijian_lesson=" + tuijian_lesson +
                ", skexam_info=" + skexam_info +
                ", show_vip_tag='" + show_vip_tag + '\'' +
//                ", groupbuy_info=" + groupbuy_info +
                ", initial_size='" + initial_size + '\'' +
                ", isdownload='" + isdownload + '\'' +
                '}';
    }

    public String getZt_url() {
        return zt_url;
    }

    public void setZt_url(String zt_url) {
        this.zt_url = zt_url;
    }
}
