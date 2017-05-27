$(document).ready(function () {
    //屏幕宽度
    var width = window.screen.height;
    $(".content").css("min-height", width);
    $(".sidebar").css("min-height", width);

    //默认加载我的分享
    window.onload = function () {
        $("#goGetMyShare").trigger("click");
    }
    // 我的分享


    $("#goGetMyShare").click(getShare());


    function getShare() {
        var userId = $("#userId").val();
        var page = $("#page").val();
        var shares;
        var sharelength;
        var tags;
        $("#view-more-button").val("点击加载更多");
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
                    sharelength = shares.length;
                    for (var i = 0; i < sharelength; i++) {
                        tags = shares[i].tagList;
                        $("#share-container").append("<li>" +
                            "<div class='share-box'>" +
                            "<div id='share-c" + shares[i].id + "'>" +
                                   "<div class='author' id='author" + shares[i].id + "'>" +
                                          "<a class='avatar' target='_blank' href=''><img  src='../res/img/avatar.png' alt='96'></a>" +
                                              "<div class='name'>" +
                                                   "<a class='blue-link' target='_blank' href=''>&nbsp;&nbsp;&nbsp;&nbsp;" + shares[i].nickname + "</a>" +
                                                   "<span class='time' data-shared-at='2017-05-26T13:59:38+08:00'>&nbsp;&nbsp;&nbsp;&nbsp;" + shares[i].createTime + "</span>" +
                                              "</div>" +
                                    "</div>" +
                                   "<a class='title' target='_blank' href='/p/36b28ed1f3c7'>" + shares[i].title + "</a>" +
                                   " <p class='abstract' style='margin-left: 5px'>" + shares[i].summary +"</p>"+
                                 "<div class='share-i' id='share-i" + shares[i].id + "' ></div>" +
                            "</div>"+
                            "<div class='clearfix'></div>"+
                            "<div class='meta' id='meta" + shares[i].id + "'></div></li>");
                        if(shares[i].img != '' && shares[i].img != null){
                            $("#author"+ shares[i].id).append("<img style='float: right;margin-top: 20px' class='share-single-image' src='"+shares[i].img+"'>")
                        }
                        for (var j = 0; j < tags.length; j++) {
                            $("#meta" + shares[i].id).append("<a class='collection-tag' target='_blank' href=''>" + tags[j] + "</a>");
                        }

                        $("#meta" + shares[i].id).append(
                            "<a target='_blank' href=''>" +
                            "<i class='fa fa-eye'>&nbsp;&nbsp;" + shares[i].viewCount + "</i>" +
                            "<i class='fa fa-heart'>&nbsp;&nbsp;" + shares[i].likeCount + "</i>" +
                            "<i class='fa fa-comment'>&nbsp;&nbsp;" + shares[i].commentCount + "</i>" +
                            "<i class='fa fa-share'>&nbsp;&nbsp;" + shares[i].shareCount + "</i>" +
                            "<i class='fa fa-file'>&nbsp;&nbsp;" + shares[i].collectionCount + "</i>" +
                            "<i class='fa fa-thumbs-up'>&nbsp;&nbsp;" + shares[i].thumbUpCount + "</i>" +
                            "<i class='fa fa-thumbs-down'>&nbsp;&nbsp;" + shares[i].thumbDownCount + "</i>" +
                            "</a>");
                    }
                    //没有内容
                    if(sharelength == 0){
                        $("#view-more-button").val("没有更多.....");
                    }
                }
            },
            error: function () {

            }
        });
    }

    // 加载更多
    $("#view-more-button").click(function () {
        getShare()
    })

});
