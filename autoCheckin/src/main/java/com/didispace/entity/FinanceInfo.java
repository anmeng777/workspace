package com.didispace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.didispace.util.DataDev;

@Entity
public class FinanceInfo {

	@Id
    @GeneratedValue
    private Long id;
	@Column(length=20)
    private String user;
    @Column(length=30)
    private String createTime=DataDev.getTime();
    @Column(length=15)
    private String startDate=DataDev.getDateWithCut();
    private int period=3;
    private int totalAmt;
    @Column(length=10)
    private String rate="0.085";
    private boolean isBack=false;
    @Column(length=20)
    private String ticketNo;
    private short getLixiTimes=0;
    
	public int getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(int totalAmt) {
		this.totalAmt = totalAmt;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public short getGetLixiTimes() {
		return getLixiTimes;
	}
	public void setGetLixiTimes(short getLixiTimes) {
		this.getLixiTimes = getLixiTimes;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public boolean isBack() {
		return isBack;
	}
	public void setBack(boolean isBack) {
		this.isBack = isBack;
	}
    
}
