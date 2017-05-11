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

    $('#signUpButton').click(function () {
        $('#registerForm').data('bootstrapValidator').validate();
    });
    $('#signInButton').click(function () {
        //表单提交前再进行一次验证
        var bootstrapValidator = $("#loginForm").data('bootstrapValidator');
        bootstrapValidator.validate();
        //如果验证通过()则提交表单
        alert(bootstrapValidator.validate());
    });


    // 登录表单验证
    $('#loginform').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                message: 'The username is not valid',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 20,
                        message: '用户名长度在6~20之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '用户名只能包含字母数字下划线和点'
                    },
                    different: {
                        field: 'password',
                        message: '用户名和密码不能相同'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    different: {
                        field: 'username',
                        message: '密码和用户名不能相同'
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
            username: {
                validators: {
                    remote: {
                        message: "用户名已经被使用，抓紧换一个吧",
                        url: "../user/checkUsername",
                        data: {
                            username: $("#register_username").val()
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
            password: {
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
            email: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress: {
                        message: '邮箱地址格式有误'
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