package github.xiny.simpleblog.config;

import cn.dev33.satoken.stp.StpInterface;
import github.xiny.simpleblog.domain.UserRole;
import github.xiny.simpleblog.mapper.UserPermissionMapper;
import github.xiny.simpleblog.mapper.UserRoleMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {


    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private UserPermissionMapper userPermissionMapper;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return userPermissionMapper.getUserPermissionByUserId(loginId.toString());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        final List<UserRole> role = userRoleMapper.getUserRoleByUserId(loginId.toString());
        List<String> roleList = new ArrayList<>();
        for (UserRole userRole : role) {
            roleList.add(userRole.getRoleName());
        }
        return roleList;
    }
}
