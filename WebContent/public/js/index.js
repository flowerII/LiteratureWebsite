$(function(){
	
	/**
	 *  wiper 滚动
	 **/
	var mySwiper = new Swiper('.swiper-container',{
		//分页
		//pagination : '.pagination',
		pagination : '.swiper-pagination',
		paginationClickable :true,
		DOMAnimation : false,	  	
		//自动滚动 
		autoplay : 2000,
		prevButton:'.swiper-button-prev',
		nextButton:'.swiper-button-next',
		loop: true
		//其他设置
	}); 
	
	$('.swiper-button-prev').hide();
	$('.swiper-button-next').hide();
		  
	 $('.swiper-container').mouseenter(function(){
	 	$('.swiper-button-prev').show();
	 	$('.swiper-button-next').show();
		mySwiper.stopAutoplay();
	})
	$('.swiper-container').mouseleave(function(){
		$('.swiper-button-prev').hide();
	 	$('.swiper-button-next').hide();
		mySwiper.startAutoplay();
	})
	
	

	/**
	 *     ajax  获取最新消息 、 征稿活动 
	 * */

	$._ajax({
		url: '',
	}).then(function(news){
		//5条最新消息
		news = JSON.parse(news);
		var $content = $(".news").find("ol");
		var html = "";
		news.forEach(function(value){
			var item = '<li class="list-group-item "><a href="/LiteratureWebsite/views/admin/newscontent.jsp?news='+value.new_id+'">'+value.new_name+'</a></li>';
			html += item;
		});
		$content.html(html);
	});
	
	
	
	$._ajax({
		url: '',
	}).then(function(activities){
		//5条最新征稿活动
		activities = JSON.parse(activities);
		var $content = $(".activities").find("ol");
		var html = "";
		activities.forEach(function(activity,i){
			var item = '<li class="list-group-item "><a href="/LiteratureWebsite/views/admin/activities.jsp?activity='+activity.activity_id+'">'+activity.activity_name+'</a></li>';
			html += item;
		});
		$content.html(html);
	});
	
	
	
})
