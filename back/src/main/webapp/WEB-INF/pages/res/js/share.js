/**
 * Created by rainy on 2017/5/14.
 */

$(document).ready(function () {
    var editor = new wangEditor('editor-container');
    $(function () {
        //图片上传地址
        editor.config.uploadImgUrl = "upload/richText?${_csrf.parameterName}=${_csrf.token}";
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
});
