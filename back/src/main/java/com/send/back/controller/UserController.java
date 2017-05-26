package com.send.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.send.back.domain.response.Failed;
import com.send.back.domain.response.Result;
import com.send.back.domain.response.Success;
import com.send.back.domain.share.Share;
import com.send.back.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("personal")
    public ModelAndView goToPersonal() {
        ModelAndView modelAndView = new ModelAndView("user/personal");
        return modelAndView;
    }

    @RequestMapping("setup")
    public ModelAndView goToSetup() {
        ModelAndView modelAndView = new ModelAndView("user/personal");
        return modelAndView;
    }

    @RequestMapping("feedback")
    public ModelAndView goToFeedback() {
        ModelAndView modelAndView = new ModelAndView("common/feedback");
        return modelAndView;
    }

    /**
    * 功能描述：主页-我的分享 ajax请求处理
    * @param
    * @return
    * @author 董森
    * @since 2017/5/18
    * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
    */
    @ResponseBody
    @RequestMapping("getMyShare")
    public Result getMyShare(Integer page,
                             Integer userId){
        if(null !=page && null != userId) {
            List<Share> shareListPersonalDTOS;
            try {
                shareListPersonalDTOS = userService.getMyShare(page,userId);
            } catch (Exception e) {
                return new Failed("查询我的分享失败");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("shareList",shareListPersonalDTOS);
            jsonObject.put("nextPage",page+1);
            return new Success("查询成功",jsonObject );
        }else{
            return new Failed("查询我的分享:参数错误");
        }
    }

}
