package github.xiny.simpleblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.xiny.simpleblog.domain.UserPermission;
import github.xiny.simpleblog.service.UserPermissionService;
import github.xiny.simpleblog.mapper.UserPermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author 86459
* @description 针对表【user_permission】的数据库操作Service实现
* @createDate 2023-02-04 13:30:58
*/
@Service
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermission>
    implements UserPermissionService{

}




