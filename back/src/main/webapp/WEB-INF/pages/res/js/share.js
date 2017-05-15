/**
 * Created by rainy on 2017/5/14.
 */

$(document).ready(function () {
    var editor = new wangEditor('editor-container');
    // 阻止输出log
    wangEditor.config.printLog = false;

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

    //提交
    $("#publish-button").click(function () {
        var title = $("#share-title").val();
        var tag = $("#share-tag").val();
        var userId = $("#userId").val();
        //去除空格
        var content = $("#editor-container").html();
        content = $.trim(content);
        console.log(content);

        if(title == ''){
            $("#share-title").attr("placeholder","标题不能为空");
            return;
        }
        if(tag == ''){
            $("#share-tag").attr("placeholder","标签不能为空");
            return;
        }
        $.ajax({
            type: 'POST',
            url: './share/addShare',
            data: 'title=' + title + '&content=' + encodeURIComponent(encodeURIComponent(content))+'&tag='+tag +'&userId='+userId,
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
