package com.didispace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.didispace.util.DataDev;

@Entity
public class MailRecord {

	@Id
    @GeneratedValue
    private Long id;
    @Column(length=100)
    private String name;
    @Column(length=250)
    private String title;
    @Column(length=250)
    private String content;
    @Column(length=20)
    private String sendTime=DataDev.getTime();
    private boolean isSuccess=true;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
    
    

    
}
