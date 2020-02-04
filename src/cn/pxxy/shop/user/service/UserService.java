package cn.pxxy.shop.user.service;

import java.io.IOException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.transaction.annotation.Transactional;

import cn.pxxy.shop.user.dao.UserDao;
import cn.pxxy.shop.user.entity.User;
import cn.pxxy.shop.utils.UUIDUtils;

/**
 * 
 * 用户模块业务层代码
 * @author 严子江
 *
 */
@Transactional
public class UserService {
	//注入UserDao
	private UserDao userDao;
	//注入javaMail
	private JavaMailSender mailSender;
	
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	//按用户名查询用户的方法
	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}
	//业务层完成注册的代码
	public void save(User user) throws MessagingException  {
		//将数据存入到数据库
		user.setState(0);//0代表未激活，1代表已激活
		String code=UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//发送激活邮件
		String text="<h1>购物天堂网上商城官方激活邮件！点击下面链接完成激活操作！</h1><h3><a href='http://localhost:8080/Shop"
				+ "/user_active.action?code="+code+"'>http://localhost:8080/Shop/user_active.action?"
						+ "code="+code+"</a></h3>";
		System.out.println(mailSender);
		System.out.println(userDao);
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage, true);
		messageHelper.setTo(user.getEmail());
        messageHelper.setFrom("3468827556@qq.com");
        messageHelper.setSubject("来自网站的一封激活邮件");
        messageHelper.setText(text,true);
        mailSender.send(mimeMessage);
	}
	public User findByCode(String code) {
		// TODO Auto-generated method stub
		return userDao.findByCode(code);
	}
	public void update(User existUser) {
		// TODO Auto-generated method stub
		userDao.update(existUser);
	}
	//用户登录的方法
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}
	
}
