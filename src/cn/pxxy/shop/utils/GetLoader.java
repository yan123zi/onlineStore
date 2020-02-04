package cn.pxxy.shop.utils;

import java.io.InputStream;

public class GetLoader {
	public InputStream getLoader(String propName) {
		return this.getClass().getClassLoader().getResourceAsStream(propName);
	}
}
