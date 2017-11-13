package com.hooray.test;

import com.hooray.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

public class SomeTest {

	@Before
	public void init(){
	}

	@Test
	public void testBeanCopy() {
		User u1 = new User();
		u1.setId(1L);
		u1.setAge(11);
		u1.setName("u1");

		User u2 = new User();
		u2.setId(2L);
		u2.setAge(22);
		u2.setName("u2");
//		u2.setInterests(Arrays.asList("addr1","addr2"));
		BeanUtils.copyProperties(u1,u2,getNullPropertyNames(u1));
		System.out.println("u1: " + u1);
		System.out.println("u2: " + u2);

		BeanUtils.copyProperties(u1,u2);
		System.out.println("u1: " + u1);
		System.out.println("u2: " + u2);
	}

	private String[] getNullPropertyNames (Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<>();
		for(java.beans.PropertyDescriptor pd : pds) {
			//check if value of this property is null then add it to the collection
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

}
