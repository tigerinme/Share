$(document).ready(function () {
    //屏幕宽度
    var width = window.screen.height;
    $(".content").css("min-height", width);
    $(".sidebar").css("min-height", width);


    $("#btn-chat").click(function () {
        var content = $("#chat_text").val();
        var userId = $("#userId").val();
        var toid = $("#shareId").val();//评论分享id
        var pid = $("#pid").val();//评论为0，回复为父评论id
        pid=0;
        //全局样式
        layer.config({
            skin: 'comment-tip'//自定义样式
        })
        if (content.length == 0) {
            layer.msg('请输入评论内容', {time: 1500});
            return;
        }
        if(content.length>255){
            layer.msg('评论内容过长', {time: 1500});
            return;
        }
        $.ajax({
            type: 'POST',
            url: '../user/addComment',
            data: 'userId=' + userId+'&pid='+pid+'&toid='+toid+'&content='+content,
            success: function (data) {
                if (data.status == 0) {  //添加评论失败
                    layer.msg('出现异常，请稍后再试', {time: 1500});
                } else { //添加评论成功
                    layer.msg('评论成功', {time: 1500});
                    //动态追加评论内容
                }
            },
            error: function () {
                layer.msg('网络出现异常，请稍后重试', {time: 1500});
            }
        });
    });
})
