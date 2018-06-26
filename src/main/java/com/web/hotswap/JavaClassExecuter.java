package com.web.hotswap;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * JavaClass执行工具
 *，通过反射调用测试类中的main方法，最后取出HackSystem中字节数组流中的数据进行返回
 */
public class JavaClassExecuter {

	/**
	 * 执行外部传过来的代表一个Java类的Byte数组<br>
	 * 将输入类的byte数组中代表java.lang.System的CONSTANT_Utf8_info常量修改为劫持后的HackSystem类
	 * 执行方法为该类的static main(String[] args)方法，输出结果为该类向System.out/err输出的信息
	 * 
	 * @param classByte
	 *            代表一个Java类的Byte数组
	 * @return 执行结果
	 */
	@SuppressWarnings("rawtypes")
	public static String execute(byte[] classByte) {
		HackSystem.clearBuffer();
		ClassModifier cm = new ClassModifier(classByte);
		byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", "com/web/hotswap/HackSystem");
		
		//修改后的文件保存到硬盘
		try {
			FileOutputStream out = new FileOutputStream("d:/TestClass.class");
			out.write(modiBytes, 0, modiBytes.length);
			out.flush();
			out.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		HotSwapClassLoader loader = new HotSwapClassLoader();
		Class clazz = loader.loadByte(modiBytes);
		try {
			Method method = clazz.getMethod("main", new Class[] { String[].class });
			method.invoke(null, new String[] { null });
		} catch (Throwable e) {
			e.printStackTrace(HackSystem.out); 
		}
		return HackSystem.getBufferString();
	}
}