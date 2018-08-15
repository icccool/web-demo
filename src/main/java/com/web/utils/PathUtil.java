package com.web.utils;

import java.io.File;

/**
 * Created by WANG on 2018/7/16.
 */
public class PathUtil {


    public static String getWebRootPath() {
        String classPath = PathUtil.class.getClassLoader().getResource("/").getPath();
        String rootPath = "";
        //windows下
        if("\\".equals(File.separator)){
            rootPath = classPath.substring(1,classPath.indexOf("/WEB-INF"));
            rootPath = rootPath.replace("%20", " ");
        }
        //linux下
        if("/".equals(File.separator)){
            rootPath = classPath.substring(0,classPath.indexOf("/WEB-INF"));
        }
        return rootPath;
    }

}
