package com.web.hotswap;

import com.web.utils.SpringContextHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.lang.annotation.Annotation;

public class TestClass {
	public static void main(String[] args) {
		System.out.println("hello world!!!");
		System.out.println("--------------------------------------<br>");
		ApplicationContext ctx = SpringContextHolder.getApplicationContext();
		System.out.println("ctx: " + ctx+"<br>");
		for (String str : ctx.getBeanDefinitionNames()) {
			Class clz = ctx.getBean(str).getClass();
			Annotation[] annotation = clz.getAnnotationsByType(Controller.class);
			if (annotation != null) {
				System.out.println("[" + str + "]==>" + clz+"<br>");
			}
		}
		System.out.println("--------------------------------------<br>");
//		ClassLoader cl = TestClass.class.getClassLoader();
//		System.out.println("self: " + cl);
//		while (cl.getParent() != null) {
//			System.out.println(cl.getParent().getClass());
//			cl = cl.getParent();
//		}
	}
}
