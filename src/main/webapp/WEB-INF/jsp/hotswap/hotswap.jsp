<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.web.hotswap.*" %>
<%@ page import="com.web.utils.PathUtil" %>
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
    String cls = PathUtil.getWebRootPath() + "/WEB-INF/classes/com/web/hotswap/TestClass.class";
    InputStream is = new FileInputStream(cls);
    byte[] b = new byte[is.available()];
    is.read(b);
    is.close();

    out.println("calss = " + cls);
    out.println("<textarea style='width:1200px; height:500px'>");
    out.println(JavaClassExecuter.execute(b));
    out.println("</textarea>"); 
%>