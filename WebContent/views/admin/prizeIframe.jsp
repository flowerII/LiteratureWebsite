<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="com.website.service.IndexService,com.website.service.ArticleService,com.website.enty.Article,java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String activity_id=request.getParameter("activity");
    //System.out.println(activity_id);
    ArticleService articleService=new ArticleService();
    IndexService indexService=new IndexService();
    List<Article> articles=new ArrayList<Article>();
    articles=articleService.getArticleByActivityId(Integer.parseInt(activity_id));
    int status=indexService.getActivityStatus(Integer.parseInt(activity_id));
    System.out.println("status:"+status);
    request.setAttribute("status", status);
    session.setAttribute("activity_id", activity_id);
%>
<!DOCTYPE html>
<html>
    <head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title></title>
		<link rel="stylesheet" type="text/css" href="../../public/css/bootstrap.min.css" />
		<style type="text/css">
			*{
				margin: 0 auto;
				padding: 0px;
				line-height: 30px;
			}
			html,
			body {
				height: 100%;
				min-height: 500px;
			}
			ul{
				list-style: none;
			}
			ul li{
				border-top: 1px solid #bbbbbb;
				padding: 5px 0px;
				margin: 0px 2px;
				position: relative;
				text-align: center;
			}
			ul>li:nth-last-child(1){
				border-bottom: 1px solid #bbbbbb;
			}
			.right{
				position: absolute;
				right: 15px;
				top: 4px;
			}
			button{
				vertical-align: middle;
				padding: 6px 10px !important;
			}
			.selection{
				vertical-align: middle;
				padding: 5px;
			}
		</style>
		<script src="../../public/js/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../public/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../public/js/angular.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../public/js/plugs.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function(){
				//奖项设置
				$('.prize').on('change',function(){
					var $id = $(this).parent().parent().parent().attr('id');
					if(confirm("你确定要修改奖项吗？")){
						$._ajax({
							url: "",
							data: {
								article_id : $id,
								article_prize : $(this).val()
							}
						}).done(function(back){
							if(back == "ok"){
								alert("奖项设置成功");
							}else{
								alert("奖项设置失败，请稍后再试");
							}
						});
					}
				});
				
				//移除
				$('.delete').on('click',function() {
					var $id = $(this).parent().parent().parent().attr('id');
					if(confirm("移除后，奖项为无")){
						$._ajax({
							url: "",
							data: {
								article_id : $id,
								article_prize : '0'
							}
						}).done(function(back){
							if(back == "ok"){
								alert("移除成功");
								location.reload();
							}else{
								alert("移除失败，请稍后再试");
							}
						});
					}
			
				});
				
				//发布
				$('.sure').on('click',function() {
					if(confirm("你确定发布活动奖项？")){
						$._ajax({
							url: "",
							data :{
								activity_id: <%=activity_id %>
							}
						}).done(function(back){
							if(back == "ok"){
								location.reload();
							}else{
								alert("发布失败，请稍后再试");
							}
						});
					}
			
				});
				
			})
		</script>
	</head>
    <body ng-app="">
    	<ul class="list-group">
    	<% 
	     int i=0;
	     int article_article_id;
	     int article_article_prize;
	       for(Article article:articles){
	    	   i++;
	    	   article_article_id=article.getArticle_id();
	    	   article_article_prize=article.getArticle_prize();
	    %>
    		<li id="<%=article.getArticle_id()%>">
    			<div class="row">
    				<div class="col-xs-5">
    					<a href="/LiteratureWebsite/views/admin/content.jsp?article=<%=article.getArticle_id()%>"><%=article.getArticle_name()%></a>
    				</div>
    				<div class="col-xs-7" ng-init="text<%=article.getArticle_id()%>='<%=article_article_prize %>'">
    					<select class="selection prize" ng-model="text<%=article.getArticle_id()%>">
							<option value="0">无</option>
							<option value="1">一等奖</option>
							<option value="2">二等奖</option>
							<option value="3">三等奖</option>
							<option value="4">优胜奖</option>
						</select>
						<button class="btn btn-danger delete">移除</button>
    				</div>	
    			</div>
    		</li>
    		<script type="text/javascript">	
				$("#<%=article_article_id%>").find(".prize").val(<%=article_article_prize%>);
			</script>
    		<%} %>
    	</ul>
    	<div class="sure btn btn-info col-xs-12">发布</div>
    	<c:if test="${status<=0}"><p>当前状态：未发布</p></c:if>
    	<c:if test="${status>=1}"><p>当前状态：已发布</p></c:if>
 	</body>
</html>