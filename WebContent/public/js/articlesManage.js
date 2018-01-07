$(function(){
	
	//获取活动年份
	$._ajax({
		url : 'admin/activitiesYearList'
	}).done(function(list){
		var html = "";
		html = '<option value="-1">请选择活动年份</option>';
		list = JSON.parse(list);
		if(list.length!=0){
			list.forEach(function(year,i){
				html += '<option value="'+year.activity_year+'">'+year.activity_year+'</option>';
			});
		}
		$("#years").append(html);
	})
	
	//获取活动
	$("#years").change(function(){
		if($("#years").val()=='-1') return;
		$._ajax({
			url : 'admin/activitiesThisYear',
			data:{
				activity_year:$("#years").val()
			}
				
		}).done(function(list){
			var html = "";
			html = '<option value="-1">请选择相应的活动</option>';
			list = JSON.parse(list);
			if(list.length!=0){
				list.forEach(function(activity,i){
					html += '<option value="'+activity.activity_id+'">'+activity.activity_name+'</option>';
				});
			}
			$("#activities").append(html);
		})
	})
	
	//获取活动稿件
	$("#activities").change(function(){
		if($("#activities").val()=='-1') return;
		var src = "articleList.jsp?page=1&activity="+$("#activities").val();
		$("#articlesIframe").attr('src',src);
		$("#articlesIframe").get(0).onload=function(){
			$("#articlesIframe").height($("#articlesIframe").contents().find("html").height());
		}
//		$._ajax({
//			type : 'get',
//			url : 'admin/articlesManage/articles?page=1'
//		}).done(function(list){
//			//返回10条文章数据和总文章数量
//			var html = "";
//			list = JSON.parse(list);
//			var articles = list.articles;
//			var num = list.num;
//			var i =location.search.substring(1).split("&")[0].split("=")[1];
//			//生成分页导航栏
//			$.page(".body-main",i,num);
//			if(articles.length!=0){
//				articles.forEach(function(article,i){
//					html = '<li id="'+article.article_id+'">'+
//							'<div>'+
//								'<a href="/content?article='+article.article_id+'">123</a>'+
//								'<a class="right photo" href="#list'+i+'" data-toggle="collapse"></a>'+
//							'</div>'+
//							'<div class="panel collapse" id="#list'+i+'">'+
//								'奖项：'+
//								'<select class="selection" class="prize">'+
//									'<option value="0">无</option>'+
//									'<option value="1">一等奖</option>'+
//									'<option value="2">二等奖</option>'+
//									'<option value="3">三等奖</option>'+
//									'<option value="4">优胜等奖</option>'+
//								'</select>'+
//								'<button class="btn btn-danger delete">删除</button>'+
//							'</div>'+
//						'</li>';
//					$(".main").append(html); 
//				});
//			}else{
//				$(".main").html('暂时无人投稿'); 
//			}
//			
//		})
	});
	
	
//	//奖项设置
//	$('.prize').on('change',function(){
//		var $id = $(this).parent().parent().attr('id');
//		if(confirm("你确定要修改用户权限吗？")){
//			$._ajax({
//				url: "admin/articlesManage/prize",
//				data: {
//					article_id : $id,
//					article_prize : $(this).val()
//				}
//			}).done(function(back){
//				if(back == "ok"){
//					alert("奖项设置成功");
//				}else{
//					alert("奖项设置失败，请稍后再试");
//				}
//			});
//		}
//	});
//	
//	//删除书籍
//	$('.delete').on('click',function() {
//		var $id = $(this).parent().parent().attr('id');
//		if(confirm("你确定要删除改稿件吗？")){
//			$._ajax({
//				url: "admin/articlesManage/delete",
//				data: {
//					article_id : $id,
//				}
//			}).done(function(back){
//				if(back == "ok"){
//					alert("稿件删除成功");
//					location.reload();
//				}else{
//					alert("稿件删除失败，请稍后再试");
//				}
//			});
//		}
//
//	});
	
})