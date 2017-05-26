/**
 * Created by Administrator on 2017/5/17.
 */
$(document).ready(function () {
    //屏幕宽度
    var width = window.screen.height;
    $(".content").css("min-height", width);
    $(".sidebar").css("min-height", width);
    // 我的分享
    $("#goGetMyShare").click(function () {
        var userId = $("#userId").val();
        var page = $("#page").val();
        var shares;
        var sharelength;
        var tags;
        $.ajax({
            type: 'POST',
            url: '../user/getMyShare',
            data: 'userId=' + userId + '&page=' + page,
            success: function (data) {
                if (data.status == 0) {  //获取列表失败
                    $("#showError").removeAttr('style');
                    $('#showError').html(data.info);
                    //登陆按钮置为可用
                    $('#signInButton').removeAttr("disabled");
                    setTimeout(
                        function () {
                            $("#showError").hide('slow');
                            $("#showError").html('');
                        }, 1000);//1秒后执行该方法
                } else { //获取列表成功
                    shares = data.returnMessage.shareList;
                     $("#page").val(data.returnMessage.nextPage);//修改下一页页码
                    sharelength  = shares.length;
                    for(var i=0;i<sharelength;i++){
                        tags = shares[i].tagList;
                        $("#share-container").append("<li>" +
                            "<div class='share-box'>" +
                              "<div class='author'>" +
                                  "<a class='avatar' target='_blank' href=''>" +
                                   "<img  src='../res/img/avatar.png' alt='96'>" +
                                  " </a>" +
                                  "<div class='name'><a class='blue-link' target='_blank' href=''>&nbsp;&nbsp;&nbsp;&nbsp;"+shares[i].nickname+"</a>" +
                                 "<span class='time' data-shared-at='2017-05-26T13:59:38+08:00'>&nbsp;&nbsp;&nbsp;&nbsp;"+shares[i].createTime+"</span></div>" +
                            "</div>" +
                            "<a class='title' target='_blank' href='/p/36b28ed1f3c7'>"+shares[i].title+"</a>" +
                            " <p class='abstract'>"+shares[i].summary+"</p>" +
                            "<div class='meta'>");

                        // for(var i =0;i<tags.length;i++){
                        //    $("#share-container").append("<a class='collection-tag' target='_blank' href='/c/Df7njb'>"+tags[i]+"</a>");
                        // }
                        $("#share-container").append("<a target='_blank' href='/p/36b28ed1f3c7'>" +
                        "<i class='iconfont ic-list-read'></i> 597</a><a target='_blank' href=''>" +
                        "<i class='iconfont ic-list-comments'></i> 0</a>" +
                        "<span><i class='iconfont ic-list-like'></i> 27</span> </div></div></li>");
                    }
                }
            },
            error: function () {

            }
        });
    })


});
