package github.xiny.simpleblog.controller.admin;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.fasterxml.jackson.databind.JsonNode;
import github.xiny.simpleblog.domain.*;
import github.xiny.simpleblog.mapper.AvatarMapper;
import github.xiny.simpleblog.mapper.UserMapper;
import github.xiny.simpleblog.service.SelectService;
import github.xiny.simpleblog.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;


@RestController("adminUserController")
@RequestMapping("/admin/user")
@SaCheckPermission("admin.list")
@SaCheckLogin
public class UserController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;
    @Resource
    private AvatarMapper avatarMapper;
    @Resource
    private SelectService selectService;
    @RequestMapping("/get")
    public AjaxJson getUserList(@RequestBody User VO) {
        VO.setPassword(null);
        final SelectData<User> select = selectService.select(VO, userMapper);
        select.getData().forEach(user -> user.setPassword(null));
        return AjaxJson.getPageData((long) select.getTotal(),select.getData());
    }

    @RequestMapping("/add")
    @SaCheckPermission("admin.add")
    public AjaxJson addUser(@RequestBody User user) {
        System.out.println(user);
        int i;
        try {
            user.setUserId(null);
              i = userMapper.insert(user);
        }catch (Exception e){
            if (e.getMessage().contains("user.PRIMARY")) {
                return AjaxJson.getError("账号已存在！");
            }
            return AjaxJson.getError("添加失败！");
        }
        if (i > 0) {
            return AjaxJson.getSuccess("添加成功！");
        }
        return AjaxJson.getError("添加失败！");
    }

    @RequestMapping("/delete")
    @SaCheckPermission("admin.delete")
    public AjaxJson deleteUser(@RequestParam Integer userid) {
        int i;

        if (StpUtil.getLoginIdAsInt() == userid) {
            return AjaxJson.getError("不能删除自己！");
        }

        try {
            i = userMapper.deleteById(userid);
        }catch (Exception e){
            final String message = e.getMessage();
            if (message.contains("FOREIGN KEY")) {
                return AjaxJson.getError("删除失败，外键约束！");
            }else return AjaxJson.getError("删除失败！"+message);
        }
        if (i > 0) {
            return AjaxJson.getSuccess("删除成功！");
        }
        return AjaxJson.getError("删除失败！");
    }

    @RequestMapping("/update")
    @SaCheckPermission("admin.update")
    public AjaxJson updateUser(@RequestBody User user) {
        user.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        final int i = userMapper.updateById(user);
        if (i > 0) {
            return AjaxJson.getSuccess("修改成功！");
        }
        return AjaxJson.getError("修改失败！");
    }


    @RequestMapping("/login")
    @SaIgnore
    public AjaxJson login(@RequestParam String account, @RequestParam String password) {
        final User msg;
        try {
            msg = userService.login(account, password);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        if (msg.getUserId().equals(StpUtil.getLoginIdAsInt())) {
            msg.setPassword(null);
            final Avatar avatar = avatarMapper.selectById(msg.getAvatar());
            msg.setAvatarUrl(avatar.getImgUrl());
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

    @RequestMapping("/getAboutMe")
    public AjaxJson getAboutMe() {
        return AjaxJson.getSuccessData(userService.getAboutMe());
    }

    @RequestMapping("/setAboutMe")
    public AjaxJson setAboutMe(@RequestBody JsonNode json) {
        return AjaxJson.getSuccessData(userService.setAboutMe(json.get("aboutMe").asText()));
    }
}
