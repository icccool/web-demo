package com.web.controller;

import com.web.utils.VerifyCodeUtils;
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


    @RequestMapping(value = "/getPatchcaImage")
    public void getPatchcaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入会话session
        HttpSession session = request.getSession(true);
        //删除以前的
        session.removeAttribute("verifyCode");
        session.setAttribute("verifyCode", verifyCode.toLowerCase());
        //生成图片
        int w = 100, h = 30;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }


    @RequestMapping(value = "/checkCode")
    public void checkCode(HttpServletRequest res, HttpServletResponse resp, PrintWriter out) {

        boolean flag = false;
        String authcode = res.getParameter("code");
        String code = (String) res.getSession().getAttribute("KAPTCHA_SESSION_KEY");        //获取生成的验证码
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