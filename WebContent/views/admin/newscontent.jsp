<%@ page language="java" contentType="text/html;charset=gb2312"
    pageEncoding="UTF-8"%>
<%@page import="com.website.enty.News,com.google.gson.JsonObject,com.website.service.IndexService,org.apache.poi.xwpf.usermodel.XWPFDocument,org.apache.poi.xwpf.usermodel.XWPFParagraph,org.apache.poi.hwpf.extractor.WordExtractor,org.apache.poi.hwpf.HWPFDocument,com.website.dao.implement.ArticleDisplay,com.website.dao.Test2,com.website.service.ArticleService,com.website.enty.Article,java.util.*,java.io.*"%>
<!DOCTYPE html>
<% 	
	String new_id = request.getParameter("news");
	//String new_id = "2";
	//System.out.println(new_id);
	IndexService indexService = new IndexService();
	News jsonObject = new News();
	jsonObject = indexService.getNewsContent(Integer.parseInt(new_id));
	//System.out.println(jsonObject.toString());
	session.setAttribute("all_id", jsonObject.getNew_id());
	session.setAttribute("comment_type", 2);
%>
<html>

	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title><%=jsonObject.getNew_name() %></title>
		<link rel="stylesheet" type="text/css" href="../../public/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="../../public/css/wangEditor.min.css"/>
		<style type="text/css">
			.zan-end{
				color: #379BE9 !important;
				cursor: text !important;
			}
			.border-red{
				border-bottom: 4px solid #ff2222;
			}
			@font-face {
			  font-family: 'typicons';
			  src: url("../../public/font/typicons.ttf");
			}
			.panel-heading>h3,.panel-heading>p{
				text-align: center;
			}
			.panel-heading>p>span{
				margin: 0px 20px;
			}
			.zan>i{
				color: #888888;
			}
			.zan>i:hover{
				color: #379BE9;
				cursor: pointer;
			}
			.zan>i:before{
				font-family: 'typicons';
				font-weight: 500;
				font-size: 20px;
				content: '\e11c';
			}
			.comment-type>a{
				text-decoration: none;
				margin-right: 10px;
			}
			.comment-type>a:hover{
				border-bottom: 4px solid #ff2222;
			}
			.comment>h5{
				color: #379BE9;
			}
			.comment>h5>span{
				color: black;
				margin-left: 15px;
			}
			.zan>.zan-num{
				padding: 0px 3px;
			}
			.zan>.zan-num:before{
				content: '(';
			}
			.zan>.zan-num:after{
				content: ')';
			}
			.more{
				border-radius: 8px;
				text-align: center;
				background-color: #cccccc;
				height: 30px;
				line-height: 30px;
			}
			.more:hover{
				background-color: #5bc0de;
				cursor: pointer;
			}
		</style>
		<script src="../../public/js/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../public/js/angular.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../public/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>	
		<!--富文本编辑器-->
		<script src="../../public/js/wangEditor.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../public/js/plugs.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../public/js/content.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function(){
				//生成编辑器
				var editor = $("#text-main").Editor(180,null,'user');
				
				//发表评论
				$("form").submit(function(){
					
					var $html = editor.$txt.html();
					var $text = editor.$txt.text();
					//检测非空
					var s = /^\s+$/;
					var img = /img/;
					if(editor.$txt.text()==""&&!img.test($html)){
						alert("评论不能为空");
						return false;
					}
					if(!s.test(editor.$txt.text())){
						$._ajax({
							url : "",
							data :{
								comment_content: $html
							}
						}).done(function(){
							editor.$txt.text("");
							newest.click();
							showmore();
						})
					}else{
						alert("评论不能为空");
					}
					return false;
				})
				
			});
		</script>
	</head>

	<body data-ng-app="">
		<div class="container">
			<div class="panel">
				<div class="panel-heading">
					<h3><%=jsonObject.getNew_name() %></h3>
					<p><span>{{<%=jsonObject.getNew_time()%> | date:"yyyy-MM-dd HH:mm:ss"}}</span></p>
				</div>
				<div class="panel-body">
					<div class="content-main">
					<%=jsonObject.getNew_content() %>
						
					</div>
				</div>
				<div class="panel-body">
					<form novalidate="" role="form" class="form-horizontal panel-body">
						<div class="form-group">
							<div class="" id="text-main">
								<!--wangEditor-->
			                   	
								<!--wangEditor end-->
							</div>
						</div>
						<div class="form-group">
							<div class="">
								<button class="btn btn-info" type="submit" id="submit" >发表评论</button>
							</div>
						</div>
					</form>
				</div>
				<div class="panel-body">
					<h4 class="panel-title comment-type"><a href="javascript:;">最新评论</a><a href="javascript:;">热门评论</a></h4>
					<div class="panel-body comment-all">
<!-- 						<div class="comment">
							<hr />
							<h5>名字<span>2017/05/16 13:16</span></h5>
							<div>
								评论内容哈哈哈哈哈哈哈哈
							</div>
							<div class="zan">
								<i></i>
								<span class="zan-num">14</span>
							</div>
						</div> -->

					</div>
					<div class="more">
						<p>查看更多</p>
					</div>
				</div>
			</div>
			
		</div>
	</body>
	<script>
	$(function(){
		var textarea = $("textarea").eq(0).get(0);
		textarea.rows = textarea.value.match(/\n/g).length;
		$(textarea).css('overflow-y','hidden');
		
		
	})
	</script>   

</html>