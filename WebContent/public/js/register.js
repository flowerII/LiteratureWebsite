$(function(){
	var top = $("body").height()/2-$(".box").height()/2;
	$(".box").css("top",top);
	
	var $account = $("#account");
	var $password1 = $("#password1");
	var $password2 = $("#password2");
	
		// localStorage 存储
	$account.val(localStorage.getItem('user_account'));
	
	$(".register-in").click(function(){
		
		//表单验证
		if(!$account.isEmpty()){
			alert("账号不能为空！");
			$account.focus();
			return;
		}
		if(!$password1.isEmpty()){
			alert("密码不能为空！");
			$password1.focus();
			return;
		}
		if($password1.val().length<6){
			alert("密码至少为6位");
			$password1.focus();
			return;
		}
		if(!$("#code").isEmpty()){
			alert("验证码不能为空！");
			$("#code").focus();
			return;
		}
		if(!$account.isEmail()){
			alert("请输入正确邮箱！");
			$account.focus();
			return;
		}
		if(!$account.isEmail()){
			alert("请输入正确邮箱！");
			$account.focus();
			return;
		}
		if($password1.val()!=$password2.val()){
			alert("两次输入的密码不同！");
			$password2.focus();
		}
		
		$._ajax({
			url: "",
			data: {
				user_account:$account.val(),
				user_password:$password1.val(),
				code : $("#code").val()
			},
			dataType:"text"
		}).done(function(back){
			if(back=="ok"){	
						//localStorage存储账号密码
				localStorage.setItem('user_account', $account.val());
				localStorage.setItem('user_password', $password1.val());
				alert("注册成功");
				window.location.href="login.html";
			} else if(back=="err1"){
				alert("用户已存在！请重新输入账号");
				$account.focus();
			} else if(back=="err2"){
				console.log(back);
				alert("验证码错误，请重新输入！！");
			}else {
				console.log(back);
				alert("发生未知错误，请稍后再式");
			}
		});
		return ;
	});
})
