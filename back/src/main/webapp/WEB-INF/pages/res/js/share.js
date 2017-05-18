/**
 * Created by rainy on 2017/5/14.
 */

$(document).ready(function () {

    var width = window.screen.height;
    $(".show-login-tip").css("min-height",width);

    $(function () {
        $('[data-toggle="popover"]').popover()
    })

    var editor = new wangEditor('editor-container');
    // 阻止输出log
    wangEditor.config.printLog = false;

    $("#share-tag").tagsinput({
        maxTags: 4,//标签数
        maxChars: 6,//最大字符数
        trimValue: true,//去除空格
    });

    $(function () {
        //图片上传地址
        editor.config.uploadImgUrl = "../upload/uploadImageFromRichText";
        // 自定义load事件
        editor.config.uploadImgFns.onload = function (resultText) {
            var data = eval('(' + resultText + ')');
            var imgUrl = data.info;
            if (data.status == 0) {
                alert("图片上传失败")
            } else {
                var originalName = editor.uploadImgOriginalName || '';
                // 如果 resultText 是图片的url地址，可以这样插入图片：
                editor.command(null, 'insertHtml', '<img src="' + imgUrl + '" alt="' + originalName + '" style="max-width:100%;"/>');
            }

        };

        editor.create();
    });


   // 隐藏提示框
    $("#share-title").click(function () {
        $("#share-title-label").popover('hide');
    });
    $("#editor-container").click(function () {
        $("#share-content-label").popover('hide');
    });

    $(".share-tag-div").click(function () {
        $("#share-tag-label").popover('hide');
    });

    //发布分享
    $("#publish-button").click(function () {

        var title = $("#share-title").val();
        var userId = $("#userId").val();
        var tags = $("#share-tag").val();


        //去除空格
        var content = $("#editor-container").html();

        // 获取编辑器纯文本内容
        var text = editor.$txt.text();
        content = $.trim(content);
        // 没有编辑标题
        if(title == ''){
            $("#share-title-label").popover('show');
            // $("#share-title").attr("placeholder","标题不能为空");
            return;
        }
        // 没有编辑内容
        if($.trim(text)==''){
            $("#share-content-label").popover('show');
            return;
        }
        // 没有添加标签
        if(tags == ''){
            $("#share-tag-label").popover('show');
            // $("#share-tag").attr("placeholder","标签不能为空");
            return;
        }
        $.ajax({
            type: 'POST',
            url: './share/addShare',
            data: 'title=' + title + '&content=' + encodeURIComponent(content)+'&tags='+tags +'&userId='+userId,
            success: function (data) {
                if (data.status == 0) { //添加失败

                }else{
                     //跳转到我的主页
                    alert(data.status);
                }
            },
            error: function () {

            }
        });
    });

});
