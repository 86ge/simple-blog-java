package github.xiny.simpleblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.xiny.simpleblog.domain.Permission;
import github.xiny.simpleblog.service.PermissionService;
import github.xiny.simpleblog.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author 86459
* @description 针对表【permission】的数据库操作Service实现
* @createDate 2023-02-04 13:30:45
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

}




