package com.didispace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.didispace.util.DataDev;

@Entity
public class GasPrice {

	@Id
    @GeneratedValue
    private Long id;

    @Column(length=30)
    private String createTime=DataDev.getTime();
    @Column(length=5)
    private String price;

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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "GasPrice [id=" + id + ", createTime=" + createTime + ", price=" + price + "]";
	}
    
}
