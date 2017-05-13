$(document).ready(function () {
    $(".useroperator").hide();
    // alert($.session('session_user_key'));
    $("#search-input").click(function (e) {
        $(this).animate({"width": "260px"})
    }).blur(function () {
        $(this).animate({"width": "200px"})
    });

    $(".usercenter").mouseenter(function () {
        $(".userinfo").removeAttr("style");
        $(".userinfo").css("background", "gainsboro")
        $(".userinfo").css("border-radius", "5px")
        $(".useroperator").show();
    });
    $(".usercenter").mouseleave(function () {
        $(".userinfo").removeAttr("style");
        $(".userinfo").css("background", "write");
        $(".useroperator").hide();
    });
    $(".useroperator").mouseenter(function () {
        $(".useroperator").show();
    });

    $(".useroperator ul li").mouseenter(function () {
        $(this).removeAttr("style");
        $(this).css("background","gainsboro");
    });
    $(".useroperator ul li").mouseleave(function () {
        $(this).removeAttr("style");
        $(this).css("background","write");
    });
});



