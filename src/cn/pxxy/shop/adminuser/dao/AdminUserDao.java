package cn.pxxy.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.pxxy.shop.adminuser.entity.AdminUser;

public class AdminUserDao extends HibernateDaoSupport{

	public AdminUser login(AdminUser adminUser) {
		// TODO Auto-generated method stub
		String hql="from AdminUser where username=? and password=?";
		List<AdminUser> list= (List<AdminUser>) this.getHibernateTemplate().find(hql, adminUser.getUsername(),adminUser.getPassword());
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}

}
