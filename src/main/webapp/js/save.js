
$(function () {
    var username = $("#username");
    var password = $("#password");
    /**
     * ajax检测用户名是否存在
     */
    username.blur(function () {
        var username = $(this).val();
        var error_u = $("#error_u");
        if (checkUsername()){
            $.post("user/findOne",{username:username},function (data) {
                if (data==null){
                    error_u.css("color","green");
                    error_u.html("用户名可用");
                }else {
                    error_u.css("color","red");
                    error_u.html("用户名已存在");
                }

            },"json")
        }
    });
    /**
     * 异步提交表单
     */
   /* $("#saveForm").submit(function () {
        if (checkUsername()&&checkPassword()){
            $.ajax({
                url:"user/saveUser",
                contentType:"application/json;charset=utf-8",
                data:'{"username":"username","password":"password"}',
                type:"post",
                success:function () {
                    location.href = "index.html";
                }
            })

        }
    });*/
    /**
     * 用户名检测
     * @returns {boolean}
     */
    function checkUsername() {
        var error_u = $("#error_u");
        var reg_username = /^([\u4E00-\u9FA5\uf900-\ufa2d]|[a-zA-Z0-9]|[_-]){6,12}$/;
        var flag = reg_username.test(username.val());
        if (flag) {
            username.css("border","")
            error_u.html("");
        } else {
            username.css("border","1px solid red")
            error_u.css("color", "red");
            error_u.html("用户名格式错误<br>请输入6-12位字符");
        }
        return flag;
    }

    /**
     * 密码检测
     * @returns {boolean}
     */
    function checkPassword() {

        var error_p = $("#error_p");
        var reg_password=/^\w{6,12}$/;
        var flag = reg_password.test(password.val());
        if (flag){
            password.css("border","");
            error_p.html("");
        }else {
            password.css("border","1px solid red");
            error_p.css("color","red");
            error_p.html("请输入6-12位字符")
        }
        return flag;

    }
    password.blur(checkPassword);
})
