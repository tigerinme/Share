package com.send.back.controller;

import com.send.back.domain.response.Failed;
import com.send.back.domain.response.Result;
import com.send.back.domain.response.Success;
import com.send.back.domain.share.Share;
import com.send.back.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("personal")
    public String goToPersonal() {
        return "/user/personal";
    }

    @RequestMapping("setup")
    public String goToSetup() {
        return "/user/setup";
    }

    @RequestMapping("feedback")
    public String goToFeedback() {
        return "/common/feedback";
    }

    /**
    * 功能描述：主页-我的分享
    * @param
    * @return
    * @author 董森
    * @since 2017/5/18
    * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
    */
    @ResponseBody
    @RequestMapping("myShare")
    public Result getMyShare(Integer page,
                             Integer userId){
        if(null !=page && null != userId) {
            List<Share> shareListPersonalDTOS;
            try {
                shareListPersonalDTOS = userService.getMyShare(page,userId);
            } catch (Exception e) {
                return new Failed("查询我的分享失败");
            }
            return new Success("查询成功", shareListPersonalDTOS);
        }else{
            return new Failed("查询我的分享:参数错误");
        }
    }

}
