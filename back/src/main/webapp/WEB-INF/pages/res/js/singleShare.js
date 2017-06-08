$(document).ready(function () {
        //屏幕宽度
        var width = window.screen.height;
        $(".content").css("min-height", width);
        $(".sidebar").css("min-height", width);


      $("#btn-chat").click(function () {
          var comment = $("#chat_text").val();
          //全局样式
          layer.config({
              skin: 'comment-tip'//自定义样式
          })
          if(comment.length == 0){
              layer.msg('请输入评论内容', {time: 1500});
              return;
          }
      })
    }
)
