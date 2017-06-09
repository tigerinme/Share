package com.send.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.send.back.domain.response.Failed;
import com.send.back.domain.response.Result;
import com.send.back.domain.response.Success;
import com.send.back.domain.share.Comment;
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
     *
     * @param
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/18
     */
    @ResponseBody
    @RequestMapping("getMyShare")
    public Result getMyShare(Integer page,
                             Integer userId) {
        if (null != page && null != userId) {
            List<Share> shareListPersonalDTOS;
            try {
                shareListPersonalDTOS = userService.getMyShare(page, userId);
            } catch (Exception e) {
                return new Failed("查询我的分享失败");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("shareList", shareListPersonalDTOS);
            if (shareListPersonalDTOS.size() > 0) {
                jsonObject.put("nextPage", page + 1);
            } else {
                jsonObject.put("nextPage", page);
            }
            return new Success("查询成功", jsonObject);
        } else {
            return new Failed("查询我的分享:参数错误");
        }
    }

    /**
     * 功能描述：跳转到单独分享页面
     *
     * @param shareId
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/6/7
     */
    @RequestMapping("goToSingleShare")
    public ModelAndView goToSingleShare(Integer shareId) {
        ModelAndView modelAndView = new ModelAndView("user/singleShare");
        Share share = userService.getSingleShare(shareId);
        modelAndView.addObject("share", share);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("shareOperator")
    public void shareOperator() {

    }

    /**
     * 功能描述：添加评论或者回复接口
     *
     * @param userId  评论或者回复用户id
     * @param pid     评论父子关系
     * @param toid   分享id
     * @param content 评论回复内容
     * @param type    0 分享,1评论
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/6/8
     */
    @ResponseBody
    @RequestMapping("addComment")
    public Result addComment(Integer userId, Integer pid,Integer toid, String content, Integer type) {
        int count = userService.addComment(userId,pid,toid,content);
        if (count== 0){
            return new Failed("添加评论失败");
        }else{
            return new Success("添加评论成功");
        }
    }

    /**
    * 功能描述：获取评论列表
    * @param toid 分享内容id
    * @return
    * @author 董森
    * @since 2017/6/8
    * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
    */
    @RequestMapping("getCommentList")
    public ModelAndView getCommentList(Integer toid){

        List<Comment> getCommentList = userService.getCommentList(toid);

        return new ModelAndView("");
    }

}
