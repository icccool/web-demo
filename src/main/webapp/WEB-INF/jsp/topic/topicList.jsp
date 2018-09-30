<%--
  Created by IntelliJ IDEA.
  User: tongjizong
  Date: 2018/8/31
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>


<head>
    <meta charset="utf-8">
    <title>百度云群组发布</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" href="../../res/layui/css/layui.css">
    <link rel="stylesheet" href="../../res/css/global.css">
    <script src="../../res/layui/layui.js"></script>
</head>

<body>

<%--<img title="看不清，换一张" id="imgCode" src="/getPatchcaImage" class="captcha-img">--%>


<div class="header">

    <div class="main">
        <a class="title" href="index.html" target="_parent" title="百度云盘群组">
            <i class="iconfont icon-jiaoliu layui-hide-xs" style="font-size: 22px;"></i>
            百度云盘群组</a>
        <!--
        <div class="nav">
            <a class="nav-this" href="index.html">
                <i class="iconfont icon-wenda"></i>你问我答</a>
        </div>
        --->
        <div class="nav-user">
            <a   class="iconfont icon-touxiang layui-hide-xs" style="margin-top: 4px; display: inline-block;">
            </a>
            <div class="nav"  style="font-size:14px;color: white;margin-top: -5px;margin-left: 1px; "  />
            <a href="login.html"  target="_parent" >登录</a>
            <a href="register.html" target="_parent" >注册</a>
        </div>
    </div>
</div>

