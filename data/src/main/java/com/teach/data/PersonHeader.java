package com.teach.data;

import java.io.Serializable;
import java.math.BigDecimal;

public class PersonHeader implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -4575470824518435397L;
	public static final int MINE = 1;
	public static final int OTHER = 10;
	private String idolnum;
	private String uid;
	private String tag;
	private String star;
	private String friendnum;
	private String avatar;
	private String specialty_id;
	private String city="";
	private String username;
	private String fansnum;
	private String is_tx;
	private String province;
	private String gender;
	private String weibonum;
	private String sina_utime;
	private String specialty;
	private String is_sina;
	private String threadnum;
	private String replynum;
	private String groupnum;
	public int flag;
	public String lookForUID;
	private int is_sign;
	private int coin;
	private String is_vip;
	private String realName;
	private int isidol;
	private String vip_level;  //标准是2 金牌是3 E会员4
	private String vip_end_time;
	private String vip_start_time;//vip开始时间

	private String voucher;//剩余特权个数

	private String lessonTime;//本周学习时长

	private String is_avatar;	//是否已更换头像 1 已更换；0 未更换

	private String is_avatar1;	//是否已更换头像 1 已更换；0 未更换

	private String is_username;	//是否已更换用户名 1 已更换；0 未更换
	private String email;
	private String mobail;


	public String getIs_avatar() {
		return is_avatar;
	}

	public void setIs_avatar(String is_avatar) {
		this.is_avatar = is_avatar;
	}

	public String getIs_avatar1() {
		return is_avatar1;
	}

	public void setIs_avatar1(String is_avatar1) {
		this.is_avatar1 = is_avatar1;
	}

	public String getIs_username() {
		return is_username;
	}

	public void setIs_username(String is_username) {
		this.is_username = is_username;
	}

	public String getVip_start_time() {
		return vip_start_time;
	}

	public void setVip_start_time(String vip_start_time) {
		this.vip_start_time = vip_start_time;
	}

	public String getLessonTime() {
		return lessonTime;
	}

	public void setLessonTime(String lessonTime) {
		this.lessonTime = lessonTime;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public String getVip_level() {
		return vip_level;
	}

	public void setVip_level(String vip_level) {
		this.vip_level = vip_level;
	}

	public String getVip_end_time() {
		return vip_end_time;
	}

	public void setVip_end_time(String vip_end_time) {
		this.vip_end_time = vip_end_time;
	}

	public int getIsidol() {
		return isidol;
	}

	public void setIsidol(int isidol) {
		this.isidol = isidol;
	}

	public String getIs_vip() {
		return is_vip;
	}

	public void setIs_vip(String is_vip) {
		this.is_vip = is_vip;
	}

	public int getIs_sign() {
		return is_sign;
	}

	public void setIs_sign(int is_sign) {
		this.is_sign = is_sign;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobail() {
		return mobail;
	}

	public void setMobail(String mobail) {
		this.mobail = mobail;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getIdolnum() {
		return idolnum;
	}

	public void setIdolnum(String idolnum) {
		this.idolnum = idolnum;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getFriendnum() {
		return friendnum;
	}

	public void setFriendnum(String friendnum) {
		this.friendnum = friendnum;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSpecialty_id() {
		return specialty_id;
	}

	public void setSpecialty_id(String specialty_id) {
		this.specialty_id = specialty_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		if (city.equals("false") || city.equals("")) {
			this.city = "";
		} else {
			this.city = city;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFansnum() {
		int num = Integer.valueOf(fansnum);
		if (num > 10000) {
			BigDecimal decimal = new BigDecimal(num / 10000.0);
			decimal.setScale(1, BigDecimal.ROUND_HALF_UP);
			return decimal.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue()
					+ "万";
		}
		return fansnum;
	}

	public void setFansnum(String fansnum) {
		this.fansnum = fansnum;
	}

	public String getIs_tx() {
		return is_tx;
	}

	public void setIs_tx(String is_tx) {
		this.is_tx = is_tx;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getWeibonum() {
		return weibonum;
	}

	public void setWeibonum(String weibonum) {
		this.weibonum = weibonum;
	}

	public String getSina_utime() {
		return sina_utime;
	}

	public void setSina_utime(String sina_utime) {
		this.sina_utime = sina_utime;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getIs_sina() {
		return is_sina;
	}

	public void setIs_sina(String is_sina) {
		this.is_sina = is_sina;
	}

	public String getThreadnum() {
		return threadnum;
	}

	public String getReplynum() {
		return replynum;
	}

	public String getGroupnum() {
		return groupnum;
	}

	public void setThreadnum(String threadnum) {
		this.threadnum = threadnum;
	}

	public void setReplynum(String replynum) {
		this.replynum = replynum;
	}

	public void setGroupnum(String groupnum) {
		this.groupnum = groupnum;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Override
	public String toString() {
		return "PersonHeader{" +
				"idolnum='" + idolnum + '\'' +
				", uid='" + uid + '\'' +
				", tag='" + tag + '\'' +
				", star='" + star + '\'' +
				", friendnum='" + friendnum + '\'' +
				", avatar='" + avatar + '\'' +
				", specialty_id='" + specialty_id + '\'' +
				", city='" + city + '\'' +
				", username='" + username + '\'' +
				", fansnum='" + fansnum + '\'' +
				", is_tx='" + is_tx + '\'' +
				", province='" + province + '\'' +
				", gender='" + gender + '\'' +
				", weibonum='" + weibonum + '\'' +
				", sina_utime='" + sina_utime + '\'' +
				", specialty='" + specialty + '\'' +
				", is_sina='" + is_sina + '\'' +
				", threadnum='" + threadnum + '\'' +
				", replynum='" + replynum + '\'' +
				", groupnum='" + groupnum + '\'' +
				", flag=" + flag +
				", lookForUID='" + lookForUID + '\'' +
				", is_sign=" + is_sign +
				", coin=" + coin +
				", is_vip='" + is_vip + '\'' +
				", realName='" + realName + '\'' +
				", isidol=" + isidol +
				", vip_level='" + vip_level + '\'' +
				", vip_end_time='" + vip_end_time + '\'' +
				", vip_start_time='" + vip_start_time + '\'' +
				", voucher='" + voucher + '\'' +
				", lessonTime='" + lessonTime + '\'' +
				", is_avatar='" + is_avatar + '\'' +
				", is_avatar1='" + is_avatar1 + '\'' +
				", is_username='" + is_username + '\'' +
				", email='" + email + '\'' +
				", mobail='" + mobail + '\'' +
				'}';
	}

	public boolean changedAvatarUsername() {
		//已修改头像和用户名
		return "1".equals(is_avatar1) && "1".equals(is_username);
	}
}
