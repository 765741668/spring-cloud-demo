package com.hooray.common.utils;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
	public static Pattern pattern = Pattern.compile("\\{(\\d)\\}");
	public static final String EMPTY = "";

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

	public static String formatTemplate(String template, String[] args){
		MessageFormat format = new MessageFormat(EMPTY);
		format.applyPattern(template);
		return format.format(args);
	}

	public static String trimAll(CharSequence s) {
		if (s == null || s.length() == 0) {
			return EMPTY;
		}
		StringBuilder sb = new StringBuilder(s.length());
		for (int i = 0, l = s.length(); i < l; i++) {
			char c = s.charAt(i);
			if (c != ' ') {
				sb.append(c);
			}
		}
		return sb.toString();
	}

}