<div class="main layui-clear">
        <div class="wrap">
            <div class="content" style="margin-right:0">
                <!--发布问题-->
                <div class="fly-tab">
                    <!--
                        <span>
                            <a href="" class="tab-this">全部</a>
                            <a href="">未结帖</a>
                            <a href="">已采纳</a>
                            <a href="">置顶帖</a>
                            <a href="../user/index.html">我的帖</a>
                        </span>
                    -->

                    <form action="" class="fly-search">
                            <i class="iconfont icon-sousuo"></i>
                            <input class="layui-input" autocomplete="off" placeholder="搜索内容" type="text" name="q">
                    </form>
                    <a href="add.html" class="layui-btn jie-add">发布问题</a>
                </div>


                <!--帖子列表-->
                <ul class="fly-list">
                    <li class="fly-list-li">
                        <a href="../../user/home.html" class="fly-list-avatar">
                            <img src="../../res/images/uer.jpg" alt="">
                        </a>
                        <h2 class="fly-tip">
                            <a href="detail.html">基于Layui的轻量级问答社区页面模版</a>
                            <span class="fly-tip-stick">置顶</span>
                        </h2>
                        <p>
                            <span><a href="">贤心</a></span>
                            <span>刚刚</span>
                            <span class="fly-list-hint"><i class="iconfont" title="回答">&#xe60c;</i> 317</span>
                        </p>
                    </li>
                </ul>


                <!--帖子列表-->
                <ul class="fly-list">
                    <li class="fly-list-li">
                        <a href="../../user/home.html" class="fly-list-avatar">
                            <img src="../../res/images/uer.jpg" alt="">
                        </a>
                        <h2 class="fly-tip">
                            <a href="detail.html">基于Layui的轻量级问答社区页面模版</a>
                            <span class="fly-tip-stick">置顶</span>
                        </h2>
                        <p>
                            <span><a href="">贤心</a></span>
                            <span>刚刚</span>
                            <span class="fly-list-hint"><i class="iconfont" title="回答">&#xe60c;</i> 317</span>
                        </p>
                    </li>
                </ul>

                <!--帖子列表-->
                <ul class="fly-list">
                    <li class="fly-list-li">
                        <a href="../../user/home.html" class="fly-list-avatar">
                            <img src="../../res/images/uer.jpg" alt="">
                        </a>
                        <h2 class="fly-tip">
                            <a href="detail.html">基于Layui的轻量级问答社区页面模版</a>
                            <span class="fly-tip-stick">置顶</span>
                        </h2>
                        <p>
                            <span><a href="">贤心</a></span>
                            <span>刚刚</span>
                            <span class="fly-list-hint"><i class="iconfont" title="回答">&#xe60c;</i> 317</span>
                        </p>
                    </li>
                </ul>

                <!--帖子列表-->
                <ul class="fly-list">
                    <li class="fly-list-li">
                        <a href="../../user/home.html" class="fly-list-avatar">
                            <img src="../../res/images/uer.jpg" alt="">
                        </a>
                        <h2 class="fly-tip">
                            <a href="detail.html">基于Layui的轻量级问答社区页面模版</a>
                            <span class="fly-tip-stick">置顶</span>
                        </h2>
                        <p>
                            <span><a href="">贤心</a></span>
                            <span>刚刚</span>
                            <span class="fly-list-hint"><i class="iconfont" title="回答">&#xe60c;</i> 317</span>
                        </p>
                    </li>
                </ul>

                <!--帖子列表-->
                <ul class="fly-list">
                    <li class="fly-list-li">
                        <a href="../../user/home.html" class="fly-list-avatar">
                            <img src="../../res/images/uer.jpg" alt="">
                        </a>
                        <h2 class="fly-tip">
                            <a href="detail.html">基于Layui的轻量级问答社区页面模版</a>
                            <span class="fly-tip-stick">置顶</span>
                        </h2>
                        <p>
                            <span><a href="">贤心</a></span>
                            <span>刚刚</span>
                            <span class="fly-list-hint"><i class="iconfont" title="回答">&#xe60c;</i> 317</span>
                        </p>
                    </li>
                </ul>

                <!--帖子列表-->
                <ul class="fly-list">
                    <li class="fly-list-li">
                        <a href="../../user/home.html" class="fly-list-avatar">
                            <img src="../../res/images/uer.jpg" alt="">
                        </a>
                        <h2 class="fly-tip">
                            <a href="detail.html">基于Layui的轻量级问答社区页面模版</a>
                            <span class="fly-tip-stick">置顶</span>
                        </h2>
                        <p>
                            <span><a href="">贤心</a></span>
                            <span>刚刚</span>
                            <span class="fly-list-hint"><i class="iconfont" title="回答">&#xe60c;</i> 317</span>
                        </p>
                    </li>
                </ul>

                <!--帖子列表-->
                <ul class="fly-list">
                    <li class="fly-list-li">
                        <a href="../../user/home.html" class="fly-list-avatar">
                            <img src="../../res/images/uer.jpg" alt="">
                        </a>
                        <h2 class="fly-tip">
                            <a href="detail.html">基于Layui的轻量级问答社区页面模版</a>
                            <span class="fly-tip-stick">置顶</span>
                        </h2>
                        <p>
                            <span><a href="">贤心</a></span>
                            <span>刚刚</span>
                            <span class="fly-list-hint"><i class="iconfont" title="回答">&#xe60c;</i> 317</span>
                        </p>
                    </li>
                </ul>


                <!--帖子列表-->
                <ul class="fly-list">
                    <li class="fly-list-li">
                        <a href="../../user/home.html" class="fly-list-avatar">
                            <img src="../../res/images/uer.jpg" alt="">
                        </a>
                        <h2 class="fly-tip">
                            <a href="detail.html">基于Layui的轻量级问答社区页面模版</a>
                            <span class="fly-tip-stick">置顶</span>
                        </h2>
                        <p>
                            <span><a href="">贤心</a></span>
                            <span>刚刚</span>
                            <span class="fly-list-hint"><i class="iconfont" title="回答">&#xe60c;</i> 317</span>
                        </p>
                    </li>
                </ul>


                <!--帖子列表-->
                <ul class="fly-list">
                    <li class="fly-list-li">
                        <a href="../../user/home.html" class="fly-list-avatar">
                            <img src="../../res/images/uer.jpg" alt="">
                        </a>
                        <h2 class="fly-tip">
                            <a href="detail.html">基于Layui的轻量级问答社区页面模版</a>
                            <span class="fly-tip-stick">置顶</span>
                        </h2>
                        <p>
                            <span><a href="">贤心</a></span>
                            <span>刚刚</span>
                            <span class="fly-list-hint"><i class="iconfont" title="回答">&#xe60c;</i> 317</span>
                        </p>
                    </li>
                </ul>


                <!--帖子列表-->
                <ul class="fly-list">
                    <li class="fly-list-li">
                        <a href="../../user/home.html" class="fly-list-avatar">
                            <img src="../../res/images/uer.jpg" alt="">
                        </a>
                        <h2 class="fly-tip">
                            <a href="detail.html">基于Layui的轻量级问答社区页面模版</a>
                            <span class="fly-tip-stick">置顶</span>
                        </h2>
                        <p>
                            <span><a href="">贤心</a></span>
                            <span>刚刚</span>
                            <span class="fly-list-hint"><i class="iconfont" title="回答">&#xe60c;</i> 317</span>
                        </p>
                    </li>
                </ul>


                <!--帖子列表-->
                <ul class="fly-list">
                    <li class="fly-list-li">
                        <a href="../../user/home.html" class="fly-list-avatar">
                            <img src="../../res/images/uer.jpg" alt="">
                        </a>
                        <h2 class="fly-tip">
                            <a href="detail.html">基于Layui的轻量级问答社区页面模版</a>
                            <span class="fly-tip-stick">置顶</span>
                        </h2>
                        <p>
                            <span><a href="">贤心</a></span>
                            <span>刚刚</span>
                            <span class="fly-list-hint"><i class="iconfont" title="回答">&#xe60c;</i> 317</span>
                        </p>
                    </li>
                </ul>


                <!--帖子列表-->
                <ul class="fly-list">
                    <li class="fly-list-li">
                        <a href="../../user/home.html" class="fly-list-avatar">
                            <img src="../../res/images/uer.jpg" alt="">
                        </a>
                        <h2 class="fly-tip">
                            <a href="detail.html">基于Layui的轻量级问答社区页面模版</a>
                            <span class="fly-tip-stick">置顶</span>
                        </h2>
                        <p>
                            <span><a href="">贤心</a></span>
                            <span>刚刚</span>
                            <span class="fly-list-hint"><i class="iconfont" title="回答">&#xe60c;</i> 317</span>
                        </p>
                    </li>
                </ul>


                <!--帖子列表-->
                <ul class="fly-list">
                    <li class="fly-list-li">
                        <a href="../../user/home.html" class="fly-list-avatar">
                            <img src="../../res/images/uer.jpg" alt="">
                        </a>
                        <h2 class="fly-tip">
                            <a href="detail.html">基于Layui的轻量级问答社区页面模版</a>
                            <span class="fly-tip-stick">置顶</span>
                        </h2>
                        <p>
                            <span><a href="">贤心</a></span>
                            <span>刚刚</span>
                            <span class="fly-list-hint"><i class="iconfont" title="回答">&#xe60c;</i> 317</span>
                        </p>
                    </li>
                </ul>


                <!-- <div class="fly-none">并无相关数据</div> -->
                <div style="text-align: center">
                    <div class="laypage-main"><span class="laypage-curr">1</span>
                        <a href="">2</a>
                        <a href="">3</a>
                        <a href="">4</a>
                        <a href="">5</a><span>…</span>
                        <a href="" class="laypage-last" title="尾页">尾页</a>
                        <a href="" class="laypage-next">下一页</a>
                    </div>
                </div>

            </div>
        </div>
    </div>









</body>
</html>
