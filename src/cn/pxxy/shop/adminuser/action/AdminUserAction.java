package cn.pxxy.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.pxxy.shop.adminuser.entity.AdminUser;
import cn.pxxy.shop.adminuser.service.AdminUserService;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
	private AdminUser adminUser=new AdminUser();
	private AdminUserService adminUserService=new AdminUserService();
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	@Override
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}
	//管理员登录的方法
	public String login() {
		AdminUser existAdmin=adminUserService.login(adminUser);
		if(existAdmin==null) {
			this.addActionError("亲!您的用户名或者密码错误!");
			return "loginFail";
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("admin", existAdmin);
			return "loginSuccess";
		}
		
	}
}
