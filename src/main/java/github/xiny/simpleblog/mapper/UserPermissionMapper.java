package github.xiny.simpleblog.mapper;

import github.xiny.simpleblog.domain.UserPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 86459
* @description 针对表【user_permission】的数据库操作Mapper
* @createDate 2023-02-04 13:30:58
* @Entity github.xiny.myblog.domain.UserPermission
*/
@Mapper
public interface UserPermissionMapper extends BaseMapper<UserPermission> {

    @Select("""
    SELECT permission_name
    from user_permission,permission
    where user_permission.user_id = #{userId}
    and user_permission.permission_id=permission.permission_id
    """)
    List<String> getUserPermissionByUserId(String userId);

}




