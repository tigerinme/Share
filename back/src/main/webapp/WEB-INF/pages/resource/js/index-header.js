/**
 * Created by rainy on 2017/5/7.
 */

$(document).ready(function () {
    $("#search-input").click(function (e) {
        $(this).animate({"width": "260px"})
    }).blur(function () {
        $(this).animate({"width": "200px"})
    });
});



