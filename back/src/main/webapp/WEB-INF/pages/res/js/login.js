$(document).ready(function () {
    // 登陆点击事件
    $("#js-sign-in-btn").click(function () {
        $("#js-sign-up-btn").removeClass("active");
        $(".js-sign-up-container").hide(500);
        $(this).addClass("active");
        $(".js-sign-in-container").show(500);

    });
    // 注册点击事件
    $("#js-sign-up-btn").click(function () {
        $(".js-sign-in-container").hide(500);
        $("#js-sign-in-btn").removeClass("active");
        $(this).addClass("active");
        $(".js-sign-up-container").show(500);
    });
    // 设置为屏幕宽度
    $(document).ready(function () {
        var w = document.documentElement.clientHeight;
        $("#sign").css("minHeight", w + "px")
    });

// 注册表单验证
    $('#loginForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            login_username: {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 20,
                        message: '用户名必须大于6，小于20个字'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '用户名只能由字母、数字、点和下划线组成'
                    },
                    different: {
                        field: 'password',
                        message: '用户名和密码不能相同'
                    }
                }
            },
            login_password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 20,
                        message: '密码必须大于6，小于20个字'
                    },
                    different: {
                        field: 'username',
                        message: '用户名和密码不能相同'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '密码只能由字母、数字、点和下划线组成'
                    }
                }
            }
        }
    });






    // 注册表单验证
    $('#registerForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            register_username: {
                validators: {
                    remote: {
                        message: "用户名已经被使用，抓紧换一个吧",
                        url: "../user/checkUsername",
                        data: {
                            register_username: $("#register_username").val()
                        },
                        delay: 2000
                    },
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 20,
                        message: '用户名必须大于6，小于20个字'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '用户名只能由字母、数字、点和下划线组成'
                    },
                    different: {
                        field: 'password',
                        message: '用户名和密码不能相同'
                    }
                }
            },
            register_password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 20,
                        message: '密码必须大于6，小于20个字'
                    },
                    different: {
                        field: 'username',
                        message: '用户名和密码不能相同'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '密码只能由字母、数字、点和下划线组成'
                    }
                }
            },
            register_email: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    regexp: {
                        regexp: /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/,
                        message: '邮箱格式错误'
                    },
                    remote: {
                        message: "邮箱已经被使用，抓紧换一个吧",
                        url: "../user/checkEmail",
                        data: {
                            email: $("#register_email").val()
                        },
                        delay: 2000
                    }
                }
            }
        }
    });
});