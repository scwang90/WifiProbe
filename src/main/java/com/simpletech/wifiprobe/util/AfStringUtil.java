package com.simpletech.wifiprobe.util;

import java.util.regex.Pattern;

/**
 * @Description: 字符串常用工具类
 * @author 树朾
 * @date 2015-11-02 17:16:40 中国标准时间
 */
public class AfStringUtil {

	public static boolean isEmpty(String string) {
		return string == null || string.trim().length() == 0;
	}
	
	public static boolean isNotEmpty(String string) {
		return string != null && string.trim().length() > 0;
	}

	public static boolean equals(String l,String r){
		if (l != r) {
			if (l != null || r != null) {
				return l.equals(r);
			}
			return false;
		}
		return true;
	}

	/**
	 * 验证输入的邮箱格式是否符合
	 * 
	 * @param email
	 * @return 是否合法
	 */
	public static boolean emailFormat(String email) {
		String pattern = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		return Pattern.compile(pattern).matcher(email).find();
	}
	
}
