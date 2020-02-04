package cn.pxxy.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.pxxy.shop.adminuser.entity.AdminUser;

public class AdminLimitInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		// TODO Auto-generated method stub
		AdminUser adminUser=(AdminUser) ServletActionContext
				.getRequest().getSession().getAttribute("admin");
		if(adminUser==null) {
			ActionSupport actionSupport=(ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("亲！还没有登录！没有访问权限！");
			return "loginFail";
		}else {
			return actionInvocation.invoke();
		}
	}

}
