package github.xiny.simpleblog.controller.user;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import github.xiny.simpleblog.domain.*;
import github.xiny.simpleblog.mapper.AvatarMapper;
import github.xiny.simpleblog.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/user")
@SaCheckLogin
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private AvatarMapper avatarMapper;

    @RequestMapping("/login")
    @SaIgnore
    public AjaxJson login(@RequestParam String account, @RequestParam String password) {
        final User msg;
        try {
            msg = userService.login(account, password);
        } catch (Exception e) {
            return AjaxJson.getError(e.getMessage());
        }
        if (msg.getUserId().equals(StpUtil.getLoginIdAsInt())) {
            msg.setPassword(null);
            final Avatar avatar = avatarMapper.selectById(msg.getAvatar());
            msg.setAvatarUrl(avatar.getImgUrl());
            msg.setToken(StpUtil.getTokenValue());
            return AjaxJson.getSuccess(account + "登录成功!", msg);
        }
        // 进行登录逻辑
        return AjaxJson.getError("账号或密码错误！");
    }

    @RequestMapping("/logout")
    @SaIgnore
    public AjaxJson logout() {
        StpUtil.logout();
        return AjaxJson.getSuccess("退出成功！");
    }

    @RequestMapping("/checkLogin")
    @SaIgnore
    public AjaxJson checkLogin() {
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            return AjaxJson.getSuccess("未登录！");
        }
        return AjaxJson.getSuccess("已登录！");
    }
    @RequestMapping("/getMyComment")
    public AjaxJson getMyComment(@RequestBody SelectVO vo){
        final Page<Object> page = PageHelper.startPage(vo.getPageIndex(), vo.getPageSize());
        final List<CommentMessage> comment = userService.getMyComment();
        return AjaxJson.getPageData(page.getTotal(),comment);
    }
    @RequestMapping("/register")
    @SaIgnore
    public AjaxJson register(@RequestParam String account, @RequestParam String password, @RequestParam Integer avatar) {
        final String msg = userService.register(account, password,avatar);
        if (msg.equals("success")) {
            return AjaxJson.getSuccess(account + "注册成功!", StpUtil.getTokenValue());
        }
        // 进行登录逻辑
        return AjaxJson.getError("账号已存在！");
    }

    @RequestMapping("/updateInfo")
    public AjaxJson updateInfo(String oldPassword, String newPassword, String avatar) {
        if (oldPassword == null && newPassword == null && avatar == null) {
            return AjaxJson.getError("参数错误！");
        }
        if(oldPassword!=null&&newPassword==null){
            return AjaxJson.getError("参数错误！");
        }
        userService.updateInfo(oldPassword, newPassword, avatar);
        return AjaxJson.getSuccess("修改成功！");
    }

    @RequestMapping("/aboutMe")
    @SaIgnore
    public AjaxJson aboutMe() {
        return AjaxJson.getSuccessData(userService.getAboutMe());
    }
}
