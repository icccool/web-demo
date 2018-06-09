package com.web.utils;

import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesHolder {

	private static PropertiesConfiguration properties = SpringContextHolder.getBean("propertyConfigurer");
	
	
	public static String getProperties(String key) {
		return properties.getString(key);
	}
	
}
