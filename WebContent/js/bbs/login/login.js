 
function exit() {
        $.ajax({
            type: 'POST',
            url: 'bbs/login/exitLogin',
            data: {'time':new Date()},
            success: function(data){
                alert(data);
                window.location.reload();
            },
            dataType:'text'
        });

    }
function check_login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var regx_username = /[a-zA-Z0-9_]{6,16}/;
    var regx_password = /[a-zA-Z0-9_]{6,16}/;
    if(regx_username.test(username)){
        if(regx_password.test(password)){
            $.ajax({
                type: 'POST',
                url: 'bbs/login',
                data: {
                    'username':username,
                    'password':password
                },
                success: function(data){
                    if(data=='false'){
                        alert("登陆失败！检查用户名或者密码！")
                    }
                    else
                    {
                        $("#login").hide();
                        $("#logined").show();
                        document.getElementById("login_username").innerHTML=data;
                    }
                },
                dataType:'text'
            });

        }else{
            alert("密码在6-16位字母和数字");
        }
    }
    else{
        alert("用户名在6-16位字母和数字");
    }
}

function checkAuthCode() {
    var AuthCode = '';
    var flag = false;
    $.ajax({
        async:false,
        type: 'POST',
        url: 'bbs/register/returnAuthCode',
        data: {'flag':true},
        success: function(data){
            AuthCode=data;
            // alert(AuthCode);
            var inputAuthCode = document.getElementById("authcode").value;
            if(inputAuthCode == AuthCode){
//                alert("inputAuthCode"+inputAuthCode);
//                alert("AuthCode"+AuthCode);
                flag = true;
            }
        },
        dataType:'text'
    });
    return flag;
}

var regx_username = /[a-zA-Z0-9_]{6,16}/;
$.ajax({
    type: 'POST',
    url: 'bbs/login/getLoginSession',
    data: {'time':new Date()},
    success: function(data){
        if(regx_username.test(data)){
            $("#login").hide();
            $("#logined").show();
            document.getElementById("login_username").innerHTML=data;
        }
    },
    dataType:'text'
});