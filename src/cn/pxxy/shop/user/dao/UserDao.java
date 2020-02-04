package cn.pxxy.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.pxxy.shop.user.entity.User;

/**
 * 用户模块持久化层代码
 * @author 严子江
 *
 */
public class UserDao extends HibernateDaoSupport{
	//按照名称查询是否有该用户
	public User findByUserName(String username) {
		String hql="from User where username=?";
		List<User> list=(List<User>) this.getHibernateTemplate().find(hql, username);
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	//注册用户存入数据库代码实现
	public void save(User user) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(user);
	}
	//根据激活码查询用户
	public User findByCode(String code) {
		// TODO Auto-generated method stub
		String hql="from User where code=?";
		List<User> list=(List<User>)this.getHibernateTemplate().find(hql, code);
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	public void update(User existUser) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(existUser);
	}
	//用户登录的方法
	public User login(User user) {
		// TODO Auto-generated method stub
		String hql="from User where username=? and password=? and state=?";
		List<User> list=(List<User>)this.getHibernateTemplate().find(hql, user.getUsername(),user.getPassword(),1);
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}
}
