package com.send.back.controller;

import com.send.back.domain.response.Failed;
import com.send.back.domain.response.Result;
import com.send.back.domain.response.Success;
import com.send.back.service.inter.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;

@Controller
@RequestMapping("share")
public class ShareController {


    @Autowired
    private ShareService shareService;

    /**
    * 功能描述：添加分享
    * @param title
    * @param tag
    * @param content
    * @param userId
    * @return
    * @author 董森
    * @since 2017/5/15
    * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
    */
    @ResponseBody
    @RequestMapping("addShare")
    public Result addShare(String title,
                           String content,
                           Integer tag,
                           Integer userId) {
        System.out.println(URLDecoder.decode(content));
        Integer count = shareService.addShare(title, URLDecoder.decode(content), tag, userId);
        if(null != count && count>0){
            return new Success("添加成功");
        }else {
            return new Failed("添加失败");
        }
    }
}
