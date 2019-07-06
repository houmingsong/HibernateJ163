package test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lovo.hibernate.db.HibernateSession;
import com.lovo.hibernate.entity.StudentEntity;
import com.lovo.hibernate.entity.TeacherEntity;
import com.lovo.hibernate.entity.UserDto;
import com.lovo.hibernate.entity.UserEntity;

public class THQL {
	Session session = null;
	Transaction tr = null;

	@Before
	public void before() {
		session = HibernateSession.getSession();
		tr = session.getTransaction();
		tr.begin();
	}

	@Test
	public void findAll() {
		String hql = "from  UserEntity ";
		Query query = session.createQuery(hql);
		List<UserEntity> list = query.list();
		for (UserEntity u : list) {
			System.out.println(u.getUserName());
		}
	}

	@Test
	public void find() {
		String hql = "from  UserEntity where age>?";
		Query query = session.createQuery(hql);
		query.setParameter(0, 30);
		List<UserEntity> list = query.list();
		for (UserEntity u : list) {
			System.out.println(u.getUserName());
		}
	}

	@Test
	public void findObject() {
		String hql = "from  UserEntity where userName=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, "赵云2");
		UserEntity user = (UserEntity) query.uniqueResult();
		System.out.println(user.getAge());
	}

	@Test
	public void findFiled() {
		String hql = "select userName,age from  UserEntity";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		for (Object[] obj : list) {
			System.out.println("name=" + obj[0] + "/age=" + obj[1]);
		}
	}

	@Test
	public void findAge() {
		String hql = "select sum(age) from  UserEntity";
		Query query = session.createQuery(hql);
		long l = (long) query.uniqueResult();
		System.out.println(l);
	}

	@Test
	public void joinHQL() {
		String hql = "select s from StudentEntity s left join s.teacher t";
		Query query = session.createQuery(hql);
		List<StudentEntity> list = query.list();
		for (StudentEntity st : list) {
			System.out.println(st.getStuName() + "/" + st.getTeacher().getTeacherName());

		}
	}

	@Test
	public void joinHQL1() {
		String hql = "select s,t from StudentEntity s left join s.teacher t";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		for (Object[] obj : list) {
			System.out.println(((StudentEntity) obj[0]).getStuName() + "/" + ((TeacherEntity) obj[1]).getTeacherName());
		}
	}

	@Test
	public void joinHQL2() {
		String hql = "select s.stuName,t.teacherName from StudentEntity s left join s.teacher t";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		for (Object[] obj : list) {
			System.out.println(obj[0] + "/" + obj[1]);
		}
	}

	@Test
	public void joinHQLDTO() {
		String hql = "select new com.lovo.hibernate.entity.UserDto(s.stuName,t.teacherName) from StudentEntity s left join s.teacher t";
		List<UserDto> list = session.createQuery(hql).list();
		for (UserDto u : list) {
			System.out.println(u.getTeacherName() + "/" + u.getStuName());
		}
	}

	@Test
	public void findPages() {
		int currentPage = 2;
		String hql = "from UserEntity where age>?";
		List<UserEntity> list = session.createQuery(hql).setParameter(0, 31).setFirstResult((currentPage - 1) * 3)
				.setMaxResults(3).list();
		for (UserEntity u : list) {
			System.out.println(u.getUserName());
		}
	}

	@After
	public void after() {
		tr.commit();
		session.close();
	}
}
