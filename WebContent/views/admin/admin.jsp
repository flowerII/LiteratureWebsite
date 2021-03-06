<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" >
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>后台管理</title>
		<link rel="stylesheet" type="text/css" href="../../public/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="../../public/css/admin.css"/>
		
		<script src="../../public/js/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../public/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../public/js/plugs.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../public/js/admin.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<!--nav start-->
		<nav class="navbar navbar-default navbar-fixed-top">
	      <div class="container">
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false " aria-controls="navbar">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a class="navbar-brand" id="user" href="#">游客</a>
	        </div>
	        <div id="navbar" class="navbar-collapse collapse" aria-expanded="false" style="height: 1px;">
	          <ul class="nav navbar-nav">
	            <li><a href="../../public/index.html">首页</a></li>
	          </ul>
	          <ul class="nav navbar-nav navbar-right visible-xs nav-click">
	            <li><a href="#/admin/users">用户信息</a></li>
	            <li><a href="#/admin/activities">征稿活动管理</a></li>
	            <li><a href="#/admin/news">最新消息管理</a></li>
	            <li><a href="#/admin/articlesManage">稿件管理</a></li>
	            <li><a href="#/admin/addActivity">添加活动</a></li>
	            <li><a href="#/admin/addNew">添加消息</a></li>
	            <li><a href="#/admin/surePrize">奖项发布</a></li>
	          </ul>
	        </div>
	      </div>
	    </nav>
	    <!--nav end-->
	    
	    <!--body start-->
	    <div class="container box">
	    	<div class="row">
	    		<!--导航-->
	    		<div class="col-sm-3 col-xs-12 hidden-xs nav-side">
	    			<div class="panel panel-default">
	    				<div class="panel-heading">
							<p>后台管理</p>
						</div>
		    			<ul class="list-group panel-body nav-click">
				            <li class="list-group-item"><a href="#/admin/users">用户信息</a></li>
				            <li class="list-group-item"><a href="#/admin/activities">征稿活动管理</a></li>
				            <li class="list-group-item"><a href="#/admin/news">最新消息管理</a></li>
				            <li class="list-group-item"><a href="#/admin/articlesManage">稿件管理</a></li>
				            <li class="list-group-item"><a href="#/admin/addActivity">添加活动</a></li>
				            <li class="list-group-item"><a href="#/admin/addNew">添加消息</a></li>
				            <li class="list-group-item"><a href="#/admin/surePrize">奖项发布</a></li>
				        </ul>
	    			</div>
	    		</div>
	    		<!--导航 end-->
	    		<!--content-->
	    		<div class="col-sm-9 col-xs-12">
	    			<div class="panel panel-default">
	    				<div class="panel-heading content-title">
							内容
						</div>
		    			<div class="panel-body content">
		    				<iframe src="articlesManage.html" id="content-main" name="content-main" width="100%" height="0" style="border: none;"></iframe>
		    			</div>
	    			</div>
	    		</div>
	    		<!--content end-->
	    	</div>
	    </div>
	    <!--body end-->
	</body>
</html>