package com.web.controller;

import com.google.code.kaptcha.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;


/***
 * 验证码
 *
 */
@Controller
public class CaptchaController {


    protected void setResponseHeaders(HttpServletResponse response) {
        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        response.setDateHeader("Last-Modified", time);
        response.setDateHeader("Date", time);
        response.setDateHeader("Expires", time);
    }

    @RequestMapping(value = "/getPatchcaImage")
    public void getPatchcaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession();
        }
        setResponseHeaders(response);
        String token = null;
        session.setAttribute("captchaToken", token);
        System.out.println("当前的 sessionId:" + session.getId() + ",验证码：" + token);
    }


    @RequestMapping(value = "/checkCode")
    public void checkCode(HttpServletRequest res, HttpServletResponse resp, PrintWriter out) {

        boolean flag = false;
        String authcode = res.getParameter("code");
        String code = (String) res.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);        //获取生成的验证码
        System.out.println(authcode + "," + code);
        if ((code.toUpperCase()).equals(authcode.toUpperCase())) {
            flag = true;
        } else {
            flag = false;
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain;charset=utf-8");
        //向叶面输出数据
        try {
            out.write(String.valueOf(flag));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @RequestMapping(value = "/validateCode")
    public void validateCode(HttpServletRequest res, HttpServletResponse resp, PrintWriter out) {
        boolean flag = false;
        String authcode = res.getParameter("code");
        String code = (String) res.getSession().getAttribute("captchaToken");        //获取生成的验证码
        System.out.println(authcode + "," + code);
        if ((code.toUpperCase()).equals(authcode.toUpperCase())) {
            flag = true;
        } else {
            flag = false;
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain;charset=utf-8");
        //向叶面输出数据
        try {
            out.write(String.valueOf(flag));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}