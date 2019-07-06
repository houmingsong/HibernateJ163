package test;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import com.lovo.hibernate.db.HibernateSession;
import com.lovo.hibernate.entity.UserEntity;

public class THibernate {
	Session session=	null;
	@Before
	public void before(){
		session=	HibernateSession.getSession();
	}
	@Test
	public void testSession(){
		
	System.out.println(session);
	}
	public static void main(String[] args) {
		Session session=	null;
		try{
		session=	HibernateSession.getSession();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void sava(){
		for(int i=0;i<10;i++){
		UserEntity user=new UserEntity();
		user.setUserName("赵云"+i);
		user.setAge(30+i);
		//获取事务
		Transaction tr=	session.getTransaction();
		tr.begin();//开启事务
		//保存
		 session.save(user);
		tr.commit();//提交事务
		}
		session.close();//关闭session
	}
	//get 没有获取对象的值SQL出来 load,SQL语句没出来
	//load 在获取对象值的时候SQL出现
	//关闭连接后获取对象值，load会报no session 异常
	@Test
	public void getUser(){
		UserEntity user=	session.get(UserEntity.class,"4028907d67c420b80167c420be310000");
		System.out.println(user.getUserName());
	     
	       session.clear();
		   user=	session.get(UserEntity.class,"4028907d67c420b80167c420be310000");
	      
		session.close();//关闭session
		System.out.println(user.getUserName());
	}
	@Test
	public void loadUser(){
		UserEntity user=	session.load(UserEntity.class,"4028907d67c05e3b0167c05e40380000");
		
		session.close();//关闭session

		
	}
	@Test
	public void updateUser(){
		UserEntity user=	session.load(UserEntity.class,"4028907d67c05e3b0167c05e40380000");
		//获取事务
	Transaction tr=	session.getTransaction();
	tr.begin();//开启事务			
		user.setUserName("关羽");
		session.update(user);//修改
    tr.commit();
    session.close();
		
	}
	@Test
	public void updateUser1(){
		UserEntity user=	session.get(UserEntity.class,"4028907d67c42f540167c42f76d30000");
		//清空一级缓存
		session.clear();
		//获取事务
	Transaction tr=	session.getTransaction();
	tr.begin();//开启事务			
		user.setUserName("刘备2");
		//session.update(user);//修改
    tr.commit();
    session.close();
	}
	
	@Test
	public void updateUser2(){
		UserEntity user=	session.get(UserEntity.class,"4028907d67c05e3b0167c05e40380000");
		//获取事务
	Transaction tr=	session.getTransaction();
	tr.begin();//开启事务					
		//session.update(user);//修改
    tr.commit();
    session.close();
    user.setUserName("关羽4");
	}
	@Test
	public void updateUser3(){
		UserEntity user=	new UserEntity();
		//获取事务
	Transaction tr=	session.getTransaction();
	tr.begin();//开启事务	
	user.setUserId("4028907d67c42d0b80167c420be310000");
	  user.setUserName("关羽5");
	  user.setAge(30);
	   session.update(user);//修改
    tr.commit();
    session.close();
  
	}
	@Test
	public void delUser(){
		Transaction tr=	session.getTransaction();
		tr.begin();//开启事务					
		session.delete(session.get(UserEntity.class, "4028907d67c05e3b0167c05e40380000"));
	    tr.commit();
	    session.close();
	}
	@Test
	public void delUser2(){
		Transaction tr=	session.getTransaction();
		tr.begin();//开启事务		
		UserEntity user=new UserEntity();
		user.setUserId("4028907d67c074d70167c074dc070000");
		session.delete(user);
	    tr.commit();
	    session.close();
	}
	
	
}
