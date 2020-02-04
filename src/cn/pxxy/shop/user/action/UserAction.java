package cn.pxxy.shop.user.action;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.pxxy.shop.user.entity.User;
import cn.pxxy.shop.user.service.UserService;
/**
 * 用户模块的action类
 * @author 严子江
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
	//模型驱动用的对象
	private User user=new User();
	//注入userservice
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private String checkCode;
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	/**
	 * 跳转到注册页面的执行方法
	 * @return
	 */
	public String registPage() {
		// TODO Auto-generated method stub
		return "registPage";
	}
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	/**
	 * AJAX进行异步校验用户名的执行方法
	 * @return
	 * @throws IOException 
	 */
	public String findByName() throws IOException {
		// 调用Service进行查询:
		User existUser = userService.findByUserName(user.getUsername());
		// 获得response对象,向页面输出:
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// 判断
		if (existUser != null) {
			// 查询到该用户:用户名已经存在
			response.getWriter().println("<font color='red'>用户名已经存在!</font>");
		} else {
			// 没查询到该用户:用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用!</font>");
		}
		return NONE;	
	}
	/**
	 * 用户注册的方法
	 * @throws IOException 
	 * @throws MessagingException 
	 */
	public String regist() {
		//判断验证码是否正确
		String checkcode=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		System.out.println(checkCode);
		if(checkCode.equalsIgnoreCase(checkcode)) {
			try {
				userService.save(user);
				this.addActionMessage("注册成功！请去邮箱激活！");
				return "msg";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				this.addActionMessage("邮箱错误或不存在!");
				return "registPage";
			}
		}else {
			this.addActionError("验证码不正确无法注册！");
			return "registPage";
		}
	}
	/**
	 * 用户激活的方法
	 */
	public String active() {
		//根据激活码查询用户
		User existUser=userService.findByCode(user.getCode());
		if(existUser==null) {
			//激活码错误
			this.addActionMessage("激活失败：激活码错误！");
		}else {
			//激活成功
			//修改用户状态
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("激活成功！请去登录页面登录！");
		}
		return "msg";
	}
	/**
	 * 跳转到登录页面
	 */
	public String loginPage() {
		return "loginPage";
	}
	/**
	 * 用户登录方法
	 * @return
	 */
	public String login() {
		User existUser=userService.login(user);
		if(existUser==null) {
			//登录失败
			this.addActionError("登录失败：用户名或密码错误或者用户未激活");
			return LOGIN;
		}else {
			//登录成功
			//将用户的信息存入到session中
			ServletActionContext.getRequest().getSession().setAttribute("user", existUser);
			return "loginSuccess";
		}
	}	
	/**
	 * 用户退出方法
	 * @return
	 */
	public String quit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
