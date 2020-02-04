package Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import cn.pxxy.shop.utils.GetLoader;

public class prop {
	@Test
	public void fun() {
		Properties prop=new Properties();
		prop.put("12", 189);
		prop.setProperty("68", "25252");
		System.out.println(prop);
	}
	@Test
	public void fun1() throws IOException {
		Properties prop=new Properties();
		InputStream input=new GetLoader().getLoader("MailFrom.properties");
		prop.load(input);
		System.out.println(prop.getProperty("Authorization_Code"));
	}
}
