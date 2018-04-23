package com.web.filter;

import com.web.utils.SpringContextHolder;
import com.web.utils.redis.RedisUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 缓存web页面<br>
 * 拦截链接带参数cacheId,以cacheId为key缓存页面<br>
 * 如果cacheId为空清,空缓存<br>
 */
public class CacheWebPageFilter implements Filter {

    private static RedisUtils redisUtils = SpringContextHolder.getBean(RedisUtils.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        try {
            String cacheId = req.getParameter("cacheId");
            if(StringUtils.isEmpty(cacheId)){
                chain.doFilter(req, resp);
            }else{
                CacheHtmlResponseWrapper wrapper = new CacheHtmlResponseWrapper(resp);
                String cacheHtml = redisUtils.getString(cacheId);
                if(!StringUtils.isEmpty(cacheHtml)) {
                    cacheHtml = cacheHtml + "<br><font size=\"10\" color=\"red\">This page is cached in redis!</font>\n";
                }else {
                    //渲染页面并缓存
                    chain.doFilter(req, wrapper);
                    cacheHtml = wrapper.getResult();
                    redisUtils.setex(cacheId, cacheHtml, 60);
                }
                // 返回响应
                resp.setContentType("text/html; charset=utf-8");
                resp.getWriter().print(cacheHtml);
            }
        }catch (Exception e){
            e.printStackTrace();
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
