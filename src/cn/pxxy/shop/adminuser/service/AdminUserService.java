package cn.pxxy.shop.adminuser.service;

import cn.pxxy.shop.adminuser.dao.AdminUserDao;
import cn.pxxy.shop.adminuser.entity.AdminUser;

public class AdminUserService {
	private AdminUserDao adminUserDao=new AdminUserDao();

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	public AdminUser login(AdminUser adminUser) {
		// TODO Auto-generated method stub
		return adminUserDao.login(adminUser);
	}

	
}
