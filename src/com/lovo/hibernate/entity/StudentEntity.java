package com.lovo.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="sys_student")
public class StudentEntity {
	@Id
	@GenericGenerator(name="suuid",strategy="uuid")
	@GeneratedValue(generator="suuid")
	@Column(length=32)
	private String stuId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_id")
	private TeacherEntity teacher;
	
	
	@Column(length=32)
	private String stuName;

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public TeacherEntity getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherEntity teacher) {
		this.teacher = teacher;
	}
	
	
	
}
