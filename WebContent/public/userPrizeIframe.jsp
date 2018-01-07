<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.website.enty.Article,com.website.service.ArticleService,java.util.*" %>
<%
String activity_id =request.getParameter("activity");
ArticleService articleService=new ArticleService();
List<Article> articles=new ArrayList<Article>();
articles=articleService.getArticleByActivityId(Integer.parseInt(activity_id));
session.setAttribute("articles", articles);
%>
<!DOCTYPE html>
<html>
    <head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
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
			table{
				width: 100%;
			}
			th,td{
				text-align: center;
			}
		</style>
		<script src="js/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/angular.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/plugs.js" type="text/javascript" charset="utf-8"></script>
	</head>
    <body ng-app="">
    	<table class="table-striped">
    		<tr>
    			<th>作品名称</th>
    			<th class="hidden-xs">作者</th>
    			<th>获奖情况</th>
    		</tr>
    		<% for(Article article:articles){ %>
    		   <tr>
    			    <td><a href="javascript:;" onclick="top.location.href='/LiteratureWebsite/views/admin/content.jsp?article=<%=article.getArticle_id()%>'"><%=article.getArticle_name() %></a></td>
    			    <td class="hidden-xs"><%=article.getUser_name()%></td>
    			    <td>
    			       <%
    			           int n=article.getArticle_prize();
    			           if(n==1){
    			        	   out.print("一等奖");
    			           }
    			           else if(n==2){
    			        	   out.print("二等奖");
    			           }
    			           else if(n==2){
    			        	   out.print("三等奖");
    			           }else{
    			        	   out.print("优胜奖");
    			           }
    			       %>
    			     </td>
    		    </tr>
    		 <%} %>
    	</table>
 	</body>
</html> 