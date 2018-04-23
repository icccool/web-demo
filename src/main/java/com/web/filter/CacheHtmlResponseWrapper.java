package com.web.filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.PrintWriter;


/**
 * 获得经过渲染的html
 */
public class CacheHtmlResponseWrapper extends HttpServletResponseWrapper {

    private PrintWriter cachedWriter;
    private CharArrayWriter bufferedWriter;

    public CacheHtmlResponseWrapper(HttpServletResponse response) {
        super(response);
        // 保存返回结果
        bufferedWriter = new CharArrayWriter();
        cachedWriter = new PrintWriter(bufferedWriter);
    }

    @Override
    public PrintWriter getWriter() {
        return cachedWriter;
    }

    public String getResult() {
        return bufferedWriter.toString();
    }
}
