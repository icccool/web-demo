<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.web.hotswap.*" %>
<%
	/* 	public class TestClass {
	    public static void main(String[] args) {
	        System.out.println("hello world!!!");
	        ClassLoader cl = TestClass.class.getClassLoader();
	        System.out.println("self: " + cl);
	        while (cl.getParent() != null) {
	            System.out.println(cl.getParent().getClass());
	            cl = cl.getParent();
	        }
	    }
	 }*/

    InputStream is = new FileInputStream("c:\\TestClass.class");
    byte[] b = new byte[is.available()];
    is.read(b);
    is.close();
    
    out.println("<textarea style='width:1200px; height:500px'>");
    out.println(JavaClassExecuter.execute(b));
    out.println("</textarea>"); 
   
%>