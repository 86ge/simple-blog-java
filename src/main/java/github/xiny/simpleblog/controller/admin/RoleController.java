package github.xiny.simpleblog.controller.admin;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import github.xiny.simpleblog.domain.*;
import github.xiny.simpleblog.mapper.RoleMapper;
import github.xiny.simpleblog.mapper.UserRoleMapper;
import github.xiny.simpleblog.service.SelectService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/role")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@SaCheckLogin
@SaCheckPermission("admin.list")
public class RoleController {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private SelectService selectService;

    @RequestMapping("/get")
    public AjaxJson getRoleList(@RequestBody Role VO) {
        final SelectData<Role> select = selectService.select(VO, roleMapper);
        return AjaxJson.getPageData((long) select.getTotal(), select.getData());
    }

    @RequestMapping("/add")
    @SaCheckPermission("admin.add")
    public AjaxJson addRole(@RequestBody Role role) {
        final int i = roleMapper.insert(role);
        if (i > 0) {
            return AjaxJson.getSuccess("添加成功！");
        }
        return AjaxJson.getError("添加失败！");
    }

    @RequestMapping("/update")
    @SaCheckPermission("admin.update")
    public AjaxJson updateRole(@RequestBody Role role) {
        System.out.println(role);
        final int i = roleMapper.updateById(role);
        if (i > 0) {
            return AjaxJson.getSuccess("修改成功！");
        }
        return AjaxJson.getError("修改失败！");
    }

    @RequestMapping("/delete")
    @SaCheckPermission("admin.delete")
    public AjaxJson deleteRole(@RequestParam Integer roleid) {
        final int i = roleMapper.deleteById(roleid);
        if (i > 0) {
            return AjaxJson.getSuccess("删除成功！");
        }
        return AjaxJson.getError("删除失败！");
    }

    @RequestMapping("/getMyRole")
    public AjaxJson getMyRole() {
        final List<UserRole> roles = userRoleMapper.getUserRoleByUserId(StpUtil.getLoginIdAsString());
        ArrayList<String> roleList = new ArrayList<>();
        for (UserRole role : roles) {
            roleList.add(role.getRoleName());
        }
        return AjaxJson.getSuccessData(roleList);
    }

    @RequestMapping("/getUserRole")
    public AjaxJson getUserRole() {
        final List<UserRole> roles = userRoleMapper.getUserMsg();
        return AjaxJson.getSuccessData(roles);
    }

    @RequestMapping("/updateUserRole")
    @SaCheckPermission("admin.update")
    public AjaxJson updateUserRole(@RequestBody UserRole userRole) {
        System.out.println(userRole);
        final int i = userRoleMapper.updateUserRole(userRole);
        if (i > 0) {
            return AjaxJson.getSuccess("修改成功！");
        }
        return AjaxJson.getError("修改失败！");
    }

    @RequestMapping("/addUserRole")
    @SaCheckPermission("admin.add")
    public AjaxJson addUserRole(@RequestBody UserRole userRole) {
        int i;
        try {
            i= userRoleMapper.insert(userRole);
        }catch (Exception e){
            if(e.getMessage().contains("PRIMARY")){
                return AjaxJson.getError("无此角色");
            }
            return AjaxJson.getError("添加失败！");
        }
        if (i>0) {
            return AjaxJson.getSuccess("添加成功！");
        }
        return AjaxJson.getError("添加失败！");
    }

    @RequestMapping("/deleteUserRole")
    @SaCheckPermission("admin.delete")
    public AjaxJson deleteUserRole(@RequestParam Integer userid) {
        final int i = userRoleMapper.deleteUserRole(String.valueOf(userid));
        if (i > 0) {
            return AjaxJson.getSuccess("删除成功！");
        }
        return AjaxJson.getError("删除失败！");
    }
}



