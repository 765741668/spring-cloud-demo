/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hooray.common.utils;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * @author calvin
 * @version 2013-01-15
 */
public class Identities {

	private static SecureRandom random = new SecureRandom();

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid2() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 封装JDK自带的UUID, 根据给定字符串生成, 中间无-分割.
	 */
	public static String uuid3(String str){
	    return UUID.nameUUIDFromBytes(str.getBytes()).toString().replaceAll("-", "");
	}
	
	/**
	 * 封装JDK自带的UUID, 生成十六位唯一随机数字
	 */
	public static String hexUuid() {
		int first = new Random(10).nextInt(8) + 1;
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {
			hashCodeV = -hashCodeV;
		}
		return first + String.format("%015d", hashCodeV);
	}
	
	/**
	 * 使用SecureRandom随机生成Long. 
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}
	
	/**
	 * 生成4位随机验证码
	 */
	public static String randomString() {
		String str = "abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer sb = new StringBuffer(4);
		for (int i = 0; i < 4; i++) {
			char ch = str.charAt(new Random().nextInt(str.length()));
			sb.append(ch);
		}
		return sb.toString();
	}
	
}
