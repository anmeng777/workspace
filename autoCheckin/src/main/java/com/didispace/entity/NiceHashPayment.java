package com.didispace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.didispace.util.DataDev;

@Entity
public class NiceHashPayment {

	@Id
    @GeneratedValue
    private Long id;

	@Column(length=15)
	private String amount;
	
	@Column(length=15)
	private String fee;
	
	private short type;
    
    @Column(length=20)
    private String time;
    
    @Column(length=20)
    private String LocalTime;
    
    @Column(length=5)
    private String price;

    @Column(length=30)
    private String createTime=DataDev.getTime();

    
	public String getLocalTime() {
		return LocalTime;
	}

	public void setLocalTime(String localTime) {
		LocalTime = localTime;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
    
    
}
