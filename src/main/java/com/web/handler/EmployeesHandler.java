package com.web.handler;

import com.github.pagehelper.Page;
import com.web.annotation.EmpAnnotation;
import com.web.model.Employees;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * 处理 Controller 方法返回值
 *
 * Created by john on 18/4/11.
 */
public class EmployeesHandler implements HandlerMethodReturnValueHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        Class<?> type = returnType.getParameterType();
        if(type.isAssignableFrom(Page.class)){
            return true;
        }
        return false;
    }

    @Override
    public void handleReturnValue(Object returnValue,
                                  MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {

        //标识请求是否已经在该方法内完成处理
        mavContainer.setRequestHandled(true);
        // 获取方法上的注解
        EmpAnnotation annotation = returnType.getMethodAnnotation(EmpAnnotation.class);

        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        //response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" \">");
        List ls = (List) returnValue;
        for (int i = 0; i < ls.size(); i++) {
            Employees emp = (Employees)ls.get(i);
            out.println("<tr>");
            out.println("<td>"+emp.getFirstName()+"</td>");
            out.println("<td>"+emp.getLastName()+"</td>");
            out.println("<td>"+emp.getGender()+"</td>");
            out.println("<td>"+emp.getBirthDate()+"</td>");
            out.println("</tr>");
        }
        out.println("</table>");

    }

}