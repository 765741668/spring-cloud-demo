package com.hooray.common.utils;

import java.util.regex.Pattern;

/**
 * 正则 工具类
 * 
 * @author yangfeng
 * @date 2017年11月2日 下午8:21:24	
 * Email: yangfeng@hooray.cn
 */
public class PatternUtil {
	
	/**
	 * 验证手机号码 
	 * 
	 * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
	 * 联通号码段:130、131、132、136、185、186、145 
	 * 电信号码段:133、153、180、189 
	 * 
	 * @param telPhone
	 * @return
	 */
	public static boolean checktelPhone(String telPhone) {
		String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
		return Pattern.matches(regex, telPhone);
	}

	/**
	 * 验证固话号码
	 *  
	 * @param cellPhone
	 * @return
	*/
	public static boolean checkCellPhone(String cellPhone) {
		String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
		return Pattern.matches(regex, cellPhone);
	}
	
	/**
	 * 验证邮箱
	 *  
	 * @param email
	 * @return
	*/
	public static boolean checkEmail(String email) {
		String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		return Pattern.matches(regex, email);
	}
	
	/**
	 * 验证身份证
	 *  
	 * @param idCard
	 * @return
	*/
	public static boolean checkIDCard(String idCard) {
		String regex = "(^\\d{18}$)|(^\\d{15}$)";
		return Pattern.matches(regex, idCard);
	}
	
	/**
	 * 验证密码
	 *  
	 * @param password
	 * @return
	*/
	public static boolean checkPsd(String password) {
		String regex = "^[a-zA-Z0-9]{6,20}$";
		return Pattern.matches(regex, password);
	}
	
	/**
	 * 验证IP地址
	 *  
	 * @param ipAddress
	 * @return
	*/
	public static boolean checkIpAddress(String ipAddress) {
		String regex = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
		return Pattern.matches(regex, ipAddress);
	}
	
	/**
	 * 验证URL
	 *  
	 * @param url
	 * @return
	*/
	public static boolean checkUrl(String url) {
		String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
		return Pattern.matches(regex, url);
	}
	
}
