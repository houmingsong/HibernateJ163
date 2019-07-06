package com.lovo.hibernate.entity;

public class UserDto {

	private String stuName;
	private String teacherName;
	
	public UserDto(){}
	public UserDto(String stuName, String teacherName) {
		super();
		this.stuName = stuName;
		this.teacherName = teacherName;
	}
	
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	
}
