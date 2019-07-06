package com.lovo.hibernate.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="sys_teacher")
public class TeacherEntity {

	@Id
	@GenericGenerator(name="tuuid",strategy="uuid")
	@GeneratedValue(generator="tuuid")
	@Column(length=32)
	private String teacherId;
	
	@OneToMany(mappedBy="teacher",fetch=FetchType.EAGER)
	@Cascade(value=CascadeType.ALL) //级联关系，谨慎使用，一般情况下不是使用
	private Set<StudentEntity> stuSet;

	private String teacherName;


	public String getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}


	public String getTeacherName() {
		return teacherName;
	}


	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}


	public Set<StudentEntity> getStuSet() {
		return stuSet;
	}


	public void setStuSet(Set<StudentEntity> stuSet) {
		this.stuSet = stuSet;
	}

	

	
}
