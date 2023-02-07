package github.xiny.simpleblog.mapper;

import github.xiny.simpleblog.domain.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86459
* @description 针对表【permission】的数据库操作Mapper
* @createDate 2023-02-04 13:30:45
* @Entity github.xiny.myblog.domain.Permission
*/
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}




