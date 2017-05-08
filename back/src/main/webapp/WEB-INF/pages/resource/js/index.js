/**
 * Created by rainy on 2017/5/9.
 */

// 设置为自适应屏幕宽度
$(document).ready(function(){
    var w = document.documentElement.scrollHeight;
    $(".sidebar").style.minWidth = w + "px";
    $(".content").style.minWidth = w + "px";
});
