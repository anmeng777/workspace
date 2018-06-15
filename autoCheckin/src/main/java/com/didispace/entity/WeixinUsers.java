package com.didispace.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WeixinUsers entity. @author MyEclipse Persistence Tools
 */
@Entity
public class WeixinUsers implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private Long id;
	private String openid;
	private String unionid;
	private String wid;
	private Short subscribe;
	private String nickName;
	private Short sex;
	private Integer wallet;
	private Integer level;
	private String headImgUrl;
	private String city;
	private String province;
	private String country;
	private String language;
	private Timestamp firstTime;
	private Timestamp lastSynTime;
	private Timestamp unTime;
	private String skin;
	private String remark;
	private Integer groupId;
	private String tmp;
	private Timestamp tmpTime;
	private Integer age;
	private String occupAtion;
	private String makingFriends;
	private String userLong;
	private String userLat;
	private String bids;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public Short getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Short subscribe) {
		this.subscribe = subscribe;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Short getSex() {
		return sex;
	}
	public void setSex(Short sex) {
		this.sex = sex;
	}
	public Integer getWallet() {
		return wallet;
	}
	public void setWallet(Integer wallet) {
		this.wallet = wallet;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Timestamp getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(Timestamp firstTime) {
		this.firstTime = firstTime;
	}
	public Timestamp getLastSynTime() {
		return lastSynTime;
	}
	public void setLastSynTime(Timestamp lastSynTime) {
		this.lastSynTime = lastSynTime;
	}
	public Timestamp getUnTime() {
		return unTime;
	}
	public void setUnTime(Timestamp unTime) {
		this.unTime = unTime;
	}
	public String getSkin() {
		return skin;
	}
	public void setSkin(String skin) {
		this.skin = skin;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getTmp() {
		return tmp;
	}
	public void setTmp(String tmp) {
		this.tmp = tmp;
	}
	public Timestamp getTmpTime() {
		return tmpTime;
	}
	public void setTmpTime(Timestamp tmpTime) {
		this.tmpTime = tmpTime;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getOccupAtion() {
		return occupAtion;
	}
	public void setOccupAtion(String occupAtion) {
		this.occupAtion = occupAtion;
	}
	public String getMakingFriends() {
		return makingFriends;
	}
	public void setMakingFriends(String makingFriends) {
		this.makingFriends = makingFriends;
	}
	public String getUserLong() {
		return userLong;
	}
	public void setUserLong(String userLong) {
		this.userLong = userLong;
	}
	public String getUserLat() {
		return userLat;
	}
	public void setUserLat(String userLat) {
		this.userLat = userLat;
	}
	public String getBids() {
		return bids;
	}
	public void setBids(String bids) {
		this.bids = bids;
	}

	

}