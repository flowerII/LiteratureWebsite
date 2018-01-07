$(function() {
	var top = $("body").height()/2-$(".box").height()/2;
	$(".box").css("top",top);
	
	var $form = $("form");
	var $account = $("#useraccount");
	var $password = $("#password");

	// localStorage 存储
	$account.val(localStorage.getItem('user_account'));
	$password.val(localStorage.getItem('user_password'));
	
	$(".login-in").click(function(){
		
		//localStorage存储账号密码
		localStorage.setItem('user_account', $account.val());
		localStorage.setItem('user_password', $password.val());
		
		//表单验证
		if(!$account.isEmpty()){
			alert("账号不能为空！");
			$account.focus();
			return;
		}
		if(!$password.isEmpty()){
			alert("密码不能为空！");
			$password.focus();
			return;
		}
		if(!$account.isEmail()){
			alert("请输入正确邮箱！");
			$account.focus();
			return;
		}
		
		$._ajax({
			url: "",
			data: {
				user_account:$account.val(),
				user_password:$password.val()
			},
			dataType:"text"
		}).done(function(back){
			back=JSON.parse(back);
			if(back.user_name){
				sessionStorage.userName = back.user_name;
				sessionStorage.role = back.user_role;
				alert("登录成功");
				window.location.href="index.html";
			} else if(back.back=="err1"){
				alert("账号或密码错误");
			} else {
				console.log(back);
				alert("发生未知错误，请稍后再说");
			}
		});
		return ;
	});
})
