
$(document).ready(function () {
    // alert($.session('session_user_key'));
    $("#search-input").click(function (e) {
        $(this).animate({"width": "260px"})
    }).blur(function () {
        $(this).animate({"width": "200px"})
    });
});



