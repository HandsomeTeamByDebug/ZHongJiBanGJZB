$(function() {
	var verifyEmail = true;
	var verifyPhone = true;
	var verifyUserName = true;
	var verifyUservCode = false;
	$("#regit").click(function() {
		createmodalwindow("注册用户", 800, 350, 'jsp/user/regist.jsp');
	});

	//用户名
	$('#userName').validatebox({
		required : true,
		missingMessage : '请输入用户名',
		invalidMessage : '用户名不得为空',
		validType : 'length[5,30]'
	});
	$('#userName').bind('focus', function() {
		$("#userNameError").css("display", "none");
	});
	$('#userName').bind('blur', function() {
		$.ajax({
			type : "post",
			url : "/GJZB/users/verifyUserName",
			async : true,
			data : {
				userName : $('#userName').val()
			},
			dataType : "json",
			success : function(data) {
				if (data) {
					$("#userNameError").css("display", "");
					$("#userNameError").text("用户名已存在！");
					verifyUserName = false;
				}else{
					verifyUserName = true;
				}
			}
		});
	});

	//密码
	$('#userPw').validatebox({
		required : true,
		validType : 'length[5,30]',
		missingMessage : '请输入密码',
		invalidMessage : '管理员密码在5-30 位',
	});

	//新增一个规则
	$.extend($.fn.validatebox.defaults.rules, {
		savePw : {
			validator : function(value) {
				return $("#reuserPw").val() == $("#userPw").val();
			},
			message : '密码不一致',
		},
	});

	//确认密码
	$('#reuserPw').validatebox({
		required : true,
		missingMessage : '请输入确认密码',
		validType : 'savePw',
	});
	$('#reuserPw').bind('blur', function() {
		var rePw = $("#reuserPw").val().trim();
		var pw = $("#userPw").val().trim();
		if (rePw.length == 0) {// 非空校验 以及 两次输入是否一致
			$(this).validatebox('enableValidation').validatebox('validate');
		}
		if (rePw != pw) {// 非空校验 以及 两次输入是否一致
			$(this).validatebox('enableValidation').validatebox('validate');
		}
	});

	//邮箱
	$('#email').validatebox({
		required : true,
		validType : 'email',
	});
	$('#email').bind('focus', function() {
		$("#emailError").css("display", "none");
	});
	$('#email').bind('blur', function() {
		$.ajax({
			type : "post",
			url : "/GJZB/users/verifyEmail",
			async : true,
			data : {
				email : $('#email').val()
			},
			dataType : "json",
			success : function(data) {
				if (data) {
					$("#emailError").css("display", "");
					$("#emailError").text("邮箱已被注册！");
					verifyEmail = false;
				}else{
					verifyEmail = true;
				}
			}
		});
	});

	//新增一个规则
	$.extend($.fn.validatebox.defaults.rules, {
		phoneLength : {
			validator : function(value) {
				var phone = $('#phone').val();
				if (!(/^1[34578]\d{9}$/.test(phone))) {
					return false;
				} else {
					return true;
				}
			},
			message : '手机号码有误，请重填',
		},
	});
	//手机
	$('#phone').validatebox({
		required : true,
		validType : 'phoneLength',
	});
	$('#phone').bind('focus', function() {
		$("#phoneError").css("display", "none");
	});
	$('#phone').bind('blur', function() {
		$("#phoneError").css("display", "none");
		$.ajax({
			type : "post",
			url : "/GJZB/users/verifyPhone",
			async : true,
			data : {
				phone : $('#phone').val()
			},
			dataType : "json",
			success : function(data) {
				if (data) {
					$("#phoneError").css("display", "");
					$("#phoneError").text("手机号已被注册！");
					verifyPhone = false;
				}else{
					verifyPhone = true;
				}
			}
		});
	});

	//注册按钮
	$('#submit').click(function() {
		var text = true;
		$('.tdInput').each(function() {
			if (!$(this).validatebox('isValid')) {
				$(this).focus();
				text = false;
			}
		});
		
		//判断用户名是否存在
		if(!verifyUserName){
			text = false;
			$("#userName").focus();
		}
		//判断两次密码是否一致
		var pw = $("#reuserPw").val() == $("#userPw").val();
		if (!pw) {
			text = false;
			$("#reuserPw").focus();
		}
		//判断邮箱或手机号是否以及被注册
		if(!verifyEmail){
			$("#email").focus();
			text = false;
			return false;
		}
		if(!verifyPhone){
			$("#phone").focus();
			text = false;
			return false;
		}
		if (text) {
			$.ajax({
				url : '/GJZB/users/regit',
				type : 'POST',
				data : $("#regitForm").serialize(),
				dataType : "json",
				asysn : true,
				success : function(data) {
					if (data) {
						//关闭注册窗口
						parent.closemodalwindow();
						alert("注册成功！");
						parent.location.href="/GJZB/index.jsp";
					}
				}
			});
		}

		return false;

	});

	/*
	 * 校验验证码
	 */
	$('#check_code').blur(function() {
		$("#verifyCodeError").css("display", "none");
		var value = $("#check_code").val();
		if (!value) {// 非空校验
			$("#verifyCodeError").css("display", "");
			$("#verifyCodeError").text("必填");
		} else {//验证码是否一致
			verifyUservCode = true;
			$.ajax({
				cache : false,
				async : false,
				type : "POST",
				dataType : "json",
				data : {
					verify : value
				},
				url : "/GJZB/users/validateVerifyCode",
				success : function(flag) {
					if (!flag) {
						$("#verifyCodeError").css("display", "");
						$("#verifyCodeError").text("错误！");
						verifyUservCode = false;
					}else{
						verifyUservCode = true;
					}
				}
			});
		}
	});
	$("#login_userName").validatebox({
		required : true,
		validType : 'length[5,30]',
		missingMessage : '该输入项为必输入项',
	});
	$("#login_userPw").validatebox({
		required : true,
		missingMessage : '该输入项为必输入项',
	});
	
	$('#login_submit').click(
			function() {
				if (!$('#login_userName').validatebox('isValid')) {
					$('#login_userName').focus();
				} else if (!$('#login_userPw').validatebox('isValid')) {
					$('#login_userPw').focus();
				}else if(!verifyUservCode){
					$("#check_code").focus();
					return false;
				}else{
					// 服务器提交
					$.ajax({
						url : '/GJZB/users/valifyUser',
						type : 'POST',
						data : {
							userName : $('#login_userName').val(),
							userPw : $('#login_userPw').val(),
						},
						success : function(data) {
							if (data > 0) {
								location.href="/GJZB/index.jsp";
							} else {
								$.messager.alert('登录失败！', '用户名或密码错误！',
										'warning', function() {
											$('#login_userPw').select();
										});
							}
						},
						error:function(data){
							alert('error');
						}
					});
				}

				return false;
			});

});
