package com.web.hotswap;

import java.lang.annotation.Annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.web.utils.SpringContextHolder;

public class TestClass {
	public static void main(String[] args) {
		System.out.println("hello world!!!");
//		System.out.println("--------------------------------------");
//		ApplicationContext ctx = SpringContextHolder.getApplicationContext();
//		System.out.println("ctx: " + ctx);
//		for (String str : ctx.getBeanDefinitionNames()) {
//			Class clz = ctx.getBean(str).getClass();
//			Annotation[] annotation = clz.getAnnotationsByType(Controller.class);
//			if (annotation != null) {
//				System.out.println("【" + str + "】：" + clz);
//			}
//		}
//		System.out.println("--------------------------------------");
		ClassLoader cl = TestClass.class.getClassLoader();
		System.out.println("self: " + cl);
		while (cl.getParent() != null) {
			System.out.println(cl.getParent().getClass());
			cl = cl.getParent();
		}
	}
}
