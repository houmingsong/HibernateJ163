package test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lovo.hibernate.db.HibernateSession;
import com.lovo.hibernate.entity.StudentEntity;
import com.lovo.hibernate.entity.TeacherEntity;
import com.lovo.hibernate.entity.UserDto;
import com.lovo.hibernate.entity.UserEntity;

public class TSQL {
	Session session=	null;
	Transaction tr=null;
	@Before
	public void before(){
		session=	HibernateSession.getSession();
	    tr=	session.getTransaction();
	   tr.begin();
	}
	@Test
	public void findAll(){
	String sql="select * from sys_user where age>?";
	List<UserEntity>list=	session.createSQLQuery(sql).addEntity(UserEntity.class).setParameter(0,31).list();
	for (UserEntity u : list) {
		System.out.println(u.getUserName());
	} 
	}
	@Test
	public void findAll2(){
	String sql="select t.*,s.* from sys_teacher t left join sys_student s "
			+ " on t.teacherId=s.t_id where t.teacherName=?";
	List<Object[]> list=
	session.createSQLQuery(sql).addEntity("t",TeacherEntity.class)
	                           .addEntity("s",StudentEntity.class)
	                           .setParameter(0,"陈老师").list();
	
	for (Object[] obj : list) {
	System.out.println(((TeacherEntity)obj[0]).getTeacherName()+
			                "/"+((StudentEntity)obj[1]).getStuName());
	}
	}
	@Test
	public void findAll3(){
	String sql="select t.teacherName,s.stuName from sys_teacher t left join sys_student s "
			+ " on t.teacherId=s.t_id where t.teacherName=?";
	List<Object[]> list=
	session.createSQLQuery(sql).setParameter(0,"陈老师").list();
	
	for (Object[] obj : list) {
	System.out.println(obj[0]+"."+obj[1]);
	}
	}
	@Test
	public void findDTO(){
	String sql="select t.teacherName,s.stuName from sys_teacher t left join sys_student s "
			+ " on t.teacherId=s.t_id where t.teacherName=?";
	List<UserDto> list=
	session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(UserDto.class))
	                           .setParameter(0,"陈老师").list();
	for (UserDto u : list) {
		System.out.println(u.getTeacherName()+"/"+u.getStuName());
	}
	
	
	}
	
	@Test
	public void updateSQL(){
		String sql="update sys_teacher set teacherName=? where teacherName=?";
	int count=	session.createSQLQuery(sql).setParameter(0, "张老师")
		                           .setParameter(1, "陈老师")
		                           .executeUpdate();
	System.out.println(count);
	}
	
	
	@After
	public void after(){
		tr.commit();
		session.close();
	}
}
