package com.didispace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MailReciever {

	@Id
    @GeneratedValue
    private Long id;

    @Column(length=20)
    private String mail;
    @Column(length=10)
    private String name;
    
    private boolean isEnable=true;
    
    private boolean weather;
    private boolean xianhao;
    private boolean youjia;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public boolean isEnable() {
		return isEnable;
	}
	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}
	public boolean isWeather() {
		return weather;
	}
	public void setWeather(boolean weather) {
		this.weather = weather;
	}
	public boolean isXianhao() {
		return xianhao;
	}
	public void setXianhao(boolean xianhao) {
		this.xianhao = xianhao;
	}
	public boolean isYoujia() {
		return youjia;
	}
	public void setYoujia(boolean youjia) {
		this.youjia = youjia;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
}
