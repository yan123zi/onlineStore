package cn.pxxy.shop.utils;

import java.util.UUID;

/**
 * 生成随机字符串的工具类
 * @author 严子江
 *
 */
public class UUIDUtils {
	/**
	 * 获得随机字符串的方法
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	public static void main(String[] args) {
		String a="9007_1546859682410";
		System.out.println();
		System.out.println();
	}
}
