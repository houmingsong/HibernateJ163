package com.lovo.hibernate.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity //被hibernate管理
@Table(name="sys_user")//映射表名
public class UserEntity {
  //实体必须要一个ID
	@Id   
	@Column(name="uid",length=32)  //映射表的列
	@GenericGenerator(name="useruuid",strategy="uuid")//自定义ID生成策略
	@GeneratedValue(generator="useruuid")//用生成策略
	private String userId;
	
	@Column(length=32)
	private String userName;
    @Column(insertable=true)
	private int age;
    @ManyToMany(mappedBy="userSet")
    private Set<SystemEntity> sysSet;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<SystemEntity> getSysSet() {
		return sysSet;
	}

	public void setSysSet(Set<SystemEntity> sysSet) {
		this.sysSet = sysSet;
	}
	
	
}
