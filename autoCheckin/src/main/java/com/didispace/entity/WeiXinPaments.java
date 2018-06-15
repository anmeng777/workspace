package com.didispace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.didispace.util.DataDev;
import com.didispace.util.Tools;

@Entity
public class WeiXinPaments {

	@Id
    @GeneratedValue
    private Long id;
	@Column(length=30)
    private String orderid=Tools.nextSequence();
    @Column(length=30)
    private String createTime=DataDev.getTime();
    private int money;
    @Column(length=30)
    private String openid;
    @Column(length=30)
    private String nickname;
    private boolean isSuccess=false;
    @Column(length=140)
    private String body;
    private int transamt;
    @Column(length=30)
    private String wxorderid;
    
    public WeiXinPaments(){
    	
    }
	public WeiXinPaments(int money, String openid, String nickname, String body) {
		super();
		this.money = money;
		this.openid = openid;
		this.nickname = nickname;
		this.body = body;
	}
	
	public int getTransamt() {
		return transamt;
	}

	public void setTransamt(int transamt) {
		this.transamt = transamt;
	}

	public String getWxorderid() {
		return wxorderid;
	}

	public void setWxorderid(String wxorderid) {
		this.wxorderid = wxorderid;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
    
}
