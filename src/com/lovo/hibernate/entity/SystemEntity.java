package com.lovo.hibernate.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sys_system")
public class SystemEntity {

	@Id   
	@Column(name="sysid",length=32)  //映射表的列
	@GenericGenerator(name="sysuuid",strategy="uuid")//自定义ID生成策略
	@GeneratedValue(generator="sysuuid")//用生成策略
	private String sysId;
	@Column(length=48)
	private String systemName;
	@ManyToMany
	@JoinTable(name="user_system",joinColumns={@JoinColumn(name="sys_id")},
	inverseJoinColumns={@JoinColumn(name="user_id")})
	private Set<UserEntity> userSet;

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public Set<UserEntity> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<UserEntity> userSet) {
		this.userSet = userSet;
	}
	
	
	
	
	
}
