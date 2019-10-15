var TT = TAOTAO = {
	checkLogin : function(){
		var _ticket = $.cookie("TTtoken");
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://localhost:8084/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					var username = data.data.username;
					var logout="http://localhost:8084/user/logoutJsp/"+ _ticket;
					var html = username + "，欢迎来到淘淘！<a href="+logout+">[重新登录]</a>";
					$("#loginbar").html(html);
				}
			}
		});
	}
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	TT.checkLogin();
});