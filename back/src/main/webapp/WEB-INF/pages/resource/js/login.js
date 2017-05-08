$(document).ready(function() {
    // 登陆点击事件
    $("#js-sign-in-btn").click(function() {
        $("#js-sign-up-btn").removeClass("active");
        $(".js-sign-up-container").hide(500);
        $(this).addClass("active");
        $(".js-sign-in-container").show(500);
    });
    // 注册点击事件
    $("#js-sign-up-btn").click(function() {
        $(".js-sign-in-container").hide(500);
        $("#js-sign-in-btn").removeClass("active");
        $(this).addClass("active");
        $(".js-sign-up-container").show(500);

    });
    // 设置为屏幕宽度
    $(document).ready(function(){
        var w = document.documentElement.scrollWidth;
        $("#sign").style.scrollHeight = w + "px";
    });
});