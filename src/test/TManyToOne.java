package test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lovo.hibernate.db.HibernateSession;
import com.lovo.hibernate.entity.StudentEntity;
import com.lovo.hibernate.entity.TeacherEntity;

public class TManyToOne {
	Session session=	null;
	Transaction tr=null;
	@Before
	public void before(){
		session=	HibernateSession.getSession();
	    tr=	session.getTransaction();
	   tr.begin();
	}
	
	@Test
	public void sava(){
		//一的一方持久化
		TeacherEntity t=new TeacherEntity();
		t.setTeacherName("陈老师");
		 session.save(t);
		//对的一方持久化
	    StudentEntity s=new StudentEntity();
	    s.setStuName("赵云");
	    s.setTeacher(t);
	    StudentEntity s1=new StudentEntity();
	    s1.setStuName("关羽");
	    s1.setTeacher(t);
	    StudentEntity s3=new StudentEntity();
	    s3.setStuName("张飞");
	    s3.setTeacher(t);
	    session.save(s);
	    session.save(s1);
	    session.save(s3);
	}
	@Test
	public void sava1(){
		//一的一方持久化
		TeacherEntity t=new TeacherEntity();
		t.setTeacherId("4028907d67c51ed30167c51ed9f20000");
		//对的一方持久化
	    StudentEntity s=new StudentEntity();
	    s.setStuName("刘备");
	    s.setTeacher(t);
	    StudentEntity s1=new StudentEntity();
	    s1.setStuName("张飞");
	    s1.setTeacher(t);
	    
	    session.save(s);
	    session.save(s1);
	}
	@Test
	public void sava2(){
		//一的一方持久化
		TeacherEntity t=session.load(TeacherEntity.class, "4028907d67c51ed30167c51ed9f20000");

		//对的一方持久化
	    StudentEntity s=new StudentEntity();
	    s.setStuName("马超");
	    s.setTeacher(t);
	    StudentEntity s1=new StudentEntity();
	    s1.setStuName("吕布");
	    s1.setTeacher(t);
	    
	    session.save(s);
	    session.save(s1);
	}
	//查询多的一方
	@Test
	public void findMany(){
		StudentEntity stu=session.load(StudentEntity.class,"4028907d67c51ed30167c51eda1e0001");
	//stu.getStuName();
		System.out.println("学生的名字为："+stu.getStuName()+"/教师的名字："+stu.getTeacher().getTeacherName());
	}
	//查询一的一方
	@Test
	public void findOne(){
		TeacherEntity t=session.get(TeacherEntity.class,"4028907d67c51ed30167c51ed9f20000");
	  for(StudentEntity stu:t.getStuSet()){
		  System.out.println("学生="+stu.getStuName()+"、教师="+t.getTeacherName());
	  }
	}
	@Test
	public void delAll(){
		session.delete(session.load(TeacherEntity.class, "4028907d67c51ed30167c51ed9f20000"));
	}
	
	
	@After
	public void after(){
		tr.commit();
		session.close();
	}
}
