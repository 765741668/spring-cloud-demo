package com.hooray.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil extends org.apache.commons.lang3.StringUtils {
	public static Pattern pattern = Pattern.compile("\\{(\\d)\\}");

	/**
	 * 通配符的替换
	 * @param str
	 * @param arr
	 * @return
	 */
	public static String fillStringByArgs(String str, String[] arr) {
		Matcher m = pattern.matcher(str);
		while (m.find()) {
			str = str.replace(m.group(), arr[Integer.parseInt(m.group(1))]);
		}
		return str;
	}

}
